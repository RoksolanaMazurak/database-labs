package com.example.db_lab5.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.Date;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "payment", collectionRelation = "payments")
public class PaymentDto extends RepresentationModel<PaymentDto> {
    private final Integer id;
    private final String type;
    private final Date time;
    private final String cardNumber;
    private final String transaction;
}
