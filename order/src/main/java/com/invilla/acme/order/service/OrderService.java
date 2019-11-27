package com.invilla.acme.order.service;

import com.invilla.acme.order.model.Order;
import com.invilla.acme.order.repository.OrderFilter;

import java.util.List;

public interface OrderService {

    Order addOrder(Order newOrder);

    List<Order> findAllWithFilter(OrderFilter orderFilter);

    List<Order> findAll();

    Order getById(Long id);
}
