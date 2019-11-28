package com.invilla.acme.order.service;

import com.invilla.acme.commons.model.Order;
import com.invilla.acme.commons.model.Payment;
import com.invilla.acme.order.repository.OrderFilter;

import java.util.List;

public interface OrderService {

    Order addOrder(Order newOrder);

    Order createPayment(Payment newPayment, Long orderId);

    Order refundOrder(Long orderId);

    List<Order> findAllWithFilter(OrderFilter orderFilter);

    List<Order> findAll();

    Order getById(Long id);
}
