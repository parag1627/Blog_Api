package com.blogproject.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogproject.Payload.ApiResponse;
import com.blogproject.Payload.CategoryDto;
import com.blogproject.Services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	   @PostMapping("/add")
      public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		   
		   CategoryDto createCategory=this.categoryService.createCategory(categoryDto);
		   
		   return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	   }
	
	   @PutMapping("/update/{catId}")
	      public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer catId){
			   
			   CategoryDto updateCategory=this.categoryService.updateCategory(categoryDto,catId);
			   
			   return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.OK);
		   }
	   
	   
		
	
	   @DeleteMapping("/delete/{catId}")
	      public ResponseEntity<ApiResponse> deleteCategory( @PathVariable Integer catId){
			   
			   this.categoryService.deleteCategory(catId);
			   
			   return new ResponseEntity<ApiResponse>(new ApiResponse("Category is deleted successfully", true), HttpStatus.OK);
		   }
	
	   
	   //get

	   @GetMapping("/get/{catId}")
	      public ResponseEntity<CategoryDto> getCategory( @PathVariable("catId") Integer catId){
			   
			   CategoryDto categoryDto=this.categoryService.getCategory(catId);
			   
			   return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
			   
	   }
	
	
	//getAll
	  @GetMapping("/getAll")
	      public ResponseEntity<List<CategoryDto>> getAllCategory(){
			   
			   List<CategoryDto> categories=this.categoryService.getAllCategory();
			   
			   return ResponseEntity.ok(categories);
      }
}