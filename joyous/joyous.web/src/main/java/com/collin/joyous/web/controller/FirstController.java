package com.collin.joyous.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FirstController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	@RequestMapping(value="index2.do")  
    public void indexasdf(Model model){  
        model.addAttribute("liming", "黎明你好1"); 
        logger.info("index.jsp");
    }  
	@RequestMapping(value="index1.do")  
    public void indexasdf1(Model model){  
        model.addAttribute("liming1", "黎明你好1"); 
        logger.error("index1.jsp");
    }  
}
