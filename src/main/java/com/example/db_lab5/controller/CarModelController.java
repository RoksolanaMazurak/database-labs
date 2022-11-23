package com.example.db_lab5.controller;

import com.example.db_lab5.domain.CarModel;
import com.example.db_lab5.dto.CarModelDto;
import com.example.db_lab5.dto.assembler.CarModelDtoAssembler;
import com.example.db_lab5.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "/api/carModels")
public class CarModelController {
    @Autowired
    private CarModelService carModelService;
    @Autowired
    private CarModelDtoAssembler carModelDtoAssembler;

    @GetMapping(value = "/{carModelId}")
    public ResponseEntity<CarModelDto> getCarModel(@PathVariable Integer carModelId) {
        CarModel carModel = carModelService.findById(carModelId);
        CarModelDto carModelDto = carModelDtoAssembler.toModel(carModel);
        return new ResponseEntity<>(carModelDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<CarModelDto>> getAllCarModels() {
        List<CarModel> carModels = carModelService.findAll();
        CollectionModel<CarModelDto> carModelDtos = carModelDtoAssembler.toCollectionModel(carModels);
        return new ResponseEntity<>(carModelDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<CarModelDto> addCarModel(@RequestBody CarModel carModel) {
        CarModel newCarModel = carModelService.create(carModel);
        CarModelDto carModelDto = carModelDtoAssembler.toModel(newCarModel);
        return new ResponseEntity<>(carModelDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{carModelId}")
    public ResponseEntity<?> updateCarModel(@RequestBody CarModel updateCarModel, @PathVariable Integer carModelId) {
        carModelService.update(carModelId, updateCarModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{carModelId}")
    public ResponseEntity<?> deleteCarModel(@PathVariable Integer carModelId) {
        carModelService.delete(carModelId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value = "paramInsert")
    public ResponseEntity<CarModelDto> insertParamCarModel(@RequestBody CarModel carModel) {
        CarModel newCarModel = carModelService.insertParamCarModel(carModel.getName(), carModel.getCompany(),
                carModel.getClazz(), carModel.getSeatNumber());
        CarModelDto carModelDto = carModelDtoAssembler.toModel(newCarModel);
        return new ResponseEntity<>(carModelDto, HttpStatus.CREATED);
    }

    @Transactional
    @GetMapping(value = "/maxSeatNum")
    public ResponseEntity<Integer> getMaxSeatNum() {
        Integer maxSeatNum = carModelService.getMaxSeatNum();
        return new ResponseEntity<>(maxSeatNum, HttpStatus.OK);
    }

}
