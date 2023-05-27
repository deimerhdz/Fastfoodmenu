package com.dhernandez.fastfood.persistence.entities;

import java.io.Serializable;
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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="tbl_restaurants")
public class Restaurant implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="name")
	@NotEmpty
	private String name;
	
	@Column(name="description")
	@NotEmpty
	private String description;
	
	@Column(name="address")
	@NotEmpty
	private String address;
	
	@Column(name="telephone")
	@NotEmpty
	private String telephone;
	
	@Column(name="icon_id")
	private Long iconId;
	
	@Column(name="user_id")
	@NotNull
	private Long userId;
	@ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "icon_id",updatable = false,insertable = false)
	private Image icon;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;

	
	@PrePersist
	void prePersist() {
		this.createdAt = LocalDateTime.now();
	}
}
