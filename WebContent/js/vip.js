$(function(){
	
	changeNav();
	
	function changeNav(){
		$(".nav>ul>a>li").eq(4).css({
			borderBottom : "3px solid red"
		});
		$(".nav>ul>a>li").hover(
			function(){
				$(this).css({
					borderBottom : "3px solid red"
				});
			},
			function(){
				$(".nav>ul>a>li").css({
					borderBottom : "0"
				});
			}
		);
		$(".user-nav>ul>li").eq(3).css({
			backgroundColor : "white"
		});
	}
	
});