package com.dhernandez.fastfood.persistence.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="tbl_tables")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantTable implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	@Column(name="restaurant_id")
	@NotNull
	private Long restaurantId;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;

	
	@Column(name="status")
	private Boolean status;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now();
		status= true;
	}
	

}
