package com.invilla.acme.order.service;

import com.invilla.acme.order.model.Order;

public interface PaymentService {
    void validateRefund(Order order);
}
