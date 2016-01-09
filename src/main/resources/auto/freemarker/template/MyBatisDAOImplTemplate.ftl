package com.mh.proj.persist.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.mh.base.persist.impl.MyBatisTemplate;
import com.mh.base.persist.util.MyBatisHelper;
import com.mh.proj.persist.dao.${simpleClassName}DAO;
import com.mh.proj.persist.domain.${simpleClassName};

@Service
public class ${simpleClassName}DAOImpl extends MyBatisTemplate<${simpleClassName}, Integer> implements ${simpleClassName}DAO {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public String getSimpleClassName() {
		return "${simpleClassName}";
	}

	@Override
	public SqlSession getSession() {
		//return MyBatisHelper.getSession(true);
		return MyBatisHelper.getSession(sqlSessionFactory);
	}
	
}