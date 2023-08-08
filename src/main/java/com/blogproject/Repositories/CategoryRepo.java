package com.blogproject.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogproject.Entities.Category;


public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
