<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ include file="../pages/inc/taglib.jsp"%>
<%@ include file="../pages/inc/common.jsp"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
    <head>  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
        <title>Insert title here</title>  
    </head>  
    <script type="text/javascript">
    function login(){
    	$("#indexForm").attr("action","${appRoot}/login.do");
    	$("#indexForm").submit();
    }
    </script>
    <body >  
    	<form id="indexForm"></form>
        <c:out value="${liming}"></c:out>  
        index
        <input type="text" id="input" />
        <input type="button" id="button" value="button" onclick="login()"/>
    </body>  
</html> 