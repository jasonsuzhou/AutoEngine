package com.mh.proj.manage.service;

import java.util.List;

import com.mh.proj.persist.domain.${simpleClassName};
import com.mh.base.persist.util.Pager;

public interface ${simpleClassName}Service {

	${simpleClassName} get${simpleClassName}(int key);

	${simpleClassName} get${simpleClassName}(${simpleClassName} key);

	List<${simpleClassName}> get${simpleClassName}List(${simpleClassName} key);

	Pager<${simpleClassName}> get${simpleClassName}Pager(${simpleClassName} key, Pager<${simpleClassName}> pager);

	int update${simpleClassName}(int key, ${simpleClassName} obj);

	int update${simpleClassName}(${simpleClassName} obj);

	int delete${simpleClassName}(int key);

	int delete${simpleClassName}(${simpleClassName} key);

	int delete${simpleClassName}List(List<Integer> keys);

	int insert${simpleClassName}(${simpleClassName} obj);

}
