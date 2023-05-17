package com.dhernandez.fastfood.persistence;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.dhernandez.fastfood.domain.dto.CategoryDto;
import com.dhernandez.fastfood.domain.repository.ICategoryRepository;
import com.dhernandez.fastfood.persistence.crud.CategoryCrudRepository;
import com.dhernandez.fastfood.persistence.entities.Category;
import com.dhernandez.fastfood.persistence.mapper.CategoryMapper;

/**
 * 
 * @Author Deimer Hernandez <deimerhdz21@gmail.com>
 *	Creado en 15/05/2023
 */
@Repository
public class CategoryRepository implements ICategoryRepository{
	private Log Logger = LogFactory.getLog(UserRepository.class);
	
	private CategoryCrudRepository categoryCrudRepository;
	
	private CategoryMapper categoryMapper;
	
	@Autowired
	public CategoryRepository(CategoryCrudRepository categoryCrudRepository, CategoryMapper categoryMapper) {
		super();
		this.categoryCrudRepository = categoryCrudRepository;
		this.categoryMapper = categoryMapper;
	}

	@Override
	public List<CategoryDto> listAllCategories(Long userId) {
		Logger.info("Accessing the method listAllCategories()");
		List<CategoryDto> categories = this.categoryMapper.toCategoriesDto(this.categoryCrudRepository.findByUserId(userId));
		Logger.info("Ending the method listAllCategories()");
		return categories;
	}

	@Override
	public Optional<CategoryDto> findById(Long id) {
		Logger.info("Accessing the method findById(Long id)");
		 Optional<CategoryDto> category = this.categoryCrudRepository.findById(id)
					.map(categoryDB -> this.categoryMapper.toCategoryDto(categoryDB));
		Logger.info("Ending the method findById(Long id)");
		return category;
	}

	@Override
	public CategoryDto save(CategoryDto categoryDto) {
		Logger.info("Accessing the method save(CategoryDto categoryDto)"+categoryDto);
		CategoryDto userDB =this.categoryMapper.toCategoryDto(this.categoryCrudRepository.save(this.categoryMapper.toCategory(categoryDto))) ;
		Logger.info("Ending the method save(CategoryDto categoryDto)");
		return userDB;
	}

	@Override
	public CategoryDto changeStatus(Long id, Boolean status) {
		Logger.info("Accessing the method  changeStatus(Long id, Boolean status)");
		 Optional<CategoryDto> categoryDB = this.findById(id);
		 CategoryDto categoryChange = new CategoryDto();
		    if(categoryDB.isPresent()) {
		    	Category category = categoryMapper.toCategory(categoryDB.get());
		    	category.setActive(status);
		    	category.setUpdatedAt(LocalDateTime.now());
		    	categoryChange = categoryMapper.toCategoryDto(this.categoryCrudRepository.save(category));
		    }
		Logger.info("Ending the method  changeStatus(Long id, Boolean status)");
		return categoryChange;
	}

	@Override
	public CategoryDto deleteById(Long id) {
		Logger.info("Accessing the method deleteById(Long id)");
		Optional<CategoryDto> categoryDB = this.findById(id);
		if(categoryDB.isPresent()) {
			  categoryCrudRepository.deleteById(id);
		 }
		Logger.info("Ending the method deleteById(Long id)");
		return categoryDB.orElse(new CategoryDto());
	}

}
