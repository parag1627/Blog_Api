package com.blogproject.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogproject.Entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
