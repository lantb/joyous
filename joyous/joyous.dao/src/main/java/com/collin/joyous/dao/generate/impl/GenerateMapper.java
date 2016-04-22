package com.collin.joyous.dao.generate.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.collin.joyous.dao.generate.AbstractGenerateFile;
import com.collin.joyous.dao.generate.ColumnVO;
import com.collin.joyous.dao.util.HumpNameUtil;

public class GenerateMapper extends AbstractGenerateFile{
	private Logger logger = LoggerFactory.getLogger(getClass());
	static {
		FTL_NAME = "dao_mapper_head.ftl";
		SUFFIX_NAME = "java";
	}
	
	public GenerateMapper(AbstractGenerate abstractGenerate) {
		super(abstractGenerate);
	}
	
	@Override
	public Map<String, Object> buildRootMap(String package_Name, String table_Name,
			Map<String, ColumnVO> variableMap) {
		logger.debug("GenerateMapper - buildRootMap");
		
		Map<String, Object> root = new HashMap<String,Object>();
		
		String entityName = HumpNameUtil.firstUpper(table_Name);
		String className = entityName+"Mapper";
		String criteriaName = entityName + "Criteria";
		root.put("className", className);

		String superClassName = "BaseMapper<#1,#2>";
		superClassName = superClassName.replace("#1", entityName);
		superClassName = superClassName.replace("#2", criteriaName);
		
		root.put("superClassName", superClassName);
		
		List<String> importList = new ArrayList<String>();
		importList.add(package_Name + ".entity." + entityName);
		importList.add(package_Name + ".entity." + criteriaName);
		
		root.put("implementNameList", new ArrayList<String>());
		
		root.put("importList", importList);
		root.put("packageName", package_Name+".Mapper");
		return root;
	}
	@Override
	public String getFileName(String entityName) {
		return entityName + "Mapper";
	}
	/*public static void main(String[] args) {
		IGenerateClass gc = new GenerateMapper();
		String dirverName = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:joyous";
		String userName = "joyous";
		String password = "joyous";
		String packageName = "com.collin.dao";
		String ftlName = "dao_mapper_head.ftl";
		String suffixName = "java";
		String tableName = "T_ROLE";
		gc.generateFile(dirverName,url,userName,password,tableName,"d:/",packageName,ftlName,suffixName);
	}*/

}
