package com.mazurak.lab3_uklon.service.impl;

import com.mazurak.lab3_uklon.dao.ClientDao;
import com.mazurak.lab3_uklon.dao.PaymentDao;
import com.mazurak.lab3_uklon.domain.Client;
import com.mazurak.lab3_uklon.domain.Payment;
import com.mazurak.lab3_uklon.service.ClientService;
import com.mazurak.lab3_uklon.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentDao paymentDao;

    @Override
    public List<Payment> findAll() {
        return paymentDao.findAll();
    }

    @Override
    public Optional<Payment> findById(Integer id) {
        return paymentDao.findById(id);
    }

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public int update(Integer id, Payment payment) {
        return paymentDao.update(id, payment);
    }

    @Override
    public int delete(Integer id) {
        return paymentDao.delete(id);
    }

}
