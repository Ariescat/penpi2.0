<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/main_top.css" rel="stylesheet" type="text/css" />
 

</head>
<body style="background: -webkit-linear-gradient(top, 	#B0B0B0 0%,#3C4049 100%);">
            
    
    <div class="topright">    
    <img src="${pageContext.request.contextPath}/images/main_top/title.png" width="500px" height="50px" style="padding-top:20px;"> 
    <ul>
    <li style="list-style-type:none;"><span><img src="${pageContext.request.contextPath}/images/main_top/help.png" title="帮助"  class="helpimg"/></span><a href="#"><span style="color:white;">帮助</span></a></li>
    <li  style="list-style-type:none;"><a href="#"><span style="color:white;">关于</span></a></li>
    <li  style="list-style-type:none;"><a href="login.html" target="_parent"><span style="color:white;">退出</span></a></li>
    </ul>
 
    <div class="user">
    <span style="display:inline-block;padding-right:10px;background:url(${pageContext.request.contextPath}/images/main_top/user.png) no-repeat 15px 5px; padding-left:35px; "><span style="color:white">admin</span></span>
    <i><span style="color:white">消息</span></i>
    <b>5</b>
    </div>    
    
    </div>
</body>
</html>
