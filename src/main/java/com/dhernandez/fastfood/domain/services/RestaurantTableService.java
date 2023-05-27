package com.dhernandez.fastfood.domain.services;

import java.util.List;

import com.dhernandez.fastfood.domain.dto.RestaurantTableDto;
/**
 * 
 * @Author Deimer Hernandez <deimerhdz21@gmail.com>
 *	Creado en 27/05/2023
 */
public interface RestaurantTableService {
	void save(Long quantity,RestaurantTableDto restaurantTableDto);
	List<RestaurantTableDto> findByRestaurantId(Long restaurantId);
	void deleteById(Long id);
}
