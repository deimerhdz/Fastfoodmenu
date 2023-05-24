package com.dhernandez.fastfood.persistence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dhernandez.fastfood.domain.dto.ImageDto;
import com.dhernandez.fastfood.persistence.crud.ImageCrudRepository;
import com.dhernandez.fastfood.persistence.mapper.ImageMapper;

@Repository
public class ImageRepository implements com.dhernandez.fastfood.domain.repository.ImageRepository{
	private Log Logger = LogFactory.getLog(ImageRepository.class);
	
	private ImageMapper mapper;
	
	private ImageCrudRepository imageCrudRepository;
	
	@Autowired
	public ImageRepository(ImageMapper mapper, ImageCrudRepository imageCrudRepository) {
		super();
		this.mapper = mapper;
		this.imageCrudRepository = imageCrudRepository;
	}

	@Override
	public ImageDto save(ImageDto image) {
		Logger.info("Accessing the method save()"+image);
		 ImageDto imagen = mapper.toImagenDto(this.imageCrudRepository.save(mapper.toImage(image)));
		Logger.info("Ending the method save()"+image);
		return imagen;
	}

	@Override
	public void delete(long imageId) {
		Logger.info("Accessing the method delete()");
		this.imageCrudRepository.deleteById(imageId);
		Logger.info("Ending the method delete()");
	}

}
