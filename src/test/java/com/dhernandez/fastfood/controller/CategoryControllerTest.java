package com.dhernandez.fastfood.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.dhernandez.fastfood.domain.dto.CategoryDto;
import com.dhernandez.fastfood.domain.dto.UserDto;
import com.dhernandez.fastfood.domain.services.CategoryService;
import com.dhernandez.fastfood.web.controllers.CategoryController;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CategoryService categoryService;

	@Autowired
	private ObjectMapper objectMapper;
	@DisplayName("Test to save a category controller")
	@Test
	void TestSaveCategory() throws Exception {
		// given
		UserDto user = UserDto.builder().id(Long.valueOf("1")).username("Deimer Hernandez")
				.email("deimerhdz21@gmail.com").password("1233445").createdAt(LocalDateTime.now())
				.updatedAt(LocalDateTime.now()).active(true).build();
		CategoryDto category = CategoryDto.builder().name("Pizzas").active(true).userId(user.getId()).user(user)
				.createdAt(LocalDateTime.now()).updatedAt(null).build();
		given(categoryService.save(any(CategoryDto.class))).willAnswer((invocation) -> invocation.getArgument(0));
		// when
		ResultActions response = mockMvc.perform(post("/categories/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(category)));

		// then
		response.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.name").value(category.getName()))
				.andExpect(jsonPath("$.active").value(category.getActive()))
				.andExpect(jsonPath("$.userId").value(category.getUserId()));
	}

	@DisplayName("Test to list categories controller")
	@Test
	void testListCategory() throws Exception {
		// given
		List<CategoryDto> listCategory = new ArrayList();
		listCategory.add(CategoryDto.builder().name("Pizzas").active(true).userId(1l).user(UserDto.builder().id(Long.valueOf("1")).username("Deimer Hernandez").email("deimerhdz21@gmail.com").password("1233445").createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).active(true).build()).createdAt(LocalDateTime.now()).updatedAt(null).build());
		listCategory.add(CategoryDto.builder().name("Perros Calientes").active(true).userId(1l).user(UserDto.builder().id(Long.valueOf("1")).username("Deimer Hernandez").email("deimerhdz21@gmail.com").password("1233445").createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).active(true).build()).createdAt(LocalDateTime.now()).updatedAt(null).build());
		listCategory.add(CategoryDto.builder().name("Hamburguesas").active(true).userId(1l).user(UserDto.builder().id(Long.valueOf("1")).username("Deimer Hernandez").email("deimerhdz21@gmail.com").password("1233445").createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).active(true).build()).createdAt(LocalDateTime.now()).updatedAt(null).build());
		listCategory.add(CategoryDto.builder().name("Picadas").active(true).userId(1l).user(UserDto.builder().id(Long.valueOf("1")).username("Deimer Hernandez").email("deimerhdz21@gmail.com").password("1233445").createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).active(true).build()).createdAt(LocalDateTime.now()).updatedAt(null).build());
		given(categoryService.listAllCategories(1l)).willReturn(listCategory);
		// when
		ResultActions response = mockMvc.perform(get("/categories/list-all/1"));
		// then
		response.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.size()").value(listCategory.size()));
	}
	@DisplayName("Test to get category by id controller")
	@Test
	void testGetCategoryById() throws Exception {
		// given
		UserDto user = UserDto.builder().id(Long.valueOf("1")).username("Deimer Hernandez")
				.email("deimerhdz21@gmail.com").password("1233445").createdAt(LocalDateTime.now())
				.updatedAt(LocalDateTime.now()).active(true).build();
		CategoryDto category = CategoryDto.builder().name("Pizzas").active(true).userId(user.getId()).user(user)
				.createdAt(LocalDateTime.now()).updatedAt(null).build();
		given(categoryService.findById(1l)).willReturn(Optional.of(category));
		// when
		ResultActions response = mockMvc.perform(get("/categories/detail/1"));
		// then
		response.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.name").value(category.getName()))
		.andExpect(jsonPath("$.active").value(category.getActive()))
		.andExpect(jsonPath("$.userId").value(category.getUserId()));
	}
	
	@DisplayName("Test to get category by id controller not found")
	@Test
	void testGetCategoryByIdNotFound() throws Exception {
		// given
		given(categoryService.findById(1l)).willReturn(Optional.empty());
		// when
		ResultActions response = mockMvc.perform(get("/categories/detail/1"));
		// then
		response
		.andExpect(status().isNotFound())
		.andDo(print());
	}
	
	@DisplayName("Test to update category controller")
	void testUpdateCategory() throws Exception {
		//give
		UserDto user = UserDto.builder().id(Long.valueOf("1")).username("Deimer Hernandez")
				.email("deimerhdz21@gmail.com").password("1233445").createdAt(LocalDateTime.now())
				.updatedAt(LocalDateTime.now()).active(true).build();
		CategoryDto saveCategoryDB = CategoryDto.builder().name("Pizzas").active(true).userId(user.getId()).user(user)
				.createdAt(LocalDateTime.now()).updatedAt(null).build();
	
		CategoryDto updatecategory = CategoryDto.builder().name("Picadas").active(true).userId(user.getId()).user(user)
				.createdAt(LocalDateTime.now()).updatedAt(null).build();
		given(categoryService.findById(1l)).willReturn(Optional.of(saveCategoryDB));
		given(categoryService.save(any(CategoryDto.class))).willAnswer(invocation -> invocation.getArgument(0));
		//when
		ResultActions response = mockMvc.perform(put("/categories/update/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updatecategory)));
		//then
		
		response.andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$.name").value(updatecategory.getName()))
		.andExpect(jsonPath("$.active").value(updatecategory.getActive()))
		.andExpect(jsonPath("$.userId").value(updatecategory.getUserId()));
	}
	
	@DisplayName("Test to update category controller no null")
	void testUpdateCategoryNoNull() throws Exception {
		//give
		UserDto user = UserDto.builder().id(Long.valueOf("1")).username("Deimer Hernandez")
				.email("deimerhdz21@gmail.com").password("1233445").createdAt(LocalDateTime.now())
				.updatedAt(LocalDateTime.now()).active(true).build();
		CategoryDto saveCategoryDB = CategoryDto.builder().name("Pizzas").active(true).userId(user.getId()).user(user)
				.createdAt(LocalDateTime.now()).updatedAt(null).build();
	
		CategoryDto updatecategory = CategoryDto.builder().name("Picadas").active(true).userId(user.getId()).user(user)
				.createdAt(LocalDateTime.now()).updatedAt(null).build();
		given(categoryService.findById(1l)).willReturn(Optional.empty());
		given(categoryService.save(any(CategoryDto.class))).willAnswer(invocation -> invocation.getArgument(0));
		//when
		ResultActions response = mockMvc.perform(put("/categories/update/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updatecategory)));
		//then
		
		response.andDo(print()).andExpect(status().isNotFound());

	}
	
	@DisplayName("Test to delete category controller ")
	void testDeleteCategory() throws Exception {
		//give
		UserDto user = UserDto.builder().id(Long.valueOf("1")).username("Deimer Hernandez")
				.email("deimerhdz21@gmail.com").password("1233445").createdAt(LocalDateTime.now())
				.updatedAt(LocalDateTime.now()).active(true).build();
		CategoryDto categoryDB = CategoryDto.builder().name("Pizzas").active(true).userId(user.getId()).user(user)
				.createdAt(LocalDateTime.now()).updatedAt(null).build();
	
		
		given(categoryService.findById(1l)).willReturn(Optional.of(categoryDB));
		given(categoryService.deleteById(1l)).willReturn(categoryDB);
		//when
		ResultActions response = mockMvc.perform(delete("/categories/delete/1"));
				
		//then
		response.andDo(print()).andExpect(status().isOk());

	}
}
