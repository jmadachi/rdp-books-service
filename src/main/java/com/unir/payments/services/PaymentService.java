package com.unir.payments.services;

import com.unir.payments.controllers.requests.NewPaymentRequest;
import com.unir.payments.dtos.PaymentDto;
import com.unir.payments.dtos.BookDto;
import com.unir.payments.mappers.PaymentMapper;
import com.unir.payments.repositories.PaymentRepository;
import com.unir.payments.repositories.entities.PaymentEntity;
import com.unir.payments.services.interfaces.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Objects;
import java.util.stream.Collectors;

import com.unir.payments.adapters.outbound.BookClient;

@Service
public class PaymentService implements IPaymentService {

    @Autowired
    private BookClient bookClient;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<PaymentDto> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(PaymentMapper::fromPaymentEntityToPaymentDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PaymentDto> getPaymentById(Long id) {
        return paymentRepository.findById(id).map(PaymentMapper::fromPaymentEntityToPaymentDto);
    }

    @Override
    public PaymentDto createPayment(NewPaymentRequest request) {
		
		BookDto book = bookClient.getBookById(request.bookId.toString());
        if (book == null) {
            throw new IllegalArgumentException("El libro con ID " + request.bookId + " no existe.");
        }
		
        PaymentEntity paymentEntity = PaymentMapper.fromPaymentRequestToPaymentEntity(request);
        PaymentEntity paymentSaved = paymentRepository.save(paymentEntity);
        return PaymentMapper.fromPaymentEntityToPaymentDto(paymentSaved);
    }

    @Override
    public boolean deletePayment(Long id) {
        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
