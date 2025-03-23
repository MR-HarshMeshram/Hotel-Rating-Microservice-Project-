package com.HTech.User.Micro.Service.Services;

import com.HTech.User.Micro.Service.Models.Hotel;
import com.HTech.User.Micro.Service.Models.Rating;
import com.HTech.User.Micro.Service.Models.User;
import com.HTech.User.Micro.Service.Repositorys.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  RestTemplate restTemplate;

    public void  saveUser(User user){
        userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }
    public User UpDateUser(String username, User newUser) {
        User existingUser = userRepository.findByUsername(username);
        if (existingUser != null) {
            existingUser.setAbout(newUser.getAbout());
            existingUser.setEmail(newUser.getEmail());
            return userRepository.save(existingUser);
        }
        return null;
    }
    public boolean deleteUser(String username){
        User deletedUser=userRepository.findByUsername(username);
        if(deletedUser!=null){
            userRepository.delete(deletedUser);
            return true;
        }
        return false;
    }

    // double service with map
    public User getUser(String username) {
        User thisUser = userRepository.findByUsername(username);
        Rating[] ratings = restTemplate.getForObject("http://RATING-MICRO-SERVICE/rating/user/" + thisUser.getId(), Rating[].class);


        List<Rating> ratingsList = Arrays.asList(ratings);
        List<Rating> enrichedRatings = ratingsList.stream().map(rating -> {
            try {
                Hotel hotel = restTemplate.getForObject("http://HOTEL-MICRO-SERVICE/hotel/H22345", Hotel.class);// currect
                rating.setHotel(hotel); // Set the hotel JSON in the rating
            } catch (Exception e) {
                log.error("Failed to fetch hotel details for hotel ID: {}", rating.getHotelId(), e);
            }
            return rating;
        }).collect(Collectors.toList());
        thisUser.setRatings(enrichedRatings);
        return thisUser;
    }
    //   --for One microService call.
//    public User getUser(String username) {
//        // Fetch user details from the database
//        User thisUser = userRepository.findByUsername(username);
//        ArrayList<Rating>  ratings = restTemplate.getForObject("http://localhost:8083/rating/user/" + thisUser.getId(), ArrayList.class);
//        thisUser.setRatings(ratings);
//        return thisUser;
//    }
}