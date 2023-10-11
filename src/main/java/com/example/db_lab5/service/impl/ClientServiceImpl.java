package com.example.db_lab5.service.impl;

import com.example.db_lab5.domain.Client;
import com.example.db_lab5.domain.ClientCard;
import com.example.db_lab5.exception.ClientCardNotFoundException;
import com.example.db_lab5.exception.ClientNotFoundException;
import com.example.db_lab5.repository.ClientCardRepository;
import com.example.db_lab5.repository.ClientRepository;
import com.example.db_lab5.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ClientCardRepository clientCardRepository;

    public Client findById(Integer id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Transactional
    public Client create(Client client) {
        clientRepository.save(client);
        return client;
    }

    @Transactional
    public Client create(Client client, Integer clientCardId) {
        ClientCard clientCard = clientCardRepository.findById(clientCardId)
                .orElseThrow(() -> new ClientCardNotFoundException(clientCardId));
        client.setClientCard(clientCard);
        clientRepository.save(client);
        return client;
    }

    @Transactional
    public void update(Integer id, Client updateClient) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
        //update
        client.setSurname(updateClient.getSurname());
        client.setName(updateClient.getName());
        client.setPhone(updateClient.getPhone());
        client.setEmail(updateClient.getEmail());
        client.setCity(updateClient.getCity());
        client.setStreetAddress(updateClient.getStreetAddress());
        clientRepository.save(client);
    }

    @Transactional
    public void update(Integer id, Client updateClient, Integer clientCardId) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
        ClientCard clientCard = clientCardRepository.findById(clientCardId)
                .orElseThrow(() -> new ClientCardNotFoundException(clientCardId));

        //update
        client.setSurname(updateClient.getSurname());
        client.setName(updateClient.getName());
        client.setPhone(updateClient.getPhone());
        client.setEmail(updateClient.getEmail());
        client.setCity(updateClient.getCity());
        client.setStreetAddress(updateClient.getStreetAddress());
        client.setClientCard(clientCard);
        clientRepository.save(client);
    }

    @Transactional
    public void delete(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
        clientRepository.delete(client);
    }
}
