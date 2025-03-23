package com.HTech.Hotel.Micro.Service.Services;

import com.HTech.Hotel.Micro.Service.Models.Hotel;
import com.HTech.Hotel.Micro.Service.Repositorys.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    @Autowired
    HotelRepository hotelRepository;

    public  void CreateHotel(Hotel hotel){
        hotelRepository.save(hotel);
    }
    public List<Hotel> GetAllHotels(){
        return  hotelRepository.findAll();
    }
    public  Hotel OneHotel(String hotelid){
        return hotelRepository.findById(hotelid).orElse(null);
    }
    public Hotel updateHotel(String hotelid, Hotel newHotel) {
        return hotelRepository.findById(hotelid)
                .map(existingHotel -> {
                    existingHotel.setAbout(newHotel.getAbout());
                    existingHotel.setName(newHotel.getName());
                    existingHotel.setCity(newHotel.getCity());
                    return hotelRepository.save(existingHotel);
                }).orElse(null);
    }
    public  Boolean deletehotel(String hotelName){
        Hotel existinghotel=hotelRepository.findByname(hotelName);
        if(existinghotel != null){
            hotelRepository.delete(existinghotel);
            return  true;
        }
        return false;
    }
}
