//$(function() {
//	// 获取用户名和密码
//	// 统计用户登陆错误次数
//	var count = 0;
//	$(".tsubmit").on("click",function(){
//		var x = document.getElementById("username").value;
//		var y = document.getElementById("password").value;
//		if (x == "" || y == "") {
//			alert("用户名或密码不能为空");
//			return false;
//		} else if (x == "1001" || y == "0001") {
//			// 与js设置的用户名和密码进行匹配
//			// 密码匹配后跳转
//			$("#ajaxForm").submit();
//		} else {
//			count++;
//			if (count >= 3) {
//				$(".ver-code").stop().slideDown("300");
//				return false;
//			} else {
//				alert("用户名或密码错误");
//				return false;
//			}
//		}
//	});
//});

(function() {
	var defaultInd = 0;
	var list = $('#js_ban_content').children();
	var count = 0;
	var change = function(newInd, callback) {
		if (count)
			return;
		count = 2;
		$(list[defaultInd]).fadeOut(400, function() {
			count--;
			if (count <= 0) {
				if (start.timer)
					window.clearTimeout(start.timer);
				callback && callback();
			}
		});
		$(list[newInd]).fadeIn(400, function() {
			defaultInd = newInd;
			count--;
			if (count <= 0) {
				if (start.timer)
					window.clearTimeout(start.timer);
				callback && callback();
			}
		});
	}

	var next = function(callback) {
		var newInd = defaultInd + 1;
		if (newInd >= list.length) {
			newInd = 0;
		}
		change(newInd, callback);
	}

	var start = function() {
		if (start.timer)
			window.clearTimeout(start.timer);
		start.timer = window.setTimeout(function() {
			next(function() {
				start();
			});
		}, 4000);
	}

	start();

	$('#js_ban_button_box').on('click', 'a', function() {
		var btn = $(this);
		if (btn.hasClass('right')) {
			// next
			next(function() {
				start();
			});
		} else {
			// prev
			var newInd = defaultInd - 1;
			if (newInd < 0) {
				newInd = list.length - 1;
			}
			change(newInd, function() {
				start();
			});
		}
		return false;
	});

})();