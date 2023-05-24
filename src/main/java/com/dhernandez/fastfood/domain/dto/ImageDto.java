package com.dhernandez.fastfood.domain.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String imageId;
	
	private String pathUrl;
}
