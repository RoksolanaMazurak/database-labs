package com.example.db_lab5.service.impl;

import com.example.db_lab5.domain.Car;
import com.example.db_lab5.domain.CarModel;
import com.example.db_lab5.exception.CarModelNotFoundException;
import com.example.db_lab5.exception.CarNotFoundException;
import com.example.db_lab5.repository.CarModelRepository;
import com.example.db_lab5.repository.CarRepository;
import com.example.db_lab5.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    CarRepository carRepository;
    @Autowired
    CarModelRepository carModelRepository;

    public Car findById(Integer id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Transactional
    public Car create(Car car) {
        carRepository.save(car);
        return car;
    }

    @Transactional
    public Car create(Car car, Integer carModelId) {
        CarModel carModel = carModelRepository.findById(carModelId)
                .orElseThrow(() -> new CarModelNotFoundException(carModelId));
        car.setCarModel(carModel);
        carRepository.save(car);
        return car;
    }

    @Transactional
    public void update(Integer id, Car updateCar) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
        //update
        car.setCarNumber(updateCar.getCarNumber());
        carRepository.save(car);
    }

    @Transactional
    public void update(Integer id, Car updateCar, Integer carModelId) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
        CarModel carModel = carModelRepository.findById(carModelId)
                .orElseThrow(() -> new CarModelNotFoundException(carModelId));

        //update
        car.setCarNumber(updateCar.getCarNumber());
        car.setCarModel(carModel);
        carRepository.save(car);
    }

    @Transactional
    public void delete(Integer id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
        carRepository.delete(car);
    }

    public List<Car> findCarsByCarModelId(Integer carModelId) {
        CarModel carModel = carModelRepository.findById(carModelId)
                .orElseThrow(() -> new CarModelNotFoundException(carModelId));
        return carModel.getCars();
    }
}

