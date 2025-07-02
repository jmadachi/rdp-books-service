package com.unir.payments.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.unir.payments.repositories.entities.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
}
