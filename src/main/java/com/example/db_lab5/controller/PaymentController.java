package com.example.db_lab5.controller;

import com.example.db_lab5.domain.Payment;
import com.example.db_lab5.dto.PaymentDto;
import com.example.db_lab5.dto.assembler.PaymentDtoAssembler;
import com.example.db_lab5.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private PaymentDtoAssembler paymentDtoAssembler;

    @GetMapping(value = "/{paymentId}")
    public ResponseEntity<PaymentDto> getPayment(@PathVariable Integer paymentId) {
        Payment payment = paymentService.findById(paymentId);
        PaymentDto paymentDto = paymentDtoAssembler.toModel(payment);
        return new ResponseEntity<>(paymentDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<PaymentDto>> getAllPayments() {
        List<Payment> payments = paymentService.findAll();
        CollectionModel<PaymentDto> paymentDtos = paymentDtoAssembler.toCollectionModel(payments);
        return new ResponseEntity<>(paymentDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<PaymentDto> addPayment(@RequestBody Payment payment) {
        Payment newPayment = paymentService.create(payment);
        PaymentDto paymentDto = paymentDtoAssembler.toModel(newPayment);
        return new ResponseEntity<>(paymentDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{paymentId}")
    public ResponseEntity<?> updatePayment(@RequestBody Payment updatePayment, @PathVariable Integer paymentId) {
        paymentService.update(paymentId, updatePayment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{paymentId}")
    public ResponseEntity<?> deletePayment(@PathVariable Integer paymentId) {
        paymentService.delete(paymentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
