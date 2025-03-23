package com.HTech.Hotel.Micro.Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HotelMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelMicroServiceApplication.class, args);
	}

}
