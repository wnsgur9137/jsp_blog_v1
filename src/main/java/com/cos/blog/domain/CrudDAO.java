package com.cos.blog.domain;

import java.util.List;

public interface CrudDAO<T> {
	// get
	public T findById(int id);
	
	// get
	public List<T> findAll(int page);
	
	// post
	public int save(T data);
	
	// post
	public int update(T data);
	
	// post
	public int deleteByKey(int key);
}
