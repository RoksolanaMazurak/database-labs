package com.example.db_lab5.dto.assembler;

import com.example.db_lab5.controller.ClientCardController;
import com.example.db_lab5.domain.ClientCard;
import com.example.db_lab5.dto.ClientCardDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ClientCardDtoAssembler implements RepresentationModelAssembler<ClientCard, ClientCardDto> {
    @Override
    public ClientCardDto toModel(ClientCard entity) {
        ClientCardDto clientCardDto = ClientCardDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
        Link selfLink = linkTo(methodOn(ClientCardController.class).getClientCard(clientCardDto.getId())).withSelfRel();
        clientCardDto.add(selfLink);
        return clientCardDto;
    }
    @Override
    public CollectionModel<ClientCardDto> toCollectionModel(Iterable<? extends ClientCard> entities) {
        CollectionModel<ClientCardDto> clientCardDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(ClientCardController.class).getAllClientCards()).withSelfRel();
        clientCardDtos.add(selfLink);
        return clientCardDtos;
    }

    public CollectionModel<ClientCardDto> toCollectionModel(Iterable<? extends ClientCard> entities, Link link) {
        CollectionModel<ClientCardDto> clientCardDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        clientCardDtos.add(link);
        return clientCardDtos;
    }
}
