package com.collin.joyous.web.mapper;


import java.util.List;

import org.apache.ibatis.session.RowBounds;

public interface BaseMapper<T,TCriteria> {

	int countByCriteria(TCriteria criteria);

	void deleteByCriteria(TCriteria criteria);

	void deleteByPrimaryKey(String sysGuid);

	void insert(T record);

	void insertSelective(T record);

	List<T> selectByCriteriaWithRowbounds(TCriteria criteria, RowBounds rowBounds);

	List<T> selectListByCriteria(TCriteria criteria);

	T selectByPrimaryKey(String primaryKey);
	
	T selectByCriteria(TCriteria criteria);
	
	void updateByCriteriaSelective(T record,TCriteria criteria);

	void updateByCriteria(T record,TCriteria criteria);

	void updateByPrimaryKeySelective(T record);

	void updateByPrimaryKey(T record);
}
