$(function(){
	
	changeNav();
	uploadTime();
	
	function changeNav(){
		//改变被选中的导航条
		$(".nav>ul>a>li").eq(1).css({
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
				$(".nav>ul>a>li").eq(1).css({
					borderBottom : "3px solid red"
				});
			}
		);
		$(".user-nav>ul>li").eq(1).css({
			backgroundColor : "white"
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