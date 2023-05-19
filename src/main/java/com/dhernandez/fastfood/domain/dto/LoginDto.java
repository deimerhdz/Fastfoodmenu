package com.dhernandez.fastfood.domain.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @Author Deimer Hernandez <deimerhdz21@gmail.com> Creado en 17/05/2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String email;

	private String password;
}
