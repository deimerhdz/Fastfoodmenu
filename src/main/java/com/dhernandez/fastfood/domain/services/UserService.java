package com.dhernandez.fastfood.domain.services;

import java.util.List;
import java.util.Optional;

import com.dhernandez.fastfood.domain.dto.UserDto;
/**
 * 
 * @Author Deimer Hernandez <deimerhdz21@gmail.com>
 *	Creado en 15/05/2023
 */
public interface UserService {
	List<UserDto> listAllUsers();
	
	Optional<UserDto> findByEmail(String email);
	
	Optional<UserDto> findById(Long id);
	
	UserDto save(UserDto userDto);
	
	UserDto changeStatus(Long id, Boolean status);
}
