package com.example.db_lab5.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "car", collectionRelation = "cars")
public class CarDto extends RepresentationModel<CarDto> {
    private final Integer id;
    private final String carNumber;
    private final Integer carModel;
    private final Integer carShop;

}
