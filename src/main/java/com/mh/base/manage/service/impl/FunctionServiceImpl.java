package com.mh.base.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.mh.base.common.cache.CacheManager;
import com.mh.base.common.cache.CacheManagerFactory;
import com.mh.base.manage.service.FunctionService;
import com.mh.base.persist.dao.FunctionDAO;
import com.mh.base.persist.domain.Function;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class FunctionServiceImpl implements FunctionService {

	@Autowired
	private FunctionDAO functionDAOImpl;

	private CacheManager cache = CacheManagerFactory.getCacheManager();

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Function> getFunctionList() {
		List<Function> funcList = cache.getFuncionList();
		if (funcList == null) {
			Function function = new Function();
			function.setLevel(1);
			funcList = functionDAOImpl.queryListByCondition(function);
		}
		return funcList;
	}
	
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<Function> getFunctionList(int level) {
		Function function = new Function();
		function.setLevel(level);
		return functionDAOImpl.queryListByCondition(function);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int addFunction(Function function) {
		int level = function.getLevel();
		if (level < 1) {
			return 10012;
		}
		if (StringUtils.isEmpty(level)) {

		}
		String link = function.getLink();
		if (!StringUtils.isEmpty(link)) {
			Function key = new Function();
			key.setLink(function.getLink());
			key.setLevel(function.getLevel());
			Function func = this.functionDAOImpl.queryOneByCondition(key);
			if (func != null) {
				return 10010;
			}
		}
		Function key = new Function();
		key.setName(function.getName());
		key.setLevel(level);
		Function func = this.functionDAOImpl.queryOneByCondition(key);
		if (func != null) {
			return 10011;
		}
		function.initDate();
		return this.functionDAOImpl.insertSelective(function);
	}

}
