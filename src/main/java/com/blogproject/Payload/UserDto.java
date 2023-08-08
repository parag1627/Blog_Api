package com.blogproject.Payload;

import java.util.HashSet;
import java.util.Set;

import com.blogproject.Entities.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
	private int id;
	
	@NotEmpty
	@Size(min = 4,message = "At least 4 character needed")
	private String name;
	
	@Email(message = "Email should be valid")
	private String email;
	
	@NotEmpty(message = "Password cannot be null")
	@Size(min = 3,max = 10,message = "Pasword must be range between 3 to 10 characters")
	private String password;
	
	@NotEmpty(message = "About cannot be null")
	private String about;
	
	
	private Set<RoleDto> roles = new HashSet<>();
	
	


}
