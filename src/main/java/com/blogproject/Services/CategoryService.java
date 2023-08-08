package com.blogproject.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blogproject.Payload.CategoryDto;
@Service

public interface CategoryService {

	//create 
	 CategoryDto createCategory (CategoryDto categoryDto);
	
	//update
	 CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	
	//delete
	 void deleteCategory(Integer categoryId);
	
	//get
	 CategoryDto getCategory(Integer categoryId);
	
	//getAll
	 List<CategoryDto> getAllCategory();
}
