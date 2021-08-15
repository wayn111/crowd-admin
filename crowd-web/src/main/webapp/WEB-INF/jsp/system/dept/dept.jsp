<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>crowd-admin 部门</title>
<meta name="keywords" content="crowd-admin是一个后台权限管理系统脚手架，集成了rbac权限管理、消息推送、邮件发送、任务调度、代码生成、elfinder文件管理等常用功能，系统内各个业务按照模块划分，前台使用H+模板。是一个java新人易于上手，学习之后能够快速融入企业开发的指导项目">
<meta name="description"
	content="design by wayn">
<%@ include file="/common/taglib.jsp"%>
<%@ include file="/common/header.jsp"%>
<link
	href="${_ctx }/static/plugin/jqTreeGrid/jquery.treegrid.css"
	rel="stylesheet">
<!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content">
		<div class="col-sm-12 search-collapse">
			<form class="form-inline" id="roleSelect">
				<div class="form-group">
					<label for="deptName">部门名称</label> <input type="text"
						class="form-control" id="deptName" name="deptName">
				</div>
				<a class="btn btn-primary btn-rounded btn-sm"
					onclick="reload()"><i class="fa fa-search"></i>&nbsp;搜索</a> <a
					class="btn btn-warning btn-rounded btn-sm" onclick="selectReset()"><i
					class="fa fa-refresh"></i>&nbsp;重置</a>
			</form>
		</div>
		<div class="col-sm-12 select-table">
			<div class="ibox">
				<div class="ibox-body">
					<div id="exampleToolbar" role="group" class="t-bar">
						<shiro:hasPermission name="sys:dept:add">
							<button type="button"
								class="btn btn-primary" title="在根节点下添加菜单" onclick="add('0')">
								<i class="fa fa-plus" aria-hidden="true"></i>添加
							</button>
						</shiro:hasPermission>
						<button type="button"
							class="btn btn-info" onclick="expandAllOrCollapseAll()">
							<i class="fa fa-exchange" aria-hidden="true"></i>展开/折叠
						</button>
					</div>
					<table id="table1" data-mobile-responsive="true">
					</table>
				</div>
			</div>
		</div>
		<!--shiro控制bootstraptable行内按钮看见性 来自bootdo的创新方案 -->
		<div>
			<script type="text/javascript">
				var s_add_h = 'hidden';
				var s_edit_h = 'hidden';
				var s_remove_h = 'hidden';
			</script>
			<shiro:hasPermission name="sys:dept:add">
				<script>
					s_add_h = '';
				</script>
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:dept:edit">
				<script>
					s_edit_h = '';
				</script>
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:dept:remove">
				<script>
					s_remove_h = '';
				</script>
			</shiro:hasPermission>
		</div>
	</div>
	<%@ include file="/common/footer.jsp" %>
	<script
		src="${_ctx }/static/plugin/jqTreeGrid/jquery.treegrid.min.js"></script>
	<script
		src="${_ctx }/static/plugin/jqTreeGrid/jquery.treegrid.extension.js"></script>
	<script>
		var prefix =  _ctx + '/system/dept';
		function add(pId) {
			layer.open({
		        type: 2,
		        title: '增加部门',
		        maxmin: true,
		        shadeClose: false, // 点击遮罩关闭层
		        area: ['800px', '520px'],
		        content: prefix + '/add/' + pId // iframe的url
		    });
		}

		function edit(id) {
			layer.open({
		        type: 2,
		        title: '修改部门',
		        maxmin: true,
		        shadeClose: false, // 点击遮罩关闭层
		        area: ['800px', '520px'],
		        content: prefix + '/edit/' + id // iframe的url
		    });
		}

		function remove(id) {
		    layer.confirm('确定要删除选中的记录？', {
		        btn: ['确定', '取消']
		    }, function () {
		        $.ajax({
		            url: prefix + "/remove/" + id,
		            type: "delete",
		            success: function (data) {
		                if (data.code != 100) {
		                    layer.alert(data.msg);
		                } else {
		                    layer.msg("删除成功", {icon: 1});
		                    reload();
		                }
		            }
		        });
		    })
		}

		// tree表格树 展开/折叠
		var expandFlag;
		function expandAllOrCollapseAll(){
			expandFlag = expandFlag ? expandFlag : false;
		    if (!expandFlag) {
		        $('#table1').bootstrapTreeTable('expandAll');
		    } else {
		        $('#table1').bootstrapTreeTable('collapseAll');
		    }
		    expandFlag = expandFlag ? false: true;
		}

		var load = function(){
			$('#table1').bootstrapTreeTable({
				id: 'id',
	            code: 'id',
	            parentCode: 'pid',
	            type: "POST", // 请求数据的ajax类型
				cache: false,
	            url: prefix + '/list', // 请求数据的ajax的url
	            ajaxParams: {
	            	deptName : $('#deptName').val().trim()
	            }, // 请求数据的ajax的data属性
	            expandColumn: '1',// 在哪一列上面显示展开按钮
	            striped: false, // 是否各行渐变色
	            bordered: true, // 是否显示边框
	            expandAll: false, // 是否全部展开
				columns :[
					{
	                    title: '编号',
	                    field: 'id',
	                    visible: false,
	                    align: 'center',
	                    valign: 'center',
	                    width: '5%'
	                },
	                {
	                    title: '名称',
	                    valign: 'center',
	                    field: 'deptName',
	                    width: '20%'
	                },
	                {
	                    title: '描述',
	                    valign: 'center',
	                    width: '20%',
	                    field: 'remarks'
	                },
					{
						title: '排序',
						valign: 'center',
						width: '5%',
						field: 'sort'
					},
	                {
	                    title: '操作',
	                    field: 'id',
	                    align: 'center',
						width: '15%',
	                    valign: 'center',
	                    formatter: function (item, index) {
	                        var e = '<a class="btn btn-primary btn-sm '
	                            + s_edit_h
	                            + '" href="#" title="编辑" onclick="edit(\''
	                            + item.id
	                            + '\')"><i class="fa fa-edit"></i>编辑</a> ';
	                        var p = '<a class="btn btn-primary btn-sm '
	                            + s_add_h
	                            + '" href="#" title="添加" onclick="add(\''
	                            + item.id
	                            + '\')"><i class="fa fa-plus"></i>添加</a> ';
	                        var d = '<a class="btn btn-warning btn-sm '
	                            + s_remove_h
	                            + '" href="#" title="删除" onclick="remove(\''
	                            + item.id
	                            + '\')"><i class="fa fa-remove"></i>删除</a> ';
	                        return e + d + p;
	                    }
	                }
				]
			});
		};

		function reload(){
			load();
		}

		$(function() {
			load();
		})
	</script>
</body>
<!-- Mirrored from www.zi-han.net/theme/hplus/login.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:18:23 GMT -->
</html>
