package com.example.db_lab5.controller;

import com.example.db_lab5.domain.ClientCard;
import com.example.db_lab5.dto.ClientCardDto;
import com.example.db_lab5.dto.assembler.ClientCardDtoAssembler;
import com.example.db_lab5.service.ClientCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/clientCards")
public class ClientCardController {
    @Autowired
    private ClientCardService clientCardService;
    @Autowired
    private ClientCardDtoAssembler clientCardDtoAssembler;

    @GetMapping(value = "/{clientCardId}")
    public ResponseEntity<ClientCardDto> getClientCard(@PathVariable Integer clientCardId) {
        ClientCard clientCard = clientCardService.findById(clientCardId);
        ClientCardDto clientDto = clientCardDtoAssembler.toModel(clientCard);
        return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<ClientCardDto>> getAllClientCards() {
        List<ClientCard> clientCards = clientCardService.findAll();
        CollectionModel<ClientCardDto> clientCardDtos = clientCardDtoAssembler.toCollectionModel(clientCards);
        return new ResponseEntity<>(clientCardDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<ClientCardDto> addClientCard(@RequestBody ClientCard clientCard) {
        ClientCard newClientCard = clientCardService.create(clientCard);
        ClientCardDto clientCardDto = clientCardDtoAssembler.toModel(newClientCard);
        return new ResponseEntity<>(clientCardDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{clientCardId}")
    public ResponseEntity<?> updateClientCard(@RequestBody ClientCard updateClientCard, @PathVariable Integer clientCardId) {
        clientCardService.update(clientCardId, updateClientCard);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{clientCardId}")
    public ResponseEntity<?> deleteClientCard(@PathVariable Integer clientCardId) {
        clientCardService.delete(clientCardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
