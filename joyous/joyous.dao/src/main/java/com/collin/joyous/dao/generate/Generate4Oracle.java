package com.collin.joyous.dao.generate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.collin.joyous.dao.generate.impl.AbstractGenerate;

public class Generate4Oracle extends AbstractGenerate{
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public String getQueryColumnSql(String tableName) {
		String query_Column_Sql = new StringBuffer("SELECT T1.COLUMN_NAME,T1.DATA_TYPE,T1.DATA_LENGTH,T2.COMMENTS FROM USER_TAB_COLS T1,USER_COL_COMMENTS T2 WHERE T1.TABLE_NAME=T2.TABLE_NAME AND T1.COLUMN_NAME=T2.COLUMN_NAME AND LOWER(T1.TABLE_NAME)= ")
			.append("LOWER('").append(tableName).append("')")
			.toString();
		logger.debug("Generate4Oracle.java-getQueryColumnSql" + query_Column_Sql);
		return query_Column_Sql;
	}

	public static void main(String[] args) {
		Generate4Oracle gc = new Generate4Oracle();
		String dirverName = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:joyous";
		String userName = "joyous";
		String password = "joyous";
		String packageName = "com.collin.dao";
		String ftlName = "dao_mapper_head.ftl";
		String suffixName = "java";
		String tableName = "T_ROLE";
		gc.generateFile(dirverName,url,userName,password,tableName,"d:/",packageName);
	}

}
