<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<%@ include file="/commom/taglib.jsp"%>
<%@ include file="/commom/header.jsp"%>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox-content">
					<form class="form-horizontal m-t" id="user-form">
						<input id="id" name="id" value="${id}" type="hidden">
						<div class="form-group">
							<label class="col-sm-3 control-label">输入密码：</label>
							<div class="col-sm-8">
								<input id="password" name="password" class="form-control"
									type="password">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-8 col-sm-offset-3">
								<button type="submit" class="btn btn-primary">提交</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/commom/footer.jsp"%>
	<script>
		var prefix = _ctx + '/system/user';

		function save() {
			$.ajax({
				cache : true,
				type : "POST",
				url : prefix + "/resetPwd",
				data : $('#user-form').serialize(),// 你的formid
				async : false,
				error : function(request) {
					parent.layer.alert("Connection error");
				},
				success : function(data) {
					if (data.code != 100) {
						layer.alert(data.msg);
					} else {
						parent.layer.msg(data.msg);
						parent.reload();
						//关闭当前窗口
						var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
						parent.layer.close(index);
					}

				}
			});

		}
		function validateRule() {
			var icon = "<i class='fa fa-times-circle'></i> ";
			$("#user-form").validate({
				rules : {
					password : {
						required : true,
						minlength : 6
					}
				},
				messages : {
					password : {
						required : icon + "请输入您的密码",
						minlength : icon + "密码必须6个字符以上"
					}
				},
				submitHandler : function() {
					save();
				}
			})
		}

		$(function() {
			validateRule();
		})
	</script>
</body>

</html>
