<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>知来智网-上传</title>
		<link rel="stylesheet" href="css/uploadfile.css" />
		<link rel="stylesheet" href="css/top.css" />
		<link rel="stylesheet" href="css/footer.css" />
		<script src="js/jquery-1.12.3.js"></script>
		<link rel="shortcut icon" type="image/x-icon" href="img/icon2.png" />
	</head>
	
	<!--用户点击提交      -->
	<script type="text/javascript">
	</script>
	<body>

		<!-- 头部 -->
		<jsp:include page="/public/top.jsp" />
		<input type="hidden" value="${sessionScope.user.uid }" class="uid">
		<!-- 主体 -->
		<div class="body">
			<form>
				<div class="left">
					<div class="title">上传资源</div>
					<div class="choose-file">
						<div class="up-file">
							<span class="upload-icon">
								<img src="img/uploadFile.png"/>
							</span>
							<span class="choose-intro-f">点击上传资源</span>
							<input type="file" id="file" style="display:none" class="file">
							<button type="button" class="btn_choiceFile"></button>
							<div class="error-tips">资源文件不能为空！</div>
						</div>
						<div class="form_tips">您可以上传小于<span style="color: red;">220MB</span>的文件,只允许上传rar,zip,jar,txt格式的文件</div> 
					</div>
					<div class="choice-pic">
						<ul style="list-style: none; display : inline-block;">
						</ul>
						<div class="up-pic">
							<span class="upload-icon">
								<img src="img/add.png"/>
							</span>
							<span class="choose-intro-p">点击添加图片</span>
							<input type="file" id="pic" style="display:none" class="pic" accept="image/jpeg,image/jpg,image/png">
							<button type="button" class="btn_choiceFile"></button>
							<div class="error-tips">请选择至少一张图片！</div>
						</div>
						<div class="form_tips">最多可以上传&nbsp;<span style="color: red;">6</span>&nbsp;张图片&nbsp;&nbsp;只允许上传 jpg,png格式的照片</div> 
					</div>
					
					<div class="filename">
						<label>资源名称：</label>
						<input type="text" max="50" id="txt_title" name="txt_title" maxlength="80" placeholder="请输入资源名称" class="txt_file">
						<div class="from-error">请填写资源的名称!</div>
						<em></em>
						<div class="form_tips">名称最多不超过80字，不少于10字</div>
					</div>

					<div class="filetype">
						<label>资源类型：</label>
						<select>
							<option>请选择</option>
							<c:forEach items="${typeBigList }" var="type">
								<option>${type.type }</option>
							</c:forEach>
						</select>
						<div class="from-error">请选择资源类型!</div>
						<em></em>
					</div>

					<div class="fileclass">
						<label>所属分类一：</label>
						<select>
							<option value="selectType">请选择</option>
							<c:forEach items="${typeSmallList }" var="type">
								<option>${type.type }</option>
							</c:forEach>
						</select>
						<div class="from-error">请选择资源分类!</div>
						<em></em>
					</div>
					
					<div class="fileclass">
						<label>所属分类二：</label>
						<select>
							<option value="selectType">请选择(可选)</option>
							<c:forEach items="${typeSmallList }" var="type">
								<option>${type.type }</option>
							</c:forEach>
						</select>
						<em></em>
					</div>
					
					<div class="fileclass">
						<label>所属分类三：</label>
						<select>
							<option value="selectType">请选择(可选)</option>
							<c:forEach items="${typeSmallList }" var="type">
								<option>${type.type }</option>
							</c:forEach>
						</select>
						<em></em>
					</div>
					<div class="filemiaoshu">
						<label>资源描述：</label>
						<textarea name="txt_desc" maxlength="500" id="txt_desc" placeholder="请对你的资源进行描述"></textarea>
						<div class="from-error">请填写资源描述!</div>
						<em></em>
					</div>

					<div class="check">
						<input type="checkbox" id="check_file" onclick="check()" checked><span style="color: #08BF91;">已认真阅读并同意<a href="javascript:;" style="color: #07519a">《知来智网协议》</a></span>
					</div>

					<div class="button">
						<input type="button" value="提交" name="提交" id="filesubmit"/>
					</div>
				</div>
				<div class="loading">
					<img src="img/loading.gif">
				</div>
			</form>
			<div class="right">
			</div>
		</div>
		<!-- 底部 -->
		<jsp:include page="/public/footer.jsp" />
		<script src="js/top.js"></script>
		<script src="js/footer.js"></script>
		<script src="js/upload.js"></script>
		<script type="text/javascript">
			function check(){
				 if(!$("input[type='checkbox']").is(':checked')){
					 $("#filesubmit").attr("disabled",true).css("background-color","#333");
				 }else{
					 $("#filesubmit").attr("disabled",false).css("background-color","#ca0c16");
				 }
			}
		</script>
	</body>
</html>

