package com.dhernandez.fastfood.web.controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dhernandez.fastfood.domain.dto.UploadDto;
import com.dhernandez.fastfood.domain.services.UploadService;
import com.dhernandez.fastfood.web.exceptions.ImageNoValidException;

@RestController
@RequestMapping("/uploads")
public class UploadController {

	@Autowired
	UploadService uploadService;
	
	@GetMapping("/{type}/{id}")
	public ResponseEntity<UploadDto> uploadImage(
			@PathVariable("type") String type,
			@PathVariable("id") Long id,
			@PathVariable("image") MultipartFile image
			) throws IOException{
		
			BufferedImage bi = ImageIO.read(image.getInputStream());
	        if(bi == null){
	            throw new ImageNoValidException("Imagen no valida");
	        }
	        
	        UploadDto uploadDto = uploadService.upload(type, id, image);
	        if(uploadDto == null) {
	        	return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
	        } 
	        return new ResponseEntity(uploadDto,HttpStatus.OK);
	
	}
}
