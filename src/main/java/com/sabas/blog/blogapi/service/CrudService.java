package com.sabas.blog.blogapi.service;

import java.util.List;

public interface CrudService<T, ID> {
	T create(T t);
	List<T> readAll();
	T readById(ID id);
	T update(T t);
	void delete(ID id);
}
