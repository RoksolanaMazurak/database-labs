package com.mazurak.lab3_uklon.dao.impl;
import com.mazurak.lab3_uklon.dao.CarModelDao;
import com.mazurak.lab3_uklon.dao.DriverDao;
import com.mazurak.lab3_uklon.domain.Car;
import com.mazurak.lab3_uklon.domain.CarModel;
import com.mazurak.lab3_uklon.domain.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverDaoImpl implements DriverDao {
    private static final String FIND_ALL = "SELECT * FROM driver";
    private static final String CREATE = "INSERT driver(surname, name, gender, rating_id, car_id) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE driver SET surname=?, name=?, gender=?, rating_id=?, car_id=? WHERE id=?";
    private static final String DELETE = "DELETE FROM driver WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM driver WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Driver> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Driver.class));
    }

    @Override
    public Optional<Driver> findById(Integer id) {
        Optional<Driver> driver;
        try {
            driver = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Driver.class), id));
        } catch (EmptyResultDataAccessException e) {
            driver = Optional.empty();
        }
        return driver;
    }

    @Override
    public int create(Driver driver) {
        return jdbcTemplate.update(CREATE, driver.getSurname(), driver.getName(),
                driver.getGender(), driver.getRating(), driver.getCarId());
    }

    @Override
    public int update(Integer id, Driver driver) {
        return jdbcTemplate.update(UPDATE,  driver.getSurname(),
                driver.getSurname(), driver.getGender(), driver.getRating(), driver.getCarId());
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

}