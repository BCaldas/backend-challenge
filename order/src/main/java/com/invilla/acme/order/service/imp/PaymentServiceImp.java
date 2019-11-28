package com.invilla.acme.order.service.imp;

import com.invilla.acme.commons.model.Order;
import com.invilla.acme.commons.model.enums.EOrderStatus;
import com.invilla.acme.order.service.PaymentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PaymentServiceImp implements PaymentService {

    @Override
    public void validateRefund(Order order) {
        //TODO: troca eception por BusinessExeption
        if (order.getStatus() != EOrderStatus.PAYMENT_CONFIRMED) {
            throw new RuntimeException("This order or item cannot be refunded, because no amount has been charged.");
        }

        if (order.getConfirmationDate().plusDays(10).isBefore(LocalDate.now())) {
            throw new RuntimeException("Refund period has expired.");
        }
    }
}
