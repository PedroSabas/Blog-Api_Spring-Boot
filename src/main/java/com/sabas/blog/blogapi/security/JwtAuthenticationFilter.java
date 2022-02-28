package com.sabas.blog.blogapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * Esta clase Filter tiene las responsabilidades de comprobar sí la solicitud tiene un token valido y si lo tiene
 * establecer la autenticacion
 * */
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	// inject dependencies
		@Autowired
		private JwtTokenProvider tokenProvider;// Instanciamos de la clase JwtTokenProvider

		@Autowired
		private CustomUserDetailsService customUserDetailsService;// Instanciamos de la clase CustomUserDetailsService

		@Override
		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
				throws ServletException, IOException {
			
			// get JWT (token) from http request, Recuperamos el token de la peticion, porque ahora cada que hacemos una peticion tiene que venir acompañanada del token
			String token = getJWTfromRequest(request);
			
			// validate token, Valido que el token sea correcto
			if (StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
				
				// get username from token, Obtenemos el Username del token
				String username = tokenProvider.getUsernameFromJWT(token);
				
				// load user associated with token, Para saber si el username se encuentra en la bd invocamos el método loadUserByUsername de la clase customUserDetailsService y le pasamos como parametro el username
				UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// set spring security
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
			filterChain.doFilter(request, response);
		}

		// Bearer <accessToken>
		private String getJWTfromRequest(HttpServletRequest request) {
			String bearerToken = request.getHeader("Authorization");
			if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
				return bearerToken.substring(7, bearerToken.length());
			}
			return null;
		}
}
