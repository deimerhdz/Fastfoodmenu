package com.dhernandez.fastfood.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.dhernandez.fastfood.domain.dto.RestaurantTableDto;

import com.dhernandez.fastfood.persistence.entities.RestaurantTable;
@Mapper(componentModel = "Spring",uses = {UserMapper.class,CategoryMapper.class})
public interface RestaurantTableMapper {
	
	
	List<RestaurantTableDto> toRestaurantsDto(List<RestaurantTable> products);
	RestaurantTable toRestaurant(RestaurantTableDto restaurantDto);
	
	RestaurantTableDto toRestaurantDto(RestaurantTable restaurant);
}
