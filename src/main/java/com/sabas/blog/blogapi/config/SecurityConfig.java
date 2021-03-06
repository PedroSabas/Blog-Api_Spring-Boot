package com.sabas.blog.blogapi.config;

import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sabas.blog.blogapi.security.CustomUserDetailsService;
import com.sabas.blog.blogapi.security.JwtAuthenticationEntryPoint;
import com.sabas.blog.blogapi.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity // Habilitamos vía web
@EnableGlobalMethodSecurity(prePostEnabled = true) // Es necesario abilitarse para permitir acceso a siertos meyodos respecto a su rol
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	/* Las siguientes tres instancias bienen de el paquete security y sus respectivas clases */
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() { // Encripta la contraseña
		return new BCryptPasswordEncoder();
	}

	
	/*
	 * Nos permite configurar cuales son los recursos que yo podría hacer sin autorización y cuales si necesitan autenticación
	 * */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable() // Como el front est´ra en otro proyecto desabilitamos csrf
			.exceptionHandling() // Capturamos excepcioines
			.authenticationEntryPoint(authenticationEntryPoint)// Cuando no se haga autenticación se lanza el authenticationEntryPoint
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeHttpRequests()
			.antMatchers(HttpMethod.GET, "/api/**").permitAll() // 
			.antMatchers("/api/auth/**").permitAll()
			.anyRequest()
			.authenticated();
		
		//  
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {		
		auth.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder());
	}

	
	@Bean
	public AuthenticationManager authenticationManagerBean()throws Exception{
		return super.authenticationManagerBean();
	}
}
