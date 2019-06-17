<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<link rel="stylesheet" href="css/top.css" />
		<link rel="stylesheet" href="css/footer.css" />
		<link rel="stylesheet" href="css/favorite.css" />
		<script src="js/jquery-1.12.3.js"></script>
		<link rel="shortcut icon" type="image/x-icon" href="img/icon2.png" />
		<title>知来智网-我的收藏</title>
	</head>
	<body>
		<!-- 头部 -->
		<jsp:include page="/public/top.jsp" />
		<!-- 内容 -->
		<div class="wrapper">
			<div class="user-card">
				<div class="card-left">
					<div class="user-head">
						<img src="${sessionScope.user.head }" />
					</div>
					<div class="user-name">${sessionScope.user.uname }</div>
					<div class="upload-size"><span>${sessionScope.user.sign}</span> </div>
					<div class="data-count">
						<ul>
							<li>
								<label>积分</label><span>${sessionScope.user.integral}</span>
							</li>
							<li>
								<label>上传资源</label><span>${uploadNum }</span>
							</li>
							<li>
								<label>下载资源</label><span>${downloadNum }</span>
							</li>
						</ul>
					</div>
				</div>
				<div class="card-right">
					<a href="javascript:;" class="btn_vipsign">开通VIP会员 免积分下载</a>
				</div>
			</div>
			<div class="main">
				<div class="pull-left">
					<div class="user-nav">
						<ul>
							<li><a href="user.s?oper=mySrc&uid=${sessionScope.user.uid}">上传资源</a></li>
							<li><a href="user.s?oper=myDownload&uid=${sessionScope.user.uid}">下载明细</a></li>
							<li><a href="user.s?oper=favorite&uid=${sessionScope.user.uid}">我的收藏</a></li>
							<li><a href="user.s?oper=vip&uid=${sessionScope.user.uid}">VIP服务</a></li>
						</ul>
					</div>
					<div class="item-scurce">
						<ul>
							<c:forEach items="${goodsList }" var="gl">
								<li>
									<input type="hidden" class="gid" value="${gl.gid }"/>
									<div class="src-pic">
										<img src="${gl.gimage1 }" />
									</div>
									<div class="src-content">
										<h3>
											<a target="_blank" href="goods.s?oper=goods&gid=${gl.gid }&uid=${gl.uid}">
												${gl.name }
											</a>
	                                    </h3>
										<p class="src-desc">${gl.context }</p>
										<div class="date">
											<label>上传时间：</label>
											<span class="uploadTime">${gl.gdate }</span> 
										</div>
										<div class="score">
											所需积分/C币：<span class="price">${gl.price }</span>
										</div>
										<div class="cancel-fav">
	                                        <a href="javascript:void(0);">取消收藏</a>
	                                    </div>
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div class="pull-right">
					<a href="goods.s?oper=uploadfile&uid=${sessionScope.user.uid}" class="upload"><i class="fa-upload"></i>上传资源</a>
				</div>
			</div>
		</div>
		<!-- 底部 -->
		<jsp:include page="/public/footer.jsp" />
		
		<script src="js/top.js"></script>
		<script src="js/footer.js"></script>
		<script src="js/favorite.js"></script>
	</body>
</html>

