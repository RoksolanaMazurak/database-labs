package com.example.db_lab5.dto.assembler;

import com.example.db_lab5.controller.RatingController;
import com.example.db_lab5.domain.Rating;
import com.example.db_lab5.dto.RatingDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class RatingDtoAssembler implements RepresentationModelAssembler<Rating, RatingDto> {
    @Override
    public RatingDto toModel(Rating entity) {
        RatingDto ratingDto = RatingDto.builder()
                .id(entity.getId())
                .value(entity.getValue())
                .client(entity.getClient().getId())
                .build();
        Link selfLink = linkTo(methodOn(RatingController.class).getRating(ratingDto.getId())).withSelfRel();
        ratingDto.add(selfLink);
        return ratingDto;
    }
    @Override
    public CollectionModel<RatingDto> toCollectionModel(Iterable<? extends Rating> entities) {
        CollectionModel<RatingDto> ratingDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(RatingController.class).getAllRatings()).withSelfRel();
        ratingDtos.add(selfLink);
        return ratingDtos;
    }

    public CollectionModel<RatingDto> toCollectionModel(Iterable<? extends Rating> entities, Link link) {
        CollectionModel<RatingDto> ratingDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        ratingDtos.add(link);
        return ratingDtos;
    }
}
