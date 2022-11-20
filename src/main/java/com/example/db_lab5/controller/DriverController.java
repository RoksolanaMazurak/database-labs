package com.example.db_lab5.controller;

import com.example.db_lab5.domain.Driver;
import com.example.db_lab5.dto.DriverDto;
import com.example.db_lab5.dto.assembler.DriverDtoAssembler;
import com.example.db_lab5.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/drivers")
public class DriverController {
    @Autowired
    private DriverService driverService;
    @Autowired
    private DriverDtoAssembler driverDtoAssembler;

    @GetMapping(value = "/{driverId}")
    public ResponseEntity<DriverDto> getDriver(@PathVariable Integer driverId) {
        Driver driver = driverService.findById(driverId);
        DriverDto driverDto = driverDtoAssembler.toModel(driver);
        return new ResponseEntity<>(driverDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<DriverDto>> getAllDrivers() {
        List<Driver> drivers = driverService.findAll();
        CollectionModel<DriverDto> driverDtos = driverDtoAssembler.toCollectionModel(drivers);
        return new ResponseEntity<>(driverDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<DriverDto> addDriver(@RequestBody Driver driver) {
        Driver newDriver = driverService.create(driver);
        DriverDto driverDto = driverDtoAssembler.toModel(newDriver);
        return new ResponseEntity<>(driverDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{driverId}")
    public ResponseEntity<?> updateDriver(@RequestBody Driver updateDriver, @PathVariable Integer driverId) {
        driverService.update(driverId, updateDriver);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{driverId}")
    public ResponseEntity<?> deleteDriver(@PathVariable Integer driverId) {
        driverService.delete(driverId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "driver/{driverId}/car/{carId}")
    public ResponseEntity<DriverDto> addCarForDriver(@PathVariable Integer driverId, @PathVariable Integer carId) {
        Driver driver = driverService.addCarForDriver(driverId, carId);
        DriverDto driverDto = driverDtoAssembler.toModel(driver);
        return new ResponseEntity<>(driverDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "driver/{driverId}/car/{carId}")
    public ResponseEntity<DriverDto> removeCarForDriver(@PathVariable Integer driverId, @PathVariable Integer carId) {
        Driver driver = driverService.removeCarForDriver(driverId, carId);
        DriverDto driverDto = driverDtoAssembler.toModel(driver);
        return new ResponseEntity<>(driverDto, HttpStatus.OK);
    }

    @GetMapping(value = "car/{carId}")
    public ResponseEntity<CollectionModel<DriverDto>> getDriversByCarId(@PathVariable Integer carId) {
        List<Driver> persons = driverService.getDriversByCarId(carId);
        Link selfLink = linkTo(methodOn(DriverController.class).getDriversByCarId(carId)).withSelfRel();
        CollectionModel<DriverDto> driverDtos = driverDtoAssembler.toCollectionModel(persons, selfLink);
        return new ResponseEntity<>(driverDtos, HttpStatus.OK);
    }
}