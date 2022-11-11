package com.mazurak.lab3_uklon.dao.impl;

import com.mazurak.lab3_uklon.dao.CarDao;
import com.mazurak.lab3_uklon.domain.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarDaoImpl implements CarDao {
    private static final String FIND_ALL = "SELECT * FROM car";
    private static final String CREATE = "INSERT car(car_number, car_model_id) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE car SET car_number=?, car_model_id=? WHERE id=?";
    private static final String DELETE = "DELETE FROM car WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM car WHERE id=?";
    private static final String FIND_BY_CAR_NUMBER = "SELECT * FROM car WHERE car_number=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Car> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Car.class));
    }

    @Override
    public Optional<Car> findById(Integer id) {
        Optional<Car> car;
        try {
            car = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Car.class), id));
        } catch (EmptyResultDataAccessException e) {
            car = Optional.empty();
        }
        return car;
    }

    @Override
    public int create(Car car) {
        return jdbcTemplate.update(CREATE, car.getCarNumber(), car.getCarModelId());
    }

    @Override
    public int update(Integer id, Car car) {
        return jdbcTemplate.update(UPDATE, car.getCarNumber(), car.getCarModelId(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

    @Override
    public Optional<Car> findByCarNumber(String carNumber) {
        Optional<Car> car;
        try {
            car = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_CAR_NUMBER,
                    BeanPropertyRowMapper.newInstance(Car.class), carNumber));
        } catch (EmptyResultDataAccessException e) {
            car = Optional.empty();
        }
        return car;
    }


}
