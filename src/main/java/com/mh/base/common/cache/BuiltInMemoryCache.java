package com.mh.base.common.cache;

import java.util.List;

import com.mh.base.persist.domain.Function;

public class BuiltInMemoryCache implements CacheManager {
	private static BuiltInMemoryCache instance = new BuiltInMemoryCache();

	private BuiltInMemoryCache() {
	}

	public static BuiltInMemoryCache getInstance() {
		return instance;
	}

	@Override
	public List<Function> getFuncionList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFunctionList(List<Function> functions) {
		// TODO Auto-generated method stub

	}

}
