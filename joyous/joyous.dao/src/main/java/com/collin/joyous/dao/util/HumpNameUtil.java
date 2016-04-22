package com.collin.joyous.dao.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HumpNameUtil {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(HumpNameUtil.class);
	
	public static String firstUpper(String name,String separator){
		return humpName(name, separator, true);
	}

	private static String humpName(String name, String separator,
			boolean firstUpper) {
		if(StringUtils.isBlank(name)){
			return "";
		}
		String [] words = name.toLowerCase().split(separator);
		StringBuffer sb = new StringBuffer();
		String temp = null;
		for (int i = 0; i < words.length; i++) {
			temp = words[i];
			if(i==0 && !firstUpper){
				temp = temp.substring(0,1).toLowerCase() + temp.substring(1,temp.length());
				sb.append(temp);
			}else{
				temp = temp.substring(0,1).toUpperCase() + temp.substring(1,temp.length());
				sb.append(temp);
			}
		}
		return sb.toString();
	}
	
	public static String firstUpper(String name){
		return firstUpper(name,"_");
	}
	
	public static String firstLower(String name){
		return firstLower(name,"_");
	}

	public static String firstLower(String name, String separator) {
		return humpName(name, separator, false);
	}
	
//	public static void main(String[] args) {
//		CommonUtil.p(CommonUtil.humpNameFirstLower("asdf_HL_d"));
//		CommonUtil.p(CommonUtil.humpNameFirstUpper("a_adsf_agggg_"));
//		CommonUtil.p(CommonUtil.humpNameFirstLower(""));
//		CommonUtil.p(CommonUtil.humpNameFirstLower(""));
//	}
}
