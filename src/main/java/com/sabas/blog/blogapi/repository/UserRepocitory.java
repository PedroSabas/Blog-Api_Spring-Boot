package com.sabas.blog.blogapi.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sabas.blog.blogapi.entity.User;



public interface UserRepocitory extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
	Optional<User> findByUsernameOrEmail(String username, String email);
	Optional<User> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
}
