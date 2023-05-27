package com.dhernandez.fastfood.domain.dto;

import java.io.Serializable;
import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @Author Deimer Hernandez <deimerhdz21@gmail.com>
 *	Creado en 27/05/2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantTableDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Long restaurantId;
	private LocalDateTime createdAt;
}
