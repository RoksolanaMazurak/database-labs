package com.example.db_lab5.dto.assembler;

import com.example.db_lab5.controller.CarController;
import com.example.db_lab5.domain.Car;
import com.example.db_lab5.dto.CarDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CarDtoAssembler implements RepresentationModelAssembler<Car, CarDto> {
    @Override
    public CarDto toModel(Car entity) {
        CarDto carDto = CarDto.builder()
                .id(entity.getId())
                .carNumber(entity.getCarNumber())
                .carModel(entity.getCarModel().getId())
                .build();
        Link selfLink = linkTo(methodOn(CarController.class).getCar(carDto.getId())).withSelfRel();
        carDto.add(selfLink);
        return carDto;
    }

    @Override
    public CollectionModel<CarDto> toCollectionModel(Iterable<? extends Car> entities) {
        CollectionModel<CarDto> carDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CarController.class).getAllCars()).withSelfRel();
        carDtos.add(selfLink);
        return carDtos;
    }

    public CollectionModel<CarDto> toCollectionModel(Iterable<? extends Car> entities, Link link) {
        CollectionModel<CarDto> carDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        carDtos.add(link);
        return carDtos;
    }
}

