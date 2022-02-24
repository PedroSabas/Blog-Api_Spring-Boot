package com.sabas.blog.blogapi.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
	@Bean // Se coloca Bean para que spring pueda reconocer la dependencia
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
