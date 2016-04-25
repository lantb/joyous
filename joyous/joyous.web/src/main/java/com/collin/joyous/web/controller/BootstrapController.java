package com.collin.joyous.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 
 * @author Collin Lan
 * @name LoginController
 * @user AB044616
 * @date 2015-1-13  下午2:05:04
 */
@Controller
public class BootstrapController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@RequestMapping(value="bootstrap")  
    public String saveInit(Model model){  
        logger.info("bootstrap.jsp");
        return "bootstrap/bootstrap_test";
    }
	
}
