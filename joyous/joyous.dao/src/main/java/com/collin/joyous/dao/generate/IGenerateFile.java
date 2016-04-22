package com.collin.joyous.dao.generate;

import java.util.Map;

/**
 * 
 * @author Collin Lan
 * @name IGenerateFile
 * @user AB044616
 * @date 2014-11-13 上午9:55:38
 */
public interface IGenerateFile {
	String getFileName(String entityName);
	String getSuffixName();
	String getFtlName();
	Map<String, Object> buildRootMap(String packageName, String tableName,
			Map<String, ColumnVO> variableMap);
	void generateFile(String tableName, String generateFilePath,
			String packageName);
}
