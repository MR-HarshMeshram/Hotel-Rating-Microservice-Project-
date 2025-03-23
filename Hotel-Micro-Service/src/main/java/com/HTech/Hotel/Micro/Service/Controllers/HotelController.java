package com.HTech.Hotel.Micro.Service.Controllers;

import com.HTech.Hotel.Micro.Service.Models.Hotel;
import com.HTech.Hotel.Micro.Service.Services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelService hotelService;
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        if (hotel != null) {
            hotelService.CreateHotel(hotel);
            return  new ResponseEntity<>(HttpStatus.CREATED);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping
    public  ResponseEntity<List<Hotel>> getAllHotels(){
        return new ResponseEntity<>(hotelService.GetAllHotels(),HttpStatus.OK);
    }
    @GetMapping("/{hotelid}")
    public ResponseEntity<Hotel> OneHotel(@PathVariable String hotelid){
        Hotel thishotel=hotelService.OneHotel(hotelid);
        return new ResponseEntity<>(thishotel,HttpStatus.OK);
    }

    // not working
    @PutMapping("/{hotelid}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable String hotelid,@RequestBody Hotel hotel) {
        Hotel updatedHotel = hotelService.updateHotel(hotelid, hotel);
        if (updatedHotel != null) {
            return new ResponseEntity<>(updatedHotel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{hotelName}")
    public  ResponseEntity<?> deleteHotel(@PathVariable String hotelName){
        if(hotelService.deletehotel(hotelName)){
            return new ResponseEntity<>("hotel delete successfully",HttpStatus.CREATED);
        }
        return new ResponseEntity<>(" not deleted ",HttpStatus.BAD_REQUEST);
    }
}
