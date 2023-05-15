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
public class UserDto  implements Serializable{
	
	private Long id;
	private String username;
	private String email;
	private String password;
	private Boolean active;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private static final long serialVersionUID = 1L;

}
