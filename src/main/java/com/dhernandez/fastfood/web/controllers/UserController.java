package com.dhernandez.fastfood.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhernandez.fastfood.domain.dto.UserDto;
import com.dhernandez.fastfood.domain.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;

    @GetMapping("/list-all")
	public ResponseEntity<List<UserDto>> listUsers(){
		List<UserDto> users = userService.listAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
    
    @PostMapping("/save")
	public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto){
		UserDto user = userService.save(userDto);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
    
    
    @PutMapping("/change-status/{id}/{status}")
   	public ResponseEntity<UserDto> changeStatus(@PathVariable("id") Long id, @PathVariable("status") Boolean status){
   		UserDto user = userService.changeStatus(id,status);
   		return new ResponseEntity<>(user, HttpStatus.OK);
   	}
}
