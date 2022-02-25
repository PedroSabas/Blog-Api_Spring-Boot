package com.sabas.blog.blogapi.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*
 * Trata la información de un JSON mal formado como un bad request
 * */

@Getter
@AllArgsConstructor
public class BlogAPIException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	private String message;	
}
