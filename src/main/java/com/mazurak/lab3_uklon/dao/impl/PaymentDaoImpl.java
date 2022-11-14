package com.mazurak.lab3_uklon.dao.impl;

import com.mazurak.lab3_uklon.dao.DriverDao;
import com.mazurak.lab3_uklon.dao.PaymentDao;
import com.mazurak.lab3_uklon.domain.Driver;
import com.mazurak.lab3_uklon.domain.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentDaoImpl implements PaymentDao {
    private static final String FIND_ALL = "SELECT * FROM payment";
    private static final String CREATE = "INSERT payment(type, time, card_number, transaction_id) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE payment SET type=?, time=?, card_number=?, transaction_id=? WHERE id=?";
    private static final String DELETE = "DELETE FROM payment WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM payment WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Payment> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Payment.class));
    }

    @Override
    public Optional<Payment> findById(Integer id) {
        Optional<Payment> payment;
        try {
            payment = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Payment.class), id));
        } catch (EmptyResultDataAccessException e) {
            payment = Optional.empty();
        }
        return payment;
    }

    @Override
    public int create(Payment payment) {
        return jdbcTemplate.update(CREATE, payment.getType(), payment.getTime(), payment.getCardNum(), payment.getTransactionNum());
    }

    @Override
    public int update(Integer id, Payment payment) {
        return jdbcTemplate.update(UPDATE,  payment.getType(), payment.getTime(), payment.getCardNum(), payment.getTransactionNum());
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

}