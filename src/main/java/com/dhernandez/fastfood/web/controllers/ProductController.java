package com.dhernandez.fastfood.web.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dhernandez.fastfood.domain.dto.CategoryDto;
import com.dhernandez.fastfood.domain.dto.ProductDto;
import com.dhernandez.fastfood.domain.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping("/list-by-user/{userId}")
	public ResponseEntity<List<ProductDto>> listProductsByUserId(@PathVariable("userId") Long userId){
		return new ResponseEntity<List<ProductDto>>(productService.listProductsByUser(userId),HttpStatus.OK);	
	}
	

	@GetMapping("/list-by-category/{categoryId}")
	public ResponseEntity<List<ProductDto>> listProductsByCatgeoryId(@PathVariable("categoryId") Long categoryId){
		return new ResponseEntity<List<ProductDto>>(productService.listProductsByCategory(categoryId),HttpStatus.OK);	
	}
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<ProductDto> findById(@PathVariable("id") Long id){
		Optional<ProductDto> product = productService.findById(id);
		if (!product.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProductDto>(product.get(),HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<ProductDto> save(@RequestBody ProductDto productdto){
		return new ResponseEntity<ProductDto>(productService.save(productdto),HttpStatus.CREATED);
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ProductDto> update(@PathVariable("id") Long id, @RequestBody ProductDto productDto) {
		Optional<ProductDto> productDB = productService.findById(id);

		if (!productDB.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		ProductDto product = productDB.get();
		product.setName(productDto.getName());
		product.setCategoryId(productDto.getCategoryId());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setUpdatedAt(LocalDateTime.now());

		return new ResponseEntity<ProductDto>(productService.save(product), HttpStatus.OK);
	}
	

	@PutMapping("/change-status/{id}/{status}")
	public ResponseEntity<ProductDto> chageStatus(@PathVariable("id") Long id,
			@PathVariable("status") Boolean status) {
		Optional<ProductDto> productDB = productService.findById(id);

		if (!productDB.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ProductDto>(productService.changeStatus(id, status), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> chageStatus(@PathVariable("id") Long id) {
		Optional<ProductDto> productDB = productService.findById(id);

		if (!productDB.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		productService.deleteById(id);
		return new ResponseEntity<CategoryDto>(HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<ProductDto>> searchProduct(@RequestParam String name, @RequestParam Long userId){
		return new ResponseEntity<List<ProductDto>>(productService.searchByNameWithUserId(name,userId),HttpStatus.OK);	
	}
	
	
}
