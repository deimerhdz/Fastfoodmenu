package com.dhernandez.fastfood.persistence.crud;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dhernandez.fastfood.persistence.entities.Restaurant;

public interface RestaurantCrudRepository extends JpaRepository<Restaurant, Long>{
	
	Optional<Restaurant> findByUserId(Long userId);
}
