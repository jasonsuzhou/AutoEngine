package com.mh.base.persist.impl;

import java.util.List;

import com.mh.base.persist.api.DatabaseOperation;
import com.mh.base.persist.util.Pager;

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

	public T queryOneByCondition(T key) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> queryListByCondition(T key) {
		// TODO Auto-generated method stub
		return null;
	}

	public Pager<T> queryListByPager(T key, Pager<T> pager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteByIds(List<K> keys) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByIds(K[] keys) {
		// TODO Auto-generated method stub
		return 0;
	}

}
