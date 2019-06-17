
$(function(){
	code();
	//显示二维码
	function code(){
		$(".logo").hover(
			function(){
				$(".code").css({
					display : "block"
				});
			},
			function(){
				$(".code").css({
					display : "none"
				});
			}
		);
	}
});
