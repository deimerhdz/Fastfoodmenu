package com.dhernandez.fastfood.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @Author Deimer Hernandez <deimerhdz21@gmail.com>
 *	Creado en 17/05/2023
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestaurantDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String description;
	private String address;
	private String telephone;
	private Long iconId;
	private ImageDto icon;
	private Long userId;
	private LocalDateTime createdAt;
	
	
}
