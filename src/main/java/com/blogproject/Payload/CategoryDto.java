package com.blogproject.Payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDto {

	
	private Integer categoryId;
	
	@NotBlank
	@Size(min = 4,message = "Minimum size of categeory is 4 charcater")
	private String categoryTitle;
	
	@NotBlank
	@Size(min = 10, message = "Minimum size of categeory is 10 charcater")
	private String categoryDescription;
	
}
