package com.collin.joyous.web.service;

import java.util.List;

public interface IService<T extends Object, TCriteria extends Object> {

	List<T> getListByPage(TCriteria criteria, int index, int size);

	List<T> getList(TCriteria criteria);

	int count(TCriteria criteria);

	T read(TCriteria criteria);

	void insert(T entity);

	void deleteByCriteria(TCriteria criteria);
	
	void deleteByPrimaryKey(String primaryKey);
	
	void updateByCriteria(T entity, TCriteria criteria);

	void updateByCriteriaSelective(T entity, TCriteria criteria);
	
	void updateByPrimaryKeySelective(T entity);

	void updateByPrimaryKey(T entity);
	
	T selectByCriteria(TCriteria criteria);
	
	T selectByPrimaryKey(String primaryKey);
}
