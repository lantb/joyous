<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/pages/inc/taglib.jsp"%>
<%@ include file="/WEB-INF/pages/inc/common.jsp"%>  
<html>  
    <head>  
        <title>Joyous</title>
    	
    </head>  
    <script type="text/javascript">
    function logout(){
    	$("#loginForm").attr("action","${appRoot}/logout.do");
    	$("#loginForm").submit();
    }
    $(function(){
    	var url = "${appRoot}/leftTree.do";
    	//$("#iframe_left").attr("src",url);
    });
    function index(url){
   		$("#iframe_right").attr("src",url);
    }
    function clickMenu(actionStr){
    	var url = "${appRoot}/"+actionStr;
    	$("#iframe1").attr("src",url);
    }
    </script>  
    <style type="text/css">
    
    
    
    </style>
    <body>


	<form id="loginForm" action="">
		<div style="height: 98%; width: 98%" class=" navbar-fixed-top">
			<div class="container">${menuString}</div>
			<div style="height:98%;">
				<iframe id="iframe1" src="" scrolling="auto" height="98%"
					width="100%" frameborder="0"></iframe>
			</div>
		</div>
	</form>
</body>
</html> 