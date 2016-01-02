package com.mh.base.persist.impl;

import java.util.List;

import com.mh.base.persist.api.DatabaseOperation;

public class HibernateTemplate<T, K> implements DatabaseOperation<T, K> {

	public T queryOneById(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	public int insert(T obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteById(K key) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateById(T obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<T> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public int insertSelective(T obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByIdSelective(T obj) {
		// TODO Auto-generated method stub
		return 0;
	}

}
