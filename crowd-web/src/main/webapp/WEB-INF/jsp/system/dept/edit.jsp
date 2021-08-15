<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<%@ include file="/common/taglib.jsp"%>
<%@ include file="/common/header.jsp"%>
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
									<input id="id" name="id" value="${dept.id}"
										class="form-control hidden" type="text"> <input
										id="pid" name="pid" value="${pid}" class="form-control hidden"
										type="text">
								</div>
								<div class="col-sm-8">
									<input id="pName" name="pName" value="${pName}"
										class="form-control" type="text" readonly
										style="cursor: pointer;" onclick="openDept()" readonly>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"><span class="wayn-required-span">*</span>部门名称：</label>
								<div class="col-sm-8">
									<input id="deptName" name="deptName" class="form-control"
										type="text" value="${dept.deptName}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">部门备注：</label>
								<div class="col-sm-8">
									<input id="remarks" name="remarks" class="form-control"
										type="text" value="${dept.remarks}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">排序：</label>
								<div class="col-sm-8">
									<input id="sort" name="sort" class="form-control" type="text"
										value="${dept.sort}">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-8 col-sm-offset-3">
									<button type="submit" class="btn btn-primary">提 交</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/common/footer.jsp"%>
	<script>
		var prefix = _ctx + "/system/dept";

		function validateRule() {
			var e = '<i class="fa fa-times-circle"></i> ';
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
				focusCleanup: true,
				submitHandler : function() {
					save();
				}
			});
		}

		function save() {
			var config = {
				url : prefix + "/editSave",
				data : $('#dept-form').serialize(),
				type : "POST",
				dataType : "json",
				success : function(data) {
					if (data.code != 100) {
						parent.layer.alert(data.msg);
					} else {
						parent.layer.msg(data.msg, {icon: 1});
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
