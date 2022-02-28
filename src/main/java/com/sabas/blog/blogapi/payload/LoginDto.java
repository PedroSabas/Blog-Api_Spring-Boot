package com.sabas.blog.blogapi.payload;

import lombok.Data;

/*
 * Para hacer un login necesitamos el username o el email y el password
 * */
@Data
public class LoginDto {
	private String usernameOrEmail;
	private String password;
}
