package com.sabas.blog.blogapi.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
	private Long id;
	
	@NotEmpty
	@Size(min=2,message = "el nombre debe tener 2 caracteres como minimo")
	private String name;
	
	@NotEmpty
	@Email(message = "Formato de email no valido")
	private String email;
	
	@NotEmpty
	private String body;
}
