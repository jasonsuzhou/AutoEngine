package com.mh.proj.persist.dao.impl;

import org.apache.ibatis.session.SqlSession;

import com.mh.base.persist.impl.MyBatisTemplate;
import com.mh.base.persist.util.MyBatisHelper;
import com.mh.proj.persist.dao.${simpleClassName}DAO;
import com.mh.proj.persist.domain.${simpleClassName};

public class ${simpleClassName}DAOImpl extends MyBatisTemplate<${simpleClassName}, Integer> implements ${simpleClassName}DAO {

	@Override
	public String getSimpleClassName() {
		return "${simpleClassName}";
	}

	@Override
	public SqlSession getSession() {
		return MyBatisHelper.getSession(true);
	}
	
}