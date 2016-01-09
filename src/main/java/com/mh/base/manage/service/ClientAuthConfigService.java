package com.mh.base.manage.service;

import java.util.List;

import com.mh.base.persist.domain.ClientAuthConfig;
import com.mh.base.persist.util.Pager;

public interface ClientAuthConfigService {

	ClientAuthConfig getClientAuthConfig(int key);

	ClientAuthConfig getClientAuthConfig(ClientAuthConfig key);

	List<ClientAuthConfig> getClientAuthConfigList(ClientAuthConfig key);

	Pager<ClientAuthConfig> getClientAuthConfigPager(ClientAuthConfig key);

	int updateClientAuthConfig(int key, ClientAuthConfig obj);

	int updateClientAuthConfig(ClientAuthConfig key, ClientAuthConfig obj);

	int deleteClientAuthConfig(int id);

	int deleteClientAuthConfig(ClientAuthConfig key);

	int insertClientAuthConfig(ClientAuthConfig obj);

}
