$(function(){
		$("#login").click(login);
		$("#regist_button").click(regist);
	});
function login() {
	$("#count_span").html("");
	$("#password_span").html("");
	var name = $("#count").val().trim();
	var password = $("#password").val().trim();

	var ok = true;
	if (name == "") {
		$("#count_span").html("用户名为空");
		ok = false;
	}
	if (password == "") {
		$("#password_span").html("密码为空");
		ok = false;
	}
	if (ok) {
		$.ajax({
			url : path + "/user/login.do",
			type : "post",
			data : {
				"name" : name,
				"password" : password
			},
			dataType : "json",
			success : function(obj) {
				if(obj.state==0){
					var user=obj.data;
					addCookie("userId",user.id,2);
					window.location.href="edit.html";
				}else if(obj.state==2){
					$("#count_span").html(result.message);
				}else if(obj.state==3){
					$("#password_span").html(result.message);
				}
			},
			error : function() {
				alert("登录失败");
			}
		});
	}
}
function regist(){
	$("#warning_1 span").html("");
	$("#warning_2 span").html("");
	$("#warning_3 span").html("");
	
	var name=$("#regist_username").val().trim();
	var nick=$("#nickname").val().trim();
	var password=$("#regist_password").val().trim();
	var final_password=$("#final_password").val().trim();
	
	var ok=true;
	if(name==""){
		ok=false;
		$("#warning_1 span").html("用户名为空");
		$("#warning_1").show();
	}
	if(password==""){
		ok=false;
		$("#warning_2 span").html("密码为空");
		$("#warning_2").show();
	}else if(password.length<6){
		ok=false;
		$("#warning_2 span").html("密码长度小于6位");
		$("#warning_2").show();
	}
	if(final_password!=password){
		ok=false;
		$("#warning_3 span").html("密码不一致");
		$("#warning_3").show();
	}
	if(ok){
		$.ajax({
			url:path+"/user/regist.do",
			type:"post",
			data:{
				"name":name,
				"nick":nick,
				"password":password
			},
			dataType:"json",
			success:function(result){
				if(result.state==0){
					var user=result.data;
					$("#back").click();
					$("#count").val(user.name);
					$("#password").focus();
				}else if(result.state==2){
					$("#warning_1 span").html(result.message);
					$("#warning_1").show();
				}else{
					alert(result.message);
				}
			},
			error:function(){
				alert("注册失败");
			}
		});
	}
}