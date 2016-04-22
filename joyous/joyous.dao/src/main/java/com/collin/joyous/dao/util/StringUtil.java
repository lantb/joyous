package com.collin.joyous.dao.util;

import org.apache.commons.lang.StringUtils;

public class StringUtil {

	public static boolean include(String word,String str) {
		if(word == null){
			return false;
		}
		if(str == null){
			return false;
		}
		return word.toUpperCase().indexOf(str) > -1;
	}
	
	public static String empty2String(String str){
		if(StringUtils.isBlank(str)){
			return "";
		}
		return str.trim();
	}
}
