package com.unir.payments.controllers;

import com.unir.payments.controllers.requests.NewPaymentRequest;
import com.unir.payments.dtos.PaymentDto;
import com.unir.payments.services.interfaces.IPaymentService;
import com.unir.payments.adapters.outbound.BookClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag   ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

@Tag(name = "API para gestionar Pagos", description = "Tareas relacionadas a pagos")
@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    @Autowired
    private IPaymentService service;

    @Operation(summary = "Listar todos los pagos")
    @GetMapping
    public ResponseEntity<List<PaymentDto>> getAllPayments() {
        List<PaymentDto> payments = service.getAllPayments();
        if (payments != null) {
            return ResponseEntity.ok(payments);
        } else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @Operation(summary = "Obtener un pago con el ID")
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable Long id) {
        return service.getPaymentById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un pago")
    @PostMapping
    public ResponseEntity<PaymentDto> createPayment(@RequestBody NewPaymentRequest request) {
		log.info("Creating payment...");
        PaymentDto created = service.createPayment(request);

        if (created != null) {
            return ResponseEntity.ok(created);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Borrar un pago por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        if (service.deletePayment(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
