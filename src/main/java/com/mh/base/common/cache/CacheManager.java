package com.mh.base.common.cache;

import java.util.List;

import com.mh.base.persist.domain.Function;

public interface CacheManager {
	
	List<Function> getFuncionList();
	
	void setFunctionList(List<Function> functions);

}
