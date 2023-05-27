package com.dhernandez.fastfood.persistence.crud;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dhernandez.fastfood.persistence.entities.RestaurantTable;
/**
 * 
 * @Author Deimer Hernandez <deimerhdz21@gmail.com>
 *	Creado en 27/05/2023
 */
public interface RestaurantTableCrudRepository extends JpaRepository<RestaurantTable, Long>{
	
	List<RestaurantTable> findByRestaurantId(Long restaurantId);
}
