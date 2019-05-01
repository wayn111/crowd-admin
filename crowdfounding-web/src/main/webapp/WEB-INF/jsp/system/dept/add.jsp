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
				<div class="ibox float-e-margins">
					<div class="ibox-content">
						<form class="form-horizontal m-t" id="dept-form">
							<div class="form-group">
								<label class="col-sm-3 control-label">上级部门：</label>
								<div class="col-sm-8">
									<input id="pid" name="pid" value="${pid}"
										class="form-control hidden" type="text">
								</div>
								<div class="col-sm-8">
									<input id="pName" name="pName" value="${pName}"
										class="form-control" type="text" readonly
										style="cursor: pointer;" onclick="openDept()" readonly>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">部门名称：</label>
								<div class="col-sm-8">
									<input id="deptName" name="deptName" class="form-control"
										type="text">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">部门描述：</label>
								<div class="col-sm-8">
									<input id="deptDesc" name="deptDesc" class="form-control"
										type="text">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">排序：</label>
								<div class="col-sm-8">
									<input id="sort" name="sort" class="form-control" type="text">
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
		let prefix = _ctx + "/system/dept";

		function validateRule() {
			let e = '<i class="fa fa-times-circle"></i> ';
			$("#dept-form").validate({
				rules : {
					deptName : {
						required : true
					}
				},
				messages : {
					deptName : {
						required : e + "请输入部门名"
					},
				},
				ignore : ".ignore",
				submitHandler : function() {
					save();
				}
			});
		}

		function save() {
			let config = {
				url : prefix + "/addSave",
				data : $('#dept-form').serialize(),
				type : "POST",
				dataType : "json",
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
				},
				error : function(err) {
					console.log(err)
				}
			};
			$.ajax(config);
		}

		var openDept = function() {
			layer.open({
				type : 2,
				title : "选择部门",
				area : [ '300px', '450px' ],
				content : prefix + "/treeView"
			})
		}
		function loadDept(pid, pName) {
			$("#pid").val(pid);
			$("#pName").val(pName);
		}

		$(function() {
			validateRule();
		})
	</script>
</body>

</html>
