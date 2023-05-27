package com.dhernandez.fastfood.domain.services;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhernandez.fastfood.domain.dto.RestaurantTableDto;
import com.dhernandez.fastfood.persistence.RestaurantTableRepository;

/**
 * 
 * @Author Deimer Hernandez <deimerhdz21@gmail.com>
 *	Creado en 27/05/2023
 */

@Service
@Transactional
public class RestaurantTableServiceImpl implements RestaurantTableService{
	@Autowired
	RestaurantTableRepository restaurantTableRepository;
	
	@Override
	public void save(Long quantity,RestaurantTableDto restaurantTableDto) {
		for (int i = 1; i <= quantity; i++) {
			restaurantTableDto.setName("Mesa #"+i);
			restaurantTableRepository.save(restaurantTableDto);
		}
	}
	@Override
	public List<RestaurantTableDto> findByRestaurantId(Long restaurantId) {
		
		return restaurantTableRepository.findByRestaurantId(restaurantId);
	}
	@Override
	public void deleteById(Long id) {
		restaurantTableRepository.deleteById(id);
		
	}

}
