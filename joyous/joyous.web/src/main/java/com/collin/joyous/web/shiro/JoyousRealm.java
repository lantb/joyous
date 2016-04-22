package com.collin.joyous.web.shiro;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.collin.joyous.web.entity.TUser;
import com.collin.joyous.web.entity.TUserCriteria;
import com.collin.joyous.web.entity.TUserCriteria.TUserColumnName;
import com.collin.joyous.web.exception.AuthenticationDBConnException;
import com.collin.joyous.web.service.ITUserService;

@Controller
public class JoyousRealm extends AuthorizingRealm {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private ITUserService userService;
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		 //获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()  
        String currentUsername = (String)super.getAvailablePrincipal(principals);  
//      List<String> roleList = new ArrayList<String>();  
//      List<String> permissionList = new ArrayList<String>();  
//      //从数据库中获取当前登录用户的详细信息  
//      User user = userService.getByUsername(currentUsername);  
//      if(null != user){  
//          //实体类User中包含有用户角色的实体类信息  
//          if(null!=user.getRoles() && user.getRoles().size()>0){  
//              //获取当前登录用户的角色  
//              for(Role role : user.getRoles()){  
//                  roleList.add(role.getName());  
//                  //实体类Role中包含有角色权限的实体类信息  
//                  if(null!=role.getPermissions() && role.getPermissions().size()>0){  
//                      //获取权限  
//                      for(Permission pmss : role.getPermissions()){  
//                          if(!StringUtils.isEmpty(pmss.getPermission())){  
//                              permissionList.add(pmss.getPermission());  
//                          }  
//                      }  
//                  }  
//              }  
//          }  
//      }else{  
//          throw new AuthorizationException();  
//      }  
//      //为当前用户设置角色和权限  
//      SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();  
//      simpleAuthorInfo.addRoles(roleList);  
//      simpleAuthorInfo.addStringPermissions(permissionList);  
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();  
        //实际中可能会像上面注释的那样从数据库取得  
        if(null!=currentUsername && "mike".equals(currentUsername)){  
            //添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色    
            simpleAuthorInfo.addRole("admin");  
            //添加权限  
            simpleAuthorInfo.addStringPermission("admin:manage");  
            System.out.println("已为用户[mike]赋予了[admin]角色和[admin:manage]权限");  
            return simpleAuthorInfo;  
        }
        //若该方法什么都不做直接返回null的话,就会导致任何用户访问/admin/listUser.jsp时都会自动跳转到unauthorizedUrl指定的地址  
        //详见applicationContext.xml中的<bean id="shiroFilter">的配置  
        return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;  
        String username = token.getUsername();  
        String password = String.valueOf(token.getPassword());
        TUser user = null;
        try {
        	TUserCriteria userCriteria = new TUserCriteria()
	        	.andEquals(TUserColumnName.USERNAME.toString(),username)
	        	.andEquals(TUserColumnName.PASSWORD.toString(),password);
        	user = userService.selectByCriteria(userCriteria);
		} catch (MyBatisSystemException e){
			logger.error("connect exception :" + e.getMessage());
			throw new AuthenticationDBConnException(e);
		} catch (Exception e) {
			logger.error("connect exception :" + e.getMessage());
		}
        if(user == null){
    		throw new UnknownAccountException();
    	}else if(StringUtils.isBlank(user.getPassword()) || !user.getPassword().equals(password)){
    		throw new IncorrectCredentialsException();
    	}
        if( username != null && !"".equals(username) ){  
        	AuthenticationInfo authInfo = new SimpleAuthenticationInfo(  
        			username,password, getName());  
            this.setSession("currentUser", username); 
            return authInfo;
        }  
        return null;  
	}

	private void setSession(String key, String value) {
		Subject currentUser = SecurityUtils.getSubject();  
        if(null != currentUser){  
            Session session = currentUser.getSession();  
            //session.setTimeout(arg0)
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");  
            if(null != session){  
                session.setAttribute(key, value);  
            }  
        }  
		
	}

}
