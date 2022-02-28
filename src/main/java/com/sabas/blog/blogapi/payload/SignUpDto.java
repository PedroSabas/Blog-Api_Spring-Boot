package com.sabas.blog.blogapi.payload;

import lombok.Data;

/*
 * Cuando se quiere registrar un usuario le enviamos estos datos
 * */

@Data
public class SignUpDto {
	private String name;
	private String username;
	private String email;
	private String password;
	
}
