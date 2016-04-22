package com.collin.joyous.dao.generate;

import java.util.Map;

import com.collin.joyous.dao.generate.impl.AbstractGenerate;
import com.collin.joyous.dao.util.HumpNameUtil;


/**
 * 
 * @author Collin Lan
 * @name IGenerateFile
 * @user AB044616
 * @date 2014-11-13 上午9:55:38
 */
public abstract class AbstractGenerateFile implements IGenerateFile{
	
	private AbstractGenerate abstractGenerate = null;
	public AbstractGenerateFile(AbstractGenerate abstractGenerate) {
		this.abstractGenerate = abstractGenerate;
	}
	
	protected static String FTL_NAME = null;
	protected static String SUFFIX_NAME = null;
	public abstract String getFileName(String entityName);
	
	
	public String getSuffixName(){
		return SUFFIX_NAME;
	}
	public String getFtlName(){
		return FTL_NAME;
	}
	public abstract Map<String, Object> buildRootMap(String packageName, String tableName,
			Map<String, ColumnVO> variableMap);
	
	public void generateFile(String tableName, String generateFilePath,
			String packageName) {
		Map<String, Object> rootMap = this.buildRootMap(packageName, tableName, abstractGenerate.getColumnMap());
		String entityName = HumpNameUtil.firstUpper(tableName);
		abstractGenerate.writeFile(generateFilePath, this.getFtlName(), this.getFileName(entityName), this.getSuffixName(), rootMap);
	}
}
