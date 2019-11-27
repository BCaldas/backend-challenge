package com.invilla.acme.order.controller;

import com.invilla.acme.order.enums.EStatus;
import com.invilla.acme.order.model.Order;
import com.invilla.acme.order.repository.OrderFilter;
import com.invilla.acme.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order addOrder(@RequestBody @Valid Order newOrder) {
        return orderService.addOrder(newOrder);
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @GetMapping
    public List<Order> getAllWithFilter(@RequestParam Map<String, String> params) {

        String confirmationDate = params.get("confirmationDate");
        String status = params.get("status");

        OrderFilter orderFilter = new OrderFilter();
        orderFilter.setAddress(params.get("address"));

        if (confirmationDate != null && !confirmationDate.isEmpty()) {
            orderFilter.setConfirmationDate(LocalDate.parse(params.get("confirmationDate")));
        }

        if (status != null && !status.isEmpty()) {
            orderFilter.setStatus(EStatus.valueOf(status.toUpperCase()));
        }
        return orderService.findAllWithFilter(orderFilter);
    }
}
