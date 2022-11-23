package com.example.db_lab5.service.impl;

import com.example.db_lab5.domain.CarModel;
import com.example.db_lab5.exception.CarModelNotFoundException;
import com.example.db_lab5.repository.CarModelRepository;
import com.example.db_lab5.service.CarModelService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CarModelServiceImpl implements CarModelService {
    @Autowired
    CarModelRepository carModelRepository;

    public CarModel findById(Integer id) {
        return carModelRepository.findById(id)
                .orElseThrow(() -> new CarModelNotFoundException(id));
    }

    public List<CarModel> findAll() {
        return carModelRepository.findAll();
    }

    @Transactional
    public CarModel create(CarModel carModel) {
        carModelRepository.save(carModel);
        return carModel;
    }

    @Transactional
    public void update(Integer id, CarModel updateCarModel) {
        CarModel carModel = carModelRepository.findById(id)
                .orElseThrow(() -> new CarModelNotFoundException(id));
        //update
        carModel.setName(updateCarModel.getName());
        carModel.setCompany(updateCarModel.getCompany());
        carModel.setClazz(updateCarModel.getClazz());
        carModel.setSeatNumber(updateCarModel.getSeatNumber());

        carModelRepository.save(carModel);
    }

    @Transactional
    public void delete(Integer id) {
        CarModel carModel = carModelRepository.findById(id)
                .orElseThrow(() -> new CarModelNotFoundException(id));
        carModelRepository.delete(carModel);
    }

    @Override
    public CarModel insertParamCarModel(String name, String company, String clazz, Integer seatNum) {
        return carModelRepository.insertParamCarModel(name, company, clazz, seatNum);
    }

    @Override
    public Integer getMaxSeatNum() {
        return carModelRepository.getMaxSeatNum();
    }
}
