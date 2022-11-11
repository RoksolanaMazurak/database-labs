package com.mazurak.lab3_uklon.service.impl;

import com.mazurak.lab3_uklon.dao.PaymentDao;
import com.mazurak.lab3_uklon.dao.RatingDao;
import com.mazurak.lab3_uklon.domain.Payment;
import com.mazurak.lab3_uklon.domain.Rating;
import com.mazurak.lab3_uklon.service.PaymentService;
import com.mazurak.lab3_uklon.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingDao ratingDao;

    @Override
    public List<Rating> findAll() {
        return ratingDao.findAll();
    }

    @Override
    public Optional<Rating> findById(Integer id) {
        return ratingDao.findById(id);
    }

    @Override
    public int create(Rating rating) {
        return ratingDao.create(rating);
    }

    @Override
    public int update(Integer id, Rating rating) {
        return ratingDao.update(id, rating);
    }

    @Override
    public int delete(Integer id) {
        return ratingDao.delete(id);
    }

}
