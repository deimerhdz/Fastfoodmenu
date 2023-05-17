package com.dhernandez.fastfood.domain.repository;

import java.util.List;
import java.util.Optional;

import com.dhernandez.fastfood.domain.dto.CategoryDto;
import com.dhernandez.fastfood.domain.dto.UserDto;
/**
 * 
 * @Author Deimer Hernandez <deimerhdz21@gmail.com>
 *	Creado en 15/05/2023
 */
public interface ICategoryRepository {
	
	List<CategoryDto> listAllCategories(Long userId);
	
	Optional<CategoryDto> findById(Long id);
	
	CategoryDto save(CategoryDto categoryDto);
	
	CategoryDto changeStatus(Long id, Boolean status);
	
	CategoryDto deleteById(Long id);
}
