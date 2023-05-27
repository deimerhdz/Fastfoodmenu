package com.dhernandez.fastfood.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhernandez.fastfood.domain.dto.RestaurantTableDto;

import com.dhernandez.fastfood.domain.services.RestaurantTableService;



@RestController
@RequestMapping("/tables")
public class RestaurantTableController {
	
	@Autowired
	RestaurantTableService restaurantService;
	
	
	@GetMapping("/list-all/{restaurantId}")
	public ResponseEntity<List<RestaurantTableDto>> findAllByRestaurantId(@PathVariable Long restaurantId ){
		return new ResponseEntity<>(restaurantService.findByRestaurantId(restaurantId),HttpStatus.OK);
	}
	
	@PostMapping("/save/{quantity}")
	public ResponseEntity<?> save(
			@PathVariable Long quantity,
			@RequestBody RestaurantTableDto restaurantTableDto){
		restaurantService.save(quantity ,restaurantTableDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		restaurantService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
