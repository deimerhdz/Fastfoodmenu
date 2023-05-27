package com.dhernandez.fastfood.domain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhernandez.fastfood.domain.dto.RestaurantDto;
import com.dhernandez.fastfood.domain.repository.IRestaurantRepository;

@Service
@Transactional
public class RestaurantServiceImpl implements IRestaurantService{
	@Autowired
	private IRestaurantRepository resIRestaurantRepository;
	
	@Override
	public RestaurantDto save(RestaurantDto restaurantDto) {
		
		return resIRestaurantRepository.save(restaurantDto);
	}

	@Override
	public Optional<RestaurantDto> findByUserId(Long userId) {
		return resIRestaurantRepository.findByUserId(userId);
	}

}
