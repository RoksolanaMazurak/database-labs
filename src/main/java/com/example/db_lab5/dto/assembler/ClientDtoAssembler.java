package com.example.db_lab5.dto.assembler;
import com.example.db_lab5.controller.ClientController;
import com.example.db_lab5.domain.Client;
import com.example.db_lab5.dto.ClientDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ClientDtoAssembler implements RepresentationModelAssembler<Client, ClientDto> {
    @Override
    public ClientDto toModel(Client entity) {
        ClientDto clientDto = ClientDto.builder()
                .id(entity.getId())
                .surname(entity.getSurname())
                .name(entity.getName())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .city(entity.getCity())
                .streetAddress(entity.getStreetAddress())
                .clientCard(entity.getClientCard().getId())
                .build();
        Link selfLink = linkTo(methodOn(ClientController.class).getClient(clientDto.getId())).withSelfRel();
        clientDto.add(selfLink);
        return clientDto;
    }
    @Override
    public CollectionModel<ClientDto> toCollectionModel(Iterable<? extends Client> entities) {
        CollectionModel<ClientDto> clientDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(ClientController.class).getAllClients()).withSelfRel();
        clientDtos.add(selfLink);
        return clientDtos;
    }

    public CollectionModel<ClientDto> toCollectionModel(Iterable<? extends Client> entities, Link link) {
        CollectionModel<ClientDto> clientDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        clientDtos.add(link);
        return clientDtos;
    }
}
