package com.mh.base.persist.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import com.mh.base.persist.api.DatabaseOperation;
import com.mh.base.persist.util.Pager;

public abstract class MyBatisTemplate<T, K> implements DatabaseOperation<T, K> {

	public T queryOneById(K key) {
		StringBuilder statement = new StringBuilder(32);
		statement.append(getMapperLocatedPackage()).append(".myBatis").append(getSimpleClassName()).append("Mapper.get")
				.append(getSimpleClassName()).append("ById");
		return getSession().selectOne(statement.toString(), key);
	}

	public T queryOneByCondition(T key) {
		StringBuilder statement = new StringBuilder(32);
		statement.append(getMapperLocatedPackage()).append(".myBatis").append(getSimpleClassName()).append("Mapper.get")
				.append(getSimpleClassName()).append("ByCondition");
		List<T> listResult = getSession().selectList(statement.toString(), key);
		return queryListByCondition(key).isEmpty() ? null : listResult.get(0);
	}

	public List<T> queryListByCondition(T key) {
		StringBuilder statement = new StringBuilder(32);
		statement.append(getMapperLocatedPackage()).append(".myBatis").append(getSimpleClassName()).append("Mapper.get")
				.append(getSimpleClassName()).append("ByCondition");
		return getSession().selectList(statement.toString(), key);
	}

	public Pager<T> queryListByPager(T key, Pager<T> pager) {
		StringBuilder statement = new StringBuilder(32);
		statement.append(getMapperLocatedPackage()).append(".myBatis").append(getSimpleClassName()).append("Mapper.get")
				.append(getSimpleClassName()).append("ByPager");
		RowBounds rows = new RowBounds(pager.getOffset(), pager.getLimit());
		List<T> listResult = getSession().selectList(statement.toString(), key, rows);
		pager.setTotalCount(getTotalCount(statement.toString(), key));
		pager.setResultSet(listResult);
		return pager;
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

	@Override
	public int deleteByIds(List<K> keys) {
		StringBuilder statement = new StringBuilder(32);
		statement.append(getMapperLocatedPackage()).append(".myBatis").append(getSimpleClassName())
				.append("Mapper.delete").append(getSimpleClassName()).append("List");
		return getSession().delete(statement.toString(), keys);
	}

	@Override
	public int deleteByIds(K[] keys) {
		StringBuilder statement = new StringBuilder(32);
		statement.append(getMapperLocatedPackage()).append(".myBatis").append(getSimpleClassName())
				.append("Mapper.delete").append(getSimpleClassName()).append("Array");
		return getSession().delete(statement.toString(), keys);
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

	protected String getBaseMapperLocatedPackage() {
		return "com.mh.base.persist.domain";
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

	private long getTotalCount(String statement, T key) {
		long count = 0;
		SqlSession session = this.getSession();
		MappedStatement mst = session.getConfiguration().getMappedStatement(statement);
		BoundSql boundSql = mst.getBoundSql(key);
		String sql = " select count(*) total_count from (" + boundSql.getSql() + ") t ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = session.getConnection().prepareStatement(sql);
			setParameters(pstmt, mst, boundSql, key);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getLong("total_count");
			}
		} catch (SQLException e) {
			e.printStackTrace();// TODO
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace(); // TODO
			}
		}
		return count;
	}

	protected void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql,
			Object parameterObject) throws SQLException {
		ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		if (parameterMappings != null) {
			Configuration configuration = mappedStatement.getConfiguration();
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)
							&& boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value)
									.getValue(propertyName.substring(prop.getName().length()));
						}
					} else {
						value = metaObject == null ? null : metaObject.getValue(propertyName);
					}
					TypeHandler typeHandler = parameterMapping.getTypeHandler();
					if (typeHandler == null) {
						throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName
								+ " of statement " + mappedStatement.getId());
					}
					typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
				}
			}
		}
	}

}
