package com.sabas.blog.blogapi.service;

import java.util.List;

import com.sabas.blog.blogapi.payload.CommentDto;

// import com.sabas.blog.blogapi.entity.Comment;

public interface CommentService  {// extends CrudService<Comment, Long>
	CommentDto createComment(Long postId, CommentDto commentDto);
	List<CommentDto> getCommentsByPostId(Long postId);
	CommentDto getCommentById(Long postId, Long commentId);
	CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest);
	void deleteComment(Long postId, Long commentId);
}
