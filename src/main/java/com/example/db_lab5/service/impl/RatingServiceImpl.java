package com.example.db_lab5.service.impl;

import com.example.db_lab5.domain.Client;
import com.example.db_lab5.domain.Rating;
import com.example.db_lab5.exception.RatingNotFoundException;
import com.example.db_lab5.repository.ClientRepository;
import com.example.db_lab5.repository.RatingRepository;
import com.example.db_lab5.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    ClientRepository clientRepository;

    public Rating findById(Integer id) {
        return ratingRepository.findById(id)
                .orElseThrow(() -> new RatingNotFoundException(id));
    }

    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    @Transactional
    public Rating create(Rating rating) {
        ratingRepository.save(rating);
        return rating;
    }

    @Transactional
    public Rating create(Rating rating, Integer clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RatingNotFoundException(clientId));
        rating.setClient(client);
        ratingRepository.save(rating);
        return rating;
    }

    @Transactional
    public void update(Integer id, Rating updateRating) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new RatingNotFoundException(id));
        //update
        rating.setValue(updateRating.getValue());
        ratingRepository.save(rating);
    }

    @Transactional
    public void update(Integer id, Rating updateRating, Integer clientId) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new RatingNotFoundException(id));
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RatingNotFoundException(clientId));

        //update
        rating.setValue(updateRating.getValue());
        rating.setClient(client);
        ratingRepository.save(rating);
    }

    @Transactional
    public void delete(Integer id) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new RatingNotFoundException(id));
        ratingRepository.delete(rating);
    }
}