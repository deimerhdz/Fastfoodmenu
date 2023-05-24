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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 
 * @Author Deimer Hernandez <deimerhdz21@gmail.com>
 *	Creado en 15/05/2023
 */
@Entity
@Table(name="tbl_categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category  implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="active")
	private Boolean active;
	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name = "image_id")
    private Long imageId;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "image_id",updatable = false,insertable = false)
    private Image image;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id",insertable = false,updatable = false)
	private User user;
	
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
