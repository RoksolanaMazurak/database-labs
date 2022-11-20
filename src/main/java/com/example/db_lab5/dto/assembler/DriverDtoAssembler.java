package com.example.db_lab5.dto.assembler;

import com.example.db_lab5.controller.DriverController;
import com.example.db_lab5.domain.Driver;
import com.example.db_lab5.dto.DriverDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class DriverDtoAssembler implements RepresentationModelAssembler<Driver, DriverDto> {
    @Override
    public DriverDto toModel(Driver entity) {
        DriverDto driverDto = DriverDto.builder()
                .id(entity.getId())
                .surname(entity.getSurname())
                .name(entity.getName())
                .gender(entity.getGender())
                .rating(entity.getRating().getId())
                .build();
        Link selfLink = linkTo(methodOn(DriverController.class).getDriver(driverDto.getId())).withSelfRel();
        driverDto.add(selfLink);
        return driverDto;
    }
    @Override
    public CollectionModel<DriverDto> toCollectionModel(Iterable<? extends Driver> entities) {
        CollectionModel<DriverDto> driverDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(DriverController.class).getAllDrivers()).withSelfRel();
        driverDtos.add(selfLink);
        return driverDtos;
    }

    public CollectionModel<DriverDto> toCollectionModel(Iterable<? extends Driver> entities, Link link) {
        CollectionModel<DriverDto> driverDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        driverDtos.add(link);
        return driverDtos;
    }
}
