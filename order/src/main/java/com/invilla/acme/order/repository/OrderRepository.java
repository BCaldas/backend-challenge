package com.invilla.acme.order.repository;

import com.invilla.acme.order.enums.EOrderStatus;
import com.invilla.acme.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE (:address is null or o.address = :address)" +
            " and (:confirmationDate is null or o.confirmationDate = :confirmationDate)" +
            " and (:status is null or o.status = :status)")
    List<Order> findWithFilter(@Param("address") String address,
                               @Param("confirmationDate") LocalDate confirmationDate,
                               @Param("status") EOrderStatus status);
}
