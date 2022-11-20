package com.example.db_lab5.service.impl;

import com.example.db_lab5.domain.Payment;
import com.example.db_lab5.exception.PaymentNotFoundException;
import com.example.db_lab5.repository.PaymentRepository;
import com.example.db_lab5.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    public Payment findById(Integer id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException(id));
    }

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Transactional
    public Payment create(Payment payment) {
        paymentRepository.save(payment);
        return payment;
    }

    @Transactional
    public void update(Integer id, Payment updatePayment) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException(id));
        //update
        payment.setType(updatePayment.getType());
        payment.setTime(updatePayment.getTime());
        payment.setCardNumber(updatePayment.getCardNumber());
        payment.setTransactionId(updatePayment.getTransactionId());
        paymentRepository.save(payment);
    }

    @Transactional
    public void delete(Integer id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException(id));
        paymentRepository.delete(payment);
    }
}
