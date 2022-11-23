package com.example.db_lab5.dto.assembler;

import com.example.db_lab5.controller.CarShopController;
import com.example.db_lab5.domain.CarShop;
import com.example.db_lab5.dto.CarShopDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CarShopDtoAssembler implements RepresentationModelAssembler<CarShop, CarShopDto> {
    @Override
    public CarShopDto toModel(CarShop entity) {
        CarShopDto carShopDto = CarShopDto.builder()
                .id(entity.getId())
                .carShopName(entity.getCarShopName())
                .build();
        Link selfLink = linkTo(methodOn(CarShopController.class).getCarShop(carShopDto.getId())).withSelfRel();
        carShopDto.add(selfLink);
        return carShopDto;
    }

    @Override
    public CollectionModel<CarShopDto> toCollectionModel(Iterable<? extends CarShop> entities) {
        CollectionModel<CarShopDto> carShopDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CarShopController.class).getAllCarShops()).withSelfRel();
        carShopDtos.add(selfLink);
        return carShopDtos;
    }

    public CollectionModel<CarShopDto> toCollectionModel(Iterable<? extends CarShop> entities, Link link) {
        CollectionModel<CarShopDto> carShopDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        carShopDtos.add(link);
        return carShopDtos;
    }
}
