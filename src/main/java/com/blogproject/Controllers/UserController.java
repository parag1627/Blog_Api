package com.blogproject.Controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogproject.Payload.ApiResponse;
import com.blogproject.Payload.UserDto;
import com.blogproject.Services.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/users")

public class UserController {
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/add")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createUserDto = userService.createUser(userDto);
		
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uid){
		UserDto updateDto= this.userService.updateUser(userDto, uid);
		return new ResponseEntity<>(updateDto,HttpStatus.OK);
	}
	
	
//	@DeleteMapping("/{userId}")
//	public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer uid){
//		this.userService.deleteUser(uid);
//		return new ResponseEntity<>(Map.of("message","User Deleted Successfully"),HttpStatus.OK);
//	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid){
		this.userService.deleteUser(uid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully", true),HttpStatus.OK);
	}
	
	@GetMapping("/getUser")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUser());
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer uid){
		UserDto userDto = this.userService.getUserById(uid);
		
		return new ResponseEntity<UserDto>(userDto,HttpStatus.ACCEPTED);
	}
	
	
	

}
