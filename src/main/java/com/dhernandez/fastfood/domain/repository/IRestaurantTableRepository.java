package com.dhernandez.fastfood.domain.repository;

import java.util.List;


import com.dhernandez.fastfood.domain.dto.RestaurantTableDto;

public interface IRestaurantTableRepository {
	
	RestaurantTableDto save(RestaurantTableDto restaurantTableDto);
	List<RestaurantTableDto> findByRestaurantId(Long restaurantId);
	void deleteById(Long id);
}
