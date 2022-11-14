package com.mazurak.lab3_uklon.service.impl;

import com.mazurak.lab3_uklon.dao.CarDao;
import com.mazurak.lab3_uklon.dao.ClientCardDao;
import com.mazurak.lab3_uklon.domain.Car;
import com.mazurak.lab3_uklon.domain.ClientCard;
import com.mazurak.lab3_uklon.service.CarService;
import com.mazurak.lab3_uklon.service.ClientCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientCardServiceImpl implements ClientCardService {
    @Autowired
    private ClientCardDao clientCardDao;

    @Override
    public List<ClientCard> findAll() {
        return clientCardDao.findAll();
    }

    @Override
    public Optional<ClientCard> findById(Integer id) {
        return clientCardDao.findById(id);
    }

    @Override
    public int create(ClientCard card) {
        return clientCardDao.create(card);
    }

    @Override
    public int update(Integer id, ClientCard card) {
        return clientCardDao.update(id, card);
    }

    @Override
    public int delete(Integer id) {
        return clientCardDao.delete(id);
    }

}
