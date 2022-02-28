package com.sabas.blog.blogapi.security;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/*
 * Está clase tiene la responsabilidad de que valida el token de seguridad cuando llega una petición
 * Le da una respuesta el backend que no tiene permisos sin haver hecho una autenticación
 * */

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{


	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,authException.getMessage());
	}
}
