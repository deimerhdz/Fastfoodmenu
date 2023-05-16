package com.dhernandez.fastfood.domain.services;

import java.util.List;
import java.util.Optional;

import com.dhernandez.fastfood.domain.dto.CategoryDto;

public interface CategoryService {
	List<CategoryDto> listAllCategories(Long userId);
	
	Optional<CategoryDto> findById(Long id);
	
	CategoryDto save(CategoryDto categoryDto);
	
	CategoryDto changeStatus(Long id, Boolean status);
	
	CategoryDto deleteById(Long id);
}
