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
@Relation(itemRelation = "reservation", collectionRelation = "reservations")
public class ReservationDto extends RepresentationModel<ReservationDto> {
    private final Integer id;
    private final Integer client;
    private final Date time;
    private final Integer payment;
    private final String startAddress;
    private final String finalAddress;
    private final Integer car;
}
