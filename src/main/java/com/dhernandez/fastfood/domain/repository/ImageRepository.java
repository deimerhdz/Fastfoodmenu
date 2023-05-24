package com.dhernandez.fastfood.domain.repository;

import com.dhernandez.fastfood.domain.dto.ImageDto;

public interface ImageRepository {
	ImageDto save(ImageDto image);
	
	void delete(long imageId);
}
