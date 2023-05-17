package com.dhernandez.fastfood.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhernandez.fastfood.domain.dto.CategoryDto;
import com.dhernandez.fastfood.domain.repository.ICategoryRepository;
/**
 * 
 * @Author Deimer Hernandez <deimerhdz21@gmail.com>
 *	Creado en 16/05/2023
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	ICategoryRepository categoryRepository;
	@Override
	public List<CategoryDto> listAllCategories(Long userId) {
		return categoryRepository.listAllCategories(userId);
	}

	@Override
	public Optional<CategoryDto> findById(Long id) {
		return categoryRepository.findById(id);
	}

	@Override
	public CategoryDto save(CategoryDto categoryDto) {
		return categoryRepository.save(categoryDto);
	}

	@Override
	public CategoryDto changeStatus(Long id, Boolean status) {
		return categoryRepository.changeStatus(id, status);
	}

	@Override
	public CategoryDto deleteById(Long id) {
		return categoryRepository.deleteById(id);
	}

}
