package com.mazurak.lab3_uklon.dao.impl;

import com.mazurak.lab3_uklon.dao.RatingDao;
import com.mazurak.lab3_uklon.dao.ReservationDao;
import com.mazurak.lab3_uklon.domain.Rating;
import com.mazurak.lab3_uklon.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationDaoImpl implements ReservationDao {
    private static final String FIND_ALL = "SELECT * FROM reservation";
    private static final String CREATE = "INSERT reservation(client_id, time, payment_id, " +
            "start_address, final_address, car_id) VALUES (?, ?, ?, ?, ?. ?)";
    private static final String UPDATE = "UPDATE reservation SET client_id=?, time=?, payment_id=?, " +
            "start_address=?, final_address=?, car_id=? WHERE id=?";
    private static final String DELETE = "DELETE FROM reservation WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM reservation WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Reservation> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Reservation.class));
    }

    @Override
    public Optional<Reservation> findById(Integer id) {
        Optional<Reservation> reservation;
        try {
            reservation = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Reservation.class), id));
        } catch (EmptyResultDataAccessException e) {
            reservation = Optional.empty();
        }
        return reservation;
    }

    @Override
    public int create(Reservation reservation) {
        return jdbcTemplate.update(CREATE, reservation.getClient(), reservation.getTime(), reservation.getPayment(),
                reservation.getStartAddress(), reservation.getFinalAddress(), reservation.getCar());
    }

    @Override
    public int update(Integer id, Reservation reservation) {
        return jdbcTemplate.update(UPDATE, reservation.getClient(), reservation.getTime(), reservation.getPayment(),
                reservation.getStartAddress(), reservation.getFinalAddress(), reservation.getCar());
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

}
