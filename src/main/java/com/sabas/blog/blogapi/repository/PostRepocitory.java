package com.sabas.blog.blogapi.repository;


import org.springframework.stereotype.Repository;

import com.sabas.blog.blogapi.entity.Post;

@Repository // Aplica metodos de persistencia a la entidad Post
public interface PostRepocitory extends IGenericRepocitory<Post, Long> { //Perciste de la entidad de Post y es de tipo Long

}
