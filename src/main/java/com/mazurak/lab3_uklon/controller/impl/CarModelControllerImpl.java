package com.mazurak.lab3_uklon.controller.impl;

import com.mazurak.lab3_uklon.controller.CarModelController;
import com.mazurak.lab3_uklon.domain.Car;
import com.mazurak.lab3_uklon.domain.CarModel;
import com.mazurak.lab3_uklon.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarModelControllerImpl implements CarModelController {

    @Autowired
    private CarModelService carModelService;

    @Override
    public List<CarModel> findAll() {
        return carModelService.findAll();
    }

    @Override
    public Optional<CarModel> findById(Integer id) {
        return carModelService.findById(id);
    }

    @Override
    public int create(CarModel carModel) {
        return carModelService.create(carModel);
    }

    @Override
    public int update(Integer id, CarModel carModel) {
        return carModelService.update(id, carModel);
    }

    @Override
    public int delete(Integer id) {
        return carModelService.delete(id);
    }

}
