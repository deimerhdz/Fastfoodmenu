package com.dhernandez.fastfood.persistence.crud;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhernandez.fastfood.persistence.entities.Category;

/**
 * 
 * @Author Deimer Hernandez <deimerhdz21@gmail.com>
 *	Creado en 15/05/2023
 */
public interface CategoryCrudRepository extends JpaRepository<Category, Long>{
	List<Category> findByUserId(Long id);
}
