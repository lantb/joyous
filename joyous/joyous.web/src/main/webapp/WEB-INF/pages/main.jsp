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
    </script>  
    <style type="text/css">
    
    
    
    </style>
    <form id="loginForm" action=""></form>
    <frameset framespacing="0" border="0" frameborder="0" rows="94,*,40">
    
		<frame name="ctiFrm" scrolling="no" noresize target="contents" src="${appRoot}/main_top.do">
		<frameset cols="231,*" name="f1">
			<frame name="iframe_left" src="${appRoot}/main_left.do" noresize scrolling="auto">
			<frame id="iframe_right" name="iframe_right" scrolling="auto" noresize src="">
		</frameset>
		<frame name="ctiFrm" scrolling="no" noresize target="contents" src="${appRoot}/main_bottom.do">
	</frameset>
</html> 