<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>crowd-admin 系统日志</title>
    <meta name="keywords" content="crowd-admin,基于H+,后台HTML,响应式后台">
    <meta name="description"
          content="design by wayn">
    <%@ include file="/commom/taglib.jsp" %>
    <%@ include file="/commom/header.jsp" %>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="col-sm-12 search-collapse">
        <form class="form-inline" id="logSelect">
            <input type="hidden" id="deptId" name="deptId">
            <div class="form-group">
                <label for="userName">操作用户</label>
                <input type="text" class="form-control wayn-width-105" id="userName" name="userName">
            </div>
            <div class="form-group  margin-left10">
                <label for="userName">模块名称</label>
                <input type="text" class="form-control wayn-width-105" id="moduleName" name="moduleName">
            </div>
            <div class="form-group  margin-left10">
                <label for="userName">IP</label>
                <input type="text" class="form-control wayn-width-105" id="ip" name="ip">
            </div>
            <div class="form-group margin-left10">
                <label for="operState">操作类型</label>
                <select
                        class="js-example-basic-single" id="operation" name="operation">
                </select>
            </div>
            <div class="form-group margin-left10">
                <label for="operState">操作状态</label>
                <select
                        class="js-example-basic-single" id="operState" name="operState">
                </select>
            </div>
            <div class="form-group margin-left10 select-time">
                <label for="startTime">操作时间</label>
                <input type="text" class="form-control wayn-width-105" id="startTime" name="startTime"
                       placeholder="开始时间"/>
                <span>-</span>
                <input type="text" class="form-control wayn-width-105" id="endTime" name="endTime" placeholder="结束时间"/>
            </div>
            <a class="btn btn-primary btn-rounded btn-sm margin-left10" onclick="reload()">
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
                <div id="exampleToolbar" role="group" class="t-bar">
                    <shiro:hasPermission name="sys:log:remove">
                        <button type="button" class="btn btn-danger" onclick="batchRemove()">
                            <i class="fa fa-trash" aria-hidden="true"></i>删除
                        </button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="sys:log:log">
                        <button type="button" class="btn btn-success" onclick="exportExcel()">
                            <i class="fa fa-arrow-circle-down" aria-hidden="true"></i>导出
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
            var s_remove = 'hidden';
        </script>
        <shiro:hasPermission name="sys:log:remove">
            <script>
                s_remove = '';
            </script>
        </shiro:hasPermission>
    </div>
</div>
<%@ include file="/commom/footer.jsp" %>
<script>
    var prefix = _ctx + '/system/log';

    function load() {
        $('#table1')
            .bootstrapTable(
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
                    clickToSelect: true,
                    queryParamsType: "",//If queryParamsType = 'limit',
                    //the params object contains: limit, offset, search, sort, order.
                    //Else, it contains: pageSize, pageNumber, searchText, sortName, sortOrder.
                    queryParams: function (params) {
                        params.userName = $('#userName').val();
                        params.moduleName = $('#moduleName').val();
                        params.ip = $('#ip').val();
                        params.operation = $('#operation').val();
                        params.operState = $('#operState').val();
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
                        {
                            checkbox: true
                        },
                        {
                            title: '序号',
                            align: 'center',
                            formatter: function (value, row, index) {
                                //return index + 1;
                                var pageSize = $('#table1').bootstrapTable('getOptions').pageSize;//通过表的#id 可以得到每页多少条
                                var pageNumber = $('#table1').bootstrapTable('getOptions').pageNumber;//通过表的#id 可以得到当前第几页
                                return pageSize * (pageNumber - 1) + index + 1;//返回每条的序号： 每页条数 * （当前页 - 1 ）+ 序号
                            }
                        },
                        {
                            field: 'userName', // 列字段名
                            title: '操作用户' // 列标题
                        },
                        {
                            field: 'moduleName',
                            title: '模块名称'
                        },
                        {
                            field: 'operation',
                            title: '操作类型'
                        },
                        {
                            field: 'operState',
                            title: '操作状态',
                            formatter: function (value, row, index) {
                                if (value == '-1') {
                                    return '<span class="badge badge-danger">失败</span>';
                                } else if (value == '1') {
                                    return '<span class="badge badge-primary">成功</span>';
                                }
                            }
                        },
                        {
                            field: 'ip',
                            title: 'ip'
                        },
                        {
                            field: 'url',
                            title: '地址'
                        },

                        {
                            field: 'createTime',
                            title: '操作时间',
                            sortable: true
                        },
                        {
                            title: '操作',
                            field: 'id',
                            align: 'center',
                            formatter: function (value, row, index) {
                                var r = '<a class="'
                                    + s_remove
                                    + ' btn btn-warning btn-sm" href="#" title="删除" onclick="remove(\''
                                    + row.id + '\')"><i class="fa fa-remove"></i>删除</a> ';
                                var d = '<a class="'
                                    + ' btn btn-info btn-sm" href="#" title="详情" onclick="detail(\''
                                    + row.id + '\')"><i class="fa fa-info-circle"></i>详情</a> ';
                                return d + r;
                            }
                        }]
                });
    }

    function detail(id) {
        layer.open({
            type: 2,
            title: '详情',
            maxmin: true,
            shadeClose: false, // 点击遮罩关闭层
            area: ['800px', '600px'],
            content: prefix + '/detail/' + id
        });
    }

    function remove(id) {
        layer.confirm('确定要删除选中的记录吗？', {
            btn: ['确 定', '取 消']
        }, function () {
            $.ajax({
                url: prefix + "/remove/" + id,
                type: "delete",
                data: {
                    'id': id
                },
                success: function (r) {
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

    function exportExcel() {
        layer.confirm("确认要导出所有日志数据吗?", {
            btn: ['确定', '取消']
            // 按钮
        }, function () {
            exportData(prefix + '/export', 'logSelect', '日志列表.xls');
        }, function () {
        });
    }

    function batchRemove() {
        var rows = $('#table1').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
        if (rows.length == 0) {
            layer.msg("请选择要删除的日志", {icon: 6});
            return;
        }
        layer.confirm("确认要强制下线选中的'" + rows.length + "'个日志吗?", {
            btn: ['确 定', '取 消']
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

    function reload() {
        $('#table1').bootstrapTable("refresh");
    }

    $(function () {
        var config = {
            data: [{
                id: '',
                text: '全部'
            }, {
                id: 1,
                text: '成功'
            }, {
                id: -1,
                text: '失败'
            }],
            width: '80px',
            minimumResultsForSearch: -1
        };
        select2Init('select[name="operState"]', config);
        var optArr = ${operation};
        optArr.splice(0, 0, {
            id: '',
            text: '全部'
        });
        var config1 = {
            data: optArr,
            width: '100px'
        };
        select2Init('select[name="operation"]', config1);
        load();
    })
</script>
</body>
<!-- Mirrored from www.zi-han.net/theme/hplus/login.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:18:23 GMT -->
</html>
