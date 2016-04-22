<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/pages/inc/taglib.jsp"%>
<%@ include file="/WEB-INF/pages/inc/common.jsp"%>
<html>  
    <head>  
        <title>Joyous</title>
    	
    </head>  
    <script type="text/javascript">
    	if(this.parent!=this){//登录后，session失效，跳转login.jsp,在iframe中,则将父页面跳转到登录页面
    		this.parent.location = "${appRoot}/login.do";
    	}
    	var loginMsg = '${loginMsg}';
    	if(loginMsg != ""){
    		alert(loginMsg);
    	}
    	function login(){
	    	$("#loginForm").attr("action","${appRoot}/login.do");
    		$("#loginForm").submit();
    	}
    
    </script>  
    <body>  
    	<form id="loginForm" action="" method="post">
    	<table align="center" >
    		<tr>
    			<td>用户名：</td>
    			<td><input type="text" id="username" name="username"/></td>
    		</tr>
    		<tr>
    			<td>密码：</td>
    			<td><input type="password" id="password" name="password"/></td>
    		</tr>
    		<tr>
    			<input type="button" value="登录" onclick="login()"/>
    		</tr>
    	</table>
    	</form>
    </body>  
</html> 