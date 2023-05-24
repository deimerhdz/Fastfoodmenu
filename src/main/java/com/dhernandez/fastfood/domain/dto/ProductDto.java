package com.dhernandez.fastfood.domain.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Deimer Hernandez <deimerhdz21@gmail.com>
 *	Creado en 17/05/2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto  implements Serializable{

	private Long id;
	private String name;
	private BigDecimal price;
	private String description;
	private Long userId;
	private Long categoryId;
	private CategoryDto category;
	private Long imageId;
	private ImageDto image;
	private Boolean active;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
