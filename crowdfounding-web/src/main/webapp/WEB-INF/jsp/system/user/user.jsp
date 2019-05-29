<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>crowdfounding 用户</title>
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
		<div class="row">
			<div class="col-sm-2">
				<div class="ibox ibox-body">
					<div class="ibox-title">
						<h5>选择部门</h5>
					</div>
					<div class="ibox-content">
						<div id="jstree"></div>
					</div>
				</div>
			</div>
			<div class="col-sm-10">
				<div class="ibox">
					<div class="ibox-body">
						<div class="fixed-table-toolbar">
							<div class="columns pull-left">
								<shiro:hasPermission name="sys:user:add">
									<button type="button"
										class="btn  btn-primary" onclick="add()">
										<i class="fa fa-plus hidden" aria-hidden="true"></i>添加
									</button>
								</shiro:hasPermission>
								<shiro:hasPermission name="sys:user:remove">
									<button type="button"
										class="btn  btn-danger" onclick="batchRemove()">
										<i class="fa fa-trash hidden" aria-hidden="true"></i>删除
									</button>
								</shiro:hasPermission>
							</div>
							<div class="columns pull-right">
								<button class="btn btn-success" onclick="reload()">查询</button>
							</div>

							<div class="columns pull-right col-md-2 nopadding">
								<input id="searchName" type="text" class="form-control"
									placeholder="姓名">
							</div>
						</div>
						<table id="exampleTable" data-mobile-responsive="true">
						</table>
					</div>
				</div>
			</div>
		</div>
		<!--shiro控制bootstraptable行内按钮看见性 来自bootdo的创新方案 -->
		<div>
			<script type="text/javascript">
				var s_edit_h = 'hidden';
				var s_remove_h = 'hidden';
				var s_resetPwd_h = 'hidden';
			</script>
			<shiro:hasPermission name="sys:user:edit">
				<script>
					s_edit_h = '';
				</script>
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:user:remove">
				<script>
					s_remove_h = '';
				</script>
			</shiro:hasPermission>
			<shiro:hasPermission name="sys:user:resetPwd">
				<script>
					s_resetPwd_h = '';
				</script>
			</shiro:hasPermission>
		</div>
	</div>
	<%@ include file="/commom/footer.jsp"%>
	<script>
		let prefix = _ctx + "/system/user"
		
		function load(deptId) {
			$('#exampleTable')
				.bootstrapTable(
					{
						method : 'post', // 服务器数据的请求方式 get or post
						url : prefix + "/list", // 服务器数据的加载地址
						// showRefresh : true,
						// showToggle : true,
						// showColumns : true,
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						striped : true, // 设置为true会有隔行变色效果
						dataType : "json", // 服务器返回的数据类型
						pagination : true, // 设置为true会在底部显示分页条
						// queryParamsType : "limit",
						// //设置为limit则会发送符合RESTFull格式的参数
						singleSelect : false, // 设置为true将禁止多选
						// contentType : "application/x-www-form-urlencoded",
						// //发送到服务器的数据编码类型
						pageSize : 10, // 如果设置了分页，每页数据条数
						pageNumber : 1, // 如果设置了分布，首页页码
						// search : true, // 是否显示搜索框
						showColumns : false, // 是否显示内容下拉框（选择显示的列）
						sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者
						dataField : "records",
						// "server"
						queryParamsType : "",//If queryParamsType = 'limit', 
											 //the params object contains: limit, offset, search, sort, order.
											 //Else, it contains: pageSize, pageNumber, searchText, sortName, sortOrder.
						queryParams : function(params) {
							return {
								// 说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
								pageSize : params.pageSize,
								pageNumber : params.pageNumber,
								name : $('#searchName').val().trim(),
								deptId : deptId
							};
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
								field : 'id', 
								width : '23%',				
								title : '序号'
							},
							{
								field : 'userName',
								title : '用户名'
							},
							{
								field : 'createTime',
								title : '创建时间'
							},
							{
								field : 'userState',
								title : '状态',
								align : 'center',
								formatter : function(value, row, index) {
									if (value == '-1') {
										return '<span class="badge badge-danger">禁用</span>';
									} else if (value == '1') {
										return '<span class="badge badge-primary">正常</span>';
									}
								}
							},
							{
								title : '操作',
								field : 'userId',
								align : 'center',
								formatter : function(value, row, index) {
									var e = '<a  class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
										+ row.id
										+ '\')"><i class="fa fa-edit "></i></a> ';
									var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
										+ row.id
										+ '\')"><i class="fa fa-remove"></i></a> ';
									var f = '<a class="btn btn-success btn-sm ' + s_resetPwd_h + '" href="#" title="重置密码"  mce_href="#" onclick="resetPwd(\''
										+ row.id
										+ '\')"><i class="fa fa-key"></i></a> ';
									return e + d + f;
								}
							} ]
					});
		}
		function reload(deptId) {
			$('#exampleTable').bootstrapTable('refresh');
		}
		function add() {
			// iframe层
			layer.open({
				type : 2,
				title : '增加用户',
				maxmin : true,
				shadeClose : false, // 点击遮罩关闭层
				area : [ '800px', '520px' ],
				content : prefix + '/add'
			});
		}
		function remove(id) {
			layer.confirm('确定要删除选中的记录？', {
				btn : [ '确定', '取消' ]
			}, function() {
				$.ajax({
					url : prefix + "/remove/" + id,
					type : "delete",
					success : function(data) {
						if (data.code != 100) {
							layer.alert(data.msg);
						} else {
							layer.msg(data.msg);
							reload();
						}
					}
				});
			})
		}
		function edit(id) {
			layer.open({
				type : 2,
				title : '用户修改',
				maxmin : true,
				shadeClose : false,
				area : [ '800px', '520px' ],
				content : prefix + '/edit/' + id // iframe的url
			});
		}
		function resetPwd(id) {
			layer.open({
				type : 2,
				title : '重置密码',
				maxmin : true,
				shadeClose : false, // 点击遮罩关闭层
				area : [ '400px', '260px' ],
				content : prefix + '/resetPwd/' + id // iframe的url
			});
		}
		function batchRemove() {
			var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
			if (rows.length == 0) {
				layer.msg("请选择要删除的数据");
				return;
			}
			layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
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
					url : prefix + '/batchRemove',
					success : function(data) {
						if (data.code != 100) {
							layer.alert(data.msg);
						} else {
							layer.msg(data.msg);
							reload();
						}
					}
				});
			}, function() {});
		}
		function getTreeData() {
			$.ajax({
				type : "POST",
				url : _ctx + "/system/dept/tree",
				success : function(tree) {
					loadTree(tree);
				}
			});
		}
		function loadTree(tree) {
			let ref = $('#jstree').jstree({
				'core' : {
					'data' : tree,
					'themes' : {
						'name' : "proton"
					}
			
				},
				"plugins" : [ "search" ]
			});
			
		}
		
        $("#jstree").on("loaded.jstree", function (event, data) {
            // 展开所有节点
            $('#jstree').jstree(true).open_all();
        });
		
		$('#jstree').on("changed.jstree", function(e, data) {
			if (data.selected == -1) {
				var opt = {
					query : {
						deptId : '',
					}
				}
				$('#exampleTable').bootstrapTable('refresh', opt);
			} else {
				var opt = {
					query : {
						deptId : data.selected[0],
					}
				}
				$('#exampleTable').bootstrapTable('refresh',opt);
			}
		});
		
		
		$(function() {
			var deptId = '';
			getTreeData();
			load(deptId);
		});

	</script>
</body>
<!-- Mirrored from www.zi-han.net/theme/hplus/login.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:18:23 GMT -->
</html>
