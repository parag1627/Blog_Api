package com.blogproject.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blogproject.Entities.Post;
import com.blogproject.Payload.PostDto;
import com.blogproject.Payload.PostResponse;

@Service
public interface PostService {
    
	
	//post
	PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//update
	void deletePost( Integer postId);
	
	//getAll
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy ,String sortDir);
	
	//getSingle post
	PostDto getPostById(Integer postId);
	
	
	//get all post by category
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//get all post by user
	List<PostDto> getAllPostByUser(Integer userId);
	
	//search post
	List<PostDto> searchPosts(String keyword);
	
}
