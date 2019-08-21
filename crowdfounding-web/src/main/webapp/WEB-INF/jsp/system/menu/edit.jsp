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
						<form class="form-horizontal m-t" id="menu-form">
							<input id="id" name="id" type="hidden" value="${menu.id}"
								class="ignore">
							<div class="form-group">
								<label class="col-sm-3 control-label">上级菜单：</label>
								<div class="col-sm-8">
									<input id="pid" name="pid" class="hidden" value="${pid}">
									<input id="pName" name="pName" class="form-control ignore"
										type="text" value="${pName}" readonly style="cursor: pointer;"
										onclick="openMenu()">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"><span class="wayn-required-span">*</span>菜单类型：</label>
								<div class="col-sm-8">
									<c:forEach items="${menuTypes}" var="dict">
										<label class="radio-inline i-checks"> <input type="radio"
										name="type" value="${dict.id}" <c:if test="${menu.type == dict.id }">checked</c:if>/> ${dict.text}
										</label>
									</c:forEach>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label"><span class="wayn-required-span">*</span>菜单名称：</label>
								<div class="col-sm-8">
									<input id="menuName" name="menuName" class="form-control"
										type="text" value="${menu.menuName }">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">链接地址：</label>
								<div class="col-sm-8">
									<input id="url" name="url" class="form-control" type="text"
										value="${menu.url }">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">权限标识：</label>
								<div class="col-sm-8">
									<input id="resource" name="resource" class="form-control"
										type="text" value="${menu.resource }">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">排序号：</label>
								<div class="col-sm-8">
									<input id="sort" name="sort" class="form-control" type="text"
										value="${menu.sort }">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">图标：</label>
								<div class="col-sm-6">
									<input id="icon" name="icon" class="form-control" type="text"
										placeholder="例如：fa fa-circle-o" value="${menu.icon }">
								</div>
								<input id="ico-btn" class="btn btn-warning ignore" type="button"
									value="选择图标">
							</div>


							<div class="form-group">
								<div class="col-sm-8 col-sm-offset-3">
									<button id="save-button" type="submit" class="btn btn-primary">提交</button>
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
		var prefix = _ctx + "/system/menu";

		function validateRule() {
			var e = '<i class="fa fa-times-circle"></i> ';
			$("#menu-form").validate({
				debug : true,
				rules : {
					menuName : {
						required : true
					},
					type : {
						required : true
					}
				},
				messages : {
					menuName : {
						required : e + "请输入菜单名"
					},
					type : {
						required : e + "请选择菜单类型"
					}
				},
				ignore : ".ignore",
				focusCleanup : true,
				submitHandler : function() {
					update();
				}
			});
		}

		function update() {
			var config = {
				url : prefix + "/editSave",
				data : $('#menu-form').serialize(),
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

		var openMenu = function() {
			layer.open({
				type : 2,
				title : "选择菜单",
				area : [ '350px', '480px' ],
				content : prefix + "/treeView"
			})
		};

		function loadMenu(pid, pName) {
			$("#pid").val(pid);
			$("#pName").val(pName);
		}

		$(function() {
			$("#ico-btn").click(function() {
				layer.open({
					type : 2,
					title : '图标列表',
					anim: 5,
					content : _ctx + '/system/menu/chooseIcon',
					area : [ '480px', '90%' ],
					success : function(layero, index) {
						//var body = layer.getChildFrame('.ico-list', index);
						//console.log(layero, index);
					}
				});
			});
			validateRule();
		})
	</script>
</body>

</html>
