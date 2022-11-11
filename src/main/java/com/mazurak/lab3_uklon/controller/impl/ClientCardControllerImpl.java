package com.mazurak.lab3_uklon.controller.impl;

import com.mazurak.lab3_uklon.controller.CarController;
import com.mazurak.lab3_uklon.controller.ClientCardController;
import com.mazurak.lab3_uklon.domain.Car;
import com.mazurak.lab3_uklon.domain.ClientCard;
import com.mazurak.lab3_uklon.service.CarService;
import com.mazurak.lab3_uklon.service.ClientCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientCardControllerImpl implements ClientCardController {

    @Autowired
    private ClientCardService clientCardService;

    @Override
    public List<ClientCard> findAll() {
        return clientCardService.findAll();
    }

    @Override
    public Optional<ClientCard> findById(Integer id) {
        return clientCardService.findById(id);
    }

    @Override
    public int create(ClientCard card) {
        return clientCardService.create(card);
    }

    @Override
    public int update(Integer id, ClientCard card) {
        return clientCardService.update(id, card);
    }

    @Override
    public int delete(Integer id) {
        return clientCardService.delete(id);
    }
}
