$(function(){
	changePanel();
	comment();
	search_fr();
	changeNav();
	
	//更换面板
	function changePanel(){
		$(".controlPanel>ul>li").eq(0).css({
			backgroundColor: "#49AF4F",
			color: "#fff"
		});
		$(".controlPanel>ul>li").on("click",function(){
			var op = $("#opera").val();
			$(this).css({
				backgroundColor: "#49AF4F",
				color: "#fff"
			});
			$(".controlPanel>ul>li").not(this).css({
				backgroundColor: "#fff",
				color: "#333"
			});
		});
		
		$(".controlPanel>ul>li").eq(0).on("click",function(){
			$(".commentList").css({
				display : "block"
			});
			$(".private-letter").css({
				display : "none"
			});
		});
		
		$(".controlPanel>ul>li").eq(1).on("click",function(){
			$(".commentList").css({
				display : "none"
			});
			$(".private-letter").css({
				display : "block"
			});
		});
		
	}
	//评论
	function comment(){
		$("body").delegate(".comment-textarea", "propertychange input", function() {
			//判断是否输入了内容
			var wordCount = $(this).val().length
			if (wordCount > 0) {
				$(this).siblings(".reply-send").prop("disabled", false);
			} else {
				$(this).siblings(".reply-send").prop("disabled", true);
			}
		});
		
		
		//评论框聚焦
		$("body").delegate(".comment-textarea","focus",function(){
			$(this).css({
				height : "40px"
			});
			$(this).siblings(".reply-send").css({
				display : "inline-block"
			});
			$(".comment-textarea").not($(this)).css({
				height : "15px"
			});
			$(".reply-send").not($(this).siblings(".reply-send")).css({
				display : "none"
			});
		});
		
		$("body").delegate(".comment-goods","click", function() {
			var turl = $(this).children(".turl").val();
			window.location.href=turl;
		});
		
		
		
		
		$("body").delegate(".reply-send","click", function() {
			//得到用户输入
			var text = $(this).siblings(".comment-textarea").val().trim();
			if(text == ""){
				alert("评论不能为空");
				return;
			}
			var uname = $(this).siblings(".uname").val();
			var myhead=$(this).siblings(".thead").val();
			var tmid=$(this).siblings(".tmid").val(); //回复的留言的tmid
			var uid2=$(this).siblings(".uid2").val();
			var gid=$(this).siblings(".gid").val();
			if(text.length > 0){
				//清空文字
				$(this).siblings(".comment-textarea").val("");
				$(this).siblings(".comment-textarea").css({
					height : "15px"
				});
				$(this).css({
					display : "none"
				});
				//构造节点
				//根据内容创建节点
				//直接的 id
				var uid1=$("#uid").val();
				
				if(uid1==''||uid1==null){
					alert("请先登录,再进行留言");
				}
				
				var flag ='1';
				$.ajax({
	                type: "POST",
	                url: "board",
	                data:"oper=rely&"+"uid1="+uid1+"&uid2="+uid2+"&text="+text+"&tmid="+tmid+"&gid="+gid,
	                cache: false, //不缓存此页面
	                success: function (data) {
	                    if(data==null||data=='' ||data=='null'){
	                    	alert("系统繁忙,请稍后再试");
	                    	flag='2';
							return ;
	                    }else{
                            flag='1';
	                    }
	                }
	            });
				if(flag =='1'){
					var point = createEle(text, "我", uname,myhead);
					//插入节点
					$(this).parents(".comment-frame").before(point);
				}
				
			}
		});
		
		//创建节点的方法
		function createEle(text, user1, user2, pic) {
			var point = $(
				"<div class=\"comment-context\">" +
					"<img src= "+ pic +">" +
					"<div class=\"user-name\">"+ user1 +"<span>&nbsp;回复&nbsp;</span>"+ user2 +"</div>" +
					"<div class=\"context\">"+ text +"</div>" +
				"</div>"
			);
			return point;
		}
	}
	//搜索好友
	function search_fr(){
		//监听输入框的输入
		$("body").delegate(".f-pr", "propertychange input", function() {
			//判断是否输入了内容
			var wordCount = $(this).val().length
			if (wordCount > 0) {
				$(".search_pic").css({
					display : "block"
				});
			} else {
				$(".search_pic").css({
					display : "none"
				});
			}
		});
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
});