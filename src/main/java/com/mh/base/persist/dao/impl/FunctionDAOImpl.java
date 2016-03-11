package com.mh.base.persist.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.base.persist.dao.FunctionDAO;
import com.mh.base.persist.domain.Function;
import com.mh.base.persist.impl.MyBatisTemplate;
import com.mh.base.persist.util.MyBatisHelper;

@Service
public class FunctionDAOImpl extends MyBatisTemplate<Function, Integer> implements FunctionDAO {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public String getSimpleClassName() {
		return "Function";
	}

	@Override
	public SqlSession getSession() {
		return MyBatisHelper.getSession(sqlSessionFactory);
	}

	@Override
	public String getMapperLocatedPackage() {
		return this.getBaseMapperLocatedPackage();
	}
}
