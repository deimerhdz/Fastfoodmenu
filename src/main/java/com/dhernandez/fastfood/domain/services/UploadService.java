package com.dhernandez.fastfood.domain.services;


import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dhernandez.fastfood.domain.dto.CategoryDto;
import com.dhernandez.fastfood.domain.dto.ProductDto;
import com.dhernandez.fastfood.domain.dto.UploadDto;

@Service
public class UploadService {
	

	
	@Autowired
	ImageProductService imagenProductService;
	@Autowired
	ImageCategoryService imagenCategoryService;
	public UploadDto upload(String type, Long id, MultipartFile image) throws IOException {
		UploadDto upload = null;
		switch (type) {
		case "category":
			upload = imagenCategoryService.upload(id,image);
			break;
		case "product":
			upload = imagenProductService.upload(id,image);
			break;
		}
		return upload;
	}

}
