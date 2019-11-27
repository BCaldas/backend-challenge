package com.invilla.acme.order.repository;

import com.invilla.acme.order.enums.EStatus;

import java.time.LocalDate;

public class OrderFilter {

    private String address;

    private LocalDate confirmationDate;

    private EStatus status;

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

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }
}
