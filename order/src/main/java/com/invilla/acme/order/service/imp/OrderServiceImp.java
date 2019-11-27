package com.invilla.acme.order.service.imp;

import com.invilla.acme.order.enums.EStatus;
import com.invilla.acme.order.model.Order;
import com.invilla.acme.order.repository.OrderFilter;
import com.invilla.acme.order.repository.OrderRepository;
import com.invilla.acme.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order addOrder(Order newOrder) {
        newOrder.setStatus(EStatus.PAYMENT_PENDING);
        newOrder.getItems().forEach(item -> item.setOrder(newOrder));
        return saveOrder(newOrder);
    }

    @Override
    public List<Order> findAllWithFilter(OrderFilter orderFilter) {
        String address = orderFilter.getAddress();
        LocalDate confirmationDate = orderFilter.getConfirmationDate();
        EStatus status = orderFilter.getStatus();

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
