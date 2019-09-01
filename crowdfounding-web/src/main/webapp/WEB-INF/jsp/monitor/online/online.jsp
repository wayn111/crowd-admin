<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>crowdfounding 在线用户</title>
<meta name="keywords" content="wayn,基于H+,后台HTML,响应式后台">
<meta name="description"
	content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">
<%@ include file="/commom/taglib.jsp"%>
<%@ include file="/commom/header.jsp"%>
<!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content">
		<div class="col-sm-12 select-table">
			<div class="ibox">
				<div class="ibox-body">
					<div id="exampleToolbar" role="group" class="t-bar">
						<shiro:hasPermission name="monitor:online:logout">
							<button type="button"
								class="btn btn-primary" title="在根节点下添加菜单" onclick="batchForceLogout()">
								<i class="fa fa-trash" aria-hidden="true"></i>强退
							</button>
						</shiro:hasPermission>
					</div>
					<table id="table1" data-mobile-responsive="true">
					</table>
				</div>
			</div>
		</div>
		<!--shiro控制bootstraptable行内按钮看见性 来自bootdo的创新方案 -->
		<div>
			<script type="text/javascript">
				var s_logout = 'hidden';
			</script>
			<shiro:hasPermission name="monitor:online:logout">
				<script>
					s_logout = '';
				</script>
			</shiro:hasPermission>
		</div>
	</div>
	<%@ include file="/commom/footer.jsp"%>
	<script>
		var prefix = _ctx + '/monitor/online';

		function load() {
			$('#table1').bootstrapTable(
					{
						method : 'post', // 服务器数据的请求方式 get or post
						cache: false,
						url : prefix + "/list", // 服务器数据的加载地址
						showRefresh : true, //显示刷新按钮
						showToggle : true, //show the toggle button to toggle table / card view.
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : false, // 设置为true会在底部显示分页条
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						silent : true, //静默刷新数据
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						//search : true, // 是否显示搜索框
						showColumns : true, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
						dataField : "records",
						sortName : 'createTime',
						sortOrder : 'desc',
						clickToSelect: true,
						queryParamsType : "",//If queryParamsType = 'limit', 
						//the params object contains: limit, offset, search, sort, order.
						//Else, it contains: pageSize, pageNumber, searchText, sortName, sortOrder.
						queryParams : function(params) {
							return params;
						},
						// //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
						// queryParamsType = 'limit' ,返回参数必须包含
						// limit, offset, search, sort, order 否则, 需要包含:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// 返回false将会终止请求
						columns : [
							{
								checkbox : true
							},
							{
								field : 'id', // 列字段名
								title : '会话id' // 列标题
							},
							{
								field : 'username',
								title : '用户名'
							},
							{
								field : 'host',
								title : '主机'
							},
							{
								field : 'os',
								title : '操作系统'
							},
							{
								field : 'browser',
								title : '浏览器'
							},
							{
								field : 'startTimestamp',
								title : '登录时间'
							},
							{
								field : 'lastAccessTime',
								title : '最后访问时间'
							},
							{
								field : 'timeout',
								title : '过期时间'
							},
							{
								field : 'status',
								title : '状态',
								align : 'center',
								formatter : function(value, row, index) {
									if (value == 'on_line') {
										return '<span class="label label-success">在线</span>';
									} else if (value == 'off_line') {
										return '<span class="label label-primary">离线</span>';
									}
								}
							},
							{
								title : '操作',
								field : 'id',
								align : 'center',
								formatter : function(value, row, index) {
									var d = '<a class="'+s_logout+' btn btn-warning btn-sm" href="#" title="强退"  mce_href="#" onclick="forceLogout(\''
											+ row.id
											+ '\')"><i class="fa fa-remove"></i>强退</a> ';
									return d;
								}
							} ]
					});
		}

		function forceLogout(id) {
			layer.confirm('确定要强制该用户下线吗？', {
				btn : [ '确定', '取消' ]
			}, function() {
				$.ajax({
					url : prefix+"/forceLogout/" + id,
					type : "delete",
					data : {
						'id' : id
					},
					success : function(r) {
						if (r.code == 100) {
							layer.msg(r.msg, {icon: 1});
							reload();
						} else {
							layer.alert(r.msg);
						}
					}
				});
			})
		}
		
		function batchForceLogout() {
			var rows = $('#table1').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
			if (rows.length == 0) {
				layer.msg("请选择要下线的用户", {icon: 6});
				return;
			}
			layer.confirm("确认要强制下线选中的'" + rows.length + "'个用户吗?", {
				btn : [ '确定', '取消' ]
			// 按钮
			}, function() {
				var ids = new Array();
				// 遍历所有选择的行数据，取每条数据对应的ID
				$.each(rows, function(i, row) {
					ids[i] = row['id'];
				});
				$.ajax({
					type : 'POST',
					data : {
						"ids" : ids
					},
					url : prefix + '/batchForceLogout',
					success : function(data) {
						if (data.code != 100) {
							layer.alert(data.msg);
						} else {
							layer.msg(data.msg, {icon: 1});
							reload();
						}
					}
				});
			}, function() {
			});
		}
		
		function reload() {
			$('#table1').bootstrapTable("refresh");
		}

		$(function() {
			load();
		})
	</script>
</body>
<!-- Mirrored from www.zi-han.net/theme/hplus/login.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:18:23 GMT -->
</html>
