package com.sabas.blog.blogapi.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.sabas.blog.blogapi.repository.PostRepocitory;
import com.sabas.blog.blogapi.entity.Comment;
import com.sabas.blog.blogapi.entity.Post;
import com.sabas.blog.blogapi.exception.ResourceNotFoundException;
import com.sabas.blog.blogapi.payload.CommentDto;
import com.sabas.blog.blogapi.repository.CommentRepocitory;
import com.sabas.blog.blogapi.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	private CommentRepocitory commentRepocitory;
	private PostRepocitory postRepository;
	private ModelMapper mapper;

	public CommentServiceImpl(CommentRepocitory commentRepository, PostRepocitory postRepository, ModelMapper mapper) {
		this.commentRepocitory = commentRepository;
		this.postRepository = postRepository;
		this.mapper = mapper;
	}
	
	
	@Override
	public CommentDto createComment(Long postId, CommentDto commentDto) {
		// TODO Auto-generated method stub
		
		Comment comment=mapToEntity(commentDto);
		
		Post post=postRepository.findById(postId)
				.orElseThrow( //Sí ni encuentra el posId me lanza está excepción
						()->new ResourceNotFoundException("Post","id", postId)
				);
		// Asocio el comentario con el POST
		comment.setPost(post);
		
		Comment newComment=commentRepocitory.save(comment);
		
		
		return mapToDTO(newComment);
		
	}

	@Override
	public List<CommentDto> getCommentsByPostId(Long postId) {
		// TODO Auto-generated method stub
		List<Comment> comments = commentRepocitory.findByPostId(postId);

		return comments.stream()
				.map(comment->mapToDTO(comment))
				.collect(Collectors.toList());
	}

	@Override
	public CommentDto getCommentById(Long postId, Long commentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteComment(Long postId, Long commentId) {
		// TODO Auto-generated method stub

	}

	
	// Convert Comment to CommentDto
	private CommentDto mapToDTO(Comment comment) {
		CommentDto commentDto=mapper.map(comment, CommentDto.class);
		return commentDto;
	}

	// Convert CommentsDto to Comment
	private Comment mapToEntity(CommentDto commentDto) {
		Comment comment=mapper.map(commentDto, Comment.class);
		return comment;
	}
}

