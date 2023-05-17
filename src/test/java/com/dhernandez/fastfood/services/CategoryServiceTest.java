package com.dhernandez.fastfood.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dhernandez.fastfood.domain.dto.CategoryDto;
import com.dhernandez.fastfood.domain.dto.UserDto;
import com.dhernandez.fastfood.domain.repository.ICategoryRepository;
import com.dhernandez.fastfood.domain.services.CategoryServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
	
	@Mock
	private ICategoryRepository categoryRepository;
	
	@InjectMocks
	private CategoryServiceImpl categoryService;
	
	private CategoryDto category;
	
	@BeforeEach
	void setup() {
		UserDto user = UserDto.builder()
				.id(1l)
				.username("Deimer Hernandez")
				.email("deimerhdz21@gmail.com")
				.password("1233445")
				.createdAt(LocalDateTime.now())
				.updatedAt(LocalDateTime.now())
				.active(true)
				.build();
	   category = CategoryDto.builder()
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
	void testSaveCategory() {
		//given
		given(categoryRepository.save(category)).willReturn(category);
		//when
		CategoryDto saveCategoryDto = categoryService.save(category);
		
		//then
		assertThat(saveCategoryDto).isNotNull();
	}
	
	@DisplayName("Test to list categories")
	@Test
	void testListCategories() {
		//given
		UserDto user = UserDto.builder()
				.id(1l)
				.username("Deimer Hernandez")
				.email("deimerhdz21@gmail.com")
				.password("1233445")
				.createdAt(LocalDateTime.now())
				.updatedAt(LocalDateTime.now())
				.active(false)
				.build();
		CategoryDto category1 = CategoryDto.builder()
							.name("Pizzas")
							.active(true)
							.userId(user.getId())
							.user(user)
							.createdAt(LocalDateTime.now())
							.updatedAt(null)
							.build();
		
		given(categoryRepository.listAllCategories(1l)).willReturn(List.of(category,category1));
		//when
		List<CategoryDto> categories = categoryService.listAllCategories(1l);
		//then
		assertThat(categories).isNotNull();
		assertThat(categories.size()).isEqualTo(2);
	}
	@DisplayName("Test to list empty categories")
	@Test
	void TestListEmptyCategories() {
		//given
		given(categoryRepository.listAllCategories(1l)).willReturn(Collections.emptyList());
		//when
		List<CategoryDto> listCategories = categoryService.listAllCategories(1l);
		
		//then
		assertThat(listCategories.isEmpty());
		assertThat(listCategories.size()).isEqualTo(0);
		
	}
	
	@DisplayName("Test to get category by id")
	@Test
	void testGetCategoryById() {
		//given
		given(categoryRepository.findById(1L)).willReturn(Optional.of(category));
		//when	
		CategoryDto saveCategory = categoryService.findById(1L).get();
		//then
		assertThat(saveCategory).isNotNull();
		
	}
	
	@DisplayName("Test to update category by id")
	@Test
	void testUpdateCategory() {
		//given
		given(categoryRepository.save(category)).willReturn(category);
		category.setName("Picadas");
		//when
		CategoryDto updateCategory =  categoryService.save(category);
		//then
		assertThat(updateCategory.getName()).isEqualTo("Picadas");

	}
	
	@DisplayName("Test to delete category")
	@Test
	void testDeleteCategory() {
		//given
		given(categoryRepository.deleteById(1L)).willReturn(category);
		//when
		CategoryDto deleteCategory = categoryService.deleteById(1L);
		//then
		assertThat(deleteCategory.getId()).isNull();
	}
	
	@DisplayName("Test to change status category")
	@Test
	void testChangeStatusCategory() {
		//given
		given(categoryRepository.changeStatus(1L,false)).willReturn(category);
		//when
		CategoryDto categoryDB = categoryService.changeStatus(1L,false);
		//then
		assertThat(categoryDB.getActive()).isEqualTo(category.getActive());
	}

}
