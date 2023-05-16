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
import org.springframework.web.bind.annotation.RestController;

import com.dhernandez.fastfood.domain.dto.CategoryDto;
import com.dhernandez.fastfood.domain.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/list-all/{id}")
	public ResponseEntity<List<CategoryDto>> findAllByUserId(@PathVariable("id") Long id) {
		return new ResponseEntity<>(categoryService.listAllCategories(id), HttpStatus.OK);
	}

	@GetMapping("/detail/{id}")
	public ResponseEntity<CategoryDto> findById(@PathVariable("id") Long id) {
		Optional<CategoryDto> category = categoryService.findById(id);
		if (!category.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CategoryDto>(category.get(), HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<CategoryDto> save(@RequestBody CategoryDto categoryDto) {
		return new ResponseEntity<CategoryDto>(categoryService.save(categoryDto), HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<CategoryDto> update(@PathVariable("id") Long id, @RequestBody CategoryDto categoryDto) {
		Optional<CategoryDto> categoryDB = categoryService.findById(id);

		if (!categoryDB.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		CategoryDto category = categoryDB.get();
		category.setName(categoryDto.getName());
		category.setUpdatedAt(LocalDateTime.now());

		return new ResponseEntity<CategoryDto>(categoryService.save(category), HttpStatus.OK);
	}

	@PutMapping("/change-status/{id}/{status}")
	public ResponseEntity<CategoryDto> chageStatus(@PathVariable("id") Long id,
			@PathVariable("status") Boolean status) {
		Optional<CategoryDto> categoryDB = categoryService.findById(id);

		if (!categoryDB.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CategoryDto>(categoryService.changeStatus(id, status), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<CategoryDto> chageStatus(@PathVariable("id") Long id) {
		Optional<CategoryDto> categoryDB = categoryService.findById(id);

		if (!categoryDB.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CategoryDto>(categoryService.deleteById(id), HttpStatus.OK);
	}

}
