package com.blogproject.Services.Impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogproject.Config.AppConstants;
import com.blogproject.Entities.Role;
import com.blogproject.Entities.User;
import com.blogproject.Exception.ResourceNotFoundException;
import com.blogproject.Payload.UserDto;
import com.blogproject.Repositories.RoleRepo;
import com.blogproject.Repositories.UserRepo;
import com.blogproject.Services.UserService;

@Service

public class UserserviceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	
	
	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		
		//encode the password
		
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		//roles
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		
		
		user.getRoles().add(role);
		
		User newUser = this.userRepo.save(user);
		
		
		return this.modelMapper.map(newUser, UserDto.class);
	}
	

	@Override
	public UserDto createUser(UserDto userDto) {
		User user= this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		

		return this.userToUserDto(savedUser);
	}
	

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
	  
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		
		User updatedUser = this.userRepo.save(user);
		
		UserDto userDto1= this.userToUserDto(updatedUser);
		
		return userDto1;	
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		 User user = this.userRepo.findById(userId)
				 .orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		 
		 return this.userToUserDto(user);
		 
	}

	@Override
	public List<UserDto> getAllUser() {
	
		List<User> users = this.userRepo.findAll();
		
		List<UserDto> userDtos = users.stream()
				.map(user-> this.userToUserDto(user))
				.collect(Collectors.toList());
		
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = userRepo.findById(userId)
		.orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));;
		
		this.userRepo.delete(user);
		
		
	}
	
	private User dtoToUser(UserDto userDto) {
		
		// here we will use modelMapper in that we required source and in which 
		//class we want conversion.
		
		 User user = this.modelMapper.map(userDto, User.class);
		
		 
		//this below is manually conversion
		 
//		User user = new User();
		
//		user.setId(userDto.getId());
//		user.setEmail(userDto.getEmail());
//		user.setAbout(userDto.getAbout());
//		user.setName(userDto.getName());
//		user.setPassword(userDto.getPassword());
		
		return user;
	}
	
	public UserDto userToUserDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
//		UserDto userDto = new UserDto();
//		
//		userDto.setEmail(user.getEmail());
//		userDto.setId(user.getId());
//		userDto.setAbout(user.getAbout());
//		userDto.setName(user.getName());
//		userDto.setPassword(user.getPassword());
//		
		return userDto;
		
	}



	
	


}
