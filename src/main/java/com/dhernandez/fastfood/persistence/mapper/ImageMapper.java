package com.dhernandez.fastfood.persistence.mapper;

import org.mapstruct.Mapper;

import com.dhernandez.fastfood.domain.dto.ImageDto;
import com.dhernandez.fastfood.persistence.entities.Image;



@Mapper(componentModel = "Spring")
public interface ImageMapper {
 
	ImageDto toImagenDto(Image imagen);

    Image toImage(ImageDto imagenDto);
}
