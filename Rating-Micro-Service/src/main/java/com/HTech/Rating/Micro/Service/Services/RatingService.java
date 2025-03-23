package com.HTech.Rating.Micro.Service.Services;

import com.HTech.Rating.Micro.Service.Models.Rating;
import com.HTech.Rating.Micro.Service.Repositorys.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {
    @Autowired
    RatingRepository ratingRepository;


    public Rating CreateRating(Rating rating){
        return ratingRepository.save(rating);
    }

    public List<Rating> AllRatings(){
        return ratingRepository.findAll();
    }
    public List<Rating> getRatingByUserID(String userId){
        return ratingRepository.findByUserId(userId);
    }
    public List<Rating> getRatingByHotelID(String HotelId){
        return ratingRepository.findByHotelId(HotelId);
    }
}
