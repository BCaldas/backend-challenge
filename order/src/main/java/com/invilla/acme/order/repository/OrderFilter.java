package com.invilla.acme.order.repository;

import com.invilla.acme.commons.model.enums.EOrderStatus;

import java.time.LocalDate;

public class OrderFilter {

    private String address;

    private LocalDate confirmationDate;

    private EOrderStatus status;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(LocalDate confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public EOrderStatus getStatus() {
        return status;
    }

    public void setStatus(EOrderStatus status) {
        this.status = status;
    }
}
