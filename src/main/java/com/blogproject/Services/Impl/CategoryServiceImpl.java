package com.blogproject.Services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogproject.Entities.Category;
import com.blogproject.Exception.ResourceNotFoundException;
import com.blogproject.Payload.CategoryDto;
import com.blogproject.Repositories.CategoryRepo;
import com.blogproject.Services.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper mapper;

	
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat =  this.mapper.map(categoryDto, Category.class);	
		Category addedCat = this.categoryRepo.save(cat);
		
		return this.mapper.map(addedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

		Category cat = this.categoryRepo
				.findById(categoryId)
				.orElseThrow(()->
				new ResourceNotFoundException
				("Category", "Category Id", categoryId));
		
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedCategory=this.categoryRepo.save(cat);
		
		return this.mapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {

		
		Category cat = this.categoryRepo
		.findById(categoryId).orElseThrow(()->
		new ResourceNotFoundException("Category" ,"Category Id", categoryId));
		this.categoryRepo.delete(cat);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {

		Category cat = this.categoryRepo.findById(categoryId).
				orElseThrow(()-> new ResourceNotFoundException("Category" ,"Category Id", categoryId));
	    CategoryDto categoryDto = this.mapper.map(cat, CategoryDto.class);
		
		return categoryDto;
	}

	@Override
	public List<CategoryDto> getAllCategory() {

     List<Category> listCategories = this.categoryRepo.findAll();
     List<CategoryDto> categoryDtos= listCategories.stream().map((cat)-> this.mapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return categoryDtos;
	}
	
}
