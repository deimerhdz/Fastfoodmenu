package com.dhernandez.fastfood.domain.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.dhernandez.fastfood.domain.dto.UploadDto;

public interface IUpload {
	public UploadDto upload(Long id, MultipartFile image) throws IOException; 
	
}
