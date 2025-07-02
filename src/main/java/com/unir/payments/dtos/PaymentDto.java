package com.unir.payments.dtos;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public class PaymentDto {

    public Long id;

    public Long bookId;

    public String buyer;

    public LocalDate date;

    public Double amount;

}
