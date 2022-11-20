package com.example.db_lab5.controller;

import com.example.db_lab5.domain.Rating;
import com.example.db_lab5.dto.RatingDto;
import com.example.db_lab5.dto.assembler.RatingDtoAssembler;
import com.example.db_lab5.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;
    @Autowired
    private RatingDtoAssembler ratingDtoAssembler;

    @GetMapping(value = "/{ratingId}")
    public ResponseEntity<RatingDto> getRating(@PathVariable Integer ratingId) {
        Rating rating = ratingService.findById(ratingId);
        RatingDto ratingDto = ratingDtoAssembler.toModel(rating);
        return new ResponseEntity<>(ratingDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<RatingDto>> getAllRatings() {
        List<Rating> ratings = ratingService.findAll();
        CollectionModel<RatingDto> ratingDtos = ratingDtoAssembler.toCollectionModel(ratings);
        return new ResponseEntity<>(ratingDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<RatingDto> addRating(@RequestBody Rating rating) {
        Rating newRating = ratingService.create(rating);
        RatingDto ratingDto = ratingDtoAssembler.toModel(newRating);
        return new ResponseEntity<>(ratingDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{ratingId}")
    public ResponseEntity<?> updateRating(@RequestBody Rating updateRating, @PathVariable Integer ratingId) {
        ratingService.update(ratingId, updateRating);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{ratingId}")
    public ResponseEntity<?> deleteRating(@PathVariable Integer ratingId) {
        ratingService.delete(ratingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
