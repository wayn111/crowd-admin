<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>crowd-admin 字典数据</title>
    <meta name="keywords" content="crowd-admin是一个后台权限管理系统脚手架，集成了rbac权限管理、消息推送、邮件发送、任务调度、代码生成、elfinder文件管理等常用功能，系统内各个业务按照模块划分，前台使用H+模板。是一个java新人易于上手，学习之后能够快速融入企业开发的指导项目">
    <meta name="description"
          content="design by wayn">
    <%@ include file="/commom/taglib.jsp" %>
    <%@ include file="/commom/header.jsp" %>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content ui-layout-center gray-bg">
    <div class="col-sm-12 search-collapse">
        <form class="form-inline">
            <div class="form-group">
                <label for="dictType">字典名称</label>
                <select
                        class="js-example-basic-single" id="dictType">
                </select>
            </div>
            <div class="form-group">
                <label for="name">标签名</label>
                <input type="text"
                       class="form-control" id="name">
            </div>
            <a class="btn btn-primary btn-rounded btn-sm"
               onclick="reload()"><i class="fa fa-search"></i>&nbsp;搜索</a>
            <a
                    class="btn btn-warning btn-rounded btn-sm" onclick="selectReset()"><i
                    class="fa fa-refresh"></i>&nbsp;重置</a>
        </form>
    </div>
    <div class="col-sm-12 select-table">
        <div class="ibox">
            <div class="ibox-body">
                <div id="exampleToolbar" role="group">
                    <shiro:hasPermission name="commom:dict:add">
                        <button type="button" class="btn  btn-primary" onclick="add()">
                            <i class="fa fa-plus" aria-hidden="true"></i>添加
                        </button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="commom:dict:remove">
                        <button type="button" class="btn  btn-danger"
                                onclick="batchRemove()">
                            <i class="fa fa-trash" aria-hidden="true"></i>删除
                        </button>
                    </shiro:hasPermission>
                    <button type="button" class="btn  btn-info"
                            onclick="activeTabClose()">
                        <i class="fa fa-reply-all" aria-hidden="true"></i>关闭
                    </button>
                </div>
                <table id="exampleTable" data-mobile-responsive="true">
                </table>
            </div>
        </div>
    </div>
    <!--shiro控制bootstraptable行内按钮看见性 来自bootdo的创新方案 -->
    <div>
        <script type="text/javascript">
            var s_edit_h = 'hidden';
            var s_remove_h = 'hidden';
        </script>
        <shiro:hasPermission name="commom:dict:edit">
            <script>
                s_edit_h = '';
            </script>
        </shiro:hasPermission>
        <shiro:hasPermission name="commom:dict:remove">
            <script>
                s_remove_h = '';
            </script>
        </shiro:hasPermission>
    </div>
</div>
<%@ include file="/commom/footer.jsp" %>
<script>
    var prefix = _ctx + "/common/dict/data";

    var dictType;

    function load() {
        $('#exampleTable').bootstrapTable(
            {
                method: 'post', // 服务器数据的请求方式 get or post
                cache: false,
                url: prefix + "/list", // 服务器数据的加载地址
                showRefresh: true, //显示刷新按钮
                showToggle: true, //show the toggle button to toggle table / card view.
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                silent: true, //静默刷新数据
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                contentType: 'application/x-www-form-urlencoded',
                // //发送到服务器的数据编码类型
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                // search : true, // 是否显示搜索框
                showColumns: true, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者
                dataField: "records",
                sortName: 'sort',
                sortOrder: 'asc',
                clickToSelect: true,
                // "server"
                queryParamsType: "",//If queryParamsType = 'limit',
                //the params object contains: limit, offset, search, sort, order.
                //Else, it contains: pageSize, pageNumber, searchText, sortName, sortOrder.
                queryParams: function (params) {
                    params.name = $('#name').val();
                    params.dictType = $('#dictType').val();
                    params.type = 2;
                    return params;
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'id',
                        title: '字典主键'
                    },
                    {
                        field: 'name',
                        title: '标签名'
                    },
                    {
                        field: 'value',
                        title: '标签值'
                    }, {
                        field: 'dictType',
                        title: '字典类型'
                    }, {
                        field: 'dictState',
                        title: '字典状态',
                        formatter: function (value, row, index) {
                            if (value == '-1') {
                                return '<span class="badge badge-danger">禁用</span>';
                            } else if (value == '1') {
                                return '<span class="badge badge-primary">正常</span>';
                            }
                        }
                    },
                    {
                        field: 'sort',
                        title: '字典排序',
                        width: '8%',
                        sortable: true
                    },
                    {
                        field: 'remarks',
                        title: '描述'
                    },
                    {
                        title: '操作',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a  class="btn btn-primary btn-sm ' + s_edit_h
                                + '" href="#" title="编辑" onclick="edit(\'' + row.id
                                + '\')"><i class="fa fa-edit "></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm ' + s_remove_h
                                + '" href="#" title="删除" onclick="remove(\'' + row.id
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            return e + d;
                        }
                    }]
            });
    }

    function reload() {
        dictType = $('#dictType').val();
        $('#exampleTable').bootstrapTable('refresh');
    }

    function add() {
        // iframe层
        layer.open({
            type: 2,
            title: '增加字典数据',
            maxmin: true,
            shadeClose: false, // 点击遮罩关闭层
            area: ['800px', '520px'],
            content: prefix + '/add'
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
                        layer.msg(data.msg, {icon: 1});
                        reload();
                    }
                }
            });
        })
    }

    function edit(id) {
        layer.open({
            type: 2,
            title: '字典数据修改',
            maxmin: true,
            shadeClose: false,
            area: ['800px', '520px'],
            content: prefix + '/edit/' + id // iframe的url
        });
    }

    function batchRemove() {
        var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
        if (rows.length == 0) {
            layer.msg("请选择要删除的数据", {icon: 6});
            return;
        }
        layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
            btn: ['确定', '取消']
            // 按钮
        }, function () {
            var ids = new Array();
            // 遍历所有选择的行数据，取每条数据对应的ID
            $.each(rows, function (i, row) {
                ids[i] = row['id'];
            });
            $.ajax({
                type: 'POST',
                data: {
                    "ids": ids
                },
                url: prefix + '/batchRemove',
                success: function (data) {
                    if (data.code != 100) {
                        layer.alert(data.msg);
                    } else {
                        layer.msg(data.msg, {icon: 1});
                        reload();
                    }
                }
            });
        }, function () {
        });
    }

    $(function () {
        var config = {
            data: JSON.parse('${dicts}'),
            width: '150px'
        };
        select2Init('.js-example-basic-single', config);
        dictType = $('#dictType').val();
        load();
    });
</script>
</body>
<!-- Mirrored from www.zi-han.net/theme/hplus/login.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:18:23 GMT -->
</html>
