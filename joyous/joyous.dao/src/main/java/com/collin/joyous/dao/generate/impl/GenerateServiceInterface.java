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

public class GenerateServiceInterface extends AbstractGenerateFile{
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	static {
		FTL_NAME = "service_interface_head.ftl";
		SUFFIX_NAME = "java";
	}
	public GenerateServiceInterface(AbstractGenerate abstractGenerate) {
		super(abstractGenerate);
	}
	@Override
	public Map<String, Object> buildRootMap(String package_Name, String table_Name,
			Map<String, ColumnVO> variableMap) {
		logger.debug("GenerateServiceImpl - buildRootMap");
		
		Map<String, Object> root = new HashMap<String,Object>();
		
		String entityName = HumpNameUtil.firstUpper(table_Name);
		String className = "I" + entityName + "Service";

		List<String> importList = new ArrayList<String>();
		
		importList.add(package_Name + ".entity." + entityName);
		importList.add(package_Name + ".entity." + entityName + "Criteria");
		
		root.put("superClassName", "IService<"+entityName+", "+entityName+"Criteria>");
		
		root.put("implementNameList", new ArrayList<String>());
		root.put("importList", importList);
		root.put("packageName", package_Name+".service");
		root.put("className", className);
		
		return root;
	}
	@Override
	public String getFileName(String entityName) {
		return "I" + entityName + "Service";
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
