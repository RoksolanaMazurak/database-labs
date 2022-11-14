package com.mazurak.lab3_uklon.controller.impl;

import com.mazurak.lab3_uklon.controller.ReservationController;
import com.mazurak.lab3_uklon.domain.Reservation;
import com.mazurak.lab3_uklon.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationControllerImpl implements ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Override
    public List<Reservation> findAll() {
        return reservationService.findAll();
    }

    @Override
    public Optional<Reservation> findById(Integer id) {
        return reservationService.findById(id);
    }

    @Override
    public int create(Reservation reservation) {
        return reservationService.create(reservation);
    }

    @Override
    public int update(Integer id, Reservation reservation) {
        return reservationService.update(id, reservation);
    }

    @Override
    public int delete(Integer id) {
        return reservationService.delete(id);
    }
}
