package com.example.db_lab5.controller;

import com.example.db_lab5.domain.Client;
import com.example.db_lab5.dto.ClientDto;
import com.example.db_lab5.dto.assembler.ClientDtoAssembler;
import com.example.db_lab5.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientDtoAssembler clientDtoAssembler;

    @GetMapping(value = "/{clientId}")
    public ResponseEntity<ClientDto> getClient(@PathVariable Integer clientId) {
        Client client = clientService.findById(clientId);
        ClientDto clientDto = clientDtoAssembler.toModel(client);
        return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<ClientDto>> getAllClients() {
        List<Client> clients = clientService.findAll();
        CollectionModel<ClientDto> clientDtos = clientDtoAssembler.toCollectionModel(clients);
        return new ResponseEntity<>(clientDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<ClientDto> addClient(@RequestBody Client client) {
        Client newClient = clientService.create(client);
        ClientDto clientDto = clientDtoAssembler.toModel(newClient);
        return new ResponseEntity<>(clientDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{clientId}")
    public ResponseEntity<?> updateClient(@RequestBody Client updateClient, @PathVariable Integer clientId) {
        clientService.update(clientId, updateClient);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable Integer clientId) {
        clientService.delete(clientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
