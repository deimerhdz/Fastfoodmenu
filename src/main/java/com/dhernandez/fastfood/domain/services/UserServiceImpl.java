package com.dhernandez.fastfood.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhernandez.fastfood.domain.dto.UserDto;
import com.dhernandez.fastfood.domain.repository.IUserRepository;
/**
 * @Author Deimer Hernandez <deimerhdz21@gmail.com>
 *	Creado en 15/05/2023
 */


@Transactional
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private IUserRepository userRepository;

	
	@Override
	public List<UserDto> listAllUsers() {
		return userRepository.listAllUsers();
	}

	@Override
	public Optional<UserDto> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public Optional<UserDto> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public UserDto save(UserDto userDto) {
		return userRepository.save(userDto);
	}

	@Override
	public UserDto changeStatus(Long id, Boolean status) {
		return userRepository.changeStatus(id, status);
	}

}
