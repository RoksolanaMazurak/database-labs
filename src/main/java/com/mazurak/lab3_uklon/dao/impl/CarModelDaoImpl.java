package com.mazurak.lab3_uklon.dao.impl;
import com.mazurak.lab3_uklon.dao.CarModelDao;
import com.mazurak.lab3_uklon.domain.Car;
import com.mazurak.lab3_uklon.domain.CarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarModelDaoImpl implements CarModelDao {
    private static final String FIND_ALL = "SELECT * FROM car_model";
    private static final String CREATE = "INSERT car_model(name, company, class, seat_number) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE car_model SET name=?, company=?, class=?, seat_number=? WHERE id=?";
    private static final String DELETE = "DELETE FROM car_model WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM car_model WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<CarModel> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(CarModel.class));
    }

    @Override
    public Optional<CarModel> findById(Integer id) {
        Optional<CarModel> carModel;
        try {
            carModel = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(CarModel.class), id));
        } catch (EmptyResultDataAccessException e) {
            carModel = Optional.empty();
        }
        return carModel;
    }

    @Override
    public int create(CarModel carModel) {
        return jdbcTemplate.update(CREATE, carModel.getName(), carModel.getCompany(), carModel.getCarClass(), carModel.getSeatNum());
    }

    @Override
    public int update(Integer id, CarModel carModel) {
        return jdbcTemplate.update(UPDATE, carModel.getName(), carModel.getCompany(), carModel.getCarClass(), carModel.getSeatNum());
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

}