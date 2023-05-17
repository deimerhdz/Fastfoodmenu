package com.dhernandez.fastfood.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @Author Deimer Hernandez <deimerhdz21@gmail.com>
 *	Creado en 15/05/2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Boolean active;
	private Long userId;
	private UserDto user;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}