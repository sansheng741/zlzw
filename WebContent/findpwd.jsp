<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
	<!-- InstanceBegin template="/Templates/login_reg.dwt" codeOutsideHTMLIsLocked="false" -->
	<head>
		<meta charset="utf-8">
		<link rel="shortcut icon" type="image/x-icon" href="img/icon1.png" />
		<!-- InstanceBeginEditable name="doctitle" -->
		<title>知来智网-找回密码</title>
		<link rel="stylesheet" type="text/css" href="css/findpwd.css">
		<link rel="stylesheet" type="text/css" href="css/base.css">
		<script src="js/jquery-1.12.3.js"></script>
		<script src="js/findpwd.js"></script>

	</head>

	<body>

		<article>
			<div class="logo">
				<a href="login.jsp" title="前往登录页面"><img src="img/icon1.png" /></a>
			</div>
			<!-- InstanceBeginEditable name="article" -->
			<!--可编辑内容区-->
			<h1>找回密码</h1>
			
			<form action="FindPwdServlet" method="post">
			
				<div class="cell">
					<span>用&nbsp;&nbsp;户&nbsp;&nbsp;名：</span>
					<input type="text" required="" name="username" id="username" onkeyup="checkContent(this)" value="<%=(request.getParameter("username")==null?"":request.getParameter("username"))%>" placeholder="请输入用户名">
				</div>
				<div class="cell">
					<span>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：</span>
					<input type="email" required="" name="email" id="email" value="<%=(request.getParameter("email")==null?"":request.getParameter("email"))%>" placeholder="请输入邮箱"/>
				</div>
				<div class="cell">
					<span>验&nbsp;&nbsp;证&nbsp;&nbsp;码：</span>
					<input type="text" required="" id="code" name="code" placeholder="请输入验证码"/>
					<input type="button" id="b" disabled="disabled" name="code" value="获取验证码" onclick="getCode()"/>
				</div>
				<div class="cell">
					<span>新&nbsp;&nbsp;密&nbsp;&nbsp;码：</span>
					<!-- <img id="img1" onclick="hideShowPsw()" src="img/visible.png"> -->
					<input type="password" required="" name="npwd" id="input1" maxlength="16" placeholder="请输入新密码"/>
				</div>
				<div class="cell">
					<span>确认密码：</span>
					<!-- <img id="img1" onclick="hideShowPsw()" src="img/visible.png"> -->
					<input type="password" required="" name="tnpwd" id="input1" maxlength="16" placeholder="请输入新密码"/>
				</div>
				
				<div class="button">
					<input type="submit" value="确认">
				</div>
			</form>
			
		</article>

		<div class="foot">
			<div class="shenming">
				<a>©2018 zhilaizhiwang</a>
				<a href="#">移动开放平台</a>
				|
				<a href="#">服务协议</a>
				|
				<a href="#">权利声明</a>
				|
				<a href="#">版本更新</a>
				|
				<a href="#">帮助中心</a>
				|
				<a href="#">版权投诉</a>
			</div>
		</div>
	
	</body>
	<!-- InstanceEnd -->
	<c:if test="${ ! empty msg}">
	<script type="text/javascript">
	alert('${msg}');
	</script>
	</c:if>
</html>

