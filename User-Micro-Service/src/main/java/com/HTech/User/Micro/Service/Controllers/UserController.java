package com.HTech.User.Micro.Service.Controllers;

import com.HTech.User.Micro.Service.Models.User;
import com.HTech.User.Micro.Service.Services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;

    //getAll
    @GetMapping
    public ResponseEntity<?> AllUser(){
        List<User> AllUser=userService.getAll();
        return new ResponseEntity<>(AllUser,HttpStatus.OK);
    }
    @GetMapping("/{username}")
    @CircuitBreaker(name="RatingHotelBreaker",fallbackMethod = "ratingHotelFallBack")
    public  ResponseEntity<?> getByUsername(@PathVariable String username){
        User user=userService.getUser(username);
        if(user==null){
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    // create fallbackMethod
    public  ResponseEntity<User> ratingHotelFallBack(String username ,Throwable t){
        log.info("FallBack is executed because service is down: "+t.getMessage());
        User user=new  User();
          user.setEmail("null");
          user.setId("null");
          user.setAbout("null");
          user.setUsername("null");
        return  new ResponseEntity<>(user,HttpStatus.OK);
    }

    //Create user
    @PostMapping
    public ResponseEntity<User> CreateUser(@RequestBody  User user){
        if(user != null){
            userService.saveUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    //update user
    @PutMapping("{username}")
    public ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody User user) {
        User updatedUser = userService.UpDateUser(username,user);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username){
        boolean isdDelete=userService.deleteUser(username);
        if(isdDelete){
            return new ResponseEntity<>("user deleted Successfully",HttpStatus.OK);

        }else {
            return new ResponseEntity<>("user not found ",HttpStatus.NOT_FOUND);
        }

    }


}
