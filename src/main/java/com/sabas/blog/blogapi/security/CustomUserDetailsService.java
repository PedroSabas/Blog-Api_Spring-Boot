package com.sabas.blog.blogapi.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sabas.blog.blogapi.entity.Role;
import com.sabas.blog.blogapi.entity.User;
import com.sabas.blog.blogapi.repository.UserRepocitory;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	private UserRepocitory userRepository;
	
	public CustomUserDetailsService(UserRepocitory userRepository) {
		this.userRepository=userRepository;
	}
	// Función que lanza una excepcion cuando no encuentra un usuario
	/*
	 * Básicamente es un método de autenticación le enviamos el user y el correo nos dice si se encuentra o no
	 * */
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		User user=userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
				.orElseThrow(()->
							new UsernameNotFoundException("User not found username or email:"+usernameOrEmail));
		
		return new org.springframework.security.core.userdetails.User(user.getEmail()
				,user.getPassword()
				,mapRolesToAuthorities(user.getRoles()));
		
	}
	
	/*
	 * Convertimos un Role a GrandtedAuthority porque spring security maneja el rol de está forma
	 * */
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
		return roles.stream()
				.map(role->new SimpleGrantedAuthority(role.getName())) // Transformo de role a SimpleGrantedAuthority
				.collect(Collectors.toList());
	}
	
	
}
