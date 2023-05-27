package com.dhernandez.fastfood.persistence;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dhernandez.fastfood.domain.dto.RestaurantDto;
import com.dhernandez.fastfood.domain.repository.IRestaurantRepository;
import com.dhernandez.fastfood.persistence.crud.RestaurantCrudRepository;
import com.dhernandez.fastfood.persistence.entities.Restaurant;
import com.dhernandez.fastfood.persistence.mapper.RestaurantMapper;

@Repository
public class RestaurantRepository implements IRestaurantRepository{
	private Log Logger = LogFactory.getLog(RestaurantRepository.class);
	
	private RestaurantCrudRepository restaurantCrudRepository;
	
	private RestaurantMapper mapper;
	
	
	@Autowired
	public RestaurantRepository(RestaurantCrudRepository restaurantCrudRepository, RestaurantMapper mapper) {
		super();
		this.restaurantCrudRepository = restaurantCrudRepository;
		this.mapper = mapper;
	}

	@Override
	public RestaurantDto save(RestaurantDto restaurantDto) {
		Logger.info("Accessing the method save(restaurantDto)"+restaurantDto);
		RestaurantDto restaurantDB = mapper
				.toRestaurantDto(restaurantCrudRepository.
						save(mapper.toRestaurant(restaurantDto)));
		Logger.info("Ending the method save()");
		return restaurantDB;
	}

	@Override
	public Optional<RestaurantDto> findByUserId(Long userId) {
		Logger.info("Accessing the method findByUserId(userId)"+userId);
		// TODO Auto-generated method stub
		Optional<RestaurantDto> restaurantDB = restaurantCrudRepository.findByUserId(userId).map(restaurant -> mapper.toRestaurantDto(restaurant));
		Logger.info("Ending the method findByUserId(userId)");
		return restaurantDB;
	}

}
