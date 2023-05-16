package com.dhernandez.fastfood.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.dhernandez.fastfood.domain.dto.CategoryDto;
import com.dhernandez.fastfood.persistence.entities.Category;


/**
 * 
 * @Author Deimer Hernandez <deimerhdz21@gmail.com>
 *	Creado en 15/05/2023
 */
@Mapper(componentModel = "Spring",uses = {UserMapper.class})
public interface CategoryMapper {
	
	List<CategoryDto> toCategoriesDto(List<Category> categories);
	
	CategoryDto toCategoryDto(Category user);
	
	Category toCategory(CategoryDto categoryDto);
}
