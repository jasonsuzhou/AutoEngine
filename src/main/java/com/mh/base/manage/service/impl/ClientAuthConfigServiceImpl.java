package com.mh.base.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mh.base.manage.service.ClientAuthConfigService;
import com.mh.base.persist.dao.ClientAuthConfigDAO;
import com.mh.base.persist.domain.ClientAuthConfig;
import com.mh.base.persist.util.Pager;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ClientAuthConfigServiceImpl implements ClientAuthConfigService {

	@Autowired
	private ClientAuthConfigDAO clientAuthConfigDAOImpl;

	@Override
	public ClientAuthConfig getClientAuthConfig(int key) {
		return clientAuthConfigDAOImpl.queryOneById(key);
	}

	@Override
	public ClientAuthConfig getClientAuthConfig(ClientAuthConfig key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClientAuthConfig> getClientAuthConfigList(ClientAuthConfig key) {
		return this.clientAuthConfigDAOImpl.queryAll();
	}

	@Override
	public Pager<ClientAuthConfig> getClientAuthConfigPager(ClientAuthConfig key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateClientAuthConfig(int key, ClientAuthConfig obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateClientAuthConfig(ClientAuthConfig key, ClientAuthConfig obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteClientAuthConfig(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteClientAuthConfig(ClientAuthConfig key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public int insertClientAuthConfig(ClientAuthConfig obj) {
		return this.clientAuthConfigDAOImpl.insertSelective(obj);
	}

}
