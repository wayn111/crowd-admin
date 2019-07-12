<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<%@ include file="/commom/taglib.jsp"%>
<%@ include file="/commom/header.jsp"%>
<body class="gray-bg">
	<div class="wrapper wrapper-content ">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<form class="form-horizontal m-t" id="user-form">
							<div class="form-group">
								<label class="col-sm-3 control-label">用户名：</label>
								<div class="col-sm-8">
									<input id="userName" name="userName" class="form-control"
										type="text">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">密码：</label>
								<div class="col-sm-8">
									<input id="password" name="password" class="form-control"
										type="password">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">部门：</label>
								<div class="col-sm-8">
									<input id="deptId" name="deptId" class="hidden">
									<input
										id="deptName" name="deptName" class="form-control" type="text"
										style="cursor: pointer;" onclick="openDept()"
										readonly="readonly" placeholder="选择所属部门">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">状态:</label>
								<div class="col-sm-8">
									<input type="hidden" id="userState" name="userState" value="1">
									<input type="checkbox" name="userStateSwicth" checked>
								</div>
							</div>
							<input type="hidden" name="roleIds" id="roleIds">
							<div class="form-group">
								<label class="col-sm-3 control-label">角色</label>
								<div class="col-sm-8">
									<c:forEach items="${roles}" var="role">
										<label class="checkbox-inline"> <input name="roleId"
											type="checkbox" value="${role.id}"> ${role.roleName }
										</label>
									</c:forEach>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">备注：</label>
								<div class="col-sm-8">
									<textarea id="remarks" name="remarks" class="form-control" rows="3"></textarea>
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

	</div>
	<%@ include file="/commom/footer.jsp"%>
	<script>
		let prefix = _ctx + '/system/user';

		function getCheckedRoles() {
			let adIds = "";
			$("input:checkbox[name=roleId]:checked").each(function(i) {
				if (0 == i) {
					adIds = $(this).val();
				} else {
					adIds += ("," + $(this).val());
				}
			});
			return adIds;
		}
		function save() {
			$("#roleIds").val(getCheckedRoles());
			$.ajax({
				cache : true,
				type : "POST",
				dataType: "json",
				url : prefix + "/addSave",
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
						let index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
						parent.layer.close(index);
					}

				}
			});

		}
		function validateRule() {
			let icon = "<i class='fa fa-times-circle'></i> ";
			$("#user-form").validate({
				rules : {
					userName : {
						required : true,
						minlength : 2,
						remote : {
							url : prefix + "/exists", // 后台处理程序
							type : "post", // 数据发送方式
							dataType : "json", // 接受数据格式
							data : { // 要传递的数据
								userName : function() {
									return $("#userName").val();
								}
							}
						}
					},
					password : {
						required : true,
						minlength : 6
					},
					deptName : {
						required : true
					}
				},
				messages : {
					userName : {
						required : icon + "请输入您的用户名",
						minlength : icon + "用户名必须两个字符以上",
						remote : icon + "用户名已经存在"
					},
					password : {
						required : icon + "请输入您的密码",
						minlength : icon + "密码必须6个字符以上"
					},
					deptName : {
						required : icon + "请选择部门",
					}
				},
				submitHandler : function() {
					save();
				}
			})
		}

		let openDept = function () {
			layer.open({
				type: 2,
				title: "选择部门",
				area: ['300px', '450px'],
				content: _ctx + "/system/dept/treeView"
			})
		};
		function loadDept(deptId, deptName) {
			$("#deptId").val(deptId);
			$("#deptName").val(deptName);
		}

		$(function() {
			switchInit('user');
			validateRule();
		})
	</script>
</body>

</html>
