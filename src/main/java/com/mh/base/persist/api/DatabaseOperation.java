package com.mh.base.persist.api;

import java.util.List;

public interface DatabaseOperation<T,K> {
	
	T queryOneById(K key);
	
	List<T> queryAll();
	
	int insert(T obj);
	
	int deleteById(K key);
	
	int updateById(T obj);

}
