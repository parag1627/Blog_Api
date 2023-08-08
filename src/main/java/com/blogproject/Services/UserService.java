package com.blogproject.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blogproject.Payload.UserDto;

@Service
public interface UserService {
	UserDto registerNewUser(UserDto userDto);
	
	UserDto createUser(UserDto userDto);
	
	UserDto updateUser(UserDto userDto,Integer userId);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUser();
	
	void deleteUser(Integer userId);

}
