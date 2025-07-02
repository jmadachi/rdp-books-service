package com.unir.payments.repositories.entities;

import jakarta.persistence.*;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID del pago (generado automáticamente)", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "ID del libro")
    private Long bookId;

    @Schema(description = "Nombre del Comprador")
    private String buyer;

    @Column(name = "payment_date", nullable = false)
    @Schema(description = "Fecha de la compra")
    private LocalDate date;

    @Schema(description = "Valor")
    private Double amount;

}
