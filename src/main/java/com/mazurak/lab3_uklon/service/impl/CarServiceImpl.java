package com.mazurak.lab3_uklon.service.impl;

import com.mazurak.lab3_uklon.dao.CarDao;
import com.mazurak.lab3_uklon.domain.Car;
import com.mazurak.lab3_uklon.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarDao carDao;

    @Override
    public List<Car> findAll() {
        return carDao.findAll();
    }

    @Override
    public Optional<Car> findById(Integer id) {
        return carDao.findById(id);
    }

    @Override
    public int create(Car car) {
        return carDao.create(car);
    }

    @Override
    public int update(Integer id, Car car) {
        return carDao.update(id, car);
    }

    @Override
    public int delete(Integer id) {
        return carDao.delete(id);
    }
}

