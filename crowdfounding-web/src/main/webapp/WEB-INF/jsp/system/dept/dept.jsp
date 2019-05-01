<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>crowdfounding 部门</title>
<meta name="keywords" content="wayn,基于H+,后台HTML,响应式后台">
<meta name="description"
	content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">
<%@ include file="/commom/taglib.jsp"%>
<%@ include file="/commom/header.jsp"%>
<link
	href="${_ctx }/static/plugin/jqTreeGrid/jquery.treegrid.css"
	rel="stylesheet">
<!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content">
		<div class="col-sm-12">
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
	<%@ include file="/commom/footer.jsp" %>
	<script
		src="${_ctx }/static/plugin/jqTreeGrid/jquery.treegrid.min.js"></script>
	<script
		src="${_ctx }/static/plugin/jqTreeGrid/jquery.treegrid.extension.js"></script>
	<script>
		let prefix =  _ctx + '/system/dept';
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
		                    layer.msg("删除成功");
		                    reload();
		                }
		            }
		        });
		    })
		}
		
		// tree表格树 展开/折叠
		let expandFlag;
		function expandAllOrCollapseAll(){
			expandFlag = expandFlag ? expandFlag : false;
		    if (!expandFlag) {
		        $('#table1').bootstrapTreeTable('expandAll');
		    } else {
		        $('#table1').bootstrapTreeTable('collapseAll');
		    }
		    expandFlag = expandFlag ? false: true;
		}
		
		let load = function(){
			$('#table1').bootstrapTreeTable({
				id: 'id',
	            code: 'id',
	            parentCode: 'pid',
	            type: "POST", // 请求数据的ajax类型
	            url: prefix + '/list?_r=' + Math.random(), // 请求数据的ajax的url
	            ajaxParams: {}, // 请求数据的ajax的data属性
	            expandColumn: '1',// 在哪一列上面显示展开按钮
	            striped: true, // 是否各行渐变色
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
	                    field: 'deptDesc'
	                },
	                {
	                    title: '操作',
	                    field: 'id',
	                    align: 'center',
	                    valign: 'center',
	                    formatter: function (item, index) {
	                        var e = '<a class="btn btn-primary btn-sm '
	                            + s_edit_h
	                            + '" href="#" mce_href="#" title="编辑" onclick="edit(\''
	                            + item.id
	                            + '\')"><i class="fa fa-edit"></i></a> ';
	                        var p = '<a class="btn btn-primary btn-sm '
	                            + s_add_h
	                            + '" href="#" mce_href="#" title="添加下级" onclick="add(\''
	                            + item.id
	                            + '\')"><i class="fa fa-plus"></i></a> ';
	                        var d = '<a class="btn btn-warning btn-sm '
	                            + s_remove_h
	                            + '" href="#" title="删除"  mce_href="#" onclick="remove(\''
	                            + item.id
	                            + '\')"><i class="fa fa-remove"></i></a> ';
	                        return e + d + p;
	                    }
	                }
				]
			});
		}
		
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
