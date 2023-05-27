package com.dhernandez.fastfood.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dhernandez.fastfood.domain.dto.CategoryDto;
import com.dhernandez.fastfood.domain.dto.ProductDto;
import com.dhernandez.fastfood.domain.dto.UserDto;
import com.dhernandez.fastfood.domain.repository.IProductRepository;
import com.dhernandez.fastfood.domain.services.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
	@Mock
	private IProductRepository productRepository;
	@InjectMocks
	private ProductServiceImpl productService;

	private ProductDto product;
	private CategoryDto category;

	private UserDto user;
	
	@BeforeEach
	void setup() {
		   user = UserDto.builder()
					.id(1l)
					.username("Deimer Hernandez")
					.email("deimerhdz21@gmail.com")
					.password("1233445")
					.createdAt(LocalDateTime.now())
					.updatedAt(LocalDateTime.now())
					.active(true)
					.build();
		   category = CategoryDto.builder()
				   				.id(1l)
								.name("Pizzas")
								.active(true)
								.userId(user.getId())
								
								.createdAt(LocalDateTime.now())
								.updatedAt(null)
								.build();
		   
		  product = ProductDto.builder()
				  .id(1l)
				  .name("Pizza napolitana")
				  .price(BigDecimal.valueOf(28000))
				  .description("test")
				  .categoryId(category.getId())
				  .category(category)
				  .userId(user.getId())
				
				  .active(false)
				  .createdAt(LocalDateTime.now())
				  .build();
		
	}
	
	@DisplayName("Test to save product")
	@Test
	void testSaveProduct() {
		//give
		given(productRepository.save(product)).willReturn(product);
		//when
		ProductDto productDto = productService.save(product);
		//then
		assertThat(productDto).isNotNull();
	}
	
	@DisplayName("Test to list products")
	@Test
	void testListProducts() {
		//give
		ProductDto product1 = ProductDto.builder()
				  .id(1l)
				  .name("Pizza napolitana")
				  .price(BigDecimal.valueOf(28000))
				  .description("test")
				  .categoryId(category.getId())
				  .category(category)
				  .userId(user.getId())
				
				  .active(true)
				  .createdAt(LocalDateTime.now())
				  .build();
		given(productRepository.listProductsByUser(1l)).willReturn(List.of(product,product1));
		//when
		List<ProductDto> products = productService.listProductsByUser(1l);
		//then
		assertThat(products).isNotNull();
		assertThat(products.size()).isEqualTo(2);
	}
	
	@DisplayName("Test to list empty products")
	@Test
	void testEmptyListProducts() {
		//give
		given(productRepository.listProductsByUser(1l)).willReturn(Collections.emptyList());
		//when
		List<ProductDto> products = productService.listProductsByUser(1l);
		//then
		assertThat(products).isEmpty();
		assertThat(products.size()).isEqualTo(0);
	}
	
	@DisplayName("Test to get product by id")
	@Test
	void testGetProductById() {
		//give
		given(productRepository.findById(product.getId())).willReturn(Optional.of(product));
		//when
		ProductDto saveProduct = productService.findById(product.getId()).get();
		//then
		assertThat(saveProduct).isNotNull();
	}
	
	@DisplayName("Test to update product")
	@Test
	void testUpdateProduct() {
		//give
		given(productRepository.save(product)).willReturn(product);
		product.setName("test");
		product.setPrice(BigDecimal.valueOf(24000));
		product.setDescription("test description");
		//when
		ProductDto updateProduct = productService.save(product);
		//then
		assertThat(updateProduct.getName()).isEqualTo("test");
		assertThat(updateProduct.getPrice()).isEqualTo(BigDecimal.valueOf(24000));
		assertThat(updateProduct.getDescription()).isEqualTo("test description");
	}
	
	@DisplayName("Test to delete product")
	@Test
	void testDeleteProduct() {
		//give
		willDoNothing().given(productRepository).deleteById(1l);
		
		//when
		productService.deleteById(1l);
		//then
		verify(productRepository,times(1)).deleteById(1l);
	}
	
	@DisplayName("Test to change status product")
	@Test
	void testChangeStatusProduct() {
		//give
		given(productRepository.changeStatus(product.getId(), false)).willReturn(product);
		//when
		ProductDto productDB= productService.changeStatus(product.getId(), false);
		//then
		assertThat(productDB.getActive()).isEqualTo(false);
	}
	
	@DisplayName("Test to search product")
	@Test
	void testSearchProduct() {
		//give
		ProductDto product1 = ProductDto.builder()
		  .id(1l)
		  .name("Pizza queso")
		  .price(BigDecimal.valueOf(28000))
		  .description("test")
		  .categoryId(category.getId())
		  .category(category)
		  .userId(user.getId())
		  
		  .active(true)
		  .createdAt(LocalDateTime.now())
		  .build();
		given(productRepository.searchByNameWithUserId("pizza", 1l)).willReturn(List.of(product,product1));
		//when
		List<ProductDto> foundProducts = productService.searchByNameWithUserId("pizza", 1l);
		//then
		assertThat(foundProducts).isNotNull();
		assertThat(foundProducts.size()).isEqualTo(2);
		assertThat(foundProducts).contains(product);
	}
}
