
function hideShowPsw(){
	var demoImg = document.getElementById("img1");
	var demoInput = document.getElementById("input1");
	//隐藏text block，显示password block
	if (demoInput.type == "password") {
		demoInput.type = "text";
		img1.src = "img/invisible.png";
	}else {
		demoInput.type = "password";
		img1.src = "img/visible.png";
	}
}

//点击获取验证码
function getCode(){
	mytime=window.setInterval("time()",1000);
	var email=$('#email');
	$.ajax({
		type:"POST",
		url:"SendCodeServlet",
		data:email,
		cache:false,
		success:function(){
			alert("验证码已发送，请查收！");
		}
	});	
}

//获取验证码按钮倒计时
var mytime;
var i=60;
function time(){
	var obj=$("#b");
	if(i>0){
		obj.css("background","#CCC");
		obj.attr("disabled","false");
		obj.attr("value","剩余("+i+")秒");
		i--;
		return;
	}else{
		obj.attr("value","获取验证码");
		obj.css("background","#0080ff");
		obj.attr("disabled","true");
		return;
	}
}

$(document).ready(function(){
	  $('#email').keyup(function(){
	    var v=$('#email').val();
	    if(!v){
	      $('#b').attr('disabled', true) 
	    }
	    else{
	     $('#b').attr('disabled', false)
	    }
	});
});


