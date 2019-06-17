
	function favorite(){
		var uid = $("#uid").val();
		if(uid == ""){
			alert("请先登录")
		}else{
			window.location.href="user.s?oper=favorite&uid="+uid;
		}
	}
	
	function myDownload(){
		var uid = $("#uid").val();
		if(uid == ""){
			alert("请先登录")
		}else{
			window.location.href="user.s?oper=myDownload&uid="+uid;
		}
	}
	
	function uploadfile(){
		var uid = $("#uid").val();
		if(uid == ""){
			alert("请先登录")
		}else{
			window.location.href="goods.s?oper=uploadfile&uid="+uid;
		}
	}
	
	function mySrc(){
		var uid = $("#uid").val();
		if(uid == ""){
			alert("请先登录")
		}else{
			window.location.href="user.s?oper=mySrc&uid="+uid;
		}
	}
	
	function index(){
		var uid = $("#uid").val();
		if(uid == ""){
			window.location.href="user.s?oper=index";
		}else{
			window.location.href="user.s?oper=index&uid="+uid;
		}
	}
	

$(function(){
	headInfo();
	search();
	isLogin();
	queryfff();
	query();
	function query(){
		
		var uid = $("#uid").val();
		$.ajax({
			url : "user.s?oper=query",
			type : "get",
			data : "uid="+uid,
			success : function(data){
				$("#newsNumber").html(data);
			}
		});
	}
	window.setInterval(query(),2000);
	
	//查询fff
	function queryfff(){
		var uid = $("#uid").val();
		$.ajax({
			url : "user.s?oper=queryfff",
			type : "get",
			data : "uid="+uid,
			success : function(data){
				var fff = data.split(";");
				$(".info>ul>li>div").eq(0).html(fff[0]);
				$(".info>ul>li>div").eq(1).html(fff[1]);
				$(".info>ul>li>div").eq(2).html(fff[2]);
			}
		});
	}
	//鼠标以向头像，将头像放大，并显示出详情信息
	function headInfo() {
		$(".myhead").mouseenter(function() {
			$(".myinfo").stop().fadeIn(500);
		});
		$(".myhead").hover(
			function() {
				$(this).addClass("myheadHover");
			}
		);
	
		$(".infos").mouseleave(function() {
			$(".myinfo").stop().fadeOut(300);
			$(".myhead").removeClass("myheadHover");
		});
		
	}
	//	搜索 
	function search(){
		$(".search-pic").on("click",function(){
			var t=$(".search-ipt").val();
			if(t=="" ){
				window.location.href="search?oper=search";
			}else{
				window.location.href="search?oper=search&key="+t;
			}
		});
	}
	
	function favorite(){
		var uid = $("#uid").val();
		if(uid == ""){
			alert("请先登录")
		}else{
			window.location.href="user.s?oper=favorite&uid="+uid;
		}
	}
	
	function myDownload(){
		var uid = $("#uid").val();
		if(uid == ""){
			alert("请先登录")
		}else{
			window.location.href="user.s?oper=myownload&uid="+uid;
		}
	}
	
	function uploadfile(){
		var uid = $("#uid").val();
		if(uid == ""){
			alert("请先登录")
		}else{
			window.location.href="goods.s?oper=uploadfile&uid="+uid;
		}
	}
	
	function mySrc(){
		var uid = $("#uid").val();
		if(uid == ""){
			alert("请先登录")
		}else{
			window.location.href="user.s?oper=mySrc&uid="+uid;
		}
	}
	
	function index(){
		var uid = $("#uid").val();
		if(uid == ""){
			window.location.href="user.s?oper=index";
		}else{
			window.location.href="user.s?oper=index&uid="+uid;
		}
	}
	
	
	
	
	//判断是否登录
	function isLogin(){
		var uid = $("#uid").val();
		if(uid == ""){
			$(".unlogin").css({
				display : "inline-block"
			});
			$(".infos").css({
				display : "none"
			});
		}
	}
	//点击登录
	$(".unlogin").on("click",function(){
		window.location.href="user.s?oper=login";
	});
	
	//进入消息页面
	$(".news-tip").on("click",function(){
		
		var uid=$("#uid").val();
		window.location.href="board?oper=news&uid="+uid;
	});
});

