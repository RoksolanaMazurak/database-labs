package com.example.db_lab5.service.impl;

import com.example.db_lab5.domain.*;
import com.example.db_lab5.exception.*;
import com.example.db_lab5.repository.*;
import com.example.db_lab5.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    CarRepository carRepository;

    public Reservation findById(Integer id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(id));
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Transactional
    public Reservation create(Reservation reservation) {
        reservationRepository.save(reservation);
        return reservation;
    }

    @Transactional
    public Reservation create(Reservation reservation, Integer clientId, Integer paymentId, Integer carId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException(paymentId));
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException(carId));
        reservation.setClient(client);
        reservation.setPayment(payment);
        reservation.setCar(car);
        reservationRepository.save(reservation);
        return reservation;
    }

    @Transactional
    public void update(Integer id, Reservation updateReservation) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(id));
        //update
        reservation.setTime(updateReservation.getTime());
        reservation.setStartAddress(updateReservation.getStartAddress());
        reservation.setFinalAddress(updateReservation.getFinalAddress());
        reservationRepository.save(reservation);
    }

    @Transactional
    public void update(Integer id, Reservation updateReservation, Integer clientId, Integer paymentId, Integer carId) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(id));
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException(paymentId));
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException(carId));

        //update
        reservation.setTime(updateReservation.getTime());
        reservation.setStartAddress(updateReservation.getStartAddress());
        reservation.setFinalAddress(updateReservation.getFinalAddress());
        reservation.setClient(client);
        reservation.setPayment(payment);
        reservation.setCar(car);
        reservationRepository.save(reservation);
    }

    @Transactional
    public void delete(Integer id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(id));
        reservationRepository.delete(reservation);
    }
}