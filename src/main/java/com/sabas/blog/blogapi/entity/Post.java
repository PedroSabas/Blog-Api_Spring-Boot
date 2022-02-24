package com.sabas.blog.blogapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data // Getter, Setter lombok.
@Entity
@Table(
		name="posts", uniqueConstraints = 
			{@UniqueConstraint(columnNames = {"title"})} //Decimos que el titulo debe de ser unico.
		)

public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="title", nullable = false)
	private String title;
	
	@Column(name="description", nullable = false)
	private String description;
	
	@Column(name="content", nullable = false)
	private String content;

}
