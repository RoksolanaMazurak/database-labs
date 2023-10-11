package com.example.db_lab5.controller;

import com.example.db_lab5.domain.Car;
import com.example.db_lab5.dto.CarDto;
import com.example.db_lab5.dto.assembler.CarDtoAssembler;
import com.example.db_lab5.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
@RequestMapping(value = "/api/cars")
public class CarController {
    @Autowired
    private CarService carService;
    @Autowired
    private CarDtoAssembler carDtoAssembler;

    @GetMapping(value = "/{carId}")
    public ResponseEntity<CarDto> getCar(@PathVariable Integer carId) {
        Car car = carService.findById(carId);
        CarDto carDto = carDtoAssembler.toModel(car);
        return new ResponseEntity<>(carDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<CarDto>> getAllCars() {
        List<Car> cars = carService.findAll();
        CollectionModel<CarDto> carDtos = carDtoAssembler.toCollectionModel(cars);
        return new ResponseEntity<>(carDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<CarDto> addCar(@RequestBody Car car) {
        Car newCar = carService.create(car);
        CarDto carDto = carDtoAssembler.toModel(newCar);
        return new ResponseEntity<>(carDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{carId}")
    public ResponseEntity<?> updateCar(@RequestBody Car updateCar, @PathVariable Integer carId) {
        carService.update(carId, updateCar);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{carId}")
    public ResponseEntity<?> deleteCar(@PathVariable Integer carId) {
        carService.delete(carId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "carModels/{carModelId}")
    public ResponseEntity<CollectionModel<CarDto>> getCarsByCarModelId(@PathVariable Integer carModelId) {
        List<Car> cars = carService.findCarsByCarModelId(carModelId);
        Link selfLink = linkTo(methodOn(CarController.class).getCarsByCarModelId(carModelId)).withSelfRel();
        CollectionModel<CarDto> carDtos = carDtoAssembler.toCollectionModel(cars, selfLink);
        return new ResponseEntity<>(carDtos, HttpStatus.OK);
    }

}
