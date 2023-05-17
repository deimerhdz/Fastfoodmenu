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
import jakarta.validation.constraints.NotEmpty;
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
@Table(name="tbl_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable{
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="username")
	@NotEmpty
	private String username;
	
	@Column(name="email", unique = true)
	@NotEmpty
	private String email;
	
	@Column(name="password")
	@NotEmpty
	private String password;
	
	@Column(name="active")
	private Boolean active;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;

	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	@PrePersist
	public void prePersist() {
		this.active = true;
		this.createdAt= LocalDateTime.now();
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
