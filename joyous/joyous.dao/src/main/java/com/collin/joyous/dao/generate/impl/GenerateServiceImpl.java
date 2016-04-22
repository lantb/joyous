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

public class GenerateServiceImpl extends AbstractGenerateFile{
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	static {
		FTL_NAME = "service_impl_head.ftl";
		SUFFIX_NAME = "java";
	}
	public GenerateServiceImpl(AbstractGenerate abstractGenerate) {
		super(abstractGenerate);
	}
	@Override
	public Map<String, Object> buildRootMap(String package_Name, String table_Name,
			Map<String, ColumnVO> variableMap) {
		logger.debug("GenerateServiceImpl - buildRootMap");
		
		Map<String, Object> root = new HashMap<String,Object>();
		
		String entityName = HumpNameUtil.firstUpper(table_Name);
		String className = entityName + "ServiceImpl";

		List<String> importList = new ArrayList<String>();
		
		importList.add("javax.annotation.Resource");
		importList.add("org.springframework.stereotype.Service");
		importList.add(package_Name + ".entity." + entityName);
		importList.add(package_Name + ".entity." + entityName + "Criteria");
		importList.add(package_Name + ".mapper.BaseMapper");
		importList.add(package_Name + ".mapper." + entityName + "Mapper");
		importList.add(package_Name + ".service.I" + entityName + "Service");
		
		root.put("superClassName", "BaseService<"+entityName+", "+entityName+"Criteria>");
		List<String> implementNameList = new ArrayList<String>();
		implementNameList.add("I" + entityName + "Service");
		root.put("implementNameList", implementNameList);
		root.put("importList", importList);
		root.put("packageName", package_Name+".service.impl");
		root.put("className", className + "ServiceImpl");
		root.put("entityName", entityName);
		root.put("annotation", "Service");
		
		return root;
	}
	@Override
	public String getFileName(String entityName) {
		return entityName + "ServiceImpl";
	}
//	public static void main(String[] args) {
//		IGenerateClass gc = new GenerateServiceImpl();
//		String dirverName = "oracle.jdbc.OracleDriver";
//		String url = "jdbc:oracle:thin:@127.0.0.1:1521:joyous";
//		String userName = "joyous";
//		String password = "joyous";
//		String packageName = "com.collin.dao";
//		String ftlName = "service_impl_head.ftl";
//		String suffixName = "java";
//		String tableName = "T_ROLE";
//		gc.generateFile(dirverName,url,userName,password,tableName,"d:/",packageName,ftlName,suffixName);
//	}

}
