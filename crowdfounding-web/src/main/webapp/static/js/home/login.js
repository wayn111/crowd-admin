let prefix = _ctx + "/home";

function validateRule() {
	let e = '<i class="fa fa-times-circle"></i> ';
	$("#login-form").validate({
		rules : {
			userName : {
				required : true,
				minlength : 2
			},
			password : {
				required : true
			}

		},
		messages : {
			userName : {
				required : e + "请输入用户名",
				minlength : e + "用户名最少由二个字母组成"
			},
			password : {
				required : e + "请输入密码",
			},
		},
		submitHandler : function() {
			login();
		}
	});
}

function login() {
	let config = {
		url : prefix + "/doLogin",
		data : $('#login-form').serialize(),
		type : "POST",
		dataType : "json",
		success : function(data) {
			if (data.code != 100) {
				layer.msg(data.msg);
			} else {
				location = _ctx + "/main";
			}
		},
		error : function(err) {
			console.log(err)
		}
	};
	$.ajax(config);
}

$(function() {
	validateRule();
})
