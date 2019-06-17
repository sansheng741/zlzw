<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>知来智网</title>
<link rel="stylesheet" href="css/top.css" />
<link rel="stylesheet" href="css/footer.css" />
<link rel="stylesheet" href="css/index.css" />
<script src="js/jquery-1.12.3.js"></script>
<link rel="shortcut icon" type="image/x-icon" href="img/icon2.png" />

</head>
<body>
	<!-- 头部 -->
	<jsp:include page="/public/top.jsp" />
	<!-- 内容 -->
	<div class="wrapper">
		<div class="Sowing-map">
			<ol class="point">
				<li></li>
				<li></li>
				<li></li>
				<li></li>
			</ol>
		</div>

		<c:forEach items="${gooddiv}" var="gdiv">
			<c:choose>
				<c:when test="${gdiv.bid ==1}">

					<div class="videos">
						<div class="home-section-header">
							<span class="home-section-title"> ${gdiv.type} <span>|</span>
							</span> <span class="home-section-desc">及时学习，跟进时代的脚步</span> <a
								href="search?bid=${gdiv.bid}&uid=${sessionScope.user.uid}"
								class="see-more">更多></a>
						</div>
						<ul class="videosPic">
							<c:forEach items="${gdiv.list}" var="good">
								<li><a
									href="goods.s?oper=goods&gid=${good.gid}&uid=${good.uid}">
										<img src="${good.gimage1}" class="course-pic" />
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
									</div></li>
							</c:forEach>

						</ul>
					</div>


				</c:when>

				<c:otherwise>

				</c:otherwise>
			</c:choose>
		</c:forEach>

		<c:forEach items="${gooddiv}" var="gdiv">
			<c:choose>
				<c:when test="${gdiv.bid ==1}">
				</c:when>

				<c:otherwise>
					<div class="g${gdiv.bid}">
						<div class="home-section-header">
							<span class="home-section-title"> ${gdiv.type} <span>|</span>
							</span> <span class="home-section-desc">及时学习，跟进时代的脚步</span> <a
								href="search?bid=${gdiv.bid}&uid=${sessionScope.user.uid}"
								class="see-more">更多></a>
						</div>
						<div class="movePic_books m${gdiv.bid }">
							<a href="javascript:void(0)" class="ls"><span
								class="leftArrow spans">&lt;</span></a>
							<ul class="booksPic p${gdiv.bid }">

								<c:forEach items="${gdiv.list}" var="good">
									<li><a
										href="goods.s?oper=goods&gid=${good.gid}&uid=${good.uid}">
											<img src="${good.gimage1}" class="course-pic" />
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
										</div></li>
								</c:forEach>
							</ul>
							<a href="javascript:void(0)" class="rs"><span
								class="rightArrow spans">&gt;</span></a>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>


	<!-- 底部 -->
	<jsp:include page="/public/footer.jsp" />

	<script src="js/jquery-1.12.3.js"></script>
	<script src="js/top.js"></script>
	<script src="js/footer.js"></script>
	<script src="js/index.js"></script>
	<c:if test="${! empty msg}">
		<script type="text/javascript">
			 alert('${msg}');
		</script>
	</c:if>
</body>
</html>

