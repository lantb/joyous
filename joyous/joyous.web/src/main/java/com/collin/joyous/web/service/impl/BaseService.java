package com.collin.joyous.web.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.collin.joyous.web.mapper.BaseMapper;
import com.collin.joyous.web.service.IService;

public class BaseService <T extends Object, TCriteria extends Object>
	implements IService<T, TCriteria> {

	@Override
	public List<T> getListByPage(TCriteria criteria, int index, int size) {
		RowBounds rBounds = new RowBounds(index, size);
		return getMapper().selectByCriteriaWithRowbounds(criteria, rBounds);
	}

	@Override
	public List<T> getList(TCriteria criteria) {
		return getMapper().selectListByCriteria(criteria);
	}

	@Override
	public int count(TCriteria criteria) {
		return getMapper().countByCriteria(criteria);
	}

	@Override
	public T read(TCriteria criteria) {
		return getMapper().selectByCriteria(criteria);
	}

	@Override
	public void insert(T entity) {
		getMapper().insert(entity);
	}

	@Override
	public void deleteByCriteria(TCriteria criteria) {
		getMapper().deleteByCriteria(criteria);
	}

	@Override
	public void updateByCriteria(T entity, TCriteria criteria) {
		getMapper().updateByCriteria(entity, criteria);
	}

	@Override
	public void updateByCriteriaSelective(T entity, TCriteria criteria) {
		getMapper().updateByCriteriaSelective(entity, criteria);
	}
	
	@Override
	public T selectByCriteria(TCriteria criteria){
		return getMapper().selectByCriteria(criteria);
	}
	
	@Override
	public T selectByPrimaryKey(String primaryKey){
		return getMapper().selectByPrimaryKey(primaryKey);
	}
	
	@Override
	public void deleteByPrimaryKey(String primaryKey){
		getMapper().deleteByPrimaryKey(primaryKey);
	}
	

	@Override
	public void updateByPrimaryKeySelective(T entity) {
		getMapper().updateByPrimaryKeySelective(entity);
	}

	@Override
	public void updateByPrimaryKey(T entity) {
		getMapper().updateByPrimaryKey(entity);
	}
	
	public BaseMapper<T, TCriteria> getMapper() {
		return null;
	}
}