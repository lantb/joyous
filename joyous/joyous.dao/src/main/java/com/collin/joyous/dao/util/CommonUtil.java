package com.collin.joyous.dao.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtil {

	private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);
	
	public static void p(Object obj){
		obj = obj == null?"":obj;
		logger.info(obj.toString());
	}
	
}
