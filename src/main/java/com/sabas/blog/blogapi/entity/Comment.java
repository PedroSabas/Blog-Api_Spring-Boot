package com.sabas.blog.blogapi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comments")
public class Comment {
	
	/*
	 * Un conjunto de comentarios van a estar asociados a una publicación (POST)
	 * 
	 * */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String email;
	private String body;
	
	// El comentario debe de ir asociado a una publicación (POST)
	// Muchos comentarios pertenecen a una publicación
	@ManyToOne
	@JoinColumn(name="post_id", nullable=false)
	private Post post;
}





















