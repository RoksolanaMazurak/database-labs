package com.mazurak.lab3_uklon.controller.impl;

import com.mazurak.lab3_uklon.controller.RatingController;
import com.mazurak.lab3_uklon.domain.Rating;
import com.mazurak.lab3_uklon.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingControllerImpl implements RatingController {

    @Autowired
    private RatingService ratingService;

    @Override
    public List<Rating> findAll() {
        return ratingService.findAll();
    }

    @Override
    public Optional<Rating> findById(Integer id) {
        return ratingService.findById(id);
    }

    @Override
    public int create(Rating rating) {
        return ratingService.create(rating);
    }

    @Override
    public int update(Integer id, Rating rating) {
        return ratingService.update(id, rating);
    }

    @Override
    public int delete(Integer id) {
        return ratingService.delete(id);
    }
}
