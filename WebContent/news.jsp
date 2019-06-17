<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>知来智网-消息</title>
		<link rel="stylesheet" href="css/news.css" />
		<link rel="stylesheet" href="css/footer.css" />
		<link rel="stylesheet" href="css/top.css" />
		<link rel="shortcut icon" type="img/x-icon" href="img/icon2.png"/>
	</head>
	<body>
		<!-- 头部 -->
		<jsp:include page="/public/top.jsp" />
		<!-- 内容 -->
		<div class="wrapper">
			<div class="controlPanel">
				<ul>
					<li>评论</li>
					<li>私信</li>
				</ul>
			</div>
			<div class="mainPanel">
			
				<div class="commentList">
				
				<c:forEach items="${list }" var="t">
				
								<div class="comment">
									
										<div class="comment-person-info">
										
											<div class="comment-person-head">
												<img src="${t.user1.head}" />
											</div>
											
											<div class="comment-name">${t.user1.uname }<span style="color: gray;">&nbsp;回复</span></div>
											<div class="comment-time">${t.message.mdate}</div>
										
										</div>
										
										<div class="comment-goods" >
										<input type="hidden" class="turl" value="goods.s?oper=goods&gid=${t.good.gid}&uid=${t.good.uid }">
											<img src="${t.good.gimage1}" class="goods-pic">
											<p class="goods-info"><a href="user.s?oper=info&uid=${sessionScope.user.uid}&currentPage=1">${t.user.uname }</a>&nbsp;:&nbsp;${t.good.name }</p>
										</div>
										
										<div class="comment-context">
											<img src="${t.user1.head }">
											<div class="user-name">${t.user1.uname }<span>&nbsp;回复&nbsp;</span>${sessionScope.user.uname }&nbsp;</div>
											<div class="context">${t.message.msg}</div>
										</div>
										
										<div class="comment-frame">
										<input class="gid" type="hidden" value="${t.message.gid}">
										<input class="uid2" type="hidden" value="${t.user1.uid }">
										<input class="tmid" type="hidden" value="${t.message.tmid }">
										<input class="thead" type="hidden" value ="${sessionScope.user.head}">
										<input class="uname" type="hidden" value ="${t.user1.uname }">
											<textarea placeholder="评论" class="comment-textarea"></textarea>
											<input type="button" value="发表评论" class="reply-send" disabled>
										</div>
										
									</div>
								
				</c:forEach>
					
				</div>
				<div class="private-letter">
					<div class="chatPanel">
						<div class="chatPanel-left">
							<div class="search-friend">
								<input type="text" autocomplete="off" class="f-pr" placeholder="搜索联系人">
								<img src="img/search.png" class="search_pic">
							</div>
							<div class="sessionwrap">
								<ul>
									<li>
										<img src="img/500.jpg" />
										<span>有酒醉三生</span>
									</li>
									<li>
										<img src="img/404.jpg" />
										<span>李青</span>
									</li>
								</ul>
							</div>
						</div>
						<div class="chatPanel-right">
							<img src="img/default_chat.png" style="width: 100%; height: 100%;"/>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 底部 -->
		<jsp:include page="/public/footer.jsp" />
		
		<script src="js/jquery-1.12.3.js"></script>
		<script src="js/top.js"></script>
		<script src="js/footer.js"></script>
		<script src="js/news.js"></script>
	</body>
</html>

