package com.invilla.acme.order.repository;

import com.invilla.acme.order.enums.EStatus;
import com.invilla.acme.order.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
