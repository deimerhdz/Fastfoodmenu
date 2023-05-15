package com.dhernandez.fastfood.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
public class ValidationErrorDto {
	private String field;
	private String error;
}
