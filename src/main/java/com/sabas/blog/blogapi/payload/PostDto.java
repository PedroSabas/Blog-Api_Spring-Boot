package com.sabas.blog.blogapi.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

	private long id;
	// Valida los campos que la request no venga sín datos y demas restricciones
	@NotEmpty
	@Size(min=2,message = "el titulo debe tener 2 caracteres como minimo")
	private String title;
	
	@NotEmpty
	@Size(min=10,message = "la descripcion debe tener 10 caracteres como minimo")
	private String description;
	
	@NotEmpty
	private String content;
}
