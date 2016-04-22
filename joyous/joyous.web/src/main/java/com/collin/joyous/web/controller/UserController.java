package com.collin.joyous.web.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.collin.joyous.web.entity.TUser;
import com.collin.joyous.web.service.ITUserService;
/**
 * 
 * @author Collin Lan
 * @name LoginController
 * @user AB044616
 * @date 2015-1-13  下午2:05:04
 */
@Controller
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private ITUserService userService;
	
	@RequestMapping(value="user/saveInit")  
    public String saveInit(Model model){  
        logger.info("user_save.jsp");
        return "user/user_save";
    }
	
	@RequestMapping(value="user/save")  
    public String save(Model model,TUser user){  
        logger.info("saveInit.jsp");
        if(user != null){
        	userService.insert(user);
        }
        return "user/user_list";
    }
}
