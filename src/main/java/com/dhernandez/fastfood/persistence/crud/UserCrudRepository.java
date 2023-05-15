package com.dhernandez.fastfood.persistence.crud;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dhernandez.fastfood.persistence.entities.User;
/**
 * 
 * @Author Deimer Hernandez <deimerhdz21@gmail.com>
 *	Creado en 15/05/2023
 */
public interface UserCrudRepository extends JpaRepository<User, Long>{
	
	
	Optional<User> findByEmail(String email);
	
	Boolean existsByEmail(String email);
}
