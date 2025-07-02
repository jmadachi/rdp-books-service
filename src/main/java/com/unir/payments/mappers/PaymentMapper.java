package com.unir.payments.mappers;

import com.unir.payments.dtos.PaymentDto;
import com.unir.payments.controllers.requests.NewPaymentRequest;
import com.unir.payments.repositories.entities.PaymentEntity;

public class PaymentMapper {

    public static PaymentDto fromPaymentEntityToPaymentDto(PaymentEntity entity) {
        if (entity == null) return null;
        return PaymentDto.builder()
            .id(entity.getId())
            .bookId(entity.getBookId())
            .buyer(entity.getBuyer())
            .date(entity.getDate())
            .amount(entity.getAmount())
            .build();
    }

    public static PaymentEntity fromPaymentDtoToPaymentEntity(PaymentDto dto) {
        if (dto == null) return null;
        return PaymentEntity.builder()
			.id(dto.id)
            .bookId(dto.bookId)
            .buyer(dto.buyer)
            .date(dto.date)
            .amount(dto.amount)
            .build();
    }

    public static PaymentEntity fromPaymentRequestToPaymentEntity(NewPaymentRequest request) {
        if (request == null) return null;
        return PaymentEntity.builder()
            .bookId(request.bookId)
            .buyer(request.buyer)
            .date(request.date)
            .amount(request.amount)
            .build();
    }
}
