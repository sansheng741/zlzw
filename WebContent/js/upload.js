$(function() {
	//统计上传图片
	var picount = 0;
	var formData = new FormData();
	
	
	changeNav();
	showUploadPic();
	del_upload_pic();
	uploadSrc();
	changeSmallTypeListener();
	
	function changeNav() {
		//改变被选中的导航条
		$(".nav>ul>a>li").eq(2).css({
			borderBottom: "3px solid red"
		});
		$(".nav>ul>a>li").hover(
			function() {
				$(this).css({
					borderBottom: "3px solid red"
				});
				$(".nav>ul>a>li").not($(this)).css({
					borderBottom: "0"
				});
			},
			function() {
				$(".nav>ul>a>li").css({
					borderBottom: "0"
				});
				$(".nav>ul>a>li").eq(2).css({
					borderBottom: "3px solid red"
				});
			}
		);
	}
	// 上传资源
	function uploadSrc() {
		function ProcessFile(e) {
			var file = document.getElementById('file').files[0];
			if (file) {
				var reader = new FileReader();
				reader.onload = function(event) {
					// 判断上传的类型
					var type = getType($("#file").val());
					if (type == ".rar" || type == ".zip" || type == ".txt" || type == ".jar") {
						if(parseInt(file.size/1024/1024) > 220){
							alert("您只能上传小于220MB的文件");
							return;
						}
						$(".up-file>span>img").attr("src","img/upload_finish.png");
						formData.append('file', $('#file')[0].files[0]);  //文件
 						// 获取文件名并展示
 						var name = document.getElementById('file').files[0].name
 						$(".choose-intro-f").html(name);
//						$(".choose-intro-f").html("上传成功");
						$(".choose-file .up-file .error-tips").css({
							display : "none"
						});
						$(".btn_choiceFile").eq(0).prop("disabled","true");
					}
				}
				reader.readAsDataURL(file);
			}
		}

		function contentLoadedF() {
			document.getElementById('file').addEventListener('change',
				ProcessFile, false);
		}

		window.addEventListener("DOMContentLoaded", contentLoadedF, false);
	}

	// 上传文件图片
	function showUploadPic() {
		function ProcessPic(e) {
			var file = document.getElementById('pic').files[0];
			if (file) {
				var reader = new FileReader();
				reader.onload = function(event) {
					txt = event.target.result;
					// 判断上传图片的类型
					var type = getType($("#pic").val());
					if (type == ".PNG" || type == ".jpg" || type == ".png" || type == ".JPG") {
						// 生成图片上传框节点
						var point = createUploadFilePic();
						// 将节点附加到对应位置
						$(".choice-pic>ul").prepend(point);
						// 将图片节点插入到对应位置
						var img = document.createElement("img");
						img.src = txt; //将图片base64字符串赋值给img的src
						document.getElementById("result").appendChild(img);
						//图片计数器+1
						picount++;
						formData.set("'pic"+picount+"'", $('#pic')[0].files[0]);
						if (picount >= 6) {
							$(".up-pic").css({
								display: "none"
							});
						}
						$(".choice-pic .up-pic .error-tips").css({
							display : "none"
						});
					}
				}
				reader.readAsDataURL(file);
			}
		}

		function contentLoadedP() {
			document.getElementById('pic').addEventListener('change',
				ProcessPic, false);
		}

		window.addEventListener("DOMContentLoaded", contentLoadedP, false);

		// 生成图片上传框节点
		function createUploadFilePic() {
			var point = $(
				"<li>"+
					"<div class=\"pic-pic\" id=\"result\">" +
						"<div class=\"upload-del\">删除</div>" +
					"</div>"+
				"</li>"
			);
			return point;
		}
	}

	//删除上传图片
	function del_upload_pic() {
		$("body").delegate(".upload-del", "click", function(){
			//获取当前图片的下标
			var index = picount - $(".choice-pic>ul>li").index($(this).parents("li"));
			$(this).parents("li").remove();
			//改变formdata里数据存放的位置
			for(var i = index; i < picount; i++){
				formData.set("'pic"+i+"'", formData.get("'pic"+(i+1)+"'"));
			}
			formData.delete("'pic"+picount+"'");
			picount--;
			$(".up-pic").css({
				display: "inline-block"
			});
		});
	}
	// 得到文件类型
	function getType(path){
		return path.substring(path.lastIndexOf("."));
	}
	
	$(".btn_choiceFile").on("click",function(){
		$(this).siblings("input[type='file']").click();
	});
	
	
	
	
	//	上传文件
	$(".button>input[type='button']").on("click",function(){
		formData.set('srcname',$(".txt_file").val());  				    //文件名
		formData.set('filetype',$(".filetype>select").val());    	    //资源类型
		formData.set('fileclass1',$(".fileclass select").eq(0).val());  //分类一
		formData.set('fileclass2', $(".fileclass select").eq(1).val()); //分类二
		formData.set('fileclass3',$(".fileclass select").eq(2).val());  //分类三
		formData.set('desc',$(".filemiaoshu>textarea").val());          //资源描述
		formData.set('uid',$(".uid").val());         				    //用户名
		
		if(formData.get("file") == null){
			$(".choose-file .up-file .error-tips").css({
				display : "inline-block"
			});
		}else if(formData.get("'pic1'") == null){
			$(".choice-pic .up-pic .error-tips").css({
				display : "inline-block"
			});
		}else if(formData.get("srcname") == ""){
			$(".filename .from-error").css({
				display : "inline-block"
			});
		}else if(formData.get("filetype") == "请选择"){
			$(".filetype .from-error").css({
				display : "inline-block"
			});
			$(".filename .from-error").css({
				display : "none"
			});
		}else if(formData.get("fileclass1") == "请选择"){
			$(".fileclass .from-error").css({
				display : "inline-block"
			});
			$(".filename .from-error").css({
				display : "none"
			});
			$(".filetype .from-error").css({
				display : "none"
			});
		}else if(formData.get("desc") == ""){
			$(".filemiaoshu .from-error").css({
				display : "inline-block"
			});
			$(".filename .from-error").css({
				display : "none"
			});
			$(".filetype .from-error").css({
				display : "none"
			});
			$(".fileclass .from-error").css({
				display : "none"
			});
		}else{
			$.ajax({
		        url: 'goods.s?oper=upload',
		        type: 'POST',
		        cache: false,
		        data: formData,
		        processData: false,
		        contentType: false,
		        success : function(data){
		        	alert(data);
		        	window.location.href="user.s?oper=mySrc&uid=" + $(".uid").val();
		        },
		        error : function(){
		        	alert("上传失败");
		        }
			});
			$(".loading").css({
				display : "block"
			});
			$(".left").css({
				display : "none"
			});
		}
	});
	//监听小类别的改变
	function changeSmallTypeListener(){
		$(".fileclass").eq(0).change(function(){
			var type1 = $(".fileclass option:selected").eq(0).html();
			var type2 = $(".fileclass option:selected").eq(1).html();
			var type3 = $(".fileclass option:selected").eq(2).html();
			if(type1 == type2 || type1 == type3){
				$(".fileclass").eq(0).find("option[value = selectType]").removeAttr("selected");
				alert("类型不能重复！");
				$(".fileclass").eq(0).find("option[value = selectType]").attr("selected","selected");
			}
		});
		$(".fileclass").eq(1).change(function(){
			var type1 = $(".fileclass option:selected").eq(0).html();
			var type2 = $(".fileclass option:selected").eq(1).html();
			var type3 = $(".fileclass option:selected").eq(2).html();
			if(type2 == type1 || type2 == type3){
				$(".fileclass").eq(1).find("option[value = selectType]").removeAttr("selected");
				alert("类型不能重复！");
				$(".fileclass").eq(1).find("option[value = selectType]").attr("selected","selected");
			}
		});
		$(".fileclass").eq(2).change(function(){
			var type1 = $(".fileclass option:selected").eq(0).html();
			var type2 = $(".fileclass option:selected").eq(1).html();
			var type3 = $(".fileclass option:selected").eq(2).html();
			if(type3 == type1 || type3 == type2){
				$(".fileclass").eq(2).find("option[value = selectType]").removeAttr("selected");
				alert("类型不能重复！");
				$(".fileclass").eq(2).find("option[value = selectType]").attr("selected","selected");
			}
		});
	}
});
