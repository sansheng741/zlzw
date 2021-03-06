$(function(){
	
	changeNav();
	cancelFav();
	uploadTime();
	
	function changeNav(){
		//改变被选中的导航条
		$(".nav>ul>a>li").eq(3).css({
			borderBottom : "3px solid red"
		});
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
				$(".nav>ul>a>li").eq(3).css({
					borderBottom : "3px solid red"
				});
			}
		);
		$(".user-nav>ul>li").eq(0).css({
			backgroundColor : "white"
		});
		
	}
	// 删除上传文件
	function cancelFav(){
		$(".cancel-fav>a").on("click",function(){
			var gid = $(this).parents("li").children(".gid").val();
			var uid = $("#uid").val();
			var flag = confirm("是否删除此上传文件");
			if(flag){
				$.ajax({
					url : "goods.s?oper=delFile",
					type : "get",
					data : "gid=" + gid + "&uid=" + uid,
					success : function(data){
						var params = data.split(";");
						if(params[0] == "删除成功"){
							$(".gid[value="+params[1]+"]").parents("li").remove();
							$(".data-count>ul>li>span").eq(1).html(params[2]);
						}else{
							alert("服务器繁忙，请稍后再试！");
						}
					}
				});
			}
		});
	}
	
	//上传时间 和 价格
	function uploadTime(){
		for(var i = 0; i < $(".uploadTime").length; i++){
			var date = $(".uploadTime").eq(i).html();
			date = date.substring(0,date.indexOf(" "));
			$(".uploadTime").eq(i).html(date);
			
			var price = $(".price").eq(i).html();
			if(price == '0.0'){
				$(".price").eq(i).html('0');
			}
		}
	}
	
});