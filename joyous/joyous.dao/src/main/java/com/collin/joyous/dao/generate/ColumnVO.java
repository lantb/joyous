package com.collin.joyous.dao.generate;

public class ColumnVO {
	
	private String columnName = null;
	private String columnHumpName = null;
	private String dataType = null;
	private String dataTypeDB = null;
	private String dataLength = null;
	private String comments = null;
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getDataTypeDB() {
		return dataTypeDB;
	}
	public void setDataTypeDB(String dataTypeDB) {
		this.dataTypeDB = dataTypeDB;
	}
	public String getDataLength() {
		return dataLength;
	}
	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getColumnHumpName() {
		return columnHumpName;
	}
	public void setColumnHumpName(String columnHumpName) {
		this.columnHumpName = columnHumpName;
	}
}
