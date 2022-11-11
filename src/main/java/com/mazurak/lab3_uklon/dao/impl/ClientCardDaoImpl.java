package com.mazurak.lab3_uklon.dao.impl;

import com.mazurak.lab3_uklon.dao.CarDao;
import com.mazurak.lab3_uklon.dao.ClientCardDao;
import com.mazurak.lab3_uklon.domain.Car;
import com.mazurak.lab3_uklon.domain.ClientCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientCardDaoImpl implements ClientCardDao {
    private static final String FIND_ALL = "SELECT * FROM client_card";
    private static final String CREATE = "INSERT client_card(name) VALUES (?)";
    private static final String UPDATE = "UPDATE client_card SET name=? WHERE id=?";
    private static final String DELETE = "DELETE FROM client_card WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM client_card WHERE id=?";
    private static final String FIND_BY_CAR_NUMBER = "SELECT * FROM client_card WHERE car_number=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ClientCard> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(ClientCard.class));
    }

    @Override
    public Optional<ClientCard> findById(Integer id) {
        Optional<ClientCard> card;
        try {
            card = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(ClientCard.class), id));
        } catch (EmptyResultDataAccessException e) {
            card = Optional.empty();
        }
        return card;
    }

    @Override
    public int create(ClientCard card) {
        return jdbcTemplate.update(CREATE, card.getName());
    }

    @Override
    public int update(Integer id, ClientCard card) {
        return jdbcTemplate.update(UPDATE, card.getName(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

}
