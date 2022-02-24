package com.sabas.blog.blogapi.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sabas.blog.blogapi.entity.Comment;



@Repository // Aplica metodos de persistencia a la entidad Comment
public interface CommentRepocitory extends IGenericRepocitory<Comment, Long> {
	List<Comment> findByPostId(long postId);
}
