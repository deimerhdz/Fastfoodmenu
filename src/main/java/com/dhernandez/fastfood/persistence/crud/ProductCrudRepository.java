package com.dhernandez.fastfood.persistence.crud;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhernandez.fastfood.persistence.entities.Product;

public interface ProductCrudRepository extends JpaRepository<Product, Long>{
	
	List<Product> findByUserId(Long userId);
	
	List<Product> findByCategoryId(Long categoryId);
	
	List<Product> findByUserIdAndNameStartsWith(Long userId,String name);
}
