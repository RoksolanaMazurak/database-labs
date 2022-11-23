package com.example.db_lab5.dto.assembler;

import com.example.db_lab5.controller.CarModelController;
import com.example.db_lab5.domain.CarModel;
import com.example.db_lab5.dto.CarModelDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CarModelDtoAssembler implements RepresentationModelAssembler<CarModel, CarModelDto> {
    @Override
    public CarModelDto toModel(CarModel entity) {
        CarModelDto carModelDto = CarModelDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .company(entity.getCompany())
                .carClass(entity.getClazz())
                .seatNum(entity.getSeatNumber())
                .build();
        Link selfLink = linkTo(methodOn(CarModelController.class).getCarModel(carModelDto.getId())).withSelfRel();
        carModelDto.add(selfLink);
        return carModelDto;
    }
    @Override
    public CollectionModel<CarModelDto> toCollectionModel(Iterable<? extends CarModel> entities) {
        CollectionModel<CarModelDto> carModelDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CarModelController.class).getAllCarModels()).withSelfRel();
        carModelDtos.add(selfLink);
        return carModelDtos;
    }

    public CollectionModel<CarModelDto> toCollectionModel(Iterable<? extends CarModel> entities, Link link) {
        CollectionModel<CarModelDto> carModelDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        carModelDtos.add(link);
        return carModelDtos;
    }
}
