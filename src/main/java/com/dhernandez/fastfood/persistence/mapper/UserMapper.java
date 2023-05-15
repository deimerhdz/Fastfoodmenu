package com.dhernandez.fastfood.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.dhernandez.fastfood.domain.dto.UserDto;
import com.dhernandez.fastfood.persistence.entities.User;
/**
 * 
 * @Author Deimer Hernandez <deimerhdz21@gmail.com>
 *	Creado en 15/05/2023
 */
@Mapper(componentModel = "Spring")
public interface UserMapper {
	
	List<UserDto> toUsersDto(List<User> users);
	
	UserDto toUserDto(User user);
	
	User toUser(UserDto userDto);
	
}
