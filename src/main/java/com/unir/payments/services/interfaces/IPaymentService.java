package com.unir.payments.services.interfaces;

import com.unir.payments.dtos.PaymentDto;
import com.unir.payments.controllers.requests.NewPaymentRequest;

import java.util.List;
import java.util.Optional;

public interface IPaymentService {
    List<PaymentDto> getAllPayments();
    Optional<PaymentDto> getPaymentById(Long id);
    PaymentDto createPayment(NewPaymentRequest request);
    boolean deletePayment(Long id);
}
