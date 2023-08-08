package com.blogproject.Services.Impl;



import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice.This;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blogproject.Entities.Category;
import com.blogproject.Entities.Post;
import com.blogproject.Entities.User;
import com.blogproject.Exception.ResourceNotFoundException;
import com.blogproject.Payload.PostDto;
import com.blogproject.Payload.PostResponse;
import com.blogproject.Repositories.CategoryRepo;
import com.blogproject.Repositories.PostRepo;
import com.blogproject.Repositories.UserRepo;
import com.blogproject.Services.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		 User user = this.userRepo.findById(userId)
				 .orElseThrow(()
						 ->new ResourceNotFoundException("User", "User id", userId));
		 Category category = this.categoryRepo.findById(categoryId)
				 .orElseThrow(()
						 ->new ResourceNotFoundException("Category", "category id", categoryId));
		 
		
			
		Post post =	this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost= this.postRepo.save(post);
		
			
		return this.modelMapper.map(newPost, PostDto.class);
	}
	
	

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
	   Post posts = this.postRepo.findById(postId).orElseThrow(()->
		new ResourceNotFoundException("post", "post id", postId));
		
	  posts.setContent(postDto.getContent());
	  posts.setTitle(postDto.getTitle());
	  posts.setImageName(postDto.getImageName());
	  
	  Post updatedPost= this.postRepo.save(posts);
	  
	  return this.modelMapper.map(updatedPost, PostDto.class); 
			  
	  
	 
	}

	@Override
	public void deletePost(Integer postId) {
		Post deletedPost=this.postRepo.findById(postId).orElseThrow(()->
		new ResourceNotFoundException("post", "post id", postId));
		
		this.postRepo.delete(deletedPost);
	    
		
		
	}

	public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
//		if(sortDir.equalsIgnoreCase("asc")) {
//			sort = Sort.by(sortBy).ascending();
//		}else {
//			sort = Sort.by(sortBy).descending();
//		}
		
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		
		 Page<Post>pagePost  =this.postRepo.findAll(p);
		  List<Post>posts = pagePost.getContent(); 
		  
		  
		  
		 List<PostDto>postDtos = posts.stream().map((post)->
		      modelMapper.map(post, PostDto.class))
				 .collect(Collectors.toList());
		 
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
	    Post post = this.postRepo.findById(postId).orElseThrow(()->
		new ResourceNotFoundException("post", "post id", postId));
	    
	    PostDto postDto = this.modelMapper.map(post, PostDto.class);
		return postDto;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->
		new ResourceNotFoundException("category", "category id", categoryId));
		
		List<Post> posts = this.postRepo.findByCategory(cat);
		
		List<PostDto> postDto  = posts.stream()
				.map((post)->
		   		this.modelMapper
				.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<PostDto> getAllPostByUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->
		new ResourceNotFoundException("user", "user id", userId));
		
		List<Post>posts= this.postRepo.findByUser(user);
		
		List<PostDto> postDtos = posts.stream().map((post)->
				    this.modelMapper.map(post, PostDto.class))
				    .collect(Collectors.toList());
		
		return postDtos;
				
		
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos = posts.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

}
