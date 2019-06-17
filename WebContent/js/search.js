$(function() {

	//改变被选中的导航条
	changeNav();
	prePage();
	nextPage();
	changePage();

	function changeNav() {
		$(".nav>ul>a>li").hover(
			function() {
				$(this).css({
					borderBottom: "3px solid red"
				});
			},
			function() {
				$(".nav>ul>a>li").css({
					borderBottom: "0"
				});
			}
		);
//		$(".category>a").eq(0).css({
//			color: "#fff",
//			background: "#08bf91"
//		});
//		$(".tag>a").eq(0).css({
//			color: "#fff",
//			background: "#08bf91"
//		});
//		$(".courses-sort>a").eq(0).css({
//			color: "#fff",
//			background: "#08bf91"
//		});
		/**
		 * 改变分页卡的样式
		 */
		/*$(".pagesCount>li").eq(1).css({
			background: "#0c9",
			color: "#fff"
		});*/
		$(".pagesCount>li").hover(
			function() {
				$(this).addClass("pagesStyle");
			},
			function() {
				$(this).removeClass("pagesStyle");
			}
		);
	}

	//上一页
	function prePage() {
		$(".prePage").on("click", function() {
			//获取当前页
			var turl=$("#turl").val();
			var currentPage = parseInt($("#currentPage").val()) - 1;;
			if (currentPage == 0) {
				alert("不能再往前翻了哦");
			} else {
				var pages = $(".pagesCount>li").not(".prePage,.nextPage");
				var firstNum =  parseInt($(".pagesCount>li").eq(1).html());
				
				//当前页超过10页后改变分页卡的是数字
				if (firstNum == currentPage + 1) {
					for (var i = 0; i < pages.length; i++) {
						var pagesNum = parseInt($(".pagesCount>li").not(".prePage,.nextPage").eq(i).html());
						$(".pagesCount>li").not(".prePage,.nextPage").eq(i).html(pagesNum - 1);
					}
				}
				//改变页码的样式
				for (var i = 0; i < pages.length; i++) {
					var pagesNum = parseInt(pages.eq(i).html());
					if(pagesNum == currentPage){
						$(".pagesCount>li").eq(i+1).css({
							background: "#0c9",
							color: "#fff"
						});
					}else{
						$(".pagesCount>li").eq(i+1).css({
							background: "white",
							color: "#565a61"
						});
					}
				}
				$("#currentPage").val(parseInt(currentPage));
				window.location.href="search?oper=search&"+turl+"currentpage="+currentPage;
			}
		});
	}
	//下一页
	function nextPage() {
		$(".nextPage").on("click", function() {
			//获取当前页
			var currentPage = parseInt($("#currentPage").val()) + 1;
			//获取总页数
			var turl=$("#turl").val();
			var totalPage = $("#totalPage").val();
			if (currentPage - 1 == totalPage) {
				alert("当前已是最后一页了哦");
			} else {
				var pages = $(".pagesCount>li").not(".prePage,.nextPage");
				var lastNum =  parseInt($(".pagesCount>li").eq(pages.length).html());
				
				//当前页超过10页后改变分页卡的是数字
				if (currentPage > 10 && lastNum == currentPage -1) {
					for (var i = 0; i < pages.length; i++) {
						var pagesNum = parseInt($(".pagesCount>li").not(".prePage,.nextPage").eq(i).html());
						$(".pagesCount>li").not(".prePage,.nextPage").eq(i).html(pagesNum + 1);
					}
				}
				//改变页码的样式
				for (var i = 0; i < pages.length; i++) {
					var pagesNum = parseInt(pages.eq(i).html());
					if(pagesNum == currentPage){
						$(".pagesCount>li").eq(i+1).css({
							background: "#0c9",
							color: "#fff"
						});
					}else{
						$(".pagesCount>li").eq(i+1).css({
							background: "white",
							color: "#565a61"
						});
					}
				}
				$("#currentPage").val(parseInt(currentPage));
				window.location.href="search?oper=search&"+turl+"currentpage="+currentPage;
			}
		});
	}
	//改变页码
	function changePage() {
		var totalPage = $("#totalPage").val();
		var turl=$("#turl").val();
		$(".pagesCount>li").not(".prePage,.nextPage").on("click", function() {
			$(this).css({
				background: "#0c9",
				color: "#fff"
			});
			$(".pagesCount>li").not($(this)).css({
				background: "white",
				color: "#565a61"
			});
			//获取当前页码值
			var currentPage = $(this).html();
			console.log(currentPage);
			//改变页码值
			currentPage=$("#currentPage").val(parseInt(currentPage));
			window.location.href="search?oper=search&"+turl+"currentpage="+currentPage.val();
		});
	}
});
