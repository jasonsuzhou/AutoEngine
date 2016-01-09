package com.mh.base.web.springboot.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mh.base.manage.service.ClientAuthConfigService;
import com.mh.base.persist.domain.ClientAuthConfig;
import com.mh.base.persist.util.JSON;

@RestController // @Controller and @ResponseBody
// @EnableAutoConfiguration
@RequestMapping("/cli-auth")
public class ClientAuthConfigController {

	@Autowired
	private ClientAuthConfigService clientAuthConfigServiceImpl;

	@RequestMapping(method = { RequestMethod.GET })
	public String getAllAuthConfig() {
		return JSON.convertToJSONString(clientAuthConfigServiceImpl.getClientAuthConfigList(null));
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public String getAuthConfig(@PathVariable("id") int id) {
		return JSON.convertToJSONString(clientAuthConfigServiceImpl.getClientAuthConfig(id));
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT })
	public String addAuthConfig(@PathVariable("id") int id) {
		ClientAuthConfig authConfig = new ClientAuthConfig();
		authConfig.setAppId(String.valueOf(new Random().nextInt(10000)));
		authConfig.setAppSecret(String.valueOf(new Random().nextInt(10000)));
		authConfig.setNonce(String.valueOf(new Random().nextInt(10000)));
		clientAuthConfigServiceImpl.insertClientAuthConfig(authConfig);
		return "success";
	}

}
