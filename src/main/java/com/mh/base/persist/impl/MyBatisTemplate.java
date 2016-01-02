package com.mh.base.persist.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.mh.base.persist.api.DatabaseOperation;

public abstract class MyBatisTemplate<T, K> implements DatabaseOperation<T, K> {

	public T queryOneById(K key) {
		StringBuilder statement = new StringBuilder(32);
		statement.append(getMapperLocatedPackage()).append(".myBatis").append(getSimpleClassName()).append("Mapper.get")
				.append(getSimpleClassName()).append("ById");
		return getSession().selectOne(statement.toString(), key);
	}

	public List<T> queryAll() {
		StringBuilder statement = new StringBuilder(32);
		statement.append(getMapperLocatedPackage()).append(".myBatis").append(getSimpleClassName())
				.append("Mapper.getAll").append(getSimpleClassName()).append("List");
		return getSession().selectList(statement.toString());
	}

	public int insert(T obj) {
		StringBuilder statement = new StringBuilder(32);
		statement.append(getMapperLocatedPackage()).append(".myBatis").append(getSimpleClassName()).append("Mapper.add")
				.append(getSimpleClassName());
		return getSession().insert(statement.toString(), obj);
	}

	public int deleteById(K key) {
		StringBuilder statement = new StringBuilder(32);
		statement.append(getMapperLocatedPackage()).append(".myBatis").append(getSimpleClassName())
				.append("Mapper.delete").append(getSimpleClassName()).append("ById");
		return getSession().delete(statement.toString(), key);
	}

	public int updateById(T obj) {
		StringBuilder statement = new StringBuilder(32);
		statement.append(getMapperLocatedPackage()).append(".myBatis").append(getSimpleClassName())
				.append("Mapper.update").append(getSimpleClassName()).append("ById");
		return getSession().update(statement.toString(), obj);
	}

	public String getMapperLocatedPackage() {
		return "com.mh.proj.persist.domain";
	}

	public int insertSelective(T obj) {
		StringBuilder statement = new StringBuilder(32);
		statement.append(getMapperLocatedPackage()).append(".myBatis").append(getSimpleClassName()).append("Mapper.add")
				.append(getSimpleClassName()).append("Selective");
		return getSession().insert(statement.toString(), obj);
	}

	public int updateByIdSelective(T obj) {
		StringBuilder statement = new StringBuilder(32);
		statement.append(getMapperLocatedPackage()).append(".myBatis").append(getSimpleClassName())
				.append("Mapper.update").append(getSimpleClassName()).append("ByIdSelective");
		return getSession().update(statement.toString(), obj);
	}

	public abstract String getSimpleClassName();

	public abstract SqlSession getSession();

}
