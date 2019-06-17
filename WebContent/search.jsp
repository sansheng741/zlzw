<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>知来智网-搜索</title>
		<link rel="stylesheet" href="css/top.css" />
		<link rel="stylesheet" href="css/search.css" />
		<link rel="stylesheet" href="css/footer.css" />
		<link rel="shortcut icon" type="image/x-icon" href="img/icon2.png" />
	</head>
	<body>
		<!-- 头部 -->
		<jsp:include page="/public/top.jsp" />
		
		<div class="wrapper">
			<div class="search-count">
			<c:if test="${!empty key }"><h4>“<span>${key }</span>”的搜索结果</h4></c:if>
				
			</div>
			<div class="content">
				<ul>
					<c:forEach items="${gdiv.list}" var="good">
					<li>
						<a href="goods.s?oper=goods&gid=${good.gid}&uid=${good.uid}">
							<img src="${good.gimage1}" class="course-pic"/>
						</a>
						<div class="course-body">
							<div class="course-name">${good.name }</div>
							<div class="course-desc">${good.context }</div>
							<div class="course-footer">
								<ul>
									<li><img src="img/collect(1).png">&nbsp;&nbsp;<span>${good.macc.cnumber }</span></li>
									<li><img src="img/comment.png">&nbsp;&nbsp;<span>${good.macc.mnumber }</span></li>
								</ul>
							</div>
						</div>
					</li>	
					</c:forEach>
				</ul>
			</div>
			
			<input type="hidden" id="turl" value="pageMax=${gdiv.pageMax}&<c:if test="${! empty key }">key=${key}&</c:if>">
			
			<div id="page">
				<c:if test="${ gdiv.pageSize>=1 }">
				<ul class="pagesCount">
				
					<li class="prePage Page-turning">
					<a>上一页</a>
					</li>
					                                 
					<c:forEach begin="${gdiv.pageMax-5 }" end="${gdiv.pageSize }" step="1" varStatus="t" >
							<li  <c:if test="${gdiv.pageNumber==t.index }"> style =" background: #0c9 ; color: #fff  ;" </c:if>    >
							${t.index }
							</li>
					</c:forEach>
				
					<li class="nextPage Page-turning">
					<a>下一页</a>
					</li>
				</ul>
			</c:if>
				<input type="hidden" id="currentPage" value="${gdiv.pageNumber}"/>
				<input type="hidden" id="totalPage" value="${gdiv.total }" />
			</div>
		</div>
		
		<!-- 底部 -->
		<jsp:include page="/public/footer.jsp" />
		
		<script src="js/jquery-1.12.3.js"></script>
		<script src="js/top.js"></script>
		<script src="js/search.js"></script>
		<script src="js/footer.js"></script>
	</body>
</html>

