package com.invilla.acme.order.service.imp;

import com.invilla.acme.order.enums.EStatus;
import com.invilla.acme.order.model.Payment;
import com.invilla.acme.order.repository.PaymentRepository;
import com.invilla.acme.order.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentServiceImp implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment addOrder(Payment newPayment) {
        newPayment.setStatus(EStatus.PAYMENT_PENDING);
        newPayment.getItems().forEach(item -> item.setOrder(newPayment));
        return saveOrder(newPayment);
    }

    @Override
    public List<Payment> findAllWithFilter(OrderFilter orderFilter) {
        String address = orderFilter.getAddress();
        LocalDate confirmationDate = orderFilter.getConfirmationDate();
        EStatus status = orderFilter.getStatus();

        return paymentRepository.findWithFilter(address, confirmationDate, status);
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found."));
    }

    private Payment saveOrder(Payment payment) {
        return paymentRepository.save(payment);
    }
}
