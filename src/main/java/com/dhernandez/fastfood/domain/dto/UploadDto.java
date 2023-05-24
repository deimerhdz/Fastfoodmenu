package com.dhernandez.fastfood.domain.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadDto implements Serializable{
	
	private boolean ok;
	private String message;
	
}	
