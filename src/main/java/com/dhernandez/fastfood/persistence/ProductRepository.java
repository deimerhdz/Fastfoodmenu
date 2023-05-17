package com.dhernandez.fastfood.persistence;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dhernandez.fastfood.domain.dto.ProductDto;
import com.dhernandez.fastfood.domain.repository.IProductRepository;
import com.dhernandez.fastfood.persistence.crud.ProductCrudRepository;
import com.dhernandez.fastfood.persistence.entities.Product;
import com.dhernandez.fastfood.persistence.mapper.ProductMapper;

/**
 * 
 * @Author Deimer Hernandez <deimerhdz21@gmail.com>
 * Creado en 17/05/2023
 */
@Repository
public class ProductRepository implements IProductRepository {
	
	private Log Logger = LogFactory.getLog(UserRepository.class);
	
	private ProductCrudRepository produtCrudRepository;
	
	private ProductMapper productMapper;
	
	@Autowired
	public ProductRepository(ProductCrudRepository procutCrudRepository, ProductMapper productMapper) {
		super();
		this.produtCrudRepository = procutCrudRepository;
		this.productMapper = productMapper;
	}

	@Override
	public List<ProductDto> listProductsByUser(Long userId) {
		Logger.info("Accessing the method listAllProducts()");
		List<ProductDto> products = this.productMapper.toProductsDto(this.produtCrudRepository.findByUserId(userId));
		Logger.info("Ending the method listAllProducts()");
		return products;
	}

	@Override
	public List<ProductDto> listProductsByCategory(Long categoryId) {
		Logger.info("Accessing the method listAllProducts(Long categoryId)");
		List<ProductDto> products = this.productMapper.toProductsDto(this.produtCrudRepository.findByCategoryId(categoryId));
		Logger.info("Ending the method listAllProducts(Long categoryId)");
		return products;
	}

	@Override
	public Optional<ProductDto> findById(Long id) {
		Logger.info("Accessing the method findById(Long id)");
		Optional<ProductDto> productDto = this.produtCrudRepository.findById(id)
				.map(product -> this.productMapper.toProductDto(product));
		Logger.info("Ending the method findById(Long id)");
		return productDto;
	}

	@Override
	public ProductDto save(ProductDto productDto) {
		Logger.info("Accessing the method save(ProductDto productDto)"+productDto);
		ProductDto productDB =this.productMapper.toProductDto(this.produtCrudRepository.save(this.productMapper.toProduct(productDto)));
		Logger.info("Ending the method findById(Long id)");
		return productDB;
	}

	@Override
	public ProductDto changeStatus(Long id, Boolean status) {
		Logger.info("Accessing the method changeStatus(Long id, Boolean status)");
		 Optional<ProductDto> productDB = this.findById(id);
		 ProductDto productChange = new ProductDto();
		    if(productDB.isPresent()) {
		    	Product product = productMapper.toProduct(productDB.get());
		    	product.setActive(status);
		    	product.setUpdatedAt(LocalDateTime.now());
		    	productChange = productMapper.toProductDto(this.produtCrudRepository.save(product));
		    }
		Logger.info("Ending the method changeStatus(Long id, Boolean status)");
		return productChange;
	}

	@Override
	public void deleteById(Long id) {
		Logger.info("Accessing the method deleteById(Long id)");
		Optional<ProductDto> productDB = this.findById(id);
		if(productDB.isPresent()) {
			this.produtCrudRepository.deleteById(id);
		 }
		Logger.info("Ending the method deleteById(Long id)");
	}

	@Override
	public List<ProductDto> searchByNameWithUserId(String name, Long userId) {
		Logger.info("Accessing the method searchByNameWithUserId() {"+name+"} {"+userId+"}");
		List<ProductDto> products = this.productMapper.toProductsDto(this.produtCrudRepository.findByUserIdAndNameStartsWith(userId,name));
		Logger.info("Ending the method searchByNameWithUserId()");
		return products;
	}

}
