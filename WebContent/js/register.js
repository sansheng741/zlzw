				//轮播图
				;(function(){
                    var defaultInd = 0;
                    var list = $('#js_ban_content').children();
                    var count = 0;
                    var change = function(newInd, callback){
                        if(count) return;
                        count = 2;
                        $(list[defaultInd]).fadeOut(400, function(){
                            count--;
                            if(count <= 0){
                                if(start.timer) window.clearTimeout(start.timer);
                                callback && callback();
                            }
                        });
                        $(list[newInd]).fadeIn(400, function(){
                            defaultInd = newInd;
                            count--;
                            if(count <= 0){
                                if(start.timer) window.clearTimeout(start.timer);
                                callback && callback();
                            }
                        });
                    }
                    
                    var next = function(callback){
                        var newInd = defaultInd + 1;
                        if(newInd >= list.length){
                            newInd = 0;
                        }
                        change(newInd, callback);
                    }
                    
                    var start = function(){
                        if(start.timer) window.clearTimeout(start.timer);
                        start.timer = window.setTimeout(function(){
                            next(function(){
                                start();
                            });
                        }, 3000);
                    }
                    
                    start();
                    
                    $('#js_ban_button_box').on('click', 'a', function(){
                        var btn = $(this);
                        if(btn.hasClass('right')){
                            //next
                            next(function(){
                                start();
                            });
                        }
                        else{
                            //prev
                            var newInd = defaultInd - 1;
                            if(newInd < 0){
                                newInd = list.length - 1;
                            }
                            change(newInd, function(){
                                start();
                            });
                        }
                        return false;
                    });
                    
                })();

//注册校验
//这里使用最原始的js语法实现，可对应换成jquery语法进行逻辑控制
var demoImg = document.getElementById("demo_img");
var demoInput = document.getElementById("demo_input");
//隐藏text block，显示password block
function hideShowPsw(){
	if (demoInput.type == "password") {
		demoInput.type = "text";
		demo_img.src = "img/invisible.png";
	}else {
		demoInput.type = "password";
		demo_img.src = "img/visible.png";
	}
}

//刷新验证码
	 function changeImg(){
document.getElementById("validateCodeImg").src="/zlzw/bufferImage?"+Math.random(); 
	}
function check(){
	 if(!$("input[type='checkbox']").is(':checked')){
		 $("#js-mobile_btn").attr("disabled",true).css("background-color","#333");
	 }else{
		 $("#js-mobile_btn").attr("disabled",false).css("background-color","#3399ea");
	 }
}




function checkEmail(email) {
if (email.value == "") {
//alert("请输入用户名!");
//email.focus();
return;
} else {
createRequest('CheckEmailServlet?em='+encodeURI(encodeURI(email.value)));
}
}
function checkUser(username) {
if (username.value == "") {
//alert("请输入用户名!");
// username.focus();
return;
} else {
createRequest2('CheckUserServlet?user='+encodeURI(encodeURI(username.value)));
}
}
function createRequest(url) {
http_request = false;
if (window.XMLHttpRequest) {
http_request = new XMLHttpRequest();
} else if (window.ActiveXObject) {
try {
   http_request = new ActiveXObject("Msxml2.XMLHTTP");
} catch (e) {
   try {
       http_request = new ActiveXObject("Microsoft.XMLHTTP");
   } catch (e) {

   }
}
}

if (!http_request) {
//alert("不能创建XMLHttpRequest对象实例!");
return false;
}
//alert("创建成功!");
http_request.onreadystatechange = getResult;
http_request.open('GET', url, true);
http_request.send(null);
}
function getResult() {
if (http_request.readyState == 4) { //请求已经完成
if (http_request.status == 200) { //请求成功
   document.getElementById("email").innerHTML = http_request.responseText;
} else {
   //alert("你请求的页面有错误!");
}
}
}
function createRequest2(url2) {
http_request2 = false;
if (window.XMLHttpRequest) {
http_request2 = new XMLHttpRequest();
} else if (window.ActiveXObject) {
try {
   http_request2 = new ActiveXObject("Msxml2.XMLHTTP");
} catch (e) {
   try {
       http_request2 = new ActiveXObject("Microsoft.XMLHTTP");
   } catch (e) {

   }
}
}

if (!http_request2) {
//alert("不能创建XMLHttpRequest对象实例!");
return false;
}
//alert("创建成功!");
http_request2.onreadystatechange = getResult2;
http_request2.open('GET', url2, true);
http_request2.send(null);
}
function getResult2() {
if (http_request2.readyState == 4) { //请求已经完成
if (http_request2.status == 200) { //请求成功
   document.getElementById("user").innerHTML = http_request2.responseText;
} else {
   //alert("你请求的页面有错误!");
}
}
}


//检查用户名
function checkName(){
	var username=document.getElementById("js-mobile_user_ipt").value;
	var userSpan=document.getElementById("user");
	var reg=/^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){4,19}$/;
	if(reg.test(username)==false){
		userSpan.innerHTML="输入的用户名格式不正确(以字母开头)".fontcolor("red");
		return false;
		}else{
		userSpan.innerHTML="";
		return true;
	}
}
//检查邮箱
/*function checkEmail(){
	var useremail=document.getElementById("js-mobile_ipt").value;
	var userEmail=document.getElementById("email");
	var reg=/^[a-z0-9]+([._\\-]*[a-z0-9]*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$)/;
	if(reg.test(useremail)==false){
		userEmail.innerHTML="输入的邮箱不正确，请重新输入".fontcolor("red");
		return false;
		}else{
		userEmail.innerHTML="";
		return true;
	}
}*/

//检查密码
function checkPwd(){
	 var p = document.getElementById("demo_input");
     var rePwd = document.getElementById("pwd");
     var reg = /^\w{6,18}$/;
     if(!reg.test(p.value)){
         p.focus();
         rePwd.innerHTML = "密码由6-18位的数字、字母、下划线组成".fontcolor("red");
     }else {
         rePwd.innerHTML = "";
     }

	/*var userpwd=document.getElementById("demo_input").value;
	var userPwd=document.getElementById("pwd");
	var reg=/^[a-zA-Z0-9]{4,16}$/;
	if(reg.test(userpwd)){
		userPwd.innerHTML="";
		return true;
		}else{
		userPwd.innerHTML="密码不能含有非法字符，长度在6-16之间".fontcolor("red");
		return false;
	}*/
}




