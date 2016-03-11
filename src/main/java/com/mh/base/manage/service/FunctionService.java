package com.mh.base.manage.service;

import java.util.List;

import com.mh.base.persist.domain.Function;

public interface FunctionService {

	List<Function> getFunctionList();
	
	List<Function> getFunctionList(int level);

	int addFunction(Function function);

}
