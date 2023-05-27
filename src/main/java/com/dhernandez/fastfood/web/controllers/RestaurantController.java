package com.dhernandez.fastfood.web.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhernandez.fastfood.domain.dto.RestaurantDto;
import com.dhernandez.fastfood.domain.services.IRestaurantService;


@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
	
	@Autowired
	IRestaurantService restaurantService;
	
	@PostMapping("/save")
	ResponseEntity<RestaurantDto> save(@RequestBody RestaurantDto restaurantDto){
		RestaurantDto restaurant =restaurantService.save(restaurantDto);
		
		return new ResponseEntity<>(restaurant,HttpStatus.OK);
	}
	
	@GetMapping("/detail/{userId}")
	ResponseEntity<RestaurantDto> getByUserId(@PathVariable Long userId){
		Optional<RestaurantDto> restaurant = restaurantService.findByUserId(userId);
		
		if(!restaurant.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(restaurant.get(),HttpStatus.OK);
	}
}
