package com.mazurak.lab3_uklon.controller.impl;

import com.mazurak.lab3_uklon.controller.ClientCardController;
import com.mazurak.lab3_uklon.controller.PaymentController;
import com.mazurak.lab3_uklon.domain.ClientCard;
import com.mazurak.lab3_uklon.domain.Payment;
import com.mazurak.lab3_uklon.service.ClientCardService;
import com.mazurak.lab3_uklon.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentControllerImpl implements PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Override
    public List<Payment> findAll() {
        return paymentService.findAll();
    }

    @Override
    public Optional<Payment> findById(Integer id) {
        return paymentService.findById(id);
    }

    @Override
    public int create(Payment payment) {
        return paymentService.create(payment);
    }

    @Override
    public int update(Integer id, Payment payment) {
        return paymentService.update(id, payment);
    }

    @Override
    public int delete(Integer id) {
        return paymentService.delete(id);
    }
}
