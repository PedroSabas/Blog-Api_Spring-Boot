package com.sabas.blog.blogapi.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Clase que lanza excepciones relacionadas con los metodos implementados, cuando no se encuentra un recurso
// Muestra Los errores, Perzonalizamos excepciones.


@ResponseStatus(value = HttpStatus.NOT_FOUND)// Retorna un Código de estado
public class ResourceNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private String resourceName;
	private String fieldName;
	private long fieldValue;
	
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s: '%s'", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public long getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(long fieldValue) {
		this.fieldValue = fieldValue;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
