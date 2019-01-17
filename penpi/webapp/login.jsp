<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Penpi后台管理系统</title>
<link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function checkUser() {
		var LoginId = document.getElementById("LoginId").value;
		var password = document.getElementById("userPsw").value;
		if (LoginId == "") {
			alert("用户名不能为空");
			return false;
		}
		if (password == "") {
			alert("密码不能为空");
			return false;
		} else {
			return true;
		}
	}
</script>
</head>

<body>

	<div class="login_box">
		<div class="login_l_img">
			<img src="${pageContext.request.contextPath}/images/login/login-img.png" />
		</div>
		<div class="login">
			<div class="login_logo">
				<a href="#"><img src="${pageContext.request.contextPath}/images/login/login_logo.png" /></a>
			</div>
			<div class="login_name">
				<p>后台管理系统</p>
			</div>
			<form action="${pageContext.request.contextPath}/admin/login/handlelogin.do" onsubmit="return checkUser()">
				<input name="LoginId" id="LoginId" type="text" value="${LoginId}" placeholder="用户名" onfocus="this.placeholder=''" onblur="if(this.placeholder==''){this.placeholder='用户名'}">
				<span id="password_text" onclick="this.style.display='none';document.getElementById('userPsw').style.display='block';document.getElementById('userPsw').focus().select();">密码</span>
				<input name="userPsw" type="password" id="userPsw" style="display: none;" onblur="if(this.value==''){document.getElementById('password_text').style.display='block';this.style.display='none'};" />
				<input value="登录" style="width: 100%;" type="submit">
				<div style="text-align: center; color: #F00; font-size: 1.3em">${message}</div>
			</form>
		</div>

		<div class="copyright">xxx公司 版权所有©2016-2018</div>
	</div>
</body>
</html>