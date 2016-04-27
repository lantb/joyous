<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/pages/inc/taglib.jsp"%>
<%@ include file="/WEB-INF/pages/inc/common.jsp"%>  
<html>  
    <head>  
        <title>top</title>
    	
    </head>
<body>
</body>
</html> 
	<div class="main_top">
	<div class="main_hello" >
		hello!<shiro:principal></shiro:principal>
	</div>
	<div class="main_logout">
		<a href="#" onclick="parent.logout();">logout</a>
	</div>
</div>
	    	