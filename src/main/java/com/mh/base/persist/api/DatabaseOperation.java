package com.mh.base.persist.api;

import java.util.List;

public interface DatabaseOperation<T, K> {

	T queryOneById(K key);

	List<T> queryAll();

	/**
	 * insert all the properties <br/>
	 * insert into %table% values (%value list%);
	 * 
	 * @param obj
	 * @return
	 */
	int insert(T obj);

	/**
	 * only insert the selected properties <br/>
	 * insert into %table%(column1, column2) values (value1, value2);
	 * 
	 * @param obj
	 * @return
	 */
	int insertSelective(T obj);

	int deleteById(K key);

	/**
	 * update all the properties<br/>
	 * update %table% set %all the columns% where id=%id%
	 * 
	 * @param obj
	 * @return
	 */
	int updateById(T obj);

	/**
	 * only update the selected properties<br/>
	 * update %table% set column1=value1, column2=value2
	 * 
	 * @param obj
	 * @return
	 */
	int updateByIdSelective(T obj);

}
