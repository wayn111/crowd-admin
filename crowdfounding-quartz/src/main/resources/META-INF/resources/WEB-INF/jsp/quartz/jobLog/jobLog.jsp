<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>crowdfounding 定时任务日志</title>
    <meta name="keywords" content="crowdfounding,基于H+,后台HTML,响应式后台">
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
        <form class="form-inline" id="jobLogSelect">
            <input type="hidden" id="deptId" name="deptId">
            <div class="form-group">
                <label for="jobName">任务名称</label>
                <input type="text" class="form-control" id="jobName" name="jobName">
            </div>
            <div class="form-group margin-left10">
                <label for="jobState">执行状态</label>
                <select class="js-example-basic-single" name="jobState" id="jobState">
                </select>
            </div>
            <div class="form-group margin-left10 select-time">
                <label for="startTime">创建时间</label>
                <input type="text" class="form-control wayn-width-105" id="startTime" name="startTime"
                       placeholder="开始时间"/>
                <span>-</span>
                <input type="text" class="form-control wayn-width-105" id="endTime" name="endTime"
                       placeholder="结束时间"/>
            </div>
            <a class="btn btn-primary btn-rounded btn-sm margin-left10"
               onclick="reload()"><i class="fa fa-search"></i>&nbsp;搜索
            </a>
            <a class="btn btn-warning btn-rounded btn-sm"
               onclick="selectReset()">
                <i class="fa fa-refresh"></i>&nbsp;重置
            </a>
        </form>
    </div>
    <div class="col-sm-12 select-table">
        <div class="ibox">
            <div class="ibox-body">
                <div id="exampleToolbar" role="group" class="t-bar">
                    <shiro:hasPermission name="quartz:jobLog:remove">
                        <button type="button" class="btn  btn-danger"
                                onclick="batchRemove()">
                            <i class="fa fa-trash" aria-hidden="true"></i>删除
                        </button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="quartz:jobLog:list">
                        <button type="button" class="btn  btn-success"
                                onclick="exportExcel()">
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
            var s_remove_h = 'hiddin';
        </script>
        <shiro:hasPermission name="quartz:jobLog:remove">
            <script type="text/javascript">
                s_remove_h = '';
            </script>
        </shiro:hasPermission>
    </div>
</div>
<%@ include file="/commom/footer.jsp" %>
<script>
    var prefix = _ctx + '/quartz/jobLog';

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
                        params.jobName = $('#jobName').val();
                        params.jobState = $('#jobState').val();
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
                            field: 'jobName', // 列字段名
                            title: '任务名称' // 列标题
                        }, {
                            field: 'jobGroup', // 列字段名
                            title: '任务组名', // 列标题
                            formatter: function (value, row, index) {
                                if (value == 'system') {
                                    return '系统';
                                } else if (value == 'default') {
                                    return '默认';
                                }
                            }
                        },
                        {
                            field: 'invokeTarget',
                            title: '调用目标字符串',
                        },
                        {
                            field: 'jobMessage',
                            title: '日志信息'
                        },
                        {
                            field: 'jobState',
                            title: '执行状态',
                            width: '5%',
                            sortable: true,
                            formatter: function (value, row, index) {
                                if (value == '-1') {
                                    return '<span class="badge badge-danger">失败</span>';
                                } else if (value == '1') {
                                    return '<span class="badge badge-primary">成功</span>';
                                }
                            }
                        },
                        {
                            field: 'createTime',
                            width: '10%',
                            title: '创建时间',
                            sortable: true
                        },
                        {
                            title: '操作',
                            field: 'id',
                            align: 'center',
                            formatter: function (value, row, index) {
                                var r = '<a class="btn btn-warning btn-sm ' + s_remove_h
                                    + '" href="#" title="删除"  mce_href="#" onclick="remove(\'' + row.id
                                    + '\')"><i class="fa fa-play-circle-o"></i>删除</a> ';
                                return r;
                            }
                        }]
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

    function exportExcel() {
        layer.confirm("确认要导出所有操作日志数据吗?", {
            btn: ['确定', '取消']
            // 按钮
        }, function () {
            exportData(prefix + '/export', 'jobLogSelect', '任务日志列表.xls');
        }, function () {
        });
    }

    function batchRemove() {
        var rows = $('#table1').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
        if (rows.length == 0) {
            layer.msg("请选择要删除的数据", {icon: 6});
            return;
        }
        layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
            btn: ['确定', '取消']
            // 按钮
        }, function () {
            var ids = [];
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
        select2Init('select[name="jobState"]', config);
        load();
    })
</script>
</body>
<!-- Mirrored from www.zi-han.net/theme/hplus/login.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:18:23 GMT -->
</html>
