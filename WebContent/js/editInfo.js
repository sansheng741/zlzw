$(function() {

	changeSex();
	addHobby();
	delHobby();
	showUploadPic();
	updateHead();
	changeNav();
	updateInfo();
	
	function changeNav(){
		//改变被选中的导航条
		$(".nav>ul>a>li").hover(
			function(){
				$(this).css({
					borderBottom : "3px solid red"
				});
				$(".nav>ul>a>li").not($(this)).css({
					borderBottom : "0"
				});
			},
			function(){
				$(".nav>ul>a>li").css({
					borderBottom : "0"
				});
			}
		);
	}
	//改变性别
	function changeSex() {
		var sex = $("#sex").val();
		if (sex == "男") {
			$(".mysex>label").eq(0).addClass("changeSex");
		} else if (sex == "女") {
			$(".mysex>label").eq(1).addClass("changeSex");
		} else {
			$(".mysex>label").eq(2).addClass("changeSex");
		}
		$(".mysex>label").on("click", function() {
			$(this).addClass("changeSex");
			$(".mysex>label").not($(this)).removeClass("changeSex");
			$("#sex").val($(this).html());
		});
	}
	//添加爱好
	function addHobby() {
		$(".addtag").on("click", function() {
			//判断tag节点下是否已经存在.hobby
			var tagLength = $(".tag").find(".hobby").length;
			var hasHobby = tagLength > 0;
			if (hasHobby) {
				//判断最后一个hobby节点是否为空
				var lastHobby = $(".tag>.wrap-tag>.hobby").eq(tagLength - 1);
				if (lastHobby.html() != "") {
					//添加一个hobby节点
					var point = createHobbyPoint();
					$(".addtag").before(point);
					$(".tag>.wrap-tag>.hobby").eq(tagLength).focus();
				} else {
					//删除最后一个hobby节点
					lastHobby.parents(".wrap-tag").remove();
				}
			} else {
				//添加一个hobby节点
				var point = createHobbyPoint();
				$(".addtag").before(point);
				$(".tag>.wrap-tag>.hobby").eq(tagLength - 1).focus();
			}
		});
	}
	//删除爱好节点
	function delHobby() {
		$("body").delegate(".removeTag", "click", function() {
			$(this).parents(".wrap-tag").remove();
		});
		$("body").delegate(".tag>.wrap-tag>.hobby", "blur", function() {
			if ($(this).html() == "") {
				$(this).parents(".wrap-tag").remove();
			}
		});
	}
	//创建爱好节点
	function createHobbyPoint() {
		var point = $(
			"<div class=\"wrap-tag\">" +
			"<span contenteditable=\"true\" name=\"hobby\" class=\"hobby\"></span>" +
			"<span class=\"removeTag\"></span>" +
			"</div>"
		);
		return point;
	}
	//展示上传头像
	function showUploadPic() {
		function ProcessPic(e) {
			var file = document.getElementById('pic').files[0];
			if (file) {
				var reader = new FileReader();
				reader.onload = function(event) {
					var txt = event.target.result;
					// 判断上传图片的类型
					var type = getType($("#pic").val());
					if (type == ".PNG" || type == ".jpg" || type == ".png" || type == ".JPG") {
						if(parseInt(file.size/1024/1024) > 2){
							alert("您只能上传小于2MB的图片");
							return;
						}
						$(".headpic").attr("src", txt);
						$(".btn-confirm").prop("disabled",false);
						$(".btn-confirm").css({
							backgroundColor : "rgb(0,161,214)",
							color : "white",
							cursor: "pointer"
						});
					}
				}
				reader.readAsDataURL(file);
			}
		}

		function contentLoadedP() {
			document.getElementById('pic').addEventListener('change',
				ProcessPic, false);
		}

		window.addEventListener("DOMContentLoaded", contentLoadedP, false);
	}
	// 得到文件类型
	function getType(path) {
		return path.substring(path.lastIndexOf("."));
	}
	//更新头像
	function updateHead(){
		$(".btn-confirm").on("click",function(){
			var formData = new FormData();
			formData.append('pic', $('#pic')[0].files[0]);  //文件
			$.ajax({
		        url: 'user.s?oper=updateHead&uid='+$("#uid").val(),
		        type: 'POST',
		        cache: false,
		        data: formData,
		        processData: false,
		        contentType: false,
		        success : function(data){
		        	alert(data);
		        	location.reload();
		        }
			});
		});
	}
	
	//保存
	function updateInfo(){
		$(".el-button").on("click", function() {
			//获取值
			var data = {}; //用来存储数据的对象
			data.uname = $("#username").val().trim();
			data.sex = $("#sex").val().trim();
			data.tel = $("#tel").val().trim();
			data.email = $("#email").val().trim();
			data.addr = $("#addr").val().trim();
			data.hobby = [];
			$('.hobby').each(function(key, value) {
				data.hobby[key] = $(this).html().trim();
			});
			data.sign = $("#motto").val().trim();
			data.birthday = $("#date").val();
			//做前端验证
			//邮箱 电话 昵称

			var emailReg = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
			var telReg = /^1\d{10}$/;
//			var nameReg = /^[a-zA-Z0-9_-]{4,16}$/;

			var emailFlag = false,
				telFlag = false;
//			if (data.uname == "") {
//				$("#username").siblings(".from-error").eq(0).css({
//					display: "inline-block"
//				});
//			} else {
//				if (!nameReg.test(data.uname)) {
//				console.log(data.uname);
//					$("#username").siblings(".from-error").eq(1).css({
//						display: "inline-block"
//					});
//					$("#username").siblings(".from-error").eq(0).css({
//						display: "none"
//					});
//				} else {
//					$("#username").siblings(".from-error").eq(1).css({
//						display: "none"
//					});
//					$("#username").siblings(".from-error").eq(0).css({
//						display: "none"
//					});
//					nameFlag = true;
//				}
//			}
			if (data.tel == "") {
				$("#tel").siblings(".from-error").eq(0).css({
					display: "inline-block"
				});
			} else {
				if(data.tel == "保密"){
					telFlag = true;
				}else{
					if (!telReg.test(data.tel)) {
						$("#tel").siblings(".from-error").eq(1).css({
							display: "inline-block"
						});
						$("#tel").siblings(".from-error").eq(0).css({
							display: "none"
						});
					} else {
						$("#tel").siblings(".from-error").eq(1).css({
							display: "none"
						});
						$("#tel").siblings(".from-error").eq(0).css({
							display: "none"
						});
						telFlag = true;
					}
				}
			}
			if (data.email == "") {
				$("#email").siblings(".from-error").eq(0).css({
					display: "inline-block"
				});
			} else {
				if (!emailReg.test(data.email)) {
					$("#email").siblings(".from-error").eq(1).css({
						display: "inline-block"
					});
					$("#email").siblings(".from-error").eq(0).css({
						display: "none"
					});
				} else {
					$("#email").siblings(".from-error").eq(1).css({
						display: "none"
					});
					$("#email").siblings(".from-error").eq(0).css({
						display: "none"
					});
					emailFlag = true;
				}
			}
			if (emailFlag && telFlag) {
				var json = JSON.stringify(data);
				$.ajax({
					url : "user.s?oper=updateInfo&uid="+$("#uid").val(),
					type : "post",
					data : "json="+json,
					success : function(data){
						alert(data);
						location.reload();
					}
				});
			}
		});
	}
});
