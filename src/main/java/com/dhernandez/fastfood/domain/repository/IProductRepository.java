package com.dhernandez.fastfood.domain.repository;

import java.util.List;
import java.util.Optional;

import com.dhernandez.fastfood.domain.dto.ProductDto;
/**
 * 
 * @Author Deimer Hernandez <deimerhdz21@gmail.com>
 *	Creado en 17/05/2023
 */
public interface IProductRepository {
	
	List<ProductDto> listProductsByUser(Long userId);
	
	List<ProductDto> listProductsByCategory(Long categoryId);
	
	List<ProductDto> searchByNameWithUserId(String  name,Long userId);
	
	Optional<ProductDto> findById(Long id);
	
	ProductDto save(ProductDto productDto);
	
	ProductDto changeStatus(Long id, Boolean status);
	
	void deleteById(Long id);
}
