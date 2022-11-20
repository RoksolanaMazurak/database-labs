package com.example.db_lab5.controller;

import com.example.db_lab5.domain.Reservation;
import com.example.db_lab5.dto.ReservationDto;
import com.example.db_lab5.dto.assembler.ReservationDtoAssembler;
import com.example.db_lab5.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ReservationDtoAssembler reservationDtoAssembler;

    @GetMapping(value = "/{reservationId}")
    public ResponseEntity<ReservationDto> getReservation(@PathVariable Integer reservationId) {
        Reservation reservation = reservationService.findById(reservationId);
        ReservationDto reservationDto = reservationDtoAssembler.toModel(reservation);
        return new ResponseEntity<>(reservationDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<ReservationDto>> getAllReservations() {
        List<Reservation> reservations = reservationService.findAll();
        CollectionModel<ReservationDto> reservationDtos = reservationDtoAssembler.toCollectionModel(reservations);
        return new ResponseEntity<>(reservationDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<ReservationDto> addReservation(@RequestBody Reservation reservation) {
        Reservation newReservation = reservationService.create(reservation);
        ReservationDto reservationDto = reservationDtoAssembler.toModel(newReservation);
        return new ResponseEntity<>(reservationDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{reservationId}")
    public ResponseEntity<?> updateReservation(@RequestBody Reservation updateReservation, @PathVariable Integer reservationId) {
        reservationService.update(reservationId, updateReservation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{reservationId}")
    public ResponseEntity<?> deleteReservation(@PathVariable Integer reservationId) {
        reservationService.delete(reservationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
