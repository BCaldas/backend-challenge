package com.invilla.acme.order.repository;

import com.invilla.acme.commons.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
