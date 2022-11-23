package com.example.db_lab5.service;

import com.example.db_lab5.domain.CarModel;

public interface CarModelService extends GeneralService<CarModel, Integer> {
    CarModel insertParamCarModel(String name, String company, String clazz, Integer seatNum);
    Integer getMaxSeatNum();
}