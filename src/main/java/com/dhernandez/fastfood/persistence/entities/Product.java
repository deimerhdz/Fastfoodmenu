package com.dhernandez.fastfood.persistence.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @Author Deimer Hernandez <deimerhdz21@gmail.com>
 *	Creado en 17/05/2023
 */
@Entity
@Table(name="tbl_products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	@NotEmpty
	private String name;
	
	@Column(name="price")
	@NotNull
	private BigDecimal price;
	
	@Column(name="description")
	private String description;
	
	@Column(name="user_id")
	@NotNull
	private Long userId;
	
	@Column(name="category_id")
	@NotNull
	private Long categoryId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id",insertable = false,updatable = false)
	private Category category;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id",insertable = false,updatable = false)
	private User user;
	
	@Column(name="active")
	private Boolean active;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
	@PrePersist
	public void prePersist() {
		this.active=true;
		this.createdAt = LocalDateTime.now();
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
