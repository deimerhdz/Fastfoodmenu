package com.dhernandez.fastfood.domain.repository;

import java.util.Optional;

import com.dhernandez.fastfood.domain.dto.RestaurantDto;


public interface IRestaurantRepository {
	RestaurantDto save(RestaurantDto restaurantDto);
	Optional<RestaurantDto> findByUserId(Long userId);
}
