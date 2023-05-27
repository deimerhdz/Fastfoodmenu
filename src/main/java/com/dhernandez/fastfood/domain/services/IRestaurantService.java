package com.dhernandez.fastfood.domain.services;

import java.util.Optional;

import com.dhernandez.fastfood.domain.dto.RestaurantDto;

public interface IRestaurantService {
	RestaurantDto save(RestaurantDto restaurantDto);
	Optional<RestaurantDto> findByUserId(Long userId);
}
