<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/pages/inc/taglib.jsp"%>
<%@ include file="/WEB-INF/pages/inc/common.jsp"%>
<html>  
    <head>  
        <title>Insert title here</title>
    	
    </head>  
    <script type="text/javascript">
    	function submit(){
    		$("#userForm").attr("action","${appRoot}/user/save.do");
    		$("#userForm").submit();
    	}
    </script>  
    <body>  
    	<form id="userForm" action="" method="post">
    	<table align="center" >
    		<tr>
    			<td>用户名：</td>
    			<td><input type="text" id="username" name="username"/></td>
    		</tr>
    		<tr>
    			<td>密码：</td>
    			<td><input type="password" id="password" name="password"/></td>
    		</tr>
    	</table>
    	</form>
    </body>  
</html> 