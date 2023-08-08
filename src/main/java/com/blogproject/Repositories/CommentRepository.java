package com.blogproject.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogproject.Entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
