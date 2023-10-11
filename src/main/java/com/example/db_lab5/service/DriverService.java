package com.example.db_lab5.service;

import com.example.db_lab5.domain.Driver;

import java.util.List;
import java.util.Set;

public interface DriverService extends GeneralService<Driver, Integer> {
    public List<Driver> getDriversByCarId(Integer carId);

    public Driver addCarForDriver(Integer driverId, Integer carId);

    public Driver removeCarForDriver(Integer driverId, Integer carId);
}
