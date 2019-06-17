$(function(){
	SowingMap();
	changeNav();
	SowingMap_slide(".m2",".p2");
	SowingMap_slide(".m3",".p3");
	SowingMap_slide(".m4",".p4");
	
	
	function changeNav(){
		//改变被选中的导航条
		$(".nav>ul>a>li").eq(4).css({
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
				$(".nav>ul>a>li").eq(4).css({
					borderBottom : "3px solid red"
				});
			}
		);
	}
	var count = 1; //用于标志轮播图当前是第几个
	//实现轮播图
	function SowingMap() {
		$(".point>li").eq(0).css({
			border: "2px solid skyblue",
			backgroundColor: "transparent",
			transform: "scale(1.2,1.2)"
		});
		var Map = m();
		$(".point>li").eq(0).on("click", function() {
			count = 1;
			pointTra(count);
			$(".Sowing-map").css({
				background: "url(img/map" + count + ".png) no-repeat",
				backgroundSize: '100% 100%'
			});
			clearInterval(Map);
			Map = m();
		});
		$(".point>li").eq(1).on("click", function() {
			count = 2;
			pointTra(count);
			$(".Sowing-map").css({
				background: "url(img/map" + count + ".jpg) no-repeat",
				backgroundSize: '100% 100%'
			});
			clearInterval(Map);
			Map = m();
		});
		$(".point>li").eq(2).on("click", function() {
			count = 3;
			pointTra(count);
			$(".Sowing-map").css({
				background: "url(img/map" + count + ".jpg) no-repeat",
				backgroundSize: '100% 100%'
			});
			clearInterval(Map);
			Map = m();
		});
		$(".point>li").eq(3).on("click", function() {
			count = 4;
			pointTra(count);
			$(".Sowing-map").css({
				background: "url(img/map" + count + ".png) no-repeat",
				backgroundSize: '100% 100%'
			});
			clearInterval(Map);
			Map = m();
		});
	
	}
	//生成一个计时器，来控制图片的显示，并返回这个计时器
	function m() {
		var Map = setInterval(function() {
			if (count >= 4) {
				count = 0;
			}
			count++;
			pointTra(count);
			if (count == 1 || count == 4) {
				$(".Sowing-map").css({
					background: "url(img/map" + count + ".png) no-repeat",
					backgroundSize: '100% 100%'
				});
			} else {
				$(".Sowing-map").css({
					background: "url(img/map" + count + ".jpg) no-repeat",
					backgroundSize: '100% 100%'
				});
			}
			$(".Sowing-map").stop().fadeOut(20).fadeIn(1000);
		}, 3000);
		return Map;
	}
	//将当前图片对应的小圆点变化，并将其他小圆点还原
	function pointTra(x) {
		$(".point>li").eq(x - 1).css({
			border: "2px solid skyblue",
			backgroundColor: "transparent",
			transform: "scale(1.2,1.2)"
		});
		$(".point>li").not($(".point>li").eq(x - 1)).css({
			border: "",
			backgroundColor: "",
			transform: ""
		});
	}
	
	
	function SowingMap_slide(cs1,cs2) {
		var key = true;
		$(cs1 + " .rs").on("click", function() {
			//右边
			$(cs2).css({
				marginLeft: function(index, value) {
					var move = parseInt(value);
					if (move == -3516) { //当图片移动超出界限后,使图片重新定位到第一张
						return 0 + "px";
					}
				}
			});
			if (key) {
				key = false;
				$(cs2).animate({
					marginLeft: "-=1172" //移动图片,累加
				}, 500, function() {
					key = true;
				});
			}
		});
		$(cs1 + " .ls").on("click",function(){
			//左
			$(cs2).css({
				marginLeft: function(index, value) {
					var move = parseInt(value);
					if(move == 0){
						return -3516 + "px";
					}
				}
			});
			if(key){
				key = false;
				$(cs2).animate({
					marginLeft:"+=1172"
				},500,function(){
					key = true;
				});
			}
		});
	}
	
	
	
});