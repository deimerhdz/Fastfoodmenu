package com.dhernandez.fastfood.persistence;

import static org.assertj.core.api.Assertions.assertThat;

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

import com.dhernandez.fastfood.persistence.crud.CategoryCrudRepository;
import com.dhernandez.fastfood.persistence.entities.Category;
import com.dhernandez.fastfood.persistence.entities.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class CategoryCrudRepositoryTest {
	@Autowired
	private CategoryCrudRepository categoryCrudRepository;
	private Category category;
	@BeforeEach
	void setup() {
		User user = User.builder()
				.id(1l)
				.username("Deimer Hernandez")
				.email("deimerhdz21@gmail.com")
				.password("1233445")
				.createdAt(LocalDateTime.now())
				.updatedAt(LocalDateTime.now())
				.active(true)
				.build();
	   category = Category.builder()
							.name("Pizzas")
							.active(true)
							.userId(user.getId())
							.user(user)
							.createdAt(LocalDateTime.now())
							.updatedAt(null)
							.build();
	}
	@DisplayName("Test to save a category")
	@Test
	public void testSaveCategory() {
		//given 
		User user = User.builder()
				.id(1l)
				.username("Deimer Hernandez")
				.email("deimerhdz21@gmail.com")
				.password("1233445")
				.createdAt(LocalDateTime.now())
				.updatedAt(LocalDateTime.now())
				.active(true)
				.build();
		Category category = Category.builder()
							.name("Perros Calientes")
							.active(true)
							.userId(user.getId())
							.user(user)
							.createdAt(LocalDateTime.now())
							.updatedAt(null)
							.build();
		//when 
		
		Category savedCategory = categoryCrudRepository.save(category);
		//then 
		assertThat(savedCategory).isNotNull();
		assertThat(savedCategory.getId()).isGreaterThan(0L);
	}
	
	
	@DisplayName("Test to list categories")
	@Test
	void testCategoryList() {
		//given 
		User user = User.builder()
				.id(1l)
				.username("Deimer Hernandez")
				.email("deimerhdz21@gmail.com")
				.password("1233445")
				.createdAt(LocalDateTime.now())
				.updatedAt(LocalDateTime.now())
				.active(true)
				.build();
		Category category1 = Category.builder()
							.name("Perros Calientes")
							.active(true)
							.userId(user.getId())
							.user(user)
							.createdAt(LocalDateTime.now())
							.updatedAt(null)
							.build();
		categoryCrudRepository.save(category1);
		categoryCrudRepository.save(category);
		//when 
			List<Category> categories = categoryCrudRepository.findByUserId(1l);
		//then 
			assertThat(categories).isNotNull();
		
	}
	
	@DisplayName("Test to get category by id")
	@Test
	void  testGetCategoryById() {
		//given 
			categoryCrudRepository.save(category);
		//when 
			Category categoryDB = categoryCrudRepository.findById(category.getId()).get();
			
		//then 
		assertThat(categoryDB).isNotNull();
		
	}
	@DisplayName("Test to update category by id")
	@Test
	void testUpdateCategory() {
		//given 
		categoryCrudRepository.save(category);
		
		//when
		Category savedCategory = categoryCrudRepository.findById(category.getId()).get();
		savedCategory.setName("Arepas");
		
		Category categoryUpdate = categoryCrudRepository.save(savedCategory);
		//then
		assertThat(categoryUpdate.getName()).isEqualTo("Arepas");
		
	}
	
	
	@Test
	void testDeleteCategory(){
		//given 
		categoryCrudRepository.save(category);
		//when
		categoryCrudRepository.deleteById(category.getId());
		Optional<Category> optionalCategory = categoryCrudRepository.findById(category.getId());
		//then
		assertThat(optionalCategory).isEmpty();
	}
}
