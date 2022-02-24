package com.sabas.blog.blogapi.controller;




import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.sabas.blog.blogapi.service.PostService;
import com.sabas.blog.blogapi.payload.PostDto;
import com.sabas.blog.blogapi.payload.PostResponse;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	// create blog post rest api
	@PostMapping
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
		return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
	}

	// get all posts rest api
	@GetMapping
	public PostResponse getAllPosts(// Parametros que se le pasen por la URL
			@RequestParam(value="pageNo",defaultValue = "0",required = false) int pageNo,
			@RequestParam(value="pageSize",defaultValue = "10",required = false) int pageSize,
			@RequestParam(value="sortBy",defaultValue = "id",required = false) String sortBy,
			@RequestParam(value="sortDir",defaultValue = "asc",required = false) String sortDir	
			){
		return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
	}

	//get post by id
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable(name="id") long id){
		return ResponseEntity.ok(postService.getPostById(id));
	}
	
	// update post by id rest api
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") long id){
       PostDto postResponse = postService.updatePost(postDto, id);
       return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    // delete post rest api
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
    }
}











