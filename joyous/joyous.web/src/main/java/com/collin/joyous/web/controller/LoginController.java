package com.collin.joyous.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.collin.joyous.web.exception.AuthenticationDBConnException;
import com.collin.joyous.web.vo.LogonVO;
/**
 * 
 * @author Collin Lan
 * @name LoginController
 * @user AB044616
 * @date 2015-1-13  下午2:05:04
 */
@Controller
public class LoginController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value="index")  
    public void index(Model model){  
        logger.info("index.jsp");
    }
	
	@RequestMapping(value="logout")  
    public String logout(Model model){  
        Subject currentUser = SecurityUtils.getSubject();
        String userName = String.valueOf(currentUser.getSession().getAttribute("currentUser"));
        currentUser.logout();
        logger.info(userName+ " logout success.");
        return "login";
    }
	@RequestMapping(value="login" )  
    public String login(Model model,LogonVO logonVo){  
        logger.info("main.jsp");
        String resultPageURL = InternalResourceViewResolver.FORWARD_URL_PREFIX + "/";
        String username = logonVo.getUsername();
        if(StringUtils.isBlank(username)){
        	return "login";
        }
        String password = logonVo.getPassword();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        logger.info("为了验证登录用户而封装的token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        String loginMsg = null;
		try {
			// 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
			// 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
			// 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
			logger.info("对用户[" + username + "]进行登录验证..验证开始");
			currentUser.login(token);
			logger.info("对用户[" + username + "]进行登录验证..验证通过");
			resultPageURL = InternalResourceViewResolver.REDIRECT_URL_PREFIX
					+ "/" + "main.do";
		} catch (UnknownAccountException uae) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
			loginMsg = "登录名不存在或密码错误";
		} catch (IncorrectCredentialsException ice) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
			loginMsg = "登录名不存在或密码错误";
		} catch (LockedAccountException lae) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
			loginMsg = "登录名已锁定";
		} catch (ExcessiveAttemptsException eae) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
			loginMsg = "登录名错误次数过多";
		} catch (AuthenticationDBConnException ae) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
			logger.error(ae.getMessage());
			loginMsg = "系统数据库问题，请联系管理员";
		} catch (AuthenticationException ae) {
			// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
			logger.error(ae.getMessage());
			loginMsg = "系统问题，请联系管理员";
		} catch (Exception e) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
			logger.error(e.getMessage());
			loginMsg = "系统问题，请联系管理员";
		}
		// 验证是否登录成功
		if (currentUser.isAuthenticated()) {
			logger.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
			return resultPageURL;
		} else {
			token.clear();
			model.addAttribute("loginMsg", loginMsg);
			return "login";
		}
        
    }
	
	@RequestMapping(value="main")  
    public void main(Model model){  
        logger.info("main.jsp");
    }
	
	@RequestMapping(value="main_top")  
    public void main_top(Model model){  
        logger.info("main_top.jsp");
    }
    @RequestMapping(value="main_bottom")  
    public void main_bottom(Model model){  
        logger.info("main_bottom.jsp");
    }
    
    @RequestMapping(value="main_left")  
    public void main_left(Model model){  
        logger.info("main_left.jsp");
    }
}
