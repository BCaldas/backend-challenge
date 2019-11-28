package com.invilla.acme.order.service;

import com.invilla.acme.order.model.Payment;

import java.util.List;

public interface PaymentService {

    Payment addOrder(Payment newPayment);

    List<Payment> findAllWithFilter(OrderFilter orderFilter);

    List<Payment> findAll();

    Payment getById(Long id);
}
