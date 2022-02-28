package com.sabas.blog.blogapi.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sabas.blog.blogapi.entity.Role;



/*
 * el paquete repository tiene todas las querys de consulta
 * */
public interface RoleRepocitory extends JpaRepository<Role, Long> {
	Optional<Role> findByName(String name);
}
