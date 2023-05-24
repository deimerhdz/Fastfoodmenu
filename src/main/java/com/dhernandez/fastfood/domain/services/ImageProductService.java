package com.dhernandez.fastfood.domain.services;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dhernandez.fastfood.domain.dto.ImageDto;
import com.dhernandez.fastfood.domain.dto.ProductDto;
import com.dhernandez.fastfood.domain.dto.UploadDto;
import com.dhernandez.fastfood.domain.repository.ImageRepository;

@Service
public class ImageProductService implements IUpload{
	
	@Autowired
	ProductService productService;
	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	CloudinaryService cloudinaryService;
	@Override
	public UploadDto upload( Long id, MultipartFile image) throws IOException {
		
		Map result;
		Optional<ProductDto> productDto = productService.findById(id);
	
		 UploadDto uploadDto = new UploadDto(false,"No se logro subir el archivo, por favor hable con el administrador");
		 if (productDto.isPresent()) {
			 ProductDto productDB;
			 ProductDto product = productDto.get();
	            if(product.getImage() !=null ){
	            	product.setImageId(null);
	            	productDB = productService.save(product);
	            	imageRepository.delete(product.getImage().getId());
	            	cloudinaryService.delete(product.getImage().getImageId());
	            }
	    
	            result = cloudinaryService.uploadImagen(image);
	            ImageDto imageDto = new ImageDto();
	            imageDto.setImageId((String)result.get("public_id"));
	            imageDto.setPathUrl((String)result.get("url"));

	            ImageDto imagenDB = imageRepository.save(imageDto);
	            product.setImageId(imagenDB.getId());
	            product.setImage(imagenDB);
	            productDB = productService.save(product);
	            uploadDto.setOk(true);
	            uploadDto.setMessage("El archivo fue subido correctamente.");
		 }
		 
		return uploadDto;
	}
}

