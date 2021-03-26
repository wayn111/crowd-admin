<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>crowd-admin 我的消息管理</title>
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
        <form class="form-inline" id="notifySelect">
            <input type="hidden" id="deptId" name="deptId">
            <div class="form-group">
                <label for="title">通知标题</label>
                <input type="text" class="form-control" id="title" name="title">
            </div>
            <div class="form-group">
                <label for="createBy">发布人</label>
                <select class="js-example-basic-single" name="createBy" id="createBy">
                </select>
            </div>
            <div class="form-group">
                <label for="createBy">状态</label>
                <select class="js-example-basic-single" name="read" id="read">
                </select>
            </div>
            <div class="form-group select-time">
                <label for="startTime">创建时间</label>
                <input type="text" class="form-control wayn-width-105" id="startTime" name="startTime"
                       placeholder="开始时间"/>
                <span>-</span>
                <input type="text" class="form-control wayn-width-105" id="endTime" name="endTime" placeholder="结束时间"/>
            </div>
            <a class="btn btn-primary btn-rounded btn-sm"
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
                    <shiro:hasPermission name="oa:notifyRecord:remove">
                        <button type="button" class="btn  btn-danger"
                                onclick="batchRemove()">
                            <i class="fa fa-trash" aria-hidden="true"></i>删除
                        </button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="oa:notifyRecord:list">
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
            var s_view_h = 'hidden';
        </script>
        <script type="text/javascript">
            var s_remove_h = 'hidden';
        </script>
        <shiro:hasPermission name="oa:notify:remove">
            <script>
                s_view_h = '';
            </script>
        </shiro:hasPermission>
        <shiro:hasPermission name="oa:notify:remove">
            <script>
                s_remove_h = '';
            </script>
        </shiro:hasPermission>
    </div>
</div>
<%@ include file="/commom/footer.jsp" %>
<%--<script src="${_ctx }/static/plugin/socket/sockjs.min.js"></script>--%>
<script src="https://cdn.bootcdn.net/ajax/libs/sockjs-client/1.5.0/sockjs.min.js"></script>
<script src="${_ctx }/static/plugin/socket/stomp.min.js"></script>
<script>
    var prefix = _ctx + '/oa/notifyRecord';

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
                        params.title = $('#title').val();
                        params.createBy = $('#createBy').val();
                        params.read = $('#read').val();
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
                            field: 'title', // 列字段名
                            title: '通知标题', // 列标题
                            formatter: function (value, row, index) {
                                return '<a href="#" onclick="view(\'' + row.id + '\')">' + row.title + '</a>';
                            }

                        }, {
                            field: 'type', // 列字段名
                            title: '通知类型', // 列标题
                            formatter: function (value, row, index) {
                                if (value == 2) {
                                    return '通知';
                                } else if (value == 1) {
                                    return '公告';
                                }
                            }
                        }, {
                            field: 'read',
                            title: '状态',
                            formatter: function (value, row, index) {
                                if (value) {
                                    return '<span class="badge badge-primary">已读</span>';
                                } else {
                                    return '<span class="badge badge-danger">未读</span>';
                                }
                            }
                        },
                        {
                            field: 'createBy',
                            title: '发布人'
                        },
                        {
                            field: 'createTime',
                            title: '创建时间',
                            sortable: true
                        },
                        {
                            field: 'updateBy',
                            title: '修改人'
                        },
                        {
                            field: 'updateTime',
                            title: '修改时间',
                            sortable: true
                        },
                        {
                            title: '操作',
                            field: 'id',
                            align: 'center',
                            formatter: function (value, row, index) {
                                var e = '<a  class="btn btn-primary btn-sm ' + s_view_h
                                    + '" href="#" title="查看" onclick="view(\'' + row.id
                                    + '\')"><i class="fa fa-book"></i>查看</a> ';
                                var d = '<a class="btn btn-warning btn-sm ' + s_remove_h
                                    + '" href="#" title="删除" onclick="remove(\'' + row.id
                                    + '\')"><i class="fa fa-remove"></i>删除</a> ';
                                return e + d;
                            }
                        }]
                });
    }


    function remove(id) {
        layer.confirm('确定要删除选中的记录？', {
            btn: ['确 定', '取 消']
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

    function view(id) {
        var index = parent.layer.open({
            type: 2,
            title: '通知公告查看',
            maxmin: true,
            shadeClose: false,
            area: ['800px', '520px'],
            content: prefix + '/view/' + id,// iframe的url
            success: function (layero, index) {
                // 已发布后
                var iframeWin = layero.find('iframe')[0];
                formView(iframeWin.contentWindow.document);
            },
            btn: ['取 消'],
            cancel: function (index) {
                return true;
            }
        });
        parent.layer.full(index);
    }

    function exportExcel() {
        layer.confirm("确认要导出所有日志数据吗?", {
            btn: ['确定', '取消']
            // 按钮
        }, function () {
            exportData(prefix + '/export', 'notifySelect', '我的通知列表.xls');
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
        var data = JSON.parse('${users}');
        data.splice(0, 0, {
            id: '',
            text: '全部'
        });
        var config = {
            data: data,
            width: '120px',
        };
        var config1 = {
            data: [
                {
                    id: '',
                    text: '全部'
                },
                {
                    id: 'true',
                    text: '已读'
                },
                {
                    id: 'false',
                    text: '未读'
                },
            ],
            width: '80px',
            minimumResultsForSearch: -1
        };
        select2Init('select[name="createBy"]', config);
        select2Init('select[name="read"]', config1);
        load();
        connect();
    });

    function connect() {
        // var sock = new SockJS(_ctx + "/ws/notify");
        var sock = new SockJS("http://wayn.xin:8080/crowd/ws/notify");
        var stompClient = Stomp.over(sock);
        stompClient.connect('guest', 'guest', function (frame) {
            // 修改我的通知状态
            stompClient.subscribe("/user/queue/notifyRecordTip", function (response) {
                reload();
            });
        });
    }
</script>
</body>
<!-- Mirrored from www.zi-han.net/theme/hplus/login.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:18:23 GMT -->
</html>
