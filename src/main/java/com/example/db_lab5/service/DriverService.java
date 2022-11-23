package com.example.db_lab5.service;

import com.example.db_lab5.domain.CarModel;
import com.example.db_lab5.domain.Driver;

import java.util.List;
import java.util.Set;

public interface DriverService extends GeneralService<Driver, Integer> {
    List<Driver> getDriversByCarId(Integer carId);

    Driver addCarForDriver(Integer driverId, Integer carId);

    Driver removeCarForDriver(Integer driverId, Integer carId);

    void insertDrivers();
    void connectDriverAndCar(String driverSurname, String carNum);
}
