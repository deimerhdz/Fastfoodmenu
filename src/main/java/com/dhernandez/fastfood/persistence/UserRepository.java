package com.dhernandez.fastfood.persistence;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dhernandez.fastfood.domain.dto.UserDto;
import com.dhernandez.fastfood.domain.repository.IUserRepository;
import com.dhernandez.fastfood.persistence.crud.UserCrudRepository;
import com.dhernandez.fastfood.persistence.entities.User;
import com.dhernandez.fastfood.persistence.mapper.UserMapper;
import com.dhernandez.fastfood.web.exceptions.UniqueException;
/**
 * 
 * @Author Deimer Hernandez <deimerhdz21@gmail.com>
 *	Creado en 15/05/2023
 */
@Repository
public class UserRepository implements IUserRepository{
	
	private Log Logger = LogFactory.getLog(UserRepository.class);
	
	private UserCrudRepository userCrudRepository;
	
	private UserMapper userMapper;
	
	@Autowired
	public UserRepository(UserCrudRepository userCrudRepository, UserMapper userMapper) {
		super();
		this.userCrudRepository = userCrudRepository;
		this.userMapper = userMapper;
	}

	@Override
	public List<UserDto> listAllUsers() {
		Logger.info("Accessing the method listAllUsers()");
		List<UserDto> users = this.userMapper.toUsersDto(this.userCrudRepository.findAll());
		Logger.info("ending the method listAllUsers()");
		return users;
	}

	@Override
	public Optional<UserDto> findByEmail(String email) {
	    Logger.info("Accessing the method findByEmail(String email)");
			Optional<UserDto> user = this.userCrudRepository.findByEmail(email)
					.map(userDB -> this.userMapper.toUserDto(userDB));
		Logger.info("ending the method findByEmail(String email)");
		return user;
	}

	@Override
	public Optional<UserDto> findById(Long id) {
	    Logger.info("Accessing the method findById(Long id)");
	    Optional<UserDto> user = this.userCrudRepository.findById(id)
				.map(userDB -> this.userMapper.toUserDto(userDB));
		Logger.info("ending the method findById(Long id)");
		return user;
	}

	@Override
	public UserDto save(UserDto userDto) {
	    Logger.info("Accessing the method save(UserDto userDto)"+userDto);
	    if(this.userCrudRepository.existsByEmail(userDto.getEmail())) {
	    	throw new UniqueException("El email ya existe");
	    }
		UserDto userDB =this.userMapper.toUserDto(this.userCrudRepository.save(this.userMapper.toUser(userDto))) ;
		Logger.info("ending the method save(UserDto userDto)");
		return userDB;
	}

	@Override
	public UserDto changeStatus(Long id, Boolean status) {
	    Logger.info("Accessing the method changeStatus(Long id, Boolean status)");
	    Optional<UserDto> userDB = this.findById(id);
	    UserDto userChange = new UserDto();
	    if(userDB.isPresent()) {
	    	User user = userMapper.toUser(userDB.get());
	    	user.setActive(status);
	    	user.setUpdatedAt(LocalDateTime.now());
	    	userChange = userMapper.toUserDto(this.userCrudRepository.save(user));
	    }
		Logger.info("ending the method changeStatus(Long id, Boolean status)");
		return userChange;
	}

}
