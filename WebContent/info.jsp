<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>知来智网-我的信息</title>
		<link rel="stylesheet" href="css/top.css" />
		<link rel="stylesheet" href="css/info.css" />
		<script src="js/jquery-1.12.3.js"></script>

		<link rel="shortcut icon" type="image/x-icon" href="img/icon2.png" />
	</head>
	<body>
		<!-- 头部 -->
		<jsp:include page="/public/top.jsp" />
		<input type="hidden" id="Infouid" value="${userInfo.uid }">
		<div class="wrapper">
			<div class="head">
				<div class="photo">
					<img src="${userInfo.head }" />
				</div>
				<div class="pf_username">
					<h1 class="username">${userInfo.uname }</h1>
					<div class="icon-sex"></div>
					<img title="关注" src="img/follow1.png" class="follow">
					<input type="hidden"  id="sex" value="${userInfo.sex }">
				</div>
				<div class="motto">${userInfo.sign }</div>
			</div>
			<div class="firstPage">
				<div class="fff_count">
				<ul>
						<li>
							<div>${followNum }</div>关注
						</li>
						<li>
							<div>${fansNum }</div>粉丝
						</li>
						<li>
							<div>${friendNum }</div>好友
						</li>
					</ul>
				</div>
				<div class="person_info1">
					<div class="info-title">
						<img src="img/basicInfo.png" />
						<span>基本资料</span>
					</div>
					<div class="desc">
						<ul class="info-ul-left">
							<li>昵称</li>
							<li>性别</li>
							<li>生日</li>
							<li>所在地</li>
							<li>邮箱</li>
						</ul>
					</div>
					<div class="info-content">
						<ul class="info-ul-right">
							<li>${userInfo.uname }</li>
							<li>${userInfo.sex}</li>
							<li>${userInfo.birthday}</li>
							<li>${userInfo.addr }</li>
							<li>${userInfo.email}</li>
						</ul>
					</div>
					<div class="more-info">
						<span>查看更多&nbsp;&nbsp;&gt;</span>
					</div>
				</div>
				<div class="person_info2">
					<div class="info-title">
						<img src="img/signature.png" />
						<span>个性签名</span>
					</div>
					<div class="motto">
						<span>${userInfo.sign }</span>
					</div>
					<div class="info-title">
						<img src="img/hobby.png" />
						<span>兴趣爱好</span>
					</div>
					<div class="hobby">
						<ul>
							<c:forEach items="${hList }" var="h">
								<li>${h }</li>
							</c:forEach>
						</ul>
					</div>
					<div class="info-hidden">
						收起 ^
					</div>
				</div>
				<div class="messageList">
					<div class="expandPic">
						<img src="">
						<div class="close"></div>
					</div>
					<c:forEach items="${DynamicList }" var="dl">
						<div class="message">
							<div class="messageContent">
								<a href="goods.s?oper=goods&gid=${dl.gid }&uid=${dl.uid}"><h1 class="messageTime">${dl.year }<span class="year">年</span>
								<p class="day">${dl.day }<span class="month">/${dl.month }月</span></p></h1></a>
								
								<p class="infoText">${dl.context }</p>
								<div class="messagePic">
									<!-- 160 * 160 -->
									<ul>
										<c:forEach items="${dl.imageDiv }" var="id">
											<li>
												<img src="${id }" />
											</li>
										</c:forEach>
									</ul>
								</div>
								<p class="infoOperation">
									<span class="infoHandle">
										<a href="javascript:void(0);">${dl.share }</a>
										<a href="javascript:void(0);">${dl.comment }</a>
										<a href="javascript:void(0);">${dl.collection }</a>
									</span>
								</p>
							</div>
						</div>
					</c:forEach>
					<div class="pages">
						<a href="javascript:;" class="prePage">上一页</a>
						<a href="javascript:;">
							<ul class="pagesCount">
								<c:if test="${pageBean.totalPage <= 6}">
									<c:forEach begin="1" end="${pageBean.totalPage }" step="1" var="index">
										<li>${index }</li>
									</c:forEach>
								</c:if>
								<c:if test="${pageBean.totalPage > 6}">
									<c:forEach begin="${minPage }" end="${maxPage }" step="1" var="index">
										<li>${index }</li>
									</c:forEach>
								</c:if>
							</ul>
						</a>
						<a href="javascript:;" class="nextPage">下一页</a>
						<input type="hidden" name="" value="${pageBean.currentPage }" id="currentPage"/>
						<input type="hidden" name="" value="${pageBean.totalPage }" id="totalPage"/> 
						
					</div>
				</div>
				<div class="emptyDynamic">
					<a href="goods.s?oper=uploadfile&uid=${sessionScope.user.uid}" class="emptyDynamic-upload"></a>
				</div>
				<div class="emptyDynamic2">
				</div>
			</div>
			<div class="secPage">
				<div class="controlPanel">
					<ul>
						<li>关注<span>${followNum }</span></li>
						<li>粉丝<span>${fansNum }</span></li>
						<li>好友<span>${friendNum }</span></li>
						<li>返回</li>
					</ul>
				</div>
				<div class="mainPanel">
					<div class="tab_item">
						<span style="margin-left: 10px;">全部关注</span>
						<span class="S_txt1"></span>
					</div>
					<div class="manage_card">
						<span class="manage_card_sort">排序<img src="img/down_triangle.png"></span>
						<input type="text" autocomplete="off" class="f-pr" placeholder="输入昵称">
						<img src="img/search.png" class="search_pic">
					</div>
					<!-- 循环生成用户卡片 -->
					
					<!-- <div class="userCard">
						<div class="mod_pic">
							<a href="javascript:;"><img src="img/default_head.jpg" ></a>
						</div>
						<div class="mod_info">
							<a href="javascript:;" class="mod_info_name">有酒醉三生</a>
							<div class="mod_info_sex"></div>
							<p class="mod_info_motto">手握日月摘星辰，世间无我这般人</p>
							<button type="button" class="mod_info_btn">私信</button>
							<button type="button" class="mod_info_btn">取消关注</button>
							<input type="hidden" class="mod_info_sex" value="女">
						</div>
					</div> -->
				</div>
			</div>
		</div>
		<script src="js/top.js"></script>
		<script src="js/info.js"></script>
	</body>
</html>
