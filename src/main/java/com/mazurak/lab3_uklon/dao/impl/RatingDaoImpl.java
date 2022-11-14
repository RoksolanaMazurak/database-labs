package com.mazurak.lab3_uklon.dao.impl;

import com.mazurak.lab3_uklon.dao.PaymentDao;
import com.mazurak.lab3_uklon.dao.RatingDao;
import com.mazurak.lab3_uklon.domain.Payment;
import com.mazurak.lab3_uklon.domain.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingDaoImpl implements RatingDao {
    private static final String FIND_ALL = "SELECT * FROM rating";
    private static final String CREATE = "INSERT rating(value, client_id) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE rating SET value=?, client_id=? WHERE id=?";
    private static final String DELETE = "DELETE FROM rating WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM rating WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Rating> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Rating.class));
    }

    @Override
    public Optional<Rating> findById(Integer id) {
        Optional<Rating> rating;
        try {
            rating = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Rating.class), id));
        } catch (EmptyResultDataAccessException e) {
            rating = Optional.empty();
        }
        return rating;
    }

    @Override
    public int create(Rating rating) {
        return jdbcTemplate.update(CREATE, rating.getValue(), rating.getClientId());
    }

    @Override
    public int update(Integer id, Rating rating) {
        return jdbcTemplate.update(UPDATE, rating.getValue(), rating.getClientId());
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

}
