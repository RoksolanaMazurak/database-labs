package com.example.db_lab5.controller;

import com.example.db_lab5.domain.Car;
import com.example.db_lab5.domain.CarShop;
import com.example.db_lab5.dto.CarDto;
import com.example.db_lab5.dto.CarShopDto;
import com.example.db_lab5.dto.assembler.CarDtoAssembler;
import com.example.db_lab5.dto.assembler.CarShopDtoAssembler;
import com.example.db_lab5.service.CarService;
import com.example.db_lab5.service.CarShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/carShops")
public class CarShopController {
    @Autowired
    private CarShopService carShopService;
    @Autowired
    private CarShopDtoAssembler carShopDtoAssembler;

    @GetMapping(value = "/{carShopId}")
    public ResponseEntity<CarShopDto> getCarShop(@PathVariable Integer carShopId) {
        CarShop carShop = carShopService.findById(carShopId);
        CarShopDto carShopDto = carShopDtoAssembler.toModel(carShop);
        return new ResponseEntity<>(carShopDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<CarShopDto>> getAllCarShops() {
        List<CarShop> carShops = carShopService.findAll();
        CollectionModel<CarShopDto> carShopDtos = carShopDtoAssembler.toCollectionModel(carShops);
        return new ResponseEntity<>(carShopDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<CarShopDto> addCarShop(@RequestBody CarShop carShop) {
        CarShop newCarShop = carShopService.create(carShop);
        CarShopDto carShopDto = carShopDtoAssembler.toModel(newCarShop);
        return new ResponseEntity<>(carShopDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{carShopId}")
    public ResponseEntity<?> updateCarShop(@RequestBody CarShop updateCarShop, @PathVariable Integer carShopId) {
        carShopService.update(carShopId, updateCarShop);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{carShopId}")
    public ResponseEntity<?> deleteCarShop(@PathVariable Integer carShopId) {
        carShopService.delete(carShopId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}