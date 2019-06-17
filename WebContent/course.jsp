<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>知来智网-更多资源等你来</title>
		<link rel="stylesheet" href="css/top.css" />
		<link rel="stylesheet" href="css/course.css" />
		<link rel="stylesheet" href="css/footer.css" />
		<link rel="shortcut icon" type="image/x-icon" href="img/icon2.png" />
	</head>
	<body>
		<!-- 头部 -->
		<jsp:include page="/public/top.jsp" />
		
		<div class="wrapper">
			<div class="content-option">
				<div class="course-cates-title">类别：</div>
				<div class="category">
				
				    <c:choose>
						    <c:when test="${empty bid && empty sid }">
						    <a href="search" style="color: rgb(255, 255, 255); background: rgb(8, 191, 145) none repeat scroll 0% 0%;" >全部</a>
						    </c:when>
						    
						    <c:otherwise>
						     <a href="search" >全部</a>
						    </c:otherwise>
				    </c:choose>
				    
					<c:forEach items="${big }" var="type">
							<c:choose>
									<c:when test="${bid ==type.bid}">
									<a href="search?bid=${type.bid}" style="color: rgb(255, 255, 255); background: rgb(8, 191, 145) none repeat scroll 0% 0%;">${type.type}</a>
									</c:when>
									
									<c:otherwise>
									<a href="search?bid=${type.bid}">${type.type}</a>
									</c:otherwise>
							</c:choose>
					</c:forEach>
			
				</div>
				<div class="course-cates-title">标签：</div>
				<div class="tag">
				
				<c:choose>
				<c:when test="${empty bid && empty sid }">
				
			     	<a href="search" style="color: rgb(255, 255, 255); background: rgb(8, 191, 145) none repeat scroll 0% 0%;" >全部</a>
				
				</c:when>
				<c:otherwise>
				
				     <c:choose>
				     <c:when test="${! empty bid && empty sid }">
				    <a href="search?bid=${bid}" style="color: rgb(255, 255, 255); background: rgb(8, 191, 145) none repeat scroll 0% 0%;" >全部</a>
				     </c:when>
				     
				     <c:otherwise>
				     <a href="search"  >全部</a>
				     </c:otherwise>
				     
				     </c:choose>
				     
				</c:otherwise>
				
				
				</c:choose>
					<!-- 判断大标签     是不是全部的   -->
					<c:choose>
							<c:when test="${empty bid}">
									<!-- bid 为空的话    -->
									<c:forEach items="${small}" var="type">
									
											<c:choose>
													<c:when test="${! empty sid &&sid ==type.sid}">
													<a href="search?sid=${type.sid}" style="color: rgb(255, 255, 255); background: rgb(8, 191, 145) none repeat scroll 0% 0%;">${type.type}</a>
													</c:when>
													
													<c:otherwise>
													<a href="search?sid=${type.sid}">${type.type}</a>
													</c:otherwise>
											</c:choose>
									
									</c:forEach>
							</c:when>
							
							<c:otherwise>
							
										<!-- bid 不为空的话    -->
										<c:forEach items="${small}" var="type">
												<c:choose>
														<c:when test="${! empty sid &&sid ==type.sid}">
														<a href="search?sid=${type.sid}&bid=${bid}" style="color: rgb(255, 255, 255); background: rgb(8, 191, 145) none repeat scroll 0% 0%;">${type.type}</a>
														</c:when>
														
														<c:otherwise>
														<a href="search?sid=${type.sid}&bid=${bid}">${type.type}</a>
														</c:otherwise>
												</c:choose>
										</c:forEach>
							</c:otherwise>
					</c:choose>
					
				</div>
				<div class="courses-sort">
				
			 	<c:choose>
				
				<c:when test="${empty bid }">
				<!-- bid 为空  -->
					<c:choose>
							<c:when test="${empty sid }">
							<!-- sid 为空 -->
									<a href="search?oper=comprehensive"  id="comprehensive" <c:if test="${gdiv.type=='comprehensive' }">style="color: rgb(255, 255, 255); background: rgb(8, 191, 145) none repeat scroll 0% 0%;"</c:if>   >综合</a>
									<a href="search?oper=fire"  id="fire" <c:if test="${gdiv.type=='fire' }">style="color: rgb(255, 255, 255); background: rgb(8, 191, 145) none repeat scroll 0% 0%;"</c:if>  >最热</a>
									<a href="search?oper=newbest"  id="newbest" <c:if test="${gdiv.type=='newbest' }">style="color: rgb(255, 255, 255); background: rgb(8, 191, 145) none repeat scroll 0% 0%;"</c:if> >最新</a>
							</c:when>
							
							<c:otherwise>
							<!-- sid不为空 -->
									<a href="search?oper=comprehensive&sid=${sid }"  id="comprehensive"  <c:if test="${gdiv.type=='comprehensive' }">style="color: rgb(255, 255, 255); background: rgb(8, 191, 145) none repeat scroll 0% 0%;"</c:if>  >综合</a>
									<a href="search?oper=fire&sid=${sid }"  id="fire"  <c:if test="${gdiv.type=='fire' }">style="color: rgb(255, 255, 255); background: rgb(8, 191, 145) none repeat scroll 0% 0%;"</c:if>  >最热</a>
									<a href="search?oper=newbest&sid=${sid }"  id="newbest" <c:if test="${gdiv.type=='newbest' }">style="color: rgb(255, 255, 255); background: rgb(8, 191, 145) none repeat scroll 0% 0%;"</c:if> >最新</a>
								</c:otherwise>
					
					</c:choose>
					
				</c:when>
				<c:otherwise>
				 <!-- bid不为空 -->
				        <c:choose>
							<c:when test="${empty sid }">
							<!-- sid 为空 -->
							        <a href="search?oper=comprehensive&bid=${bid }"  id="comprehensive" <c:if test="${gdiv.type=='comprehensive' }">style="color: rgb(255, 255, 255); background: rgb(8, 191, 145) none repeat scroll 0% 0%;"</c:if>  >综合</a>
									<a href="search?oper=fire&bid=${bid }"  id="fire" <c:if test="${gdiv.type=='fire' }">style="color: rgb(255, 255, 255); background: rgb(8, 191, 145) none repeat scroll 0% 0%;"</c:if> >最热</a>
									<a href="search?oper=newbest&bid=${bid }"  id="newbest" <c:if test="${gdiv.type=='newbest' }">style="color: rgb(255, 255, 255); background: rgb(8, 191, 145) none repeat scroll 0% 0%;"</c:if>  >最新</a>
							</c:when>
							
							<c:otherwise>
							<!-- sid不为空 -->
								    <a href="search?oper=comprehensive&bid=${bid }&sid=${sid}"  id="comprehensive" <c:if test="${gdiv.type=='comprehensive' }">style="color: rgb(255, 255, 255); background: rgb(8, 191, 145) none repeat scroll 0% 0%;"</c:if>  >综合</a>
									<a href="search?oper=fire&bid=${bid }&sid=${sid}"  id="fire"  <c:if test="${gdiv.type=='fire' }">style="color: rgb(255, 255, 255); background: rgb(8, 191, 145) none repeat scroll 0% 0%;"</c:if> >最热</a>
									<a href="search?oper=newbest&bid=${bid }&sid=${sid}"  id="newbest" <c:if test="${gdiv.type=='newbest' }">style="color: rgb(255, 255, 255); background: rgb(8, 191, 145) none repeat scroll 0% 0%;"</c:if> >最新</a>
							</c:otherwise>
					
					</c:choose>
				
				</c:otherwise>
				
				</c:choose> 
					
				</div>
			</div>
			<div class="content">

				<ul id="content">
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
			<!--  -->
			
			<input type="hidden" id="turl" value="pageMax=${gdiv.pageMax}&<c:if test="${! empty gdiv.type}">oper=${gdiv.type}&</c:if><c:if test="${! empty bid}">bid=${bid}&</c:if><c:if test="${! empty sid}">sid=${sid}&</c:if>" >
			
			</script>
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
				<input type="hidden" id="totalPage" value="${gdiv.total}" />
			</div>
		</div>
		<!-- 底部 -->
		<jsp:include page="/public/footer.jsp" />
		<script src="js/jquery-1.12.3.js"></script>
		<script src="js/top.js"></script>
		<script src="js/course.js"></script>
		<script src="js/footer.js"></script>
		
		
		
	</body>
</html>
