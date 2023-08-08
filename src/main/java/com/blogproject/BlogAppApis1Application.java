package com.blogproject;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


import com.blogproject.Config.AppConstants;
import com.blogproject.Entities.Role;
import com.blogproject.Repositories.RoleRepo;


@SpringBootApplication
public class BlogAppApis1Application implements CommandLineRunner {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApis1Application.class, args);
	}
	
	@Bean
        ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.print(this.passwordEncoder.encode("34hsshy7"));
		
		
		try {
			Role role = new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ADMIN_USER");
		
			Role role1 = new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("NORMAL_USER");
			
			List<Role>roles = new ArrayList<>(Arrays.asList(role,role1));
			
			
			// Create a mutable list using Arrays.asList()
//			List<Role> list = new ArrayList<>(Arrays.asList(role, role1));
//
//			// Make the list immutable using Collections.unmodifiableList()
//			List<Role> immutableList = Collections.unmodifiableList(list);
			
		List<Role> result = this.roleRepo.saveAll(roles);
		
		result.forEach(r->{
			System.out.println(r.getName());
		}
			
				);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		}

}
