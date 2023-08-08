package com.blogproject.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.blogproject.Entities.User;
import java.util.List;



public interface UserRepo extends JpaRepository<User, Integer>{
	
	Optional<User>findByEmail(String email);
	

}
