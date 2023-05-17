package com.dhernandez.fastfood.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhernandez.fastfood.domain.dto.ProductDto;
import com.dhernandez.fastfood.domain.repository.IProductRepository;
/**
 * 
 * @Author Deimer Hernandez <deimerhdz21@gmail.com>
 *	Creado en 17/05/2023
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private IProductRepository productRepository;

	@Override
	public List<ProductDto> listProductsByUser(Long userId) {
		return productRepository.listProductsByUser(userId);
	}

	@Override
	public List<ProductDto> listProductsByCategory(Long categoryId) {
		return productRepository.listProductsByCategory(categoryId);
	}

	@Override
	public Optional<ProductDto> findById(Long id) {

		return productRepository.findById(id);
	}

	@Override
	public ProductDto save(ProductDto productDto) {
		return productRepository.save(productDto);
	}

	@Override
	public ProductDto changeStatus(Long id, Boolean status) {
		return productRepository.changeStatus(id, status);
	}

	@Override
	public void deleteById(Long id) {
		productRepository.deleteById(id);
		
	}

	@Override
	public List<ProductDto> searchByNameWithUserId(String name, Long userId) {
		
		return productRepository.searchByNameWithUserId(name, userId);
	}
}
