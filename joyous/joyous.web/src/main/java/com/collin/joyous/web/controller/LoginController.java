package com.collin.joyous.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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

import com.collin.joyous.web.entity.TResource;
import com.collin.joyous.web.exception.AuthenticationDBConnException;
import com.collin.joyous.web.service.ITResourceService;
import com.collin.joyous.web.vo.LogonVO;
import com.collin.joyous.web.vo.MenuVO;
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
	@Resource
	private ITResourceService resourceService;
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
					+ "/" + "main";
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
			TResource r = new TResource();
			r.setcId("1");
			List<TResource> list = resourceService.selectTResource(r);
			Map<Integer, List<MenuVO>> menuMap= new HashMap<Integer, List<MenuVO>>();//level menuVO
			MenuVO t = null;
			for (TResource rr : list) {
				if(rr == null){
					continue;
				}	
				int cLevel = rr.getcLevel();
				t = new MenuVO();
				String name = rr.getcName();
				String url = rr.getcUrl();
				String id = rr.getcId();
				t.setName(name);
				t.setParent(rr.getcParentId());
				t.setSeq(rr.getcSeq());
				t.setId(id);
				t.setLevel(rr.getcLevel());
				t.setClick("clickMenu('"+url+"');");
				if(menuMap.containsKey(cLevel)){
					menuMap.get(cLevel).add(t);
				}else{
					List<MenuVO> m = new ArrayList<MenuVO>();
					m.add(t);
					menuMap.put(cLevel, m);
				}
			}
			MenuVO menu = menuMap.get(0).get(0);
			
			
			buildMenuVO(menu,menuMap);
				
			
			
			StringBuffer sb = new StringBuffer();
			sb.append("")
			.append("	<h6><nav class=\"navbar navbar-default navbar-static-to\">                                       ")
			.append("		<div class=\"container\">                                                 ")
			.append("			<div class=\"navbar-header\">                                           ")
			.append("				<button type=\"button\" class=\"navbar-toggle collapsed\"               ")
			.append("					data-toggle=\"collapse\" data-target=\".navbar-collapse\">            ")
			.append("					<span class=\"sr-only\">Toggle navigation</span> <span              ")
			.append("						class=\"icon-bar\"></span> <span class=\"icon-bar\"></span> <span   ")
			.append("						class=\"icon-bar\"></span>                                        ")
			.append("				</button>                                                           ")
			.append("				<a class=\"navbar-brand\" href=\"#\">"+menu.getName()+"</a>                   ")
			.append("			</div>                                                                ")
			.append("			<div class=\"navbar-collapse collapse\">                                ")
			.append("				<ul class=\"nav navbar-nav\">                                         ");
			for (MenuVO m2 : menu.getChildrenList()) {
				if(m2.getChildrenList() != null && m2.getChildrenList().size() >0){
					sb.append("			<li class=\"dropdown\"><a href=\"javascript:"+m2.getClick()+"\" class=\"dropdown-toggle\"          ")
					.append("						data-toggle=\"dropdown\" role=\"button\" aria-haspopup=\"true\"       ")
					.append("						aria-expanded=\"false\">"+m2.getName()+" <span class=\"caret\"></span></a>  ")
					.append("				<ul class=\"dropdown-menu\">                                      ");
					
					for(MenuVO m3 : m2.getChildrenList()){
						sb.append("				<li><a href=\"javascript:"+m3.getClick()+"\">"+m3.getName()+"</a>                     ");
						sb.append("				</li>                      ");
					}
					sb.append("				</ul>                      ");
					sb.append("			</li>                      ");
				}else{
					sb.append("			<li><a href=\"javascript:"+m2.getClick()+"\">"+m2.getName()+"</a>                     ");
					sb.append("			</li>                      ");
				}
				
			}
//			sb.append("					<li class='active'><a href='#'>Home</a></li>                      ")
//			.append("					<li><a href='#about'>About</a></li>                               ")
//			.append("					<li><a href='#abou1t'>Abou1t</a></li>                             ")
//			.append("					<li><a href='#contact'>Contact</a></li>                           ")
//			.append("					<li class='dropdown'><a href='#' class='dropdown-toggle'          ")
//			.append("						data-toggle='dropdown' role='button' aria-haspopup='true'       ")
//			.append("						aria-expanded='false'>Dropdown <span class='caret'></span></a>  ")
//			.append("						<ul class='dropdown-menu'>                                      ")
//			.append("							<li><a href='#'>Action</a></li>                               ")
//			.append("							<li><a href='#'>Another action</a></li>                       ")
//			.append("							<li><a href='#'>Something else here</a></li>                  ")
//			.append("							<li role='separator' class='divider'></li>                    ")
//			.append("							<li class='dropdown-header'>Nav header</li>                   ")
//			.append("							<li><a href='#'>Separated link</a></li>                       ")
//			.append("							<li><a href='#'>One more separated link</a></li>              ")
//			.append("						</ul></li>                                                      ")
			
			
			sb.append("				</ul>                                                               ")
			.append("			</div>                                                                ")
			.append("		</div>                                                                  ")
			.append("	</nav></h6>                                                                    ")
			;

			model.addAttribute("menuString",sb.toString().replaceAll("	", "").replace("  ", ""));
			/*
			 * 
	<nav class="navbar navbar-default">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Project name</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#about">About</a></li>
					<li><a href="#abou1t">Abou1t</a></li>
					<li><a href="#contact">Contact</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Dropdown <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">Action</a></li>
							<li><a href="#">Another action</a></li>
							<li><a href="#">Something else here</a></li>
							<li role="separator" class="divider"></li>
							<li class="dropdown-header">Nav header</li>
							<li><a href="#">Separated link</a></li>
							<li><a href="#">One more separated link</a></li>
						</ul></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>
			 */
			return "main";
		} else {
			token.clear();
			model.addAttribute("loginMsg", loginMsg);
			return "login";
		}
        
    }
	
	private void buildMenuVO(MenuVO menu, Map<Integer, List<MenuVO>> menuMap) {
		List<MenuVO> list = menuMap.get(menu.getLevel()+1);
		if(list !=null && list.size() > 0){
			List<MenuVO> l = menu.getChildrenList();
			for (MenuVO menuVO : list) {
				
				if(menuVO.getParent().equals(menu.getId())){
					l.add(menuVO);
				}
			}
			
			Collections.sort(l,getComparator());
			for (MenuVO menuVO : l) {
				buildMenuVO(menuVO,menuMap);
			}
		}
	}
	
	public Comparator<MenuVO> getComparator() {
		Comparator<MenuVO> comparator = new Comparator<MenuVO>() {
            public int compare(MenuVO s1, MenuVO s2) {
                if (s1.getSeq() != s2.getSeq()) {
                    return s1.getSeq() - s2.getSeq();
                } else{
                	return 0;
                }
            }
        };
		return comparator;
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
