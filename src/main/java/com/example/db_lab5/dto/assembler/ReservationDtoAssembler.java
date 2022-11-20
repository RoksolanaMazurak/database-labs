package com.example.db_lab5.dto.assembler;


import com.example.db_lab5.controller.ReservationController;
import com.example.db_lab5.domain.Reservation;
import com.example.db_lab5.dto.ReservationDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ReservationDtoAssembler implements RepresentationModelAssembler<Reservation, ReservationDto> {
    @Override
    public ReservationDto toModel(Reservation entity) {
        ReservationDto reservationDto = ReservationDto.builder()
                .id(entity.getId())
                .client(entity.getClient().getId())
                .time(entity.getTime())
                .payment(entity.getPayment().getId())
                .startAddress(entity.getStartAddress())
                .finalAddress(entity.getFinalAddress())
                .car(entity.getCar().getId())
                .build();
        Link selfLink = linkTo(methodOn(ReservationController.class).getReservation(reservationDto.getId())).withSelfRel();
        reservationDto.add(selfLink);
        return reservationDto;
    }
    @Override
    public CollectionModel<ReservationDto> toCollectionModel(Iterable<? extends Reservation> entities) {
        CollectionModel<ReservationDto> reservationDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(ReservationController.class).getAllReservations()).withSelfRel();
        reservationDtos.add(selfLink);
        return reservationDtos;
    }

    public CollectionModel<ReservationDto> toCollectionModel(Iterable<? extends Reservation> entities, Link link) {
        CollectionModel<ReservationDto> reservationDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        reservationDtos.add(link);
        return reservationDtos;
    }
}
