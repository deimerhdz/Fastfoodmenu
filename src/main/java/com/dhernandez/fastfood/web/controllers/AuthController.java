package com.dhernandez.fastfood.web.controllers;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhernandez.fastfood.domain.dto.JwtDto;
import com.dhernandez.fastfood.domain.dto.LoginDto;
import com.dhernandez.fastfood.domain.dto.UserAuth;
import com.dhernandez.fastfood.domain.dto.UserDto;
import com.dhernandez.fastfood.domain.services.UserDetailServiceImpl;
import com.dhernandez.fastfood.domain.services.UserService;
import com.dhernandez.fastfood.web.security.JwtProvider;

/**
 * 
 * @Author Deimer Hernandez <deimerhdz21@gmail.com> Creado en 17/05/2023
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	JwtProvider jwtProvider;
	@Autowired
	UserDetailServiceImpl  userDetailServiceImpl;
	@Autowired
	UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public ResponseEntity<Boolean> register(@RequestBody UserDto user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userService.save(user);
		return ResponseEntity.ok(true);
	}

	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@RequestBody LoginDto loginDto) {
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			UserAuth userDetails = (UserAuth) userDetailServiceImpl.loadUserByUsername(loginDto.getEmail());
			String jwt = jwtProvider.generateToken(userDetails);

			JwtDto jwtDto = new JwtDto(jwt, userDetails.getName(), userDetails.getId());
			return ResponseEntity.ok(jwtDto);

		} catch (BadCredentialsException e) {
			return new ResponseEntity(Collections.singletonMap("message", "Usuario o contraseña invalidos."),
					HttpStatus.FORBIDDEN);
		} catch (AuthenticationException ex) {
			return new ResponseEntity(Collections.singletonMap("message", "Usuario o contraseña invalidos."),
					HttpStatus.FORBIDDEN);
		}
	}

}
