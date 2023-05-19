package com.dhernandez.fastfood.domain.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dhernandez.fastfood.domain.dto.UserAuth;
import com.dhernandez.fastfood.domain.dto.UserDto;
/**
 * 
 * @Author Deimer Hernandez <deimerhdz21@gmail.com>
 *	Creado en 17/05/2023
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService{
	@Autowired
	UserService userService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	Optional<UserDto> userDto =	userService.findByEmail(username);
		return UserAuth.build(userDto.get());
	}

}
