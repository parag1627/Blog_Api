package com.blogproject.Services.Impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogproject.Entities.Comment;
import com.blogproject.Entities.Post;
import com.blogproject.Exception.ResourceNotFoundException;
import com.blogproject.Payload.CommentDto;
import com.blogproject.Repositories.CommentRepository;
import com.blogproject.Repositories.PostRepo;
import com.blogproject.Services.CommentService;
@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired 
	private CommentRepository commentRepository;
	
	@Autowired
	private ModelMapper mapper;
	

	public CommentDto createComment(CommentDto commentDto, Integer postId) {
	  Post post= this.postRepo.findById(postId)
	  .orElseThrow(()
			  ->new ResourceNotFoundException("Post", "post id",postId));
	  
	  Comment comment = this.mapper.map(commentDto, Comment.class);
	  
	  comment.setPost(post);
	  
	  Comment savedComment = this.commentRepository.save(comment);
	  
	  return this.mapper.map(savedComment, CommentDto.class);
	  
	  
	  
	  
		
	}


	public void deleteComment(Integer commentId) {
		Comment comment = this.commentRepository.findById(commentId).orElseThrow(()
				-> new ResourceNotFoundException("Comment", "comment id", commentId));
		
		this.commentRepository.delete(comment);

	}

}
