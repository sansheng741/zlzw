<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>知来智网-商品信息</title>
		<link rel="shortcut icon" type="image/x-icon" href="img/icon2.png" />
		<link rel="stylesheet" href="css/top.css" />
		<link rel="stylesheet" href="css/footer.css" />
		<link rel="stylesheet" href="css/goods.css" />
	</head>
	<body>
		<!-- 头部 -->
		<jsp:include page="/public/top.jsp" />
		<!-- 举报界面 -->
		<div class="report">
			<i class="report-close"></i>
			<h3 class="report-title">举报</h3>
			<div class="report-content">
				<p class="report-tip1">若举报审核通过，可返还被扣除的积分</p>
				<ul class="int_list">
					<li><span>举报人：</span>
						<label>${sessionScope.user.uname}</label>
					</li>
					<li><span>被举报人：</span>
						<label></label>
					</li>
					<li><span><em>*</em>类型：</span>
						<select id="report_type" name="report_type" class="form-control">
							<option style="font-size:12px">请选择类型</option>
							<option style="font-size:12px">资源无法下载 （ 404页面、下载失败、资源本身问题）</option>
							<option style="font-size:12px">资源无法使用 （文件损坏、内容缺失、题文不符）</option>
							<option style="font-size:12px">侵犯版权资源 （侵犯公司或个人版权）</option>
							<option style="font-size:12px">虚假资源 （恶意欺诈、刷分资源）</option>
							<option style="font-size:12px">含色情、危害国家安全内容</option>
							<option style="font-size:12px">含广告、木马病毒资源</option>
						</select>
					</li>
					<li class="report_reason"><span><em>*</em>详细原因：</span>
						<textarea id="report_description" name="report_description" class="texta" style="margin: 0px; width: 320px; height: 114px;"></textarea>
					</li>
				</ul>
				<p class="report-tip2"></p>
			</div>
			<div class="report-btn">
				<button type="button" class="report-cancel">取&nbsp;&nbsp;消</button>
				<button type="button" class="report-sub">提&nbsp;&nbsp;交</button>
			</div>
		</div>
		<!-- 成功收藏界面 -->
		<div class="favorite-success">
			<i class="favorite-close"></i>
			<h4 class="sec_pop_t">收藏成功！点击&nbsp;<a onclick="favorite()" href="javascript:void(0);" target="_blank">我的收藏</a>&nbsp;查看收藏的全部资源</h4>
		</div>
		<div class="hide"></div>
		<!-- 内容 -->
		<div class="wrapper">
			<!-- 卖家信息 -->
			<div class="sellerInfo">
				<input type="hidden" id="sellerUid" value="${good.user.uid }">
				<div class="seller-head">
					<img src="${good.user.head }">
				</div>
				<div class="seller-name">${good.user.uname}</div>
				<div class="seller-sex"></div>
				<input type="hidden"  id="sex" value="${good.user.sex}">
				<div class="seller-time"><span>${good.time}</span>&nbsp;上传
					&nbsp;&nbsp;&nbsp;&nbsp;大小：<span>${fileSize }</span>&nbsp;&nbsp;&nbsp;&nbsp;
					类型：<span>${good.type.type}</span>
				</div>
			</div>
			<!-- 商品信息 -->
			<div class="goods-info">
				<div class="goods-photo">
					<div class="box">
						<div class="small goods-photo-main">
							<img src="${good.good.gimage1}" />
							<div class="mask"></div>
						</div>
						<div class="big">
							<img src="${good.good.gimage1}" />
						</div>
					</div>
					<div class="goods-photo-other">
						<ul>
						<c:if test="${!empty good.good.gimage1}">
						 <li>
						<img src="${good.good.gimage1}" />
						</li>
						</c:if>
						
						<c:if test="${!empty good.good.gimage2}">
						 <li>
						<img src="${good.good.gimage2}" />
						</li>
						
						</c:if>
						<c:if test="${!empty good.good.gimage3}">
						 <li>
						<img src="${good.good.gimage3}" />
						</li>
						</c:if>
						<c:if test="${!empty good.good.gimage4}">
						 <li>
						<img src="${good.good.gimage4}" />
						</li>
						</c:if>
						<c:if test="${!empty good.good.gimage5}">
						 <li>
						<img src="${good.good.gimage5}" />
						</li>
						</c:if>
						<c:if test="${!empty good.good.gimage6}">
						 <li>
						<img src="${good.good.gimage6}" />
						</li>
						</c:if>
						
							
							<!-- <li>
								<img src="img/body_bg_page.jpg" />
							</li>
							<li>
								<img src="img/qrcode.jpg" />
							</li>
							<li>
								<img src="img/map2.jpg" />
							</li>
							<li>
								<img src="img/mid_banner/banner_01.png" />
							</li> -->
						</ul>
					</div>
				</div>
				<div class="goods-desc">${good.good.context }</div>
				<div class="selling-price">所需：<span>${good.good.price}</span>积分</div>
				<div class="selling-operation">
					<a href="goods.s?oper=download&gid=${good.good.gid }&uid=${sessionScope.user.uid}" class="down-load">立即下载</a>
					<a href="javascript:;" class="open_vip">
						<img src="img/vip.png">
						<span>开通VIP</span>
					</a>
				</div>
				<div class="share-favorite-report">
					<!--分享功能-->
					<div class="share" id="share-btn">
						<i class="share-icon">
							<img src="img/forward.png">
						</i>
						<span>分享</span>
					</div>
					<!--收藏功能-->
					<a href="javascript:;" id="favorite" class="favorite">
						<i class="favorite-icon">
							<img src="img/collect(1).png">
						</i>
						<span>收藏</span>
					</a>
					<!--举报功能-->
					<a href="javascript:;" id="download_report" class="download_report">
						<i class="report-icon">
							<img src="img/report.png">
						</i>
						<span>
							<font color="red">举报</font>
						</span>
					</a>
				</div>
				<div class="message-board">
					<h2>留言&nbsp;·&nbsp;<span id="mnumber">${good.good.macc.mnumber}</span><span class="msg-write">我要留言</span></h2>
					<div class="comment">
						<textarea class="comment-text" placeholder="想对作者说点什么" maxlength="1000"></textarea>
						<div class="comment-operation">
							你还能输入<span>1000</span>个字符
							<input type="hidden" value="${good.good.gid}" id="gid">
							
							<input type="button" value="发表评论" class="send" disabled />
						</div>
					</div>
					<div class="message">
						<ul>
						
						
						<c:forEach items="${board }" var="tboard">
						
						<li>
								<div class="user-msg">
									<div class="user-head">
										<img src="${tboard.bmu.user1.head }"  />
									</div>
									<div class="user-name">${tboard.bmu.user1.uname}</div>
									<c:if test="${sessionScope.user.uid ==tboard.bmu.message.uid1 }">
									<div class="user-delReply">删除&nbsp;&nbsp;|
									<input type="hidden" value="${tboard.bmu.message.mid }" class ="tmid" >
									</div>
									</c:if>
									
									<div class="user-reply">回复</div>
									<div class="user-msg-replay">${tboard.bmu.message.msg }</div>
									<div class="reply-comment">
										<textarea class="reply-msg" placeholder="回复" maxlength="150"></textarea>
										<div class="comment-operation">
										        <input type="hidden" value="${tboard.bmu.user1.uid }">
												<input type="hidden" value="${tboard.bmu.message.mid }">
												<input type="hidden" value="${tboard.bmu.message.gid }">
												<input type="hidden" value="${tboard.bmu.user1.uname}">
											<input type="button" value="发表评论" class="reply-send" disabled />
										</div>
									</div>
								</div>
								<c:forEach items="${tboard.list }" var="ttboard">
										<div class="reply">
											<div class="reply-user-head">
												<img src="${ttboard.user1.head }" />
											</div>
											<div class="reply-user-name">${ttboard.user1.uname }<span>&nbsp;回复&nbsp;</span>${ttboard.user2.uname }</div>
											
											<c:if test="${sessionScope.user.uid==ttboard.message.uid1 }">
											<div class="reply-delReply">删除&nbsp;&nbsp;|
											<input type="hidden" value="${ttboard.message.mid}" class ="tmid">
											</div>
											</c:if>
											
											<div class="reply-reply">回复</div>
											<div class="reply-user-msg">${ttboard.message.msg}</div>
											<div class="reply-comment">
												<textarea class="reply-msg" placeholder="回复" maxlength="150"></textarea>
												<div class="comment-operation">
												
												<input type="hidden" value="${ttboard.user1.uid }">
												<input type="hidden" value="${ttboard.message.tmid }">
												<input type="hidden" value="${ttboard.message.gid }">
												<input type="hidden" value="${ttboard.user1.uname}">
												<input type="button" value="发表评论" class="reply-send" disabled />
												</div>
											</div>
										</div>
								</c:forEach>
								
								<div class="user-time">${tboard.bmu.message.mdate}</div>
							</li>
							
						
						</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			<!-- 推荐商品 -->
			<div class="recommend">
				<h2>为你推荐</h2>
				<ul>
				
				
				<c:forEach items="${good.list}" var="t">
				  <a href="goods.s?oper=goods&gid=${t.gid }&uid=${t.uid}">
						<li>
							<img src="${t.gimage1 }" />
							<span class="desc">${t.context}</span>
						</li>
					</a>
				
				</c:forEach>
				
					
		
				</ul>
			</div>
		</div>
		<!-- 底部 -->
		<jsp:include page="/public/footer.jsp" />
		
		<script src="js/jquery-1.12.3.js"></script>
		<script src="js/top.js"></script>
		<script src="js/footer.js"></script>
		<script src="js/goods.js"></script>
		
		<c:if test="${! empty msg}">
			<script type="text/javascript">
				alert('${msg}');
			</script>
		</c:if>
	</body>
</html>
