/**
 * 头部事件
 */
$(function(){
	
	$(".soso-a").find("a").on('click',function(){
		showInFrame($(this));
		return false;
	});
	$(".searchbtn").click(function(){
		var html='';
		var keyword=$("#keyWords").val();
		if(keyword=='') return false;
		else {
			html='http://localhost:8080/cshm/rest/page/searchGoodsByKeyword/'+keyword;
			$(".content>.frame>iframe").attr("src",html);
		}
	});//end click
	
	
	/**
	 * 点击进入我的消息
	 */
	$("#showMyMessage").click(function(){
		var loginid=$("#hiddenLogin").val();
			var html=$(this).find("a").attr("href");
			html=html+"/"+loginid;
			$(".content>.frame>iframe").attr("src",html);
			return false;
	});
	
	/**
	 * 点击个人中心
	 */
    $("#showMyself").click(function(){
		var loginid=$("#hiddenLogin").val();
		if(loginid!=undefined){
			var openUrl='http://localhost:8080/cshm/rest/user/showUserDetailPre';
			var html=$(this).find("a").attr("href");
			html=html+"/"+loginid;
			$.ajax({
				data:{loginid:loginid},
				type:'post',
				dataType:'json',
				url:openUrl,
				success:function(data){
					if(data.msg=='success'){//seccess证明正在或已经进行实名认证
						$(".content>.frame>iframe").attr("src",html);
						return false;
					}else{
						parent.layer.confirm("请先进行实名认证！",{
							btn:['好的','不好'],//按钮
							shade:false//不显示遮罩
						},function(){//点击好之后
							var confirmUrl="http://localhost:8080/cshm/rest/page/getCertification/"+loginid;
							$(".content>.frame>iframe").attr("src",confirmUrl);
							layer.close();
						},function(){//点击不好之后
							layer.close();
						})
						return false;
					}
				}
			})//end ajax
			return false;
		}else{
			loginFun();
			return false;
		}
		
		
	})// end click
    
    

	$(".login").click(function(e){
		loginFun();
		e.stopPropagation(); 
	});//end login
	
	$(".register").click(function(e){
		registFun();
		e.stopPropagation(); 
	});//end register button
	
	$("#registnow").click(function(e){
		$(".loginM").fadeOut(100);
		registFun();
		e.stopPropagation();
	});
	$("#loginnow").click(function(e){
		$(".regist").fadeOut(100);
		loginFun();
		e.stopPropagation();
	});
	
	$(".registBtn").click(function(){
		//如果有错误提示，就返回false
		var text=eacError();

		if(text!=""){
			return false;
		}
		var sendData= $("#registForm").serialize();
		var frame=$("iframe").attr("src");
		var openUrl= $("#registForm").attr("action");
		$.ajax({
			data:sendData,
			type:'post',
			dataType:'json',
			url:openUrl,
			success:function(data){
				if(data.msg=='success'){
					setTimeout(function(){
						parent.layer.msg("注册成功！")
					},500);
					$("#myheader").empty().load("http://localhost:8080/cshm/rest/page/gethead");
					//框架链接不变
					$("iframe").attr("src",frame);
				}else{
					parent.layer.msg("注册失败");
					$("iframe").attr("src",frame);
				}
			},
			error:function(){
				parent.layer.msg("注册有误！");
			}
		});//end ajax
		$(".mantle").fadeOut(100);
		$(".regist").fadeOut(100);
		$(".loginM").fadeOut(100);
	})//end register
	
	$(".mantle").click(function(){
		$(".mantle").fadeOut(100);
		$(".regist").fadeOut(100);
		$(".loginM").fadeOut(100);
	});
	
	//login click
	$(".loginBtn").click(function(){
		const sendData={
				username:$("#name").val(),
				password:$("#pas").val()
		}
		$.ajax({
			data:sendData,
			type:'post',
			dataType:'json',
			url:'http://localhost:8080/cshm/rest/user/loginForm',
			success:function(data){
				var frame=$("iframe").attr("src");
				if(data.msg=='success'){
					setTimeout(function(){
						parent.layer.msg("登录成功！")
					},500);
						$("#myheader").empty().load("http://localhost:8080/cshm/rest/page/gethead");
						//框架链接不变
						$("iframe").attr("src",frame);
				}else{
					parent.layer.msg("登录失败");
					$("iframe").attr("src",frame);
				}
				$(".mantle").fadeOut(100);
				$(".regist").fadeOut(100);
				$(".loginM").fadeOut(100);
			}
		});//end ajax
	});//end login click
	
	$("#nickname").blur(function(){
		var nickname=$(this).val();
		var data={nickname:nickname};
		var openUrl='http://localhost:8080/cshm/rest/user/checkNickname';
		var html= checkinput(data,openUrl);
		$("#shownicknameError").empty().append(html);
		if(nickname.length==0){
			$("#shownicknameError").empty().append('昵称不能为空！');
		}
	});
	$("#email").blur(function(){
		var email=$(this).val();
		var data={username:email};
		var openUrl='http://localhost:8080/cshm/rest/user/checkUsername';
		var html= checkinput(data,openUrl);
		$("#showemailError").empty().append(html);
		if(email.length==0){
			$("#showemailError").empty().append('用户名不能为空！');
		}
	});
	$("#password").blur(function(){
		var password=$(this).val();
		if(password.length<6){
			$("#showpasswordError").empty().append('密码不能低于6位');
		}else $("#showpasswordError").empty();
	});
	$("#repassword").blur(function(){
		var repassword=$(this).val();
		if(repassword.length<6){
			$("#showrepasswordError").empty().append('密码不能低于6位');
		}else if(repassword!=$("#password").val()){
			$("#showrepasswordError").empty().append('两次密码不一致！');
		}else $("#showrepasswordError").empty();
	});
	
	//exit click
	$("#exit").click(function(){
		$.ajax({
			type:'post',
			dataType:'json',
			url:'http://localhost:8080/cshm/rest/user/exit',
			success:function(data){
				if(data.msg=='success'){
					parent.layer.msg("注销成功");
					window.location.reload();
				}
				$(".mantle").fadeOut(100);
				$(".regist").fadeOut(100);
				$(".loginM").fadeOut(100);
			}
		})
	});
	
	/*login information*/
    $(".selectinfo").hover(function (e) {
        var thisUl = $(this).find("ul"); 
        thisUl.fadeToggle("100");
        e.stopPropagation(); 
    });
    
    

});//end ready


function checkinput(data,openUrl){
	var html='';
	$.ajax({
		async:false,//默认为异步，false为同步！
		data:data,
		type:'post',
		dataType:'json',
		url:openUrl,
		success:function(data){
			if(data.msg=='error'){
				html+='用户名已存在';
			}
		}
	});//end ajax
	return html;
}

function loginFun(){
	$(".mantle").fadeIn(100);
	$(".loginM").fadeIn(100);
}

function registFun(){
	$(".mantle").fadeIn(100);
	$(".regist").fadeIn(100);
}
function eacError(){
	var html=''
	$(".showRegistError").each(function(){
		var text=$(this).text()
		if(text!=""){
			html=text;
			return false;
		}
	});
	return html;
}
function getMessageCount(loginid){
	$.ajax({
		data:{loginid:loginid},
		type:'post',
		dataType:'json',
		url:'http://localhost:8080/cshm/rest/message/getNoneReadMessageCount',
		success:function(data){
			if(data.count==0){
				$("#showMessageCount").css("display","none");
			}else{
				$("#showMessageCount").empty().append(data.count);
			}
		}
	})
}
