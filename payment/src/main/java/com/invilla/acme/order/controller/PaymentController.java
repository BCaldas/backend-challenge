package com.invilla.acme.order.controller;

import com.invilla.acme.order.enums.EStatus;
import com.invilla.acme.order.model.Payment;
import com.invilla.acme.order.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Payment addOrder(@RequestBody @Valid Payment newPayment) {
        return paymentService.addOrder(newPayment);
    }

    @GetMapping("/{id}")
    public Payment getById(@PathVariable Long id) {
        return paymentService.getById(id);
    }

    @GetMapping
    public List<Payment> getAllWithFilter(@RequestParam Map<String, String> params) {

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
        return paymentService.findAllWithFilter(orderFilter);
    }
}
