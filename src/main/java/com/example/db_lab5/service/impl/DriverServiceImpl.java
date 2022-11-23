package com.example.db_lab5.service.impl;

import com.example.db_lab5.domain.Car;
import com.example.db_lab5.domain.CarModel;
import com.example.db_lab5.domain.Driver;
import com.example.db_lab5.domain.Rating;
import com.example.db_lab5.exception.*;
import com.example.db_lab5.repository.CarModelRepository;
import com.example.db_lab5.repository.CarRepository;
import com.example.db_lab5.repository.DriverRepository;
import com.example.db_lab5.repository.RatingRepository;
import com.example.db_lab5.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {
    @Autowired
    DriverRepository driverRepository;
    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    CarRepository carRepository;

    @Autowired
    CarModelRepository carModelRepository;

    public Driver findById(Integer id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new DriverNotFoundException(id));
    }

    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    @Transactional
    public Driver create(Driver driver) {
        driverRepository.save(driver);
        return driver;
    }

    @Transactional
    public Driver create(Driver driver, Integer ratingId, Integer carId) {
        Rating rating = ratingRepository.findById(ratingId)
                .orElseThrow(() -> new RatingNotFoundException(ratingId));
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException(carId));
        driver.setRating(rating);
        driverRepository.save(driver);
        return driver;
    }

    @Transactional
    public void update(Integer id, Driver updateDriver) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new DriverNotFoundException(id));
        //update
        driver.setSurname(updateDriver.getSurname());
        driver.setName(updateDriver.getName());
        driver.setGender(updateDriver.getGender());
        driverRepository.save(driver);
    }

    @Transactional
    public void update(Integer id, Driver updateDriver, Integer ratingId, Integer carId) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new DriverNotFoundException(id));
        Rating rating = ratingRepository.findById(ratingId)
                .orElseThrow(() -> new RatingNotFoundException(ratingId));
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException(carId));

        //update
        driver.setSurname(updateDriver.getSurname());
        driver.setName(updateDriver.getName());
        driver.setGender(updateDriver.getGender());
        driver.setRating(rating);
        driverRepository.save(driver);
    }

    @Transactional
    public void delete(Integer id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new DriverNotFoundException(id));
        driverRepository.delete(driver);
    }

    public List<Driver> getDriversByCarId(Integer carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException(carId));
        return car.getDrivers().stream().toList();
    }

    @Transactional
    public Driver addCarForDriver(Integer driverId, Integer carId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new DriverNotFoundException(driverId));
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException(carId));
        if (driver.getCars().contains(car)) throw new DriverHasCarException(carId, driverId);
        driver.getCars().add(car);
        driverRepository.save(driver);
        return driver;
    }

    @Transactional
    public Driver removeCarForDriver(Integer driverId, Integer carId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new DriverNotFoundException(driverId));
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException(carId));
        if (!driver.getCars().contains(car)) throw new DriverHasNoCarException(carId, driverId);
        driver.getCars().remove(car);
        driverRepository.save(driver);
        return driver;
    }

    @Override
    public void insertDrivers() {
       driverRepository.insertDrivers();
    }

    @Override
    public void connectDriverAndCar(String driverSurname, String carNum) {
        driverRepository.connectDriverAndCar(driverSurname, carNum);
    }
}
