package com.sabas.blog.blogapi.service;



import com.sabas.blog.blogapi.payload.PostDto;
import com.sabas.blog.blogapi.payload.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto);
	PostResponse getAllPosts(int pageNo, int pageSize,String sortBy, String sortDir);
	PostDto getPostById(long id);
	PostDto updatePost(PostDto postDto, long id);
	void deletePostById(long id);
}
