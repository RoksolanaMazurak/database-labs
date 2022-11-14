package com.mazurak.lab3_uklon.service.impl;

import com.mazurak.lab3_uklon.dao.CarDao;
import com.mazurak.lab3_uklon.dao.CarModelDao;
import com.mazurak.lab3_uklon.domain.Car;
import com.mazurak.lab3_uklon.domain.CarModel;
import com.mazurak.lab3_uklon.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarModelServiceImpl implements CarModelService {
    @Autowired
    private CarModelDao carModelDao;

    @Override
    public List<CarModel> findAll() {
        return carModelDao.findAll();
    }

    @Override
    public Optional<CarModel> findById(Integer id) {
        return carModelDao.findById(id);
    }

    @Override
    public int create(CarModel carModel) {
        return carModelDao.create(carModel);
    }

    @Override
    public int update(Integer id, CarModel carModel) {
        return carModelDao.update(id, carModel);
    }

    @Override
    public int delete(Integer id) {
        return carModelDao.delete(id);
    }
}

