<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/pages/inc/taglib.jsp"%>
<%@ include file="/WEB-INF/pages/inc/common.jsp"%> 
<%@ include file="/WEB-INF/pages/inc/ztree.jsp"%>  
<html>  
    <head>  
        <title>leftTree</title>
    	
    </head>  
    <script type="text/javascript">
    function index(){
   		var url = "${appRoot}/index.do";
    	parent.index(url);
    }
    
    </script>  
    <style type="text/css">
    
    
    </style>
    <body>  
    	<div class="main_mid_left">
	    	<a href="#" onclick="index()">index</a>
	    	<j:tree id="tree1" name="tree1" width="217px"></j:tree>
	    </div>	
    </body>  
</html> 