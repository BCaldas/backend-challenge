package com.invilla.acme.order.service.imp;

import com.invilla.acme.commons.model.Order;
import com.invilla.acme.commons.model.Payment;
import com.invilla.acme.commons.model.Store;
import com.invilla.acme.commons.model.enums.EItemStatus;
import com.invilla.acme.commons.model.enums.EOrderStatus;
import com.invilla.acme.commons.model.enums.EPaymentStatus;
import com.invilla.acme.order.repository.OrderFilter;
import com.invilla.acme.order.repository.OrderRepository;
import com.invilla.acme.order.repository.http.StoreHttpRepository;
import com.invilla.acme.order.service.OrderService;
import com.invilla.acme.order.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private StoreHttpRepository storeHttpRepository;

    @Override
    public Order addOrder(Order newOrder) {
        ResponseEntity<Store> retrivedStore = storeHttpRepository.getStoreById(newOrder.getStoreId());

        if (retrivedStore.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Store not found.");
        }
        newOrder.setStatus(EOrderStatus.PAYMENT_PENDING);
        newOrder.getItems().forEach(item -> {
            item.setOrder(newOrder);
            item.setStatus(EItemStatus.OK);
        });
        return saveOrder(newOrder);
    }

    @Override
    public Order createPayment(Payment newPayment, Long orderId) {
        Order order = getById(orderId);

        if (order.getStatus() != EOrderStatus.PAYMENT_PENDING) {
            throw new RuntimeException("This order cannot be charged.");
        }

        newPayment.setStatus(EPaymentStatus.CONFIRMED);
        newPayment.setPaymentDate(LocalDate.now());
        newPayment.setOrder(order);
        order.setPayment(newPayment);
        order.setConfirmationDate(LocalDate.now());
        order.setStatus(EOrderStatus.PAYMENT_CONFIRMED);
        return saveOrder(order);
    }

    @Override
    public Order refundOrder(Long orderId) {
        Order order = getById(orderId);
        paymentService.validateRefund(order);
        order.setStatus(EOrderStatus.REFUNDED);
        order.getPayment().setStatus(EPaymentStatus.REFUNDED);
        return saveOrder(order);
    }

    @Override
    public List<Order> findAllWithFilter(OrderFilter orderFilter) {
        String address = orderFilter.getAddress();
        LocalDate confirmationDate = orderFilter.getConfirmationDate();
        EOrderStatus status = orderFilter.getStatus();

        return orderRepository.findWithFilter(address, confirmationDate, status);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found."));
    }

    private Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}
