package com.collin.joyous.web.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TUserCriteria implements Serializable {

	public enum TUserColumnName{
		USERNAME,
		PASSWORD
	}
	public TUserCriteria() {
		super();
	}
	
	public TUserCriteria(String orderByClause,
			String distinct) {
		super();
		this.orderByClause = orderByClause;
		this.distinct = distinct;
	}

	private static final long serialVersionUID = 1L;
	
	private String orderByClause;
	
	private String distinct;
	
	private List<Criterion> criteria = new ArrayList<Criterion>();

	public String getDistinct() {
		return distinct;
	}

	public void setDistinct(String distinct) {
		this.distinct = distinct;
	}

	public String getOrderByClause() {
		return orderByClause;
	}

	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}
	
	public TUserCriteria andEquals(String columnName, String value) {
		addCriterion(columnName + " = ",value);
		return this;
	}

	public TUserCriteria andNotEquals(String columnName, String value) {
		addCriterion(columnName + " <> ",value);
		return this;
	}
	
	public TUserCriteria andLessThan(String columnName, String value) {
		addCriterion(columnName + " < ",value);
		return this;
	}

	public TUserCriteria andLessThanOrEquals(String columnName, String value) {
		addCriterion(columnName + " <= ",value);
		return this;
	}

	public TUserCriteria andGreaterThan(String columnName, String value) {
		addCriterion(columnName + " > ",value);
		return this;
	}

	public TUserCriteria andGreaterThanOrEquals(String columnName, String value) {
		addCriterion(columnName + " >= ",value);
		return this;
	}
	
	public TUserCriteria andLike(String columnName, String value) {
		addCriterion(columnName + " like ",value);
		return this;
	}
	
	public TUserCriteria andNotLike(String columnName, String value) {
		addCriterion(columnName + " not like ",value);
		return this;
	}
	
	public TUserCriteria andIn(String columnName, List<String> values) {
		addCriterion(columnName + " in ",values);
		return this;
	}
	
	public TUserCriteria andNotIn(String columnName, List<String> values) {
		addCriterion(columnName + " not in ",values);
		return this;
	}

	public TUserCriteria andStartWith(String columnName, String value) {
		addCriterion(columnName + " like ",value+"%");
		return this;
	}
	
	public TUserCriteria andEndWith(String columnName, String value) {
		addCriterion(columnName + " like ","%"+value);
		return this;
	}
	
	public TUserCriteria andIsNull(String columnName) {
		addCriterion(columnName + " is null ");
		return this;
	}
	
	public TUserCriteria andIsNotNull(String columnName) {
		addCriterion(columnName + " is not null ");
		return this;
	}

	public TUserCriteria andBetween(String columnName, String value1,String value2) {
		addCriterion(columnName + " between",value1,value2);
		return this;
	}
	
	public TUserCriteria andNotBetween(String columnName, String value1,String value2) {
		addCriterion(columnName + " not between",value1,value2);
		return this;
	}
	
	private void addCriterion(String condition, String value) {
		criteria.add(new Criterion(condition,value));
	}
	
	private void addCriterion(String condition, String value1,String value2) {
		criteria.add(new Criterion(condition,value1,value2));
	}
	
	private void addCriterion(String condition, List<String> values) {
		criteria.add(new Criterion(condition,values));
	}
	
	private void addCriterion(String condition) {
		criteria.add(new Criterion(condition));
	}
}
