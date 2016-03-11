package com.mh.proj.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mh.proj.manage.service.${simpleClassName}Service;
import com.mh.proj.persist.dao.${simpleClassName}DAO;
import com.mh.proj.persist.domain.${simpleClassName};
import com.mh.base.persist.util.Pager;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ${simpleClassName}ServiceImpl implements ${simpleClassName}Service {

	@Autowired
	private ${simpleClassName}DAO ${lowerSimpleClassName}DAOImpl;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public ${simpleClassName} get${simpleClassName}(int key) {
		return ${lowerSimpleClassName}DAOImpl.queryOneById(key);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public ${simpleClassName} get${simpleClassName}(${simpleClassName} key) {
		return ${lowerSimpleClassName}DAOImpl.queryOneByCondition(key);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public List<${simpleClassName}> get${simpleClassName}List(${simpleClassName} key) {
		return ${lowerSimpleClassName}DAOImpl.queryAll();
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Pager<${simpleClassName}> get${simpleClassName}Pager(${simpleClassName} key, Pager<${simpleClassName}> pager) {
		return ${lowerSimpleClassName}DAOImpl.queryListByPager(key, pager);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public int update${simpleClassName}(int key, ${simpleClassName} obj) {
		obj.setId(key);
		return ${lowerSimpleClassName}DAOImpl.updateByIdSelective(obj);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public int update${simpleClassName}(${simpleClassName} obj) {
		return ${lowerSimpleClassName}DAOImpl.updateById(obj);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public int delete${simpleClassName}(int key) {
		return ${lowerSimpleClassName}DAOImpl.deleteById(key);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public int delete${simpleClassName}(${simpleClassName} key) {
		return ${lowerSimpleClassName}DAOImpl.deleteById(key.getId());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public int delete${simpleClassName}List(List<Integer> keys) {
		return ${lowerSimpleClassName}DAOImpl.deleteByIds(keys);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public int insert${simpleClassName}(${simpleClassName} obj) {
		return ${lowerSimpleClassName}DAOImpl.insertSelective(obj);
	}

}
