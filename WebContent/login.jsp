<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>知来智网-让美好永远陪伴</title>
		<link href="css/login.css" rel="stylesheet" type="text/css" />
		<link rel="shortcut icon" type="image/x-icon" href="img/icon1.png" />
		<script type="text/javascript" src="js/jquery-1.12.3.js"></script>
	</head>
	<body>
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

			<div class="banner-control" id="js_ban_button_box">
				<a href="#" class="left">左</a>
				<a href="#" class="right">右</a>
			</div>

			<div class="head">
				<span class="login-title">
					<a href="user.s?oper=index">首页</a>
					<a href="#">客户端下载</a>
					<a href="#">官方贴吧</a>
					<a href="#">官方微博</a>
					<a href="#">问题反馈</a>
					<a href="vip?oper=vip" class="vip">会员中心</a>
				</span>
			</div>

			<div class="button">
				<form action="user.s?oper=loginservice" id="ajaxForm" method="post">
				
					<div class="form">
						<div class="login_options">
							<div class="header">
								<span>账号密码登录</span>
							</div>
							<div class="validate_msg"></div>
							<label id="username"></label>
							<div class="form_item"><input placeholder="手机号码/邮箱/用户名" class="text" type="text" id="username" name="account" value="<%=(request.getParameter("account")==null?"":request.getParameter("account"))%>"/>
							</div>
							<div class="form_item"><input class="text" placeholder="密码" type="password" id="password" name="pwd"/>
							</div>
							<div class="ver-code">
								<input type="text" placeholder="验证码" maxlength="6" name="verifyCode"/>
								<img src="img/qrcode.jpg"  class="pass-verifyCode"/>
								<a href="javascript:;">换一张</a>
							</div>
							<div class="form_options">
								<div class="option_item"><input type="checkbox"  name="check"><span class="checked"></span></div>

								<span class="text">下次自动登录</span>


							</div>
							<input type="submit" value="登录" class="tsubmit">

								<!-- <a href="javascript:document.getElementById('ajaxForm').submit();">登录</a> -->
							</div>
							<div class="footer">
								<a href="user.s?oper=register" class="register">注册账号</a>
								<a href="user.s?oper=findpwd" class="wpwd">忘记密码?</a>
							</div>
						</div>
					</form>
				</div>
			</div>

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

		
		<script src="js/login.js"></script>
		
	</body>
	
	<c:if test="${ ! empty msg}">
		<script type="text/javascript">
			alert('${msg}');
		</script>
	</c:if>

</html>
