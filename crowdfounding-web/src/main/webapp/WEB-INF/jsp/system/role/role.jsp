<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>crowdfounding 角色</title>
    <meta name="keywords" content="wayn,基于H+,后台HTML,响应式后台">
    <meta name="description"
          content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">
    <%@ include file="/commom/taglib.jsp" %>
    <%@ include file="/commom/header.jsp" %>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="col-sm-12 search-collapse">
        <form class="form-inline" id="roleSelect">
            <div class="form-group">
                <label for="roleName">角色名称</label>
                <input type="text" class="form-control" id="roleName" name="roleName">
            </div>
            <div class="form-group magin-left10">
                <label for="roleState">角色状态</label>
                <select
                        class="js-example-basic-single" id="roleState" name="roleState">
                </select>
            </div>
            <div class="form-group magin-left10 select-time">
                <label for="startTime">创建时间</label>
                <input type="text" class="form-control wayn-width-105" id="startTime" name="startTime"
                       placeholder="开始时间"/>
                <span>-</span>
                <input type="text" class="form-control wayn-width-105" id="endTime" name="endTime" placeholder="结束时间"/>
            </div>
            <a class="btn btn-primary btn-rounded btn-sm magin-left10" onclick="reload()">
                <i class="fa fa-search"></i>&nbsp;搜索
            </a>
            <a class="btn btn-warning btn-rounded btn-sm" onclick="selectReset()">
                <i class="fa fa-refresh"></i>&nbsp;重置
            </a>
        </form>
    </div>
    <div class="col-sm-12 select-table">
        <div class="ibox">
            <div class="ibox-body">
                <div id="exampleToolbar" role="group">
                    <shiro:hasPermission name="sys:role:add">
                        <button type="button" class="btn btn-primary" title="在根节点下添加菜单"
                                onclick="add('0')">
                            <i class="fa fa-plus" aria-hidden="true"></i>添加
                        </button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="sys:role:remove">
                        <button type="button" class="btn btn-danger"
                                onclick="batchRemove()">
                            <i class="fa fa-trash" aria-hidden="true"></i>删除
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
            let s_edit_h = 'hidden';
            let s_remove_h = 'hidden';
        </script>
        <shiro:hasPermission name="sys:role:edit">
            <script>
                s_edit_h = '';
            </script>
        </shiro:hasPermission>
        <shiro:hasPermission name="sys:role:remove">
            <script>
                s_remove_h = '';
            </script>
        </shiro:hasPermission>
    </div>
</div>
<%@ include file="/commom/footer.jsp" %>
<script>
    let prefix = _ctx + '/system/role';

    function add() {
        layer.open({
            type: 2,
            title: '增加角色',
            maxmin: true,
            shadeClose: false, // 点击遮罩关闭层
            area: ['800px', '520px'],
            content: prefix + '/add' // iframe的url
        });
    }

    function edit(id) {
        layer.open({
            type: 2,
            title: '修改角色',
            maxmin: true,
            shadeClose: false, // 点击遮罩关闭层
            area: ['800px', '520px'],
            content: prefix + '/edit?id=' + id + '&_r=' + Math.random()// iframe的url
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
                        layer.msg(data.msg);
                        reload();
                    }
                }
            });
        })
    }

    function batchRemove() {
        let rows = $('#table1').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
        if (rows.length == 0) {
            layer.msg("请选择要删除的数据");
            return;
        }
        layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
            btn: ['确定', '取消']
        }, function () {
            let ids = new Array();
            $.each(rows, function (i, row) {
                ids[i] = row['id'];
            });
            console.log(ids);
            $.ajax({
                type: 'POST',
                data: {
                    "ids": ids
                },
                //traditional:true,
                url: prefix + '/batchRemove',
                success: function (data) {
                    if (data.code != 100) {
                        layer.alert(data.msg);
                    } else {
                        reload();
                        layer.msg(data.msg);
                    }
                }
            });
        }, function () {
        });
    }

    function load() {
        $('#table1').bootstrapTable(
            {
                method: 'post', // 服务器数据的请求方式 get or post
                url: prefix + "/list?_r=" + Math.random(), // 服务器数据的加载地址
                showRefresh: true, //显示刷新按钮
                showToggle: true, //show the toggle button to toggle table / card view.
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                silent: true, //静默刷新数据
                contentType: "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns: true, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                dataField: "records",
                sortName: 'createTime',
                sortOrder: 'desc',
                onClickRow: rowClickSelect,
                queryParamsType: "",//If queryParamsType = 'limit',
                //the params object contains: limit, offset, search, sort, order.
                //Else, it contains: pageSize, pageNumber, searchText, sortName, sortOrder.
                queryParams: function (params) {
                    params.roleName = $('#roleName').val().trim();
                    params.roleState = $('#roleState').val();
                    params.startTime = $('#startTime').val();
                    params.endTime = $('#endTime').val();
                    return params;
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns: [
                    { // 列配置项
                        // 数据类型，详细参数配置参见文档http://bootstrap-table.wenzhixin.net.cn/zh-cn/documentation/
                        checkbox: true
                        // 列表中显示复选框
                    },
                    {
                        field: 'id', // 列字段名
                        width: '16%',
                        title: '序号' // 列标题
                    },
                    {
                        field: 'roleName',
                        title: '角色名',
                        sortable: true
                    },
                    {
                        field: 'roleState',
                        title: '角色状态',
                        formatter: function (value, row, index) {
                            if (value === 1) {
                                return '<span class="badge badge-primary">启动</span>';
                            }
                            if (value === -1) {
                                return '<span class="badge badge-danger">禁用</span>';
                            }
                        }
                    },
                    {
                        field: 'createTime',
                        title: '创建时间',
                        width: '10%',
                        sortable: true
                    },
                    {
                        field: '',
                        title: '权限'
                    },
                    {
                        field: 'remarks',
                        title: '角色备注',
                        width: '15%'
                    },
                    {
                        title: '操作',
                        field: 'roleId',
                        align: 'center',
                        formatter: function (value, row, index) {
                            let e = '<a class="btn btn-primary btn-sm ' + s_edit_h
                                + '" href="#" mce_href="#" title="编辑" onclick="edit(\'' + row.id
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            let d = '<a class="btn btn-warning btn-sm ' + s_remove_h
                                + '" href="#" title="删除"  mce_href="#" onclick="remove(\'' + row.id
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            return e + d;
                        }
                    }]
            });
    }

    function reload() {
        $('#table1').bootstrapTable("refresh");
    }

    $(function () {
        let config = {
            data: JSON.parse('${states}'),
            width: '150px'
        };
        select2Init('.js-example-basic-single', config);
        load();
    })
</script>
</body>
<!-- Mirrored from www.zi-han.net/theme/hplus/login.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:18:23 GMT -->
</html>
