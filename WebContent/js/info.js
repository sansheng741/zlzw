$(function(){
	//获取uid
	var uid = $("#Infouid").val();
	var uuid = $("#uid").val();
	showMoreInfo();
	hiddenMoreInfo();
	changeNav();
	picControl();
	prePage();
	nextPage();
	changePage();
	showEmpty();
	changeSex();
	follow();
	search_fr();
	changePanel();
	cardOper();
	//更换面板
	function changePanel(){
		var infouid = $("#Infouid").val();
		$(".controlPanel>ul>li").on("click",function(){
			$(".userCard").remove();
			var index = $(".controlPanel>ul>li").index(this);
			if(index == 0){
				createCard(0,"queryFollow","取消关注");
				$(".tab_item>span").eq(0).html("全部关注");
				$(".tab_item>span").eq(1).html($(".controlPanel>ul>li>span").eq(0).html());
			}else if(index == 1){
				createCard(1,"queryFans","关注");
				$(".tab_item>span").eq(0).html("我的粉丝");
				$(".tab_item>span").eq(1).html($(".controlPanel>ul>li>span").eq(1).html());
			}else if(index == 2){
				createCard(2,"queryFriend","删除好友");
				$(".tab_item>span").eq(0).html("我的好友");
				$(".tab_item>span").eq(1).html($(".controlPanel>ul>li>span").eq(2).html());
			}
			$(this).css({
				backgroundColor: "#49AF4F",
				color: "#fff"
			});
			$(".controlPanel>ul>li").not(this).css({
				backgroundColor: "#fff",
				color: "#333"
			});
//			$(".userCard").stop().fadeToggle(500);
		});
		//返回主页
		$(".controlPanel>ul>li").eq(3).on("click",function(){
			$(".firstPage").css({
				display : "block"
			});
			$(".secPage").css({
				display : "none"
			});
			$(".controlPanel>ul>li").css({
				backgroundColor: "#fff",
				color: "#333"
			});
			window.location.reload();
		});
		$(".fff_count>ul>li").eq(0).on("click",function(){
			if(uuid == uid){
				createCard(0,"queryFollow","取消关注");
				$(".tab_item>span").eq(1).html($(".controlPanel>ul>li>span").eq(0).html());
			}
		});
		$(".fff_count>ul>li").eq(1).on("click",function(){
			if(uuid == uid){
				createCard(1,"queryFans","关注");
				$(".tab_item>span").eq(0).html("我的粉丝");
				$(".tab_item>span").eq(1).html($(".controlPanel>ul>li>span").eq(1).html());
			}
		});
		$(".fff_count>ul>li").eq(2).on("click",function(){
			if(uuid == uid){
				createCard(2,"queryFriend","删除好友");
				$(".tab_item>span").eq(0).html("我的好友");
				$(".tab_item>span").eq(1).html($(".controlPanel>ul>li>span").eq(2).html());
			}
		});	
		
		//构建卡片
		function createCard(index,oper,op){
			//跳转fff页
			$.ajax({
				url : "user.s?oper="+oper,
				type : "get",
				data : "uid="+infouid,
				success : function(data){
					$(".controlPanel>ul>li").eq(index).css({
						backgroundColor: "#49AF4F",
						color: "#fff"
					});
					var data = JSON.parse(data);
					for(var i = 0; i < data.length; i++){
						//创建节点
						var point = createUserCard(data[i].head,data[i].uname,data[i].sex,
								data[i].sign,op,data[i].uid);
						//添加节点
						$(".mainPanel").append(point);
					}
					var sexs = $(".mod_info_sex_ipt");
					for(var i = 0; i < sexs.length; i++){
						var sex = $(".mod_info_sex_ipt").eq(i);
						if(sex.val() == "男"){
							sex.siblings(".mod_info_sex").css({
								backgroundPosition: "-5px -3px"
							});
						}else if(sex.val() == "女"){
							sex.siblings(".mod_info_sex").css({
								backgroundPosition: "-30px -3px"
							});
						}else{
							sex.siblings(".mod_info_sex").css({
								display : "none"
							});
						}
					}
				}
			});
			$(".firstPage").css({
				display : "none"
			});
			$(".secPage").css({
				display : "block"
			});
			setTimeout(function(){
				$(".userCard").stop().slideToggle(500);
			},100);
		}
		//构建用户卡片节点
		function createUserCard(head,uname,sex,sign,op,id){
			var point = $(
				"<div class=\"userCard\">" + 
					"<input type=\"hidden\" class=\"mod_uid\" value=\""+ id +"\">" +
					"<div class=\"mod_pic\">" +
						"<a href=\"user.s?oper=info&uid="+ id +"&currentPage=1\" target=\"_blank\"><img src="+ head +"></a>" +
					"</div>" +
					"<div class=\"mod_info\">" +
						"<a href=\"user.s?oper=info&uid="+ id +"&currentPage=1\" class=\"mod_info_name\" target=\"_blank\">"+ uname +"</a>" +
						"<div class=\"mod_info_sex\"></div>" +
						"<p class=\"mod_info_motto\">"+ sign +"</p>" +
						"<button type=\"button\" class=\"mod_info_btn\">私信</button>" +
						"<button type=\"button\" class=\"mod_info_btn op\">"+ op +"</button>" +
						"<input type=\"hidden\" class=\"mod_info_sex_ipt\" value=\""+ sex +"\">" +
					"</div>" +
				"</div>"
			);
			return point;
		}
	}
	
	//卡片操作(关注，取关，删除好友)
	function cardOper(){
		$("body").delegate(".op","click",function(){
			var thisOp = $(this);
			var opName = thisOp.html();
			if(opName == "取消关注"){
				opName = "cancelFollow";
			}else if(opName == "关注"){
				opName = "follow";
			}else if(opName == "删除好友"){
				opName = "delFriend";
			}
			//操作对象的id
			var uid = thisOp.parent().siblings(".mod_uid").val();
			var isOp = confirm("您确定执行该操作吗?");
			if(isOp){
				$.ajax({
					url : "user.s?oper="+opName,
					type : "get",
					data : "uid="+uuid+"&Infouid="+uid,
					success : function(data){
						if(data == "操作成功" || data == "关注成功"){
							window.location.reload();
						}
					}
				});
			}
		});
	}
	
	//否关注
	function follow(){
		var uid = $("#uid").val();
		var Infouid = $("#Infouid").val();
		if(uid != Infouid){
			$(".follow").css({
				display : "inline-block"
			});
			//查询用户是否已经关注
			$.ajax({
				url : "user.s?oper=isFollow",
				type : "get",
				data : "uid="+uid+"&Infouid="+Infouid,
				success : function(data){
					if(data == "true"){
						$(".follow").attr("src","img/follow2.png");
					}
				}
			});
		}
		$(".follow").on("click",function(){
			var img = $(".follow").attr("src");
			if(img == "img/follow1.png"){
				//发请求
				$.ajax({
					url : "user.s?oper=follow",
					type : "get",
					data : "uid="+uid+"&Infouid="+Infouid,
					success : function(data){
						if(data == "关注成功"){
							$(".follow").attr("src","img/follow2.png");
							alert("关注成功");
						}else{
							alert(data);
						}
					}
				});
			}else{
				alert("您已关注此用户");
			}
		});
	
	}
	//性别
	function changeSex(){
		var sex = $("#sex").val();
		if(sex == "女"){
			$(".icon-sex").css({
				backgroundPosition:"-30px -4px"
			});
		}else if(sex == "保密"){
			$(".icon-sex").remove();
		}
	}
	//如果当前用户没发布商品则显示空页面
	function showEmpty(){
		var totalPage = $("#totalPage").val();
		if(totalPage == 0){
			$(".messageList").remove();
			if(uuid == uid){
				$(".emptyDynamic").css({
					display : "block"
				});
			}else{
				$(".emptyDynamic2").css({
					display : "block"
				});
			}
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
		var currentPage = $("#currentPage").val();
		for (var i = 0; i < 6; i++) {
			var pagesNum = parseInt($(".pagesCount>li").eq(i).html());
			if(pagesNum == currentPage){
				$(".pagesCount>li").eq(i).css({
					backgroundColor: "lightskyblue"
				});
			}else{
				$(".pagesCount>li").eq(i).css({
					backgroundColor: ""
				});
			}
		}
	}
	// 展示更多信息
	function showMoreInfo(){
		$(".more-info").on("click",function(){
			$(".person_info2").stop().slideDown(1000);
			$(this).css({
				display : "none"
			});
		});
	}
	
	// 隐藏更多信息
	function hiddenMoreInfo(){
		$(".info-hidden").on("click",function(){
			$(".person_info2").stop().slideUp(1000);
			$(".more-info").css({
				display : "block"
			});
		});
	}
	
	function picControl(){
		var key = true;
		expandPic();
		closePic();
		//放大图片
		function expandPic(){
			// 事件委托
			$("body").delegate(".messagePic>ul>li>img","click",function(){
				var path = $(this).attr("src");
				if(key){
					$(".expandPic>img").attr("src",path);
					$(".expandPic").css({
						display : "block"
					});
					key = false;
				}
			});
		}
		
		// 关闭图片
		function closePic(){
			$(".close").on("click",function(){
				$(".expandPic").css({
					display : "none"
				});
				key = true;
			});
		}
	}
	
	//上一页
	function prePage(){
		$(".prePage").on("click",function(){
			
			//获取当前页
			var currentPage = parseInt($("#currentPage").val()) - 1;;
			if (currentPage == 0) {
				alert("不能再往前翻了哦");
			} else {
				var firstNum =  parseInt($(".pagesCount>li").eq(0).html());
				var lastNum =  parseInt($(".pagesCount>li").last().html());
				//当前页超过10页后改变分页卡的是数字
				if (firstNum == currentPage + 1) {
					for (var i = 0; i < lastNum; i++) {
						var pagesNum = parseInt($(".pagesCount>li").eq(i).html());
						$(".pagesCount>li").eq(i).html(pagesNum - 1);
					}
				}
				//改变页码的样式
				for (var i = 0; i < lastNum; i++) {
					var pagesNum = parseInt($(".pagesCount>li").eq(i).html());
					if(pagesNum == currentPage){
						$(".pagesCount>li").eq(i).css({
							backgroundColor: "lightskyblue"
						});
					}else{
						$(".pagesCount>li").eq(i).css({
							backgroundColor: ""
						});
					}
				}
				$("#currentPage").val(parseInt(currentPage));
				var maxPage = $(".pagesCount>li").last().html();
				var minPage = $(".pagesCount>li").eq(0).html();
				window.location.href="user.s?oper=info&uid="+uid+"&currentPage="+currentPage+"&maxPage="+maxPage+"&minPage="+minPage;
			}
		});
	}
	//下一页
	function nextPage(){
		$(".nextPage").on("click",function(){
			//获取当前页
			var currentPage = parseInt($("#currentPage").val()) + 1;
			//获取总页数
			var totalPage = $("#totalPage").val();
			if (currentPage - 1 == totalPage) {
				alert("当前已是最后一页了哦");
			} else {
				var lastNum =  parseInt($(".pagesCount>li").last().html());
				//当前页超过6页后改变分页卡的是数字
				if (currentPage > 6 && lastNum == currentPage - 1) {
					for (var i = 0; i < lastNum; i++) {
						var pagesNum = parseInt($(".pagesCount>li").eq(i).html());
						$(".pagesCount>li").eq(i).html(pagesNum + 1);
					}
				}
				//改变页码的样式
				for (var i = 0; i < lastNum; i++) {
					var pagesNum = parseInt($(".pagesCount>li").eq(i).html());
					if(pagesNum == currentPage){
						$(".pagesCount>li").eq(i).css({
							backgroundColor: "lightskyblue"
						});
					}else{
						$(".pagesCount>li").eq(i).css({
							backgroundColor: ""
						});
					}
				}
				$("#currentPage").val(parseInt(currentPage));
				var maxPage = $(".pagesCount>li").last().html();
				var minPage = $(".pagesCount>li").eq(0).html();
				window.location.href="user.s?oper=info&uid="+uid+"&currentPage="+currentPage+"&maxPage="+maxPage+"&minPage="+minPage;
			}
		});
	}
	//改变页码
	function changePage(){
		var totalPage = $("#totalPage").val();
		$(".pagesCount>li").on("click",function(){
			
			$(this).css({
				backgroundColor: "lightskyblue"
			});
			$(".pagesCount>li").not($(this)).css({
				backgroundColor: ""
			});
			//获取当前页码值
			var currentPage = $(this).html();
			//改变页码值
			$("#currentPage").val(parseInt(currentPage));
			var maxPage = $(".pagesCount>li").last().html();
			var minPage = $(".pagesCount>li").eq(0).html();
			window.location.href="user.s?oper=info&uid="+uid+"&currentPage="+currentPage+"&maxPage="+maxPage+"&minPage="+minPage;
		});
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
});