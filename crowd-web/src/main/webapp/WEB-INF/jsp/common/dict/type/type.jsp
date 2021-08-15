<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>crowd-admin 字典类型</title>
    <meta name="keywords" content="crowd-admin是一个后台权限管理系统脚手架，集成了rbac权限管理、消息推送、邮件发送、任务调度、代码生成、elfinder文件管理等常用功能，系统内各个业务按照模块划分，前台使用H+模板。是一个java新人易于上手，学习之后能够快速融入企业开发的指导项目">
    <meta name="description"
          content="design by wayn">
    <%@ include file="/common/taglib.jsp" %>
    <%@ include file="/common/header.jsp" %>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content ui-layout-center gray-bg">
    <div class="col-sm-12 search-collapse">
        <form class="form-inline">
            <div class="form-group">
                <label for="name">名称</label>
                <input type="text" class="form-control typeahead" data-provide="typeahead" id="name">
            </div>
            <div class="form-group select-time">
                <label for="startTime">创建时间</label>
                <input type="text" class="form-control wayn-width-105" id="startTime" name="startTime"
                       placeholder="开始时间"/>
                <span>-</span>
                <input type="text" class="form-control wayn-width-105" id="endTime" name="endTime" placeholder="结束时间"/>
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
                    <shiro:hasPermission name="common:dict:add">
                        <button type="button" class="btn  btn-primary" onclick="add()">
                            <i class="fa fa-plus" aria-hidden="true"></i>添加
                        </button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="common:dict:remove">
                        <button type="button" class="btn  btn-danger"
                                onclick="batchRemove()">
                            <i class="fa fa-trash" aria-hidden="true"></i>删除
                        </button>
                    </shiro:hasPermission>
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
        <shiro:hasPermission name="common:dict:edit">
            <script>
                s_edit_h = '';
            </script>
        </shiro:hasPermission>
        <shiro:hasPermission name="common:dict:remove">
            <script>
                s_remove_h = '';
            </script>
        </shiro:hasPermission>
    </div>
</div>
<%@ include file="/common/footer.jsp" %>
<script
        src="${_ctx }/static/plugin/bootstrap-ypeahead/bootstrap3-typeahead.min.js"></script>
<script>
    var prefix = _ctx + "/common/dict/type";

    function load(deptId) {
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
                    params.startTime = $('#startTime').val();
                    params.endTime = $('#endTime').val();
                    params.type = 1;
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
                        title: '字典名称'
                    },
                    {
                        field: 'value',
                        title: '字典类型',
                        formatter: function (value, row, index) {
                            return '<a href="#" onclick="dictData(\'' + row.value + '\')">' + row.value + '</a>';
                        }
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
                        sortable: true
                    },
                    {
                        field: 'remarks',
                        title: '描述',
                    },
                    {
                        title: '字典数据',
                        formatter: function (value, row, index) {
                            return '<button type="button" class="btn btn-sm btn-info" onclick="dictData(\'' + row.value + '\')">' +
                                '<i class="fa fa-server" aria-hidden="true"></i>列表</button>';
                        }
                    },
                    {
                        field: 'createTime',
                        title: '创建时间',
                        sortable: true
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

    function dictData(value) {
        menuItemCreate(_ctx + '/common/dict/data/' + value, '字典数据');
    }

    function reload() {
        $('#exampleTable').bootstrapTable('refresh');
    }

    function add() {
        // iframe层
        layer.open({
            type: 2,
            title: '增加字典分类',
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
            title: '字典分类修改',
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
        //预处理输入
        $('.typeahead').typeahead({
            source: [
                {id: "someId1", name: '状态'},
                {id: "someId2", name: '类型'}
            ],
            autoSelect: true
        });
        load();
    });
</script>
</body>
<!-- Mirrored from www.zi-han.net/theme/hplus/login.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:18:23 GMT -->
</html>
