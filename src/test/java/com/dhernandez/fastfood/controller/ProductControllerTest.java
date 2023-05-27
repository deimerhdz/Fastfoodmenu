package com.dhernandez.fastfood.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.BDDMockito.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.dhernandez.fastfood.domain.dto.CategoryDto;
import com.dhernandez.fastfood.domain.dto.ProductDto;
import com.dhernandez.fastfood.domain.dto.UserDto;
import com.dhernandez.fastfood.domain.services.ProductService;
import com.dhernandez.fastfood.web.controllers.ProductController;
import com.dhernandez.fastfood.web.security.JwtTokenFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;
	
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	JwtTokenFilter JwtTokenFilter;
	
	@DisplayName("Test to save a product controller")
	@Test
	void TestSaveCategory() throws Exception {
		// given
		  UserDto user = UserDto.builder().id(1l).username("Deimer Hernandez").email("deimerhdz21@gmail.com")
					.password("1233445").createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).active(true)
					.build();
		 CategoryDto  category = CategoryDto.builder().id(1l).name("Pizzas").active(true)
								.userId(user.getId()).createdAt(LocalDateTime.now()).updatedAt(null)
								.build();
		   
		 ProductDto product = ProductDto.builder().id(1l).name("Pizza napolitana").price(BigDecimal.valueOf(28000)).description("test")
				  .categoryId(category.getId()).category(category).userId(user.getId())
				  .active(true).createdAt(LocalDateTime.now())
				  .build();
		given(productService.save(any(ProductDto.class))).willAnswer((invocation) -> invocation.getArgument(0));
		// when
		ResultActions response = mockMvc.perform(post("/products/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(product)));

		// then
		response.andDo(print()).andExpect(status().isCreated())
				.andExpect(jsonPath("$.name").value(product.getName()))
				.andExpect(jsonPath("$.description").value(product.getDescription()))
				.andExpect(jsonPath("$.price").value(product.getPrice()))
				.andExpect(jsonPath("$.categoryId").value(product.getCategoryId()))
				.andExpect(jsonPath("$.active").value(product.getActive()))
				.andExpect(jsonPath("$.userId").value(product.getUserId()));
	}
	@DisplayName("Test to list products controller")
	@Test
	void testListCategory() throws Exception {
		// given
		List<ProductDto> listProduct = new ArrayList<>();
		
		 UserDto user = UserDto.builder().id(1l).username("Deimer Hernandez").email("deimerhdz21@gmail.com")
					.password("1233445").createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).active(true)
					.build();
		 CategoryDto  category = CategoryDto.builder().id(1l).name("Pizzas").active(true)
								.userId(user.getId()).createdAt(LocalDateTime.now()).updatedAt(null)
								.build();
		   
		 ProductDto product1 = ProductDto.builder().id(1l).name("Pizza napolitana").price(BigDecimal.valueOf(28000)).description("test")
				  .categoryId(category.getId()).category(category).userId(user.getId())
				  .active(true).createdAt(LocalDateTime.now())
				  .build();
		 ProductDto product2 = ProductDto.builder().id(1l).name("Pizza queso").price(BigDecimal.valueOf(28000)).description("test")
				  .categoryId(category.getId()).category(category).userId(user.getId())
				  .active(true).createdAt(LocalDateTime.now())
				  .build();
		 listProduct.add(product1);
		 listProduct.add(product2);
		given(productService.listProductsByUser(1l)).willReturn(listProduct);
		// when
		ResultActions response = mockMvc.perform(get("/products/list-by-user/1"));
		// then
		response.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.size()").value(listProduct.size()));
	}
	
	@DisplayName("Test to get product by id controller")
	@Test
	void testGetCategoryById() throws Exception {
		// given
		UserDto user = UserDto.builder().id(1l).username("Deimer Hernandez").email("deimerhdz21@gmail.com")
				.password("1233445").createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).active(true)
				.build();
	 CategoryDto  category = CategoryDto.builder().id(1l).name("Pizzas").active(true)
							.userId(user.getId()).createdAt(LocalDateTime.now()).updatedAt(null)
							.build();
	   
	 ProductDto product1 = ProductDto.builder().id(1l).name("Pizza napolitana").price(BigDecimal.valueOf(28000)).description("test")
			  .categoryId(category.getId()).category(category).userId(user.getId())
			  .active(true).createdAt(LocalDateTime.now())
			  .build();
		given(productService.findById(1l)).willReturn(Optional.of(product1));
		// when
		ResultActions response = mockMvc.perform(get("/products/detail/1"));
		// then
		response.andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$.name").value(product1.getName()))
		.andExpect(jsonPath("$.description").value(product1.getDescription()))
		.andExpect(jsonPath("$.price").value(product1.getPrice()))
		.andExpect(jsonPath("$.categoryId").value(product1.getCategoryId()))
		.andExpect(jsonPath("$.active").value(product1.getActive()))
		.andExpect(jsonPath("$.userId").value(product1.getUserId()));
	}
	
	@DisplayName("Test to get product by id controller not found")
	@Test
	void testGetCategoryByIdNotFound() throws Exception {
		// given
		given(productService.findById(1l)).willReturn(Optional.empty());
		// when
		ResultActions response = mockMvc.perform(get("/products/detail/1"));
		// then
		response
		.andExpect(status().isNotFound())
		.andDo(print());
	}
	
	@DisplayName("Test to update product controller")
	@Test
	void testUpdateCategory() throws Exception {
		//give
		UserDto user = UserDto.builder().id(1l).username("Deimer Hernandez").email("deimerhdz21@gmail.com")
				.password("1233445").createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).active(true)
				.build();
	   CategoryDto  category = CategoryDto.builder().id(1l).name("Pizzas").active(true)
							.userId(user.getId()).createdAt(LocalDateTime.now()).updatedAt(null)
							.build();
	   
	  ProductDto saveProduct = ProductDto.builder().id(1l).name("Pizza napolitana").price(BigDecimal.valueOf(28000)).description("test")
			  .categoryId(category.getId()).category(category).userId(user.getId())
			 .active(true).createdAt(LocalDateTime.now())
			  .build();
	 
	  ProductDto updatedProduct = ProductDto.builder().id(1l).name("Pizza napolitana").price(BigDecimal.valueOf(28000)).description("test")
			  .categoryId(category.getId()).category(category).userId(user.getId())
			  .active(true).createdAt(LocalDateTime.now())
			  .build();
		given(productService.findById(1l)).willReturn(Optional.of(saveProduct));
		
		given(productService.save(any(ProductDto.class))).willAnswer(invocation -> invocation.getArgument(0));
		//when
		ResultActions response = mockMvc.perform(put("/products/update/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updatedProduct)));
		//then
		
		response.andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$.name").value(updatedProduct.getName()))
		.andExpect(jsonPath("$.description").value(updatedProduct.getDescription()))
		.andExpect(jsonPath("$.price").value(updatedProduct.getPrice()))
		.andExpect(jsonPath("$.categoryId").value(updatedProduct.getCategoryId()))
		.andExpect(jsonPath("$.active").value(updatedProduct.getActive()))
		.andExpect(jsonPath("$.userId").value(updatedProduct.getUserId()));
	}
	
	
	@DisplayName("Test to update product null controller ")
	@Test
	void testUpdateCategoryNull() throws Exception {
		//give
		UserDto user = UserDto.builder().id(1l).username("Deimer Hernandez").email("deimerhdz21@gmail.com")
				.password("1233445").createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).active(true)
				.build();
	   CategoryDto  category = CategoryDto.builder().id(1l).name("Pizzas").active(true)
							.userId(user.getId()).createdAt(LocalDateTime.now()).updatedAt(null)
							.build();

	  ProductDto updatedProduct = ProductDto.builder().id(1l).name("Pizza napolitana").price(BigDecimal.valueOf(28000)).description("test")
			  .categoryId(category.getId()).category(category).userId(user.getId())
			  .active(true).createdAt(LocalDateTime.now())
			  .build();
		given(productService.findById(1l)).willReturn(Optional.empty());
		
		given(productService.save(any(ProductDto.class))).willAnswer(invocation -> invocation.getArgument(0));
		//when
		ResultActions response = mockMvc.perform(put("/products/update/{id}",1l)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updatedProduct)));
		//then
		
		response.andDo(print()).andExpect(status().isNotFound());
	}
	
	@DisplayName("Test to delete product controller ")
	@Test
	void testDeleteCategory() throws Exception {
		//give
		 UserDto user = UserDto.builder().id(1l).username("Deimer Hernandez").email("deimerhdz21@gmail.com")
					.password("1233445").createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).active(true)
					.build();
		 CategoryDto  category = CategoryDto.builder().id(1l).name("Pizzas").active(true)
								.userId(user.getId()).createdAt(LocalDateTime.now()).updatedAt(null)
								.build();
		   
		 ProductDto product1 = ProductDto.builder().id(1l).name("Pizza napolitana").price(BigDecimal.valueOf(28000)).description("test")
				  .categoryId(category.getId()).category(category).userId(user.getId())
				  .build();
		
		 given(productService.findById(1l)).willReturn(Optional.of(product1));
		willDoNothing().given(productService).deleteById(product1.getId());
		
		//when
		ResultActions response = mockMvc.perform(delete("/products/delete/{id}",product1.getId()));
				
		//then
		response.andDo(print()).andExpect(status().isOk());

	}
}
