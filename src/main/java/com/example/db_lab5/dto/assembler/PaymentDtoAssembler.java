package com.example.db_lab5.dto.assembler;

import com.example.db_lab5.controller.PaymentController;
import com.example.db_lab5.domain.Payment;
import com.example.db_lab5.dto.PaymentDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PaymentDtoAssembler implements RepresentationModelAssembler<Payment, PaymentDto> {
    @Override
    public PaymentDto toModel(Payment entity) {
        PaymentDto paymentDto = PaymentDto.builder()
                .id(entity.getId())
                .type(entity.getType())
                .time(entity.getTime())
                .cardNumber(entity.getCardNumber())
                .transaction(entity.getTransactionId())
                .build();
        Link selfLink = linkTo(methodOn(PaymentController.class).getPayment(paymentDto.getId())).withSelfRel();
        paymentDto.add(selfLink);
        return paymentDto;
    }
    @Override
    public CollectionModel<PaymentDto> toCollectionModel(Iterable<? extends Payment> entities) {
        CollectionModel<PaymentDto> paymentDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(PaymentController.class).getAllPayments()).withSelfRel();
        paymentDtos.add(selfLink);
        return paymentDtos;
    }

    public CollectionModel<PaymentDto> toCollectionModel(Iterable<? extends Payment> entities, Link link) {
        CollectionModel<PaymentDto> paymentDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        paymentDtos.add(link);
        return paymentDtos;
    }
}
