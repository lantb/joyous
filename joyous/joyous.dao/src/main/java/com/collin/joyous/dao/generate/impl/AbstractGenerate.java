package com.collin.joyous.dao.generate.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.collin.joyous.dao.generate.ColumnVO;
import com.collin.joyous.dao.generate.IGenerateFile;
import com.collin.joyous.dao.util.HumpNameUtil;
import com.collin.joyous.dao.util.JDBCUtil;
import com.collin.joyous.dao.util.StringUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public abstract class AbstractGenerate {
	
	private static Logger logger = LoggerFactory.getLogger(AbstractGenerate.class);
	
	protected String db_config_dirverName;
	protected String db_config_url; 
	protected String db_config_userName; 
	protected String db_config_password;
	
	protected Map<String, Object> ROOT_MAP = new HashMap<String, Object>();
	
	protected Map<String,ColumnVO> columnMap = new HashMap<String,ColumnVO>();
	public Map<String, ColumnVO> getColumnMap() {
		return columnMap;
	}
	protected List<String> entityImportList = new ArrayList<String>();
	
	
	private static Configuration cfg = null;
	static{
		cfg = new Configuration();
		cfg.setDefaultEncoding("UTF-8");
		String path;
		try {
			path = new File(AbstractGenerate.class.getResource("").getPath()).getParentFile().getParentFile().getCanonicalPath();
			cfg.setDirectoryForTemplateLoading(new File(path+"/templates"));
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		
	}
	public void initDBConfig(String db_config_dirverName, 
			String db_config_url, String db_config_userName, 
			String db_config_password){
		this.db_config_dirverName = db_config_dirverName;
		this.db_config_url = db_config_url;
		this.db_config_userName = db_config_userName;
		this.db_config_password = db_config_password;
	}
	
	public void generateFile(String dirverName, String url, String userName, 
			String password, String tableName, String generateFilePath, 
			String packageName) {
		initDBConfig(dirverName, url, userName, password);
		initColumnMap(dirverName, url, userName, password, tableName);
		
		IGenerateFile gf = new GenerateMapper(this);
		gf.generateFile(tableName, generateFilePath, packageName);
		gf = new GenerateEntity(this);
		gf.generateFile(tableName, generateFilePath, packageName);
		gf = new GenerateServiceImpl(this);
		gf.generateFile(tableName, generateFilePath, packageName);
		gf = new GenerateSQLXML(this);
		gf.generateFile(tableName, generateFilePath, packageName);
		gf = new GenerateServiceInterface(this);
		gf.generateFile(tableName, generateFilePath, packageName);
	}

	public void writeFile(String generateFilePath,String ftlName,
			String fileName,String suffixName,Map<String, Object> rootMap){
		try {
			Template temp = cfg.getTemplate(ftlName);
			FileWriter fw = new FileWriter(new File(generateFilePath+fileName+"."+suffixName));
			temp.process(rootMap, fw);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (TemplateException e) {
			logger.error(e.getMessage());
		}
	}
	
	public void initColumnMap(String db_config_dirverName,String db_config_url,
			String db_config_userName,String db_config_password,String table_Name) {
		
		JDBCUtil.init(db_config_dirverName,db_config_url,db_config_userName,db_config_password);
		Connection conn = JDBCUtil.createConnection();
		String sql = getQueryColumnSql(table_Name);
		
		PreparedStatement pst = JDBCUtil.createPreparedStatement(conn, sql);
		ResultSet rs = null;
		columnMap = new HashMap<String,ColumnVO>();
		try {
			logger.debug(sql);
			rs = pst.executeQuery(sql);
			ColumnVO cvo = null;
			while(rs.next()){
				cvo = new ColumnVO();
				String columnName = rs.getString("COLUMN_NAME");
				cvo.setColumnName(columnName);
				cvo.setColumnHumpName(HumpNameUtil.firstUpper(columnName));
				String dataTypeDB = rs.getString("DATA_TYPE");
				cvo.setDataType(this.tranColumnDataType(dataTypeDB));
				cvo.setDataTypeDB(dataTypeDB);
				cvo.setDataLength(rs.getString("DATA_LENGTH"));
				cvo.setComments(StringUtil.empty2String(rs.getString("COMMENTS")));
				
				initImportList(dataTypeDB);
				columnMap.put(HumpNameUtil.firstLower(columnName), cvo);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}
	
	public abstract String getQueryColumnSql(String table_Name);

	private void initImportList(String dataTypeDB){
		if(StringUtil.include(dataTypeDB,"FLOAT")
				|| StringUtil.include(dataTypeDB,"DECIMAL")
				|| StringUtil.include(dataTypeDB,"DOUBLE")){
			if(!entityImportList.contains("java.math.BigDecimal")){
				entityImportList.add("java.math.BigDecimal");
			}
		}else if(StringUtil.include(dataTypeDB,"DATE")
				|| StringUtil.include(dataTypeDB,"YEAR")
				|| StringUtil.include(dataTypeDB,"DAY")
				|| StringUtil.include(dataTypeDB,"TIMES")
				|| StringUtil.include(dataTypeDB,"DOUBLE")){
			if(!entityImportList.contains("java.util.Date")){
				entityImportList.add("java.util.Date");
			}
		}
	}
	private String tranColumnDataType(String dataTypeDB) {
		
		if(StringUtil.include(dataTypeDB,"CHAR")){
			return "String";
		}else if(StringUtil.include(dataTypeDB,"LONG")){
			return "long";
		}else if(StringUtil.include(dataTypeDB,"NUMBER")
				|| StringUtil.include(dataTypeDB,"INT")){
			return "int";
		}else if(StringUtil.include(dataTypeDB,"FLOAT")
				|| StringUtil.include(dataTypeDB,"DECIMAL")
				|| StringUtil.include(dataTypeDB,"DOUBLE")){
			return "BigDecimal";
		}else if(StringUtil.include(dataTypeDB,"DATE")
				|| StringUtil.include(dataTypeDB,"YEAR")
				|| StringUtil.include(dataTypeDB,"DAY")
				|| StringUtil.include(dataTypeDB,"TIMES")
				|| StringUtil.include(dataTypeDB,"DOUBLE")){
			return "Date";
		}else {
			return "String";
		}
	}
}
