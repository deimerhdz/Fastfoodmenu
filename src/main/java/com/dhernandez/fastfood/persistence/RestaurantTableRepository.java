package com.dhernandez.fastfood.persistence;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dhernandez.fastfood.domain.dto.RestaurantTableDto;
import com.dhernandez.fastfood.domain.repository.IRestaurantTableRepository;
import com.dhernandez.fastfood.persistence.crud.RestaurantTableCrudRepository;
import com.dhernandez.fastfood.persistence.mapper.RestaurantTableMapper;
/**
 * 
 * @Author Deimer Hernandez <deimerhdz21@gmail.com>
 *	Creado en 27/05/2023
 */
@Repository
public class RestaurantTableRepository implements IRestaurantTableRepository{
	
	
	private Log Logger = LogFactory.getLog(RestaurantTableRepository.class);
	
	private RestaurantTableCrudRepository restaurantTableCrudRepository;
	
	private RestaurantTableMapper mapper;
	
	@Autowired
	public RestaurantTableRepository(RestaurantTableCrudRepository restaurantTableCrudRepository,
			RestaurantTableMapper mapper) {
		this.restaurantTableCrudRepository = restaurantTableCrudRepository;
		this.mapper = mapper;
	}

	@Override
	public RestaurantTableDto save(RestaurantTableDto restaurantTableDto) {
		Logger.info("Accessing the method save(restaurantTableDto)"+restaurantTableDto);
		RestaurantTableDto restauranDB = 
				mapper.toRestaurantDto(restaurantTableCrudRepository.save(mapper.toRestaurant(restaurantTableDto)));
		Logger.info("Ending the method save()");
		return restauranDB;
	}

	@Override
	public List<RestaurantTableDto> findByRestaurantId(Long restaurantId) {
		Logger.info("Ending the method save()");
		List<RestaurantTableDto> tables =mapper.toRestaurantsDto(restaurantTableCrudRepository.findByRestaurantId(restaurantId));
		Logger.info("Ending the method save()");
		return tables;
	}

	@Override
	public void deleteById(Long id) {
		Logger.info("Ending the method save()");
		restaurantTableCrudRepository.deleteById(id);
		Logger.info("Ending the method save()");
	}
	
}
