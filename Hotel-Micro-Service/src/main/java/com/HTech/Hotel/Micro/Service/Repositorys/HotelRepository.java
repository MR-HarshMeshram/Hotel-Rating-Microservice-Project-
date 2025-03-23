package com.HTech.Hotel.Micro.Service.Repositorys;

import com.HTech.Hotel.Micro.Service.Models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,String> {
    Hotel findByname(String name);
}
