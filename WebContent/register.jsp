<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<title>注册页面</title>
		<meta charset="utf-8">
		<link href="css/register.css" rel="stylesheet" type="text/css" />
		<link rel="shortcut icon" type="image/x-icon" href="img/icon1.png" />
    </head>
	<body>
		<script type="text/javascript" src="js/jquery-1.12.3.js"></script>
		<!-- 背景轮播图 -->
		<div class="wrap">
			<div class="banner-show" id="js_ban_content">
				<div class="cell bns-01">
					<div class="con"></div>
				</div>

				<div class="cell bns-02">
					<div class="con"></div>
				</div>
				<div class="cell bns-03">
					<div class="con"></div>
				</div>
			</div>
			<div class="banner-control" id="js_ban_button_box"> <a href="javascript:;" class="left">左</a> <a href="javascript:;"
				 class="right">右</a> </div>

			<!-- 表单内容 -->
			<div class="container">
				<div class="register-box">
					<form action="user.s?oper=regservice" method="post" id="regForm" name="registForm">
					<div class="reg-slogan"> 新用户注册</div>
					<div class="login"><a href="user.s?oper=login">已有账号，立即登录</a></div>
					<div class="reg-form" id="js-form-mobile"><br>
						<br>
						<!-- 手机号 -->
						<div class="cell">
							<input type="email" required="" onclick="checkEmail()" name="email" value="<%=(request.getParameter("email")==null?"":request.getParameter("email"))%>" onblur="checkEmail(registForm.email)" id="js-mobile_ipt" class="text" placeholder="请输入您的邮箱" autocomplete="off"/>
							<label id="email"></label>
						</div>
						<!-- 名字 -->
						<div class="cell">
							<input type="text" required="" name="uname" value="<%=(request.getParameter("uname")==null?"":request.getParameter("uname"))%>" id="js-mobile_name_ipt" class="text" placeholder="请输入您的名字" autocomplete="off"/>
							<label id="name"></label>
						</div>
						<!-- 用户名 -->
						<div class="cell">
							<input type="text" required="" onclick="checkName()" name="username" value="<%=(request.getParameter("username")==null?"":request.getParameter("username"))%>" id="js-mobile_user_ipt" class="text" placeholder="请设置您的用户名" autocomplete="off"/>
							<label id="user"></label>
						</div>
						<!-- 密码 -->
						<div class="cell">
							<img id="demo_img" onclick="hideShowPsw()" src="img/visible.png">
							<input type="password" required="" onclick="checkPwd()" name="pwd" value="<%=(request.getParameter("pwd")==null?"":request.getParameter("pwd"))%>" id="demo_input" class="text" placeholder="请输入您的密码" autocomplete="off"/>
							<label id="pwd"></label>
						</div>

						<!-- 验证码 -->
						<div class="cell vcode">
							<input type="text" required="" name="code" id="js-mobile_vcode_ipt" class="text" maxlength="6" placeholder="请输入验证码" autocomplete="off"/>
							<!-- <a href="#" id="js-get_mobile_vcode" class="button btn-disabled"> 免费获取验证码</a> -->
							<img id="validateCodeImg" onclick="changeImg()" src="/zlzw/bufferImage" style="width:175px;height:40px;margin-top:1px;"/>
						</div>

						<!-- 用户协议 -->
						<div class="check">
							<input type="checkbox" id="js-mobile_check_ipt" onclick="check()" checked ><span>已认真阅读并同意<a href="#">《知来智网协议》</a></span>
						</div>
						<!-- 注册按钮 -->
						<div class="bottom">
						<!-- <a id="js-mobile_btn" href="javascript:document.getElementById('regForm').submit();" class="button btn-green"> 立即注册</a> -->
						<input type="submit" id="js-mobile_btn" class="button btn-green" value="立即注册" style="width:330px;height:50px;">
						</div>
					</div>
					</form>
				</div>
			</div>
		</div>

		<script src="js/register.js"></script>

	</body>
	<c:if test="${ ! empty msg}">
	<script type="text/javascript">
	alert('${msg}');
	</script>
	</c:if>
</html>

