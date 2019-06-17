<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<input type="hidden" id="uid" value="${sessionScope.user.uid}"/>
<div class="top">
	<div class="icon">
		<a href="user.s?oper=index&uid=${sessionScope.user.uid}">
			<img src="img/icon3.png" />
			<span>知来智网</span>
		</a>
	</div>
	<div class="nav">
		<ul>
			<a  onclick="favorite()" href="javascript:void (0);">
				<li>我的收藏</li>
			</a>
			<a  onclick="myDownload()" href="javascript:void (0);">
				<li>我的下载</li>
			</a>
			<a  onclick="uploadfile()" href="javascript:void (0);">
				<li>上传</li>
			</a>
			<a  onclick="mySrc()" href="javascript:void (0);">
				<li>我的资源</li>
			</a>
			<a  onclick="index()" href="javascript:void (0);">
				<li>首页</li>
			</a>
		</ul>
	</div>
	<div class="search">
	
		<input type="text" class="search-ipt" placeholder="搜索" <c:if test="${ !empty key }"> value="${key}" </c:if>  />
		<img src="img/search.png" class="search-pic">
	</div>
	<div class="infos">
		<div class="myhead" > 
		<img src="${sessionScope.user.head}" id="infosHead" style="width: 40px; height: 40px; border-radius:50%  " />
		</div>
		<div class="myinfo">
			<div class="name" >
			<input type="hidden" id="tuname" value="${sessionScope.user.uname}">
				${sessionScope.user.uname}
				<a href="user.s?oper=editInfo&uid=${sessionScope.user.uid}"><img src="img/edit.png" class="edit"/></a>
			</div>
			<div class="info" >
				<ul>
					<li>
						<div></div>关注<!-- 你关注别人-->
					</li>
					
					<li>
						<div></div>粉丝<!--别人关注你 -->
					</li>
					<li>
						<div></div>好友 <!-- 互相关注 -->
					</li>
				</ul>
			</div>
			<div class="info2">
				<ul>
					<li><a href="user.s?oper=info&uid=${sessionScope.user.uid}&currentPage=1&maxPage=6&minPage=1"><img src="img/head.png">个人中心</a></li>
					<li><a href="user.s?oper=mySrc&uid=${sessionScope.user.uid}"><img src="img/src.png">资源</a></li>
					<li><a href="user.s?oper=myDownload&uid=${sessionScope.user.uid}"><img src="img/download.png">下载</a></li>
					<li><a href="user.s?oper=favorite&uid=${sessionScope.user.uid}"><img src="img/collect(1).png">收藏</a></li>
				</ul>
			</div>
			<div class="info3">
				<a href="user.s?oper=quit&account=${sessionScope.user.account }"><span>退出</span></a>
			</div>
		</div>
		<div class="news-tip">
			<img src="img/news.png"/>
			<em class="message-tips">消息(<span id="newsNumber" style="color: red;"></span>)</em>
		</div>
	</div>
	<img src="img/unlogin.png" class="unlogin" title="登录"/>
</div>

