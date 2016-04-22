package com.collin.joyous.web.entity;

import java.util.List;

public class Criterion {

	private String condition;
	private String value;
	private String value2;
	private List<String> values;
	private boolean noValue;
	private boolean singleValue;
	private boolean betweenValue;
	private boolean listValue;
	
	public Criterion(String condition, String value) {
		this.condition = condition;
		this.value = value;
		this.singleValue = true;
	}
	
	public Criterion(String condition, String value1,String value2) {
		this.condition = condition;
		this.value = value1;
		this.value2 = value2;
		this.betweenValue = true;
	}
	
	public Criterion(String condition, List<String> values) {
		this.condition = condition;
		this.values = values;
		this.listValue = true;
	}
	
	public Criterion(String condition) {
		this.condition = condition;
		this.noValue = true;
	}
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public boolean isNoValue() {
		return noValue;
	}

	public void setNoValue(boolean noValue) {
		this.noValue = noValue;
	}

	public boolean isSingleValue() {
		return singleValue;
	}

	public void setSingleValue(boolean singleValue) {
		this.singleValue = singleValue;
	}

	public boolean isBetweenValue() {
		return betweenValue;
	}

	public void setBetweenValue(boolean betweenValue) {
		this.betweenValue = betweenValue;
	}

	public boolean isListValue() {
		return listValue;
	}

	public void setListValue(boolean listValue) {
		this.listValue = listValue;
	}

}
