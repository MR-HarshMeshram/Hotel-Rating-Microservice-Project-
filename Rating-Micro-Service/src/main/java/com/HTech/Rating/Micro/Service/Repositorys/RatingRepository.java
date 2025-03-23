package com.HTech.Rating.Micro.Service.Repositorys;

import com.HTech.Rating.Micro.Service.Models.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepository extends MongoRepository<Rating,String> {
    //custom finder methods
    List<Rating> findByUserId(String userid);
    List<Rating> findByHotelId(String hotelid);
}
