package com.example.db_lab5.service;

import com.example.db_lab5.domain.Car;

import java.util.List;

public interface CarService extends GeneralService<Car, Integer> {
    List<Car> findCarsByCarModelId(Integer carModelId);

}
