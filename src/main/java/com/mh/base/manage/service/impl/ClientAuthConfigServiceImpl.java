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
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public ClientAuthConfig getClientAuthConfig(int key) {
		return clientAuthConfigDAOImpl.queryOneById(key);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public ClientAuthConfig getClientAuthConfig(ClientAuthConfig key) {
		return clientAuthConfigDAOImpl.queryOneByCondition(key);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<ClientAuthConfig> getClientAuthConfigList(ClientAuthConfig key) {
		return clientAuthConfigDAOImpl.queryAll();
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Pager<ClientAuthConfig> getClientAuthConfigPager(ClientAuthConfig key, Pager<ClientAuthConfig> pager) {
		return clientAuthConfigDAOImpl.queryListByPager(key, pager);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public int updateClientAuthConfig(int key, ClientAuthConfig obj) {
		obj.setId(key);
		return clientAuthConfigDAOImpl.updateByIdSelective(obj);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public int updateClientAuthConfig(ClientAuthConfig obj) {
		return clientAuthConfigDAOImpl.updateById(obj);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public int deleteClientAuthConfig(int key) {
		return clientAuthConfigDAOImpl.deleteById(key);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public int deleteClientAuthConfig(ClientAuthConfig key) {
		return clientAuthConfigDAOImpl.deleteById(key.getId());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public int deleteClientAuthConfigList(List<Integer> keys) {
		return clientAuthConfigDAOImpl.deleteByIds(keys);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public int insertClientAuthConfig(ClientAuthConfig obj) {
		return this.clientAuthConfigDAOImpl.insertSelective(obj);
	}

}
