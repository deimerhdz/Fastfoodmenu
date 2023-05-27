package com.dhernandez.fastfood.persistence.mapper;

import org.mapstruct.Mapper;

import com.dhernandez.fastfood.domain.dto.RestaurantDto;
import com.dhernandez.fastfood.persistence.entities.Restaurant;

@Mapper(componentModel = "Spring",uses = {UserMapper.class})
public interface RestaurantMapper {
	
	
	Restaurant toRestaurant(RestaurantDto restaurantDto);
	
	RestaurantDto toRestaurantDto(Restaurant restaurant);
}
