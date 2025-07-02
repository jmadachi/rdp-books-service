package com.unir.payments.controllers.requests;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "DTO para solicitar la información de nuevos pagos")
public class NewPaymentRequest {

    @Schema(description = "ID del libro comprado", example = "1")
    public Long bookId;

    @Schema(description = "Nombre del comprador", example = "Adan García")
    public String buyer;

    @Schema(description = "Fecha del Pago", example = "2025-06-10")
    public LocalDate date;

    @Schema(description = "Valor del pago", example = "49.99")
    public Double amount;

}
