package com.collin.joyous.web.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.collin.joyous.web.entity.TRole;
import com.collin.joyous.web.service.ITRoleService;
/**
 * 
 * @author Collin Lan
 * @name LoginController
 * @user AB044616
 * @date 2015-1-13  下午2:05:04
 */
@Controller
public class RoleController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private ITRoleService roleService;
	
	@RequestMapping(value="role/saveInit")  
    public String saveInit(Model model){  
        return "role/role_save";
    }
	
	@RequestMapping(value="role/save")  
    public String save(Model model,TRole role){  
        if(role != null){
        	roleService.save(role);
        }
        return "role/role_list";
    }
}
