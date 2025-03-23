package com.HTech.Rating.Micro.Service.Controllers;

import com.HTech.Rating.Micro.Service.Models.Rating;
import com.HTech.Rating.Micro.Service.Services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    RatingService ratingService;


    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        if(rating != null){
            ratingService.CreateRating(rating);
            return  new ResponseEntity<>(HttpStatus.CREATED);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings(){
        return new ResponseEntity<>(ratingService.AllRatings(),HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getAllRatingsByUserId(@PathVariable String userId){
        return new ResponseEntity<>(ratingService.getRatingByUserID(userId),HttpStatus.OK);
    }
    @GetMapping("/hotel/{HotelId}")
    public ResponseEntity<List<Rating>> getAllRatingHotelId(@PathVariable String HotelId){
        return new ResponseEntity<>(ratingService.getRatingByHotelID(HotelId),HttpStatus.OK);
    }

}
