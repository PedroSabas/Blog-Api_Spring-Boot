package com.sabas.blog.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


/*
 * Esta es una interfaz generica para centralizar la función de JpaRepository
 * 
 * 
 * */

@NoRepositoryBean // Evita la persistencia cuando no tenemos una entidad
public interface IGenericRepocitory<T,ID> extends JpaRepository<T, ID> {

}
