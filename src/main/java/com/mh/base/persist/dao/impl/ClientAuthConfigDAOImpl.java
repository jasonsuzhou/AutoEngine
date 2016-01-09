package com.mh.base.persist.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.base.persist.dao.ClientAuthConfigDAO;
import com.mh.base.persist.domain.ClientAuthConfig;
import com.mh.base.persist.impl.MyBatisTemplate;
import com.mh.base.persist.util.MyBatisHelper;

@Service
public class ClientAuthConfigDAOImpl extends MyBatisTemplate<ClientAuthConfig, Integer> implements ClientAuthConfigDAO {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public String getSimpleClassName() {
		return "ClientAuthConfig";
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