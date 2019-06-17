$(function() {
	changePic();
	showComment();
	comment();
	report();
	favorite();
	user_reply();
	jumpToInfo();
	changeNav();
	changeSex();
	delReply();
	magnifier();
	
	function magnifier(){
		var box = document.getElementsByClassName("box")[0];
		
		var small = box.children[0];
		var big = box.children[1];
		var bigImg = big.children[0];
		var mask = small.children[1];
		var wrapper = document.getElementsByClassName("wrapper")[0];
		var goodsInfo = document.getElementsByClassName("goods-info")[0]
		//big和mask在鼠标移入small时显示，移出时隐藏
		small.onmouseenter = function(){
			big.style.display = "block";
			mask.style.display = "block";
		};
		small.onmouseleave = function(){
			big.style.display = "none";
			mask.style.display = "none";
		};
		
		small.onmousemove = function(event){
			event = event || window.event;
			//mask跟随鼠标移动，且不会超出small范围
			//x作为mask的left值，y作mask的top值。
			var pagex = event.pageX || scroll().left + event.clientX;
			var pagey = event.pageY || scroll().top + event.clientY;
			
			//减去mask宽高的一半，让鼠标在mask的中间
			var x = pagex - wrapper.offsetLeft - mask.offsetWidth/2;
			var y = pagey - wrapper.offsetTop - goodsInfo.offsetTop - mask.offsetHeight/2;
			
			//不让mask超出small
			if(x < 0){
				x=0;
			}
			if(x > small.offsetWidth - mask.offsetWidth){
				x = small.offsetWidth - mask.offsetWidth;
			}
			if(y < 0){
				y=0;
			}
			if(y > small.offsetHeight - mask.offsetHeight){
				y = small.offsetHeight - mask.offsetHeight;
			}
			
			mask.style.left = x + "px";
			mask.style.top = y + "px";
			
			//bigImg随着mask的移动移动
			//比例 = 大图移动的距离/mask移动的距离 = 大图/小图
			var scale = bigImg.offsetWidth / small.offsetWidth;
			
			bigImg.style.marginLeft = -scale * x +"px";
			bigImg.style.marginTop = -scale * y +"px";
		}
	}
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
	//性别
	function changeSex(){
		var sex = $("#sex").val();
		if(sex == "女"){
			$(".seller-sex").css({
				backgroundPosition:"-29px -2px"
			});
		}else if(sex == "保密"){
			$(".seller-sex").remove();
		}
	}
	// 改变商品图片
	function changePic() {
		$(".goods-photo-other>ul>li>img").mouseenter(function() {
			var path = $(this).attr("src");
			
			$(".goods-photo-main>img").attr("src",path);
			$(".big>img").attr("src",path);
			$(this).parent().css({
				border: "2px solid black"
			});
			$(".goods-photo-other>ul>li>img").not($(this)).parent().css({
				border: "none"
			});
		});
	}

	//展现和隐藏评论区
	function showComment() {
		$(".msg-write").on("click", function() {
			$(".comment").stop().slideToggle(600);
		});
		$("body").delegate(".reply-reply", "click", function() {
			$(".reply-comment").not($(this)).stop().slideUp(600);
			$(this).siblings(".reply-comment").stop().slideToggle(600);
		});
		$("body").delegate(".user-reply","click",function(){
			$(".reply-comment").not($(this)).stop().slideUp(600);
			$(this).siblings(".reply-comment").stop().slideToggle(600);
		});
	}

	//监听评论区
	function comment() {
		$("body").delegate(".comment-text", "propertychange input", function() {
			//判断是否输入了内容
			var wordCount = $(this).val().length
			$(".comment-operation>span").html(1000 - wordCount);
			if ($(this).val().length > 0) {
				$(".send").prop("disabled", false);
			} else {
				$(".send").prop("disabled", true);
			}
		});

		//监听发布按钮
		$(".send").click(function() {
			//拿到用户输入的内容
			var text = $(".comment-text").val().trim();
			if(text == ""){
				alert("评论不能为空");
				return;
			}
			var uid1=$('#uid').val();
			var gid=$(this).siblings("input[type=hidden]").eq(0).val();
			var useruname1=$('#tuname').val();
			var headPic = $("#infosHead").attr("src");
			//根据内容创建节点
			if(uid1==''||uid1==null ){
				alert('请登录再进行留言');
				return;
			}
			var flag='';
			//ajax 调用     开始留言功能  返回一个信息    留言成功 开始展示已下功能     
				var ajax=null;
				 if(window.XMLHttpRequest){
					 ajax=new XMLHttpRequest();
				 }else if(window.ActiveXObject){
					 ajax=new ActiveXObject("Msxml2.XMLHTTP");
				 }
				 //2,复写
				 ajax.onreadystatechange=function(){
					 if(ajax.readyState==4){
						 flag=ajax.responseText;
						 
						 if(flag==''||flag=='null'){
							 alert("系统繁忙,请稍后再试");
							 return ;
						 }else{
							 var point = createEle(text,useruname1,headPic,uid1,flag,gid);
							 $("#mnumber").html(parseInt($("#mnumber").html()) + 1);
								//插入微博
								$(".message>ul").prepend(point);
								//清空输入框
								$(".comment-text").val("");
								//将输入的字数回到初始状态
								$(".comment-operation>span").html(1000);
								// 将输入框隐藏
								$(".comment").css({
									display : "none"
								});
						 }
					 }
				 }
				 ajax.open("post","board",true);// 向 AjaxResult01发出40.请求
				 ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				 ajax.send("oper=board&"+"uid1="+uid1+"&text="+text+"&gid="+gid);
			
		});
		/*  <input type="hidden" value="${tboard.bmu.user1.uid }">
			<input type="hidden" value="${tboard.bmu.message.mid }">
			<input type="hidden" value="${tboard.bmu.message.gid }">
			<input type="hidden" value="${tboard.bmu.user1.uname}">*/


		//创建节点的方法
		function createEle(text,useruname1,headPic,uid1,flag,gid) {
			var point = $(
				"<li>"+
				"<div class=\"user-msg\">"+
					"<div class=\"user-head\">"+
						"<img src="+headPic+" />"+
					"</div>"+
					"<div class=\"user-name\">"+useruname1+"</div>"+
					"<div class=\"user-delReply\">删除&nbsp;&nbsp;|" +
					"<input type=\"hidden\" value="+flag+" class =\"tmid\" >"+
					"</div>"+ 
					"<div class=\"user-reply\">回复</div>"+
					"<div class=\"user-msg-replay\">" + text + "</div>"+
					"<div class=\"reply-comment\">"+
						"<textarea class=\"reply-msg\" placeholder=\"回复\" maxlength=\"150\"></textarea>"+
						"<div class=\"comment-operation\">"+
						"<input type=\"hidden\" value="+uid1+">"+
						"<input type=\"hidden\" value="+flag+">"+
						"<input type=\"hidden\" value="+gid+">"+
						"<input type=\"hidden\" value="+useruname1+">"+
							"<input type=\"button\" value=\"发表评论\" class=\"reply-send\" disabled />"+
						"</div>"+
					"</div>"+
				"</div>"+
				"<div class=\"user-time\">" + getData() + "</div>"+
				"</li>");
			return point;
		}
		//生成日期
		function getData() {
			var date = new Date();
			var arr = [date.getFullYear() + "/",
				date.getMonth() + 1 + "/",
				date.getDate() + " ",
				date.getHours() + ":",
				date.getMinutes() + ":",
				date.getSeconds()
			];
			arr = arr.join("");
			return arr;
		}
	}
	//展示和关闭举报页面
	function report() {
		$(".download_report").on("click", function() {
			$(".report").css({
				display: "block"
			});
			$(".hide").css({
				display: "block"
			});
		});
		$(".report-close").on("click", function() {
			$(".report").css({
				display: "none"
			});
			$(".hide").css({
				display: "none"
			});
		});
		$(".report-cancel").on("click", function() {
			$(".report").css({
				display: "none"
			});
			$(".hide").css({
				display: "none"
			});
		});

		//对举报信息的监控
		$(".report-sub").on("click", function() {
			var errorMsg1 = $(".texta").val().length;
			var errorMsg2 = $(".form-control").val();
			if (errorMsg2 == "请选择类型") {
				$(".report-tip2").html("请选择类型！");
			} else if (errorMsg1 <= 0) {
				$(".report-tip2").html("描述不能为空");
			} else {
				// 向服务器发送请求
				$(".report-tip2").html("提交成功");
				//清空数据
				$(".texta").val("");
				$(".form-control>option").eq(0).prop("selected", true);
			}
		});
	}

	// 收藏
	function favorite() {
		var uid = $("#uid").val();
		
		var gid = $("#gid").val();
		// 控制收藏是否可以点击
		var isClick = true;
		//判断用户是否收藏
		
		if(uid!=''){
			$.ajax({
				url : "goods.s?oper=isCollect",
				type : "get",
				data : "uid="+uid + "&gid=" + gid,
				success : function(data){
					if(data == "已收藏"){
						$(".favorite-icon>img").attr("src", "img/collect(2).png");
						isClick = false;
					}
				}
			});
		}
		
		$(".favorite").on("click", function() {
			if(uid==''){
				alert("请先登录");
				return ;
			}
			if (isClick) {
				$.ajax({
					url : "goods.s?oper=collect",
					type : "post",
					data : "uid="+uid + "&gid=" + gid,
					success : function(data){
						if(data == "收藏成功"){
							$(".favorite-icon>img").attr("src", "img/collect(2).png");
							$(".favorite-success").css({
								display: "block"
							});
							$(".hide").css({
								display: "block"
							});
						}
					}
				});
			}
			if ($(".favorite-icon>img").attr("src") == "img/collect(2).png") {
				isClick = false;
			}
		});

		$(".favorite-close").on("click", function() {
			$(".favorite-success").css({
				display: "none"
			});
			$(".hide").css({
				display: "none"
			});
		});
	}

	// 用户回复
	function user_reply() {
		$("body").delegate(".message>ul>li>.user-msg", "mouseover", function() {
			$(this).children(".user-reply").css({
				display: "block"
			});
		});
		$("body").delegate(".message>ul>li>.user-msg", "mouseout", function() {
			$(".user-reply").css({
				display: "none"
			});
		});
		$("body").delegate(".reply", "mouseover", function() {
			$(this).children(".reply-reply").css({
				display: "block"
			});
		});
		$("body").delegate(".reply", "mouseout", function() {
			$(".reply-reply").css({
				display: "none"
			});
		});



		$("body").delegate(".reply-msg", "propertychange input", function() {
			//判断是否输入了内容
			var wordCount = $(this).val().length
			if ($(this).val().length > 0) {
				$(".reply-send").prop("disabled", false);
			} else {
				$(".reply-send").prop("disabled", true);
			}
		});

		//监听发布按钮
		$("body").delegate(".reply-send", "click", function() {
			var myThis = $(this);
			//拿到用户输入的内容
			var text = $(this).parent().siblings(".reply-msg").val().trim();
			if(text == ""){
				alert("评论不能为空");
				return;
			}
			//根据内容创建节点
			var uid1=$('#uid').val();
			
			var uid2=$(this).siblings("input[type=hidden]").val();
			var tmid=$(this).siblings("input[type=hidden]").eq(1).val();
			var gid=$(this).siblings("input[type=hidden]").eq(2).val();
			var useruname2=$(this).siblings("input[type=hidden]").eq(3).val();
			
			var useruname1=$('#tuname').val();
			// ajax 插入成功了  执行下面的步骤
			
			if(uid1==''||uid1==null ){
				alert('请登录再进行留言');
				return;
			}
			
			
			var flag='';
			
			//开始提交留言信息
				var ajax=null;
				 if(window.XMLHttpRequest){
					 ajax=new XMLHttpRequest();
				 }else if(window.ActiveXObject){
					 ajax=new ActiveXObject("Msxml2.XMLHTTP");
				 }
				 //2,复写
				 ajax.onreadystatechange=function(){
					 if(ajax.readyState==4){
						 flag=ajax.responseText;
						 if(flag==null||flag=='' || flag=='null'){
								//留言失败
								alert("系统繁忙,请稍后再试");
								return ;
							}else{
								var headPic = $("#infosHead").attr("src");
								console.log(uid1+"  "+ flag+" "+gid );
								var point = createEle(text,useruname1,useruname2,headPic,uid1,tmid,gid,flag);
								//插入微博
								 $("#mnumber").html(parseInt($("#mnumber").html()) + 1);
								myThis.parents(".reply").siblings(".user-time").before(point);
								myThis.parents(".user-msg").siblings(".user-time").before(point);
								//清空输入框
								$(".reply-msg").val("");
								//将输入框隐藏
								$(".reply-comment").css({
									display : "none"
								});
							}
					 }
				 }
				 ajax.open("post","board",true);// 向 AjaxResult01发出40.请求
				 ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				 ajax.send("oper=rely&"+"uid1="+uid1+"&uid2="+uid2+"&text="+text+"&tmid="+tmid+"&gid="+gid);
		});
		
		//创建节点的方法
		function createEle(text,user1,user2,pic,uid1,tmid,gid,flag) {
			var point = $(
				"<div class=\"reply\">"+
					"<div class=\"reply-user-head\">"+
						"<img src="+pic+" />"+
					"</div>"+
					"<div class=\"reply-user-name\">"+user1+"<span>&nbsp;回复&nbsp;</span>"+user2+"</div>"+
					"<div class=\"reply-delReply\">删除&nbsp;&nbsp;|" +
					"<input type=\"hidden\" value="+flag+ " class =\"tmid\" >"+
					"</div>" + 
					"<div class=\"reply-reply\">回复</div>"+
					"<div class=\"reply-user-msg\">"+text+"</div>"+
					"<div class=\"reply-comment\">"+
						"<textarea class=\"reply-msg\" placeholder=\"回复\" maxlength=\"150\"></textarea>"+
						"<div class=\"comment-operation\">"+
						"<input type=\"hidden\" value="+uid1+">"+
						"<input type=\"hidden\" value="+tmid+">"+
						"<input type=\"hidden\" value="+gid+">"+
						"<input type=\"hidden\" value="+user1+">"+
							"<input type=\"button\" value=\"发表评论\" class=\"reply-send\" disabled />"+
						"</div>"+
					"</div>"+
				"</div>");
			return point;
		}
	}
	//删除评论
	function delReply(){
		$("body").delegate(".message>ul>li>.user-msg", "mouseover", function() {
			$(this).children(".user-delReply").css({
				display: "block"
			});
		});
		$("body").delegate(".message>ul>li>.user-msg", "mouseout", function() {
			$(".user-delReply").css({
				display: "none"
			});
		});
		$("body").delegate(".reply", "mouseover", function() {
			$(this).children(".reply-delReply").css({
				display: "block"
			});
		});
		$("body").delegate(".reply", "mouseout", function() {
			$(".reply-delReply").css({
				display: "none"
			});
		});
		
		$("body").delegate(".user-delReply","click",function(){
			var isDel = confirm("是否删除这条留言");
			if(isDel){
				
				var mid =$(this).children(".tmid").val();
				/*console.log(mid)*/
				var flag ='1';
				
				$.ajax({
					url : "board?oper=delboard",
					type : "post",
					data : "mid="+mid,
					success : function(data){
						if(data =='0'){
							flag ='0';
						}else{
							var number=$("#mnumber").html();
							$("#mnumber").html(number-data);
							
						}
					}
				});
				
				if(flag !='0'){
					$(this).parents("li").remove();
					
				}
			}
		});
		$("body").delegate(".reply-delReply","click",function(){
			var isDel = confirm("是否删除这条留言");
			if(isDel){
				
				var mid =$(this).children(".tmid").val();
				/*console.log(mid)*/
				var flag ='1';
				
				$.ajax({
					url : "board?oper=delreply",
					type : "post",
					data : "mid="+mid,
					success : function(data){
						if(data =='0'){
							flag ='0';
						}else{
							var number=$("#mnumber").html();
							$("#mnumber").html(number-data);
						}
					}
				});
				
				if(flag !='0'){
					
					$(this).parents(".reply").remove();
				}
			}
		});
	}
	
	
	
	//跳转到其它用户信息页面
	function jumpToInfo(){
		$(".seller-head>img").on("click",function(){
			var uid = $("#sellerUid").val();
			window.location.href = "user.s?oper=info&uid="+ uid +"&currentPage=1";
		});
	}
});
