package com.example.db_lab5.service.impl;

import com.example.db_lab5.domain.CarShop;
import com.example.db_lab5.exception.CarShopNotFoundException;
import com.example.db_lab5.repository.CarShopRepository;
import com.example.db_lab5.service.CarShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CarShopServiceImpl implements CarShopService {
    @Autowired
    CarShopRepository carShopRepository;

    public CarShop findById(Integer id) {
        return carShopRepository.findById(id)
                .orElseThrow(() -> new CarShopNotFoundException(id));
    }

    public List<CarShop> findAll() {
        return carShopRepository.findAll();
    }

    @Transactional
    public CarShop create(CarShop carShop) {
        carShopRepository.save(carShop);
        return carShop;
    }

    @Transactional
    public void update(Integer id, CarShop updateCarShop) {
        CarShop carShop = carShopRepository.findById(id)
                .orElseThrow(() -> new CarShopNotFoundException(id));
        //update
        carShop.setCarShopName(updateCarShop.getCarShopName());
        carShopRepository.save(carShop);
    }

    @Transactional
    public void delete(Integer id) {
        CarShop carShop = carShopRepository.findById(id)
                .orElseThrow(() -> new CarShopNotFoundException(id));
        carShopRepository.delete(carShop);
    }
}