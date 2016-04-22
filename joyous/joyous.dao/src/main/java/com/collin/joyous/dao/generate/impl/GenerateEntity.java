package com.collin.joyous.dao.generate.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.collin.joyous.dao.generate.AbstractGenerateFile;
import com.collin.joyous.dao.generate.ColumnVO;
import com.collin.joyous.dao.util.HumpNameUtil;

public class GenerateEntity extends AbstractGenerateFile{
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	static {
		FTL_NAME = "dao_entity_head.ftl";
		SUFFIX_NAME = "java";
	}
	public GenerateEntity(AbstractGenerate abstractGenerate) {
		super(abstractGenerate);
	}
	@Override
	public Map<String, Object> buildRootMap(String package_Name, String table_Name,
			Map<String, ColumnVO> variableMap) {
		logger.debug("GenerateEntity - buildRootMap");
		
		Map<String, Object> root = new HashMap<String,Object>();
		String className = HumpNameUtil.firstUpper(table_Name);
		root.put("className", className);
		root.put("variableMap", variableMap);
		root.put("annotation", "");
		root.put("superClassName", "");
		root.put("implementNameList", new ArrayList<String>());
		root.put("importList", new ArrayList<String>());
		root.put("packageName", package_Name+".entity");
		return root;
	}
	@Override
	public String getFileName(String entityName) {
		return entityName;
	}
	
//	public static void main(String[] args) {
//		IGenerateClass gc = new GenerateEntity();
//		String dirverName = "oracle.jdbc.OracleDriver";
//		String url = "jdbc:oracle:thin:@127.0.0.1:1521:joyous";
//		String userName = "joyous";
//		String password = "joyous";
//		String packageName = "com.collin.dao";
//		String ftlName = "dao_entity_head.ftl";
//		String suffixName = "java";
//		String tableName = "T_ROLE";
//		gc.generateFile(dirverName,url,userName,password,tableName,"d:/",packageName,ftlName,suffixName);
//	}

}
