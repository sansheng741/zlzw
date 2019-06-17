<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>知来智网-我的信息</title>
		<link rel="shortcut icon" type="image/x-icon" href="img/icon2.png" />
		<link rel="stylesheet" href="css/editInfo.css" />
		<link rel="stylesheet" href="css/top.css" />
		<link rel="stylesheet" href="css/footer.css" />
	</head>
	<body>
		<!-- 头部引入 -->
		<jsp:include page="/public/top.jsp" />
		<!-- 内容 -->
		<div class="wrapper">
		<input type="hidden" id="uid" value="${sessionScope.user.uid}">
			<div class="title">
				<div class="title-icon"></div>
				<div class="title-text">我的信息</div>
			</div>
			<div class="my-info">
				<form class="form-data">
					<div>
						昵称：<input type="text" id="username" class="username" value="${sessionScope.user.uname}"/>
						<div class="from-error">昵称不能为空!</div>
						<div class="from-error">昵称格式错误!</div>
					</div>
					<div class="mysex">
						性别：
						<label>男</label>
						<label>女</label>
						<label>保密</label>
						<input type="hidden" id="sex" value="${sessionScope.user.sex}" />
					</div>
					<div>
						电话：<input type="text" id="tel" value="${sessionScope.user.tel}" />
						<div class="from-error">电话不能为空!</div>
						<div class="from-error">电话格式错误!</div>
					</div>
					<div>
						邮箱：<input type="text" id="email" value="${sessionScope.user.email}"/>
						<div class="from-error">邮箱不能为空!</div>
						<div class="from-error">邮箱格式错误!</div>
					</div>
					<div>
						地址：<input type="text" id="addr" value="${sessionScope.user.addr}"/>
					</div>
					<div class="tag">
						爱好：
						<c:forEach items="${hobbyList }" var="hl">
							<div class="wrap-tag">
							<span contenteditable="true" name="hobby" class="hobby">${hl }</span>
							<span class="removeTag"></span>
						</div>
						</c:forEach>
						<span class="addtag">添加爱好</span>
					</div>
					<div style="margin-left: 24px;">
						个性签名：<input type="text" id="motto" value="${sessionScope.user.sign }"/>
					</div>
					<div style="margin-left: 24px;">
						出生日期：<input id="date" type="date" value="${sessionScope.user.birthday }" />
					</div>
					<button type="button" class="el-button el-button--primary"><span>保存</span></button>
				</form>
			</div>
			<div class="title">
				<div class="title-icon"></div>
				<div class="title-text">我的头像</div>
			</div>
			<div class="my-head">
				<div class="modal-content">
					<div class="img-clip-wrap">
						<div class="clip-wrap" onclick="pic.click()">
							<input id="pic" type="file" style="display: none;"/>
							<img src="img/choice_pic.png" class="headpic">
						</div>
						<span>预览头像</span>
					</div>
					<div class="border-line"></div>
					<div class="img-preview-wrap">
						<div class="preview-wrap">
							<img src="${sessionScope.user.head}" >
						</div>
						<span>当前头像</span>
					</div>
				</div>
				<p class="descript">请选择图片上传：大小180 * 180像素支持JPG、PNG等格式，图片需小于2M</p>
				<div class="modal-footer">
					<input type="button" value="更新" class="modal-btn btn-confirm" disabled>
				</div>
			</div>
		</div>
		<!-- 底部引入 -->
		<jsp:include page="/public/footer.jsp" />
		<script src="js/jquery-1.12.3.js"></script>
		<script src="js/top.js"></script>
		<script src="js/footer.js"></script>
		<script src="js/editInfo.js"></script>
	</body>
</html>

