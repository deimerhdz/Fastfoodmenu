package com.dhernandez.fastfood.domain.services;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dhernandez.fastfood.domain.dto.CategoryDto;
import com.dhernandez.fastfood.domain.dto.ImageDto;
import com.dhernandez.fastfood.domain.dto.UploadDto;
import com.dhernandez.fastfood.domain.repository.ImageRepository;

@Service
public class ImageCategoryService implements IUpload{

	@Autowired
	CategoryService categoryService;
	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	CloudinaryService cloudinaryService;
	@Override
	public UploadDto upload(Long id, MultipartFile image) throws IOException {
		
		Map result;
		Optional<CategoryDto> categoryDto = categoryService.findById(id);
	
		 UploadDto uploadDto = new UploadDto(false,"No se logro subir el archivo, por favor hable con el administrador");
		 if (categoryDto.isPresent()) {
			 CategoryDto categoryDB;
			 CategoryDto category = categoryDto.get();
	            if(category.getImage() !=null ){
	            	category.setImageId(null);
	            	categoryDB = categoryService.save(category);
	            	imageRepository.delete(category.getImage().getId());
	            	cloudinaryService.delete(category.getImage().getImageId());
	            }
	    
	            result = cloudinaryService.uploadImagen(image);
	            ImageDto imageDto = new ImageDto();
	            imageDto.setImageId((String)result.get("public_id"));
	            imageDto.setPathUrl((String)result.get("url"));

	            ImageDto imagenDB = imageRepository.save(imageDto);
	            category.setImageId(imagenDB.getId());
	            category.setImage(imagenDB);
	            categoryDB = categoryService.save(category);
	            uploadDto.setOk(true);
	            uploadDto.setMessage("El archivo fue subido correctamente.");
		 }
		 
		return uploadDto;
	}
}
