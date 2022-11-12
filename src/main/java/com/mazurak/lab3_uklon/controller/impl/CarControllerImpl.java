package com.mazurak.lab3_uklon.controller.impl;

import com.mazurak.lab3_uklon.controller.CarController;
import com.mazurak.lab3_uklon.domain.Car;
import com.mazurak.lab3_uklon.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarControllerImpl implements CarController {

    @Autowired
    private CarService carService;

    @Override
    public List<Car> findAll() {
        return carService.findAll();
    }

    @Override
    public Optional<Car> findById(Integer id) {
        return carService.findById(id);
    }

    @Override
    public int create(Car car) {
        return carService.create(car);
    }

    @Override
    public int update(Integer id, Car carNumber) {
        return carService.update(id, carNumber);
    }

    @Override
    public int delete(Integer id) {
        return carService.delete(id);
    }
}
