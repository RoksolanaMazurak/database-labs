package com.example.db_lab5.service.impl;

import com.example.db_lab5.domain.ClientCard;
import com.example.db_lab5.exception.ClientCardNotFoundException;
import com.example.db_lab5.repository.ClientCardRepository;
import com.example.db_lab5.service.ClientCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClientCardServiceImpl implements ClientCardService {
    @Autowired
    ClientCardRepository clientCardRepository;

    public ClientCard findById(Integer id) {
        return clientCardRepository.findById(id)
                .orElseThrow(() -> new ClientCardNotFoundException(id));
    }

    public List<ClientCard> findAll() {
        return clientCardRepository.findAll();
    }

    @Transactional
    public ClientCard create(ClientCard clientCard) {
        clientCardRepository.save(clientCard);
        return clientCard;
    }

    @Transactional
    public void update(Integer id, ClientCard updateClientCard) {
        ClientCard clientCard = clientCardRepository.findById(id)
                .orElseThrow(() -> new ClientCardNotFoundException(id));
        //update
        clientCard.setName(updateClientCard.getName());
        clientCardRepository.save(clientCard);
    }

    @Transactional
    public void delete(Integer id) {
        ClientCard clientCard = clientCardRepository.findById(id)
                .orElseThrow(() -> new ClientCardNotFoundException(id));
        clientCardRepository.delete(clientCard);
    }
}
