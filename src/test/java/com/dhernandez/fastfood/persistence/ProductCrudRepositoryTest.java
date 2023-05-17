package com.dhernandez.fastfood.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import com.dhernandez.fastfood.persistence.crud.ProductCrudRepository;
import com.dhernandez.fastfood.persistence.entities.Category;
import com.dhernandez.fastfood.persistence.entities.Product;
import com.dhernandez.fastfood.persistence.entities.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class ProductCrudRepositoryTest {
	@Autowired
	private ProductCrudRepository productCrudRepository;
	private Category category;
	private Product product;
	User user;
	
	@BeforeEach
	void setup() {
	   user = User.builder()
				.id(1l)
				.username("Deimer Hernandez")
				.email("deimerhdz21@gmail.com")
				.password("1233445")
				.createdAt(LocalDateTime.now())
				.updatedAt(LocalDateTime.now())
				.active(true)
				.build();
	   category = Category.builder()
			   				.id(1l)
							.name("Pizzas")
							.active(true)
							.userId(user.getId())
							.user(user)
							.createdAt(LocalDateTime.now())
							.updatedAt(null)
							.build();
	   
	  product = Product.builder()
			  .id(1l)
			  .name("Pizza napolitana")
			  .price(BigDecimal.valueOf(28000))
			  .description("test")
			  .categoryId(category.getId())
			  .category(category)
			  .userId(user.getId())
			  .user(user)
			  .active(true)
			  .createdAt(LocalDateTime.now())
			  .build();
	}
	
	@DisplayName("Test to save a produc")
	@Test
	public void testSaveProduct() {
		
		//given
	 Product product1 = Product.builder()
			  .id(1l)
			  .name("Pizza napolitana")
			  .description("test")
			  .price(BigDecimal.valueOf(28000))
			  .categoryId(category.getId())
			  .category(category)
			  .userId(user.getId())
			  .user(user)
			  .active(true)
			  .createdAt(LocalDateTime.now())
			  .build();
		//when
	  	Product savedProduct = productCrudRepository.save(product1);
		//then
	  	assertThat(savedProduct).isNotNull();
	  	assertThat(savedProduct.getId()).isGreaterThan(0L);
		
	}
	
	@DisplayName("Test to list products")
	@Test
	void testProductList() {
		//given   
	  Product product1 = Product.builder()
			  .id(1l)
			  .name("Pizza napolitana")
			  .description("test")
			  .price(BigDecimal.valueOf(28000))
			  .categoryId(category.getId())
			  .userId(user.getId())
			  .active(true)
			  .createdAt(LocalDateTime.now())
			  .build();
	  productCrudRepository.save(product1);
	  productCrudRepository.save(product);
		//when
	  	List<Product> products = productCrudRepository.findByUserId(1l);
		//then
	  	assertThat(products).isNotNull();
	  	assertThat(products.size()).isEqualTo(2);
	  
	}
	
	@DisplayName("Test to get product by id")
	@Test
	void testGetProductById() {
		//given
		
		Product saveProduct = productCrudRepository.save(product);
		//when
		Product productDB = productCrudRepository.findById(saveProduct.getId()).get();
		//then
		assertThat(productDB).isNotNull();
	}
	
	@DisplayName("Test to update product by id")
	@Test
	void testUpdateProductById() {
		//given
		Product saveProduct = productCrudRepository.save(product);
		//when
		Product productDB = productCrudRepository.findById(saveProduct.getId()).get();
		productDB.setName("Pizza de queso");
		productDB.setPrice(BigDecimal.valueOf(24000));
		productDB.setDescription("test");
		//then
		assertThat(productDB.getName()).isEqualTo("Pizza de queso");
		assertThat(productDB.getPrice()).isEqualTo(BigDecimal.valueOf(24000));
		assertThat(productDB.getDescription()).isEqualTo("test");
	}
	
	@DisplayName("Test to delete product by id")
	@Test
	void testDeleteProductById() {
		//given
		productCrudRepository.save(product);
		//when
		productCrudRepository.deleteById(product.getId());
		Optional<Product> productOptional = productCrudRepository.findById(product.getId());
		//then
		assertThat(productOptional).isEmpty();
	}
	
	
}
