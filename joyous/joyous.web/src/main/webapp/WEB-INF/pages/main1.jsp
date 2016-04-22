<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/pages/inc/taglib.jsp"%>
<%@ include file="/WEB-INF/pages/inc/common.jsp"%>  
<html>  
    <head>  
        <title>Insert title here</title>
    	
    </head>  
    <script type="text/javascript">
    function index(){
    	$("#loginForm").attr("action","${appRoot}/index.do");
    	$("#loginForm").submit();
    }
    function logout(){
    	$("#loginForm").attr("action","${appRoot}/logout.do");
    	$("#loginForm").submit();
    }
    $(function(){
    	var url = "${appRoot}/leftTree.do";
    	//$("#iframe_left").attr("src",url);
    });
    
    </script>  
    <style type="text/css">
    .main_top {position: relative;width:100%;height:70px;background-color:red;}
    .main_hello {position: absolute; width:200px;height:20px;background-color:blue;right:20px;bottom: 0px;margin-right:10px;margin-bottom:5px;}
    .main_logout {position: absolute; width:40px;height:20px;background-color:black;right:0px;bottom: 0px;margin-right:10px;margin-bottom:5px;}
    .main_mid {position: relative;width:100%;height:400px;background-color:#872322;}
    .main_buttom {position: relative;width:100%;height:20px;background-color:green;}
    .main_mid_left {position: absolute; width:16%;height:400px;background-color:yellow;left:0px;}
    .main_mid_right {position: absolute; width:84%;height:400px;background-color:#372322;right:0px;}
    
    
    </style>
    <body>  
    	<form id="loginForm" action="" method="post">
	    	<div class="main_top">
	    		<div class="main_hello" >
	    			hello!<shiro:principal></shiro:principal>
	    		</div>
	    		<div class="main_logout">
	    			<a href="#" onclick="logout();">logout</a>
	    		</div>
	    	</div>
	    	<div class="main_mid">
		    	<div class="main_mid_left">
		    		<%@ include file="/WEB-INF/pages/leftTree.jsp"%>  
		    	</div>
		    	<div class="main_mid_right">
		    		<iframe id="iframe_right" name="iframe_right" src="" height="100%" width="100%" scrolling="auto"></iframe>
		    	</div>
	    	</div>
	    	<div class="main_buttom">
		    	buttom
	    	</div>
	    	
    	</form>
    </body>  
</html> 