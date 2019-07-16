<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>crowdfounding 用户</title>
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
<div class="ui-layout-west">
    <div class="ibox ibox-body">
        <div class="ibox-title">
            <h5>选择部门</h5>
        </div>
        <div class="ibox-tools wayn-ibox-tools">
            <a type="button" class="btn btn-box-tool menuItem" href="#"
               onclick="dept()" title="管理部门"><i class="fa fa-edit"></i>
            </a>
            <a type="button" class="btn btn-box-tool menuItem" href="#"
               onclick="refresh()" title="管理部门"><i class="fa fa-refresh"></i>
            </a>
        </div>
        <div class="ibox-content">
            <div id="jstree"></div>
        </div>
    </div>
</div>
<div class="wrapper wrapper-content ui-layout-center gray-bg">
    <div class="col-sm-12 search-collapse">
        <form class="form-inline" id="roleSelect">
            <input type="hidden" id="deptId" name="deptId">
            <div class="form-group">
                <label for="userName">用户名称</label>
                <input type="text" class="form-control wayn-width-125" id="userName" name="userName">
            </div>
            <div class="form-group magin-left10">
                <label for="userName">手机号</label>
                <input type="text" class="form-control" id="phone" name="phone">
            </div>
            <div class="form-group magin-left10">
                <label for="userName">邮箱</label>
                <input type="text" class="form-control" id="email" name="email">
            </div>
            <div class="form-group magin-left10">
                <label for="userState">用户状态</label>
                <select
                        class="js-example-basic-single" name="userState" id="userState">
                </select>
            </div>
            <div class="form-group magin-left10 select-time">
                <label for="startTime">创建时间</label>
                <input type="text" class="form-control wayn-width-105" id="startTime" name="startTime"
                       placeholder="开始时间"/>
                <span>-</span>
                <input type="text" class="form-control wayn-width-105" id="endTime" name="endTime" placeholder="结束时间"/>
            </div>
            <a class="btn btn-primary btn-rounded btn-sm magin-left10"
               onclick="reload()"><i class="fa fa-search"></i>&nbsp;搜索</a> <a
                class="btn btn-warning btn-rounded btn-sm" onclick="selectReset()"><i
                class="fa fa-refresh"></i>&nbsp;重置</a>
        </form>
    </div>
    <div class="col-sm-12 select-table">
        <div class="ibox">
            <div class="ibox-body">
                <div id="exampleToolbar" role="group">
                    <shiro:hasPermission name="sys:user:add">
                        <button type="button" class="btn  btn-primary" onclick="add()">
                            <i class="fa fa-plus" aria-hidden="true"></i>添加
                        </button>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="sys:user:remove">
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
            let s_edit_h = 'hidden';
            let s_remove_h = 'hidden';
            let s_resetPwd_h = 'hidden';
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
<%@ include file="/commom/footer.jsp" %>
<script>
    let prefix = _ctx + "/system/user";

    function load(deptId) {
        $('#exampleTable').bootstrapTable(
            {
                method: 'post', // 服务器数据的请求方式 get or post
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
                sortName: 'createTime',
                sortOrder: 'desc',
                // "server"
                queryParamsType: "",//If queryParamsType = 'limit',
                //the params object contains: limit, offset, search, sort, order.
                //Else, it contains: pageSize, pageNumber, searchText, sortName, sortOrder.
                queryParams: function (params) {
                    params.deptId = $('#deptId').val();
                    params.userName = $('#userName').val().trim();
                    params.phone = $('#phone').val();
                    params.email = $('#email').val();
                    params.userState = $('#userState').val();
                    params.startTime = $('#startTime').val();
                    params.endTime = $('#endTime').val();
                    return params;
                },
                columns: [
                    {
                        checkbox: true,
                        width: '4%',
                    },
                    {
                        field: 'id',
                        width: '16%',
                        title: '序号'
                    },
                    {
                        field: 'userName',
                        title: '用户名',
                        sortable: true
                    },
                    {
                        field: 'phone',
                        title: '手机号',
                        sortable: true
                    },
                    {
                        field: 'email',
                        title: '邮箱',
                    },
                    {
                        field: 'createTime',
                        title: '创建时间',
                        width: '10%',
                        sortable: true
                    },
                    {
                        field: 'remarks',
                        title: '备注'
                    },
                    {
                        field: 'userState',
                        title: '状态',
                        align: 'center',
                        formatter: function (value, row, index) {
                            if (value == '-1') {
                                return '<span class="badge badge-danger">禁用</span>';
                            } else if (value == '1') {
                                return '<span class="badge badge-primary">正常</span>';
                            }
                        }
                    },
                    {
                        title: '操作',
                        field: 'userId',
                        align: 'center',
                        formatter: function (value, row, index) {
                            let e = '<a  class="btn btn-primary btn-sm ' + s_edit_h
                                + '" href="#" mce_href="#" title="编辑" onclick="edit(\'' + row.id
                                + '\')"><i class="fa fa-edit "></i></a> ';
                            let d = '<a class="btn btn-warning btn-sm ' + s_remove_h
                                + '" href="#" title="删除"  mce_href="#" onclick="remove(\'' + row.id
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            let f = '<a class="btn btn-success btn-sm ' + s_resetPwd_h
                                + '" href="#" title="重置密码"  mce_href="#" onclick="resetPwd(\'' + row.id
                                + '\')"><i class="fa fa-key"></i></a> ';
                            return e + d + f;
                        }
                    }]
            });
    }

    function reload() {
        $('#exampleTable').bootstrapTable('refresh');
    }

    function dept() {
        menuItemCreate(_ctx + '/system/dept', '部门管理');
    }

    function refresh() {
        var index = 0;
        $.ajax({
            type: "POST",
            dataType: "json",
            url: _ctx + "/system/dept/tree",
            beforeSend: function (xhr) {
                index = layer.msg('加载中', {
                    icon: 16,
                    shade: 0.1,
                    time: 270
                });
            },
            success: function (tree) {
                debugger
                $('#jstree').jstree(true).settings.core.data = tree;
                $('#jstree').jstree(true).refresh();
            },
            error: function (err) {
                layer.msg(err);
                layer.close(index);
            }
        });
    }

    function add() {
        // iframe层
        layer.open({
            type: 2,
            title: '增加用户',
            maxmin: true,
            shadeClose: false, // 点击遮罩关闭层
            area: ['800px', '590px'],
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
                        layer.msg(data.msg);
                        reload();
                    }
                }
            });
        })
    }

    function edit(id) {
        layer.open({
            type: 2,
            title: '用户修改',
            maxmin: true,
            shadeClose: false,
            area: ['800px', '540px'],
            content: prefix + '/edit/' + id // iframe的url
        });
    }

    function resetPwd(id) {
        layer.open({
            type: 2,
            title: '重置密码',
            maxmin: true,
            shadeClose: false, // 点击遮罩关闭层
            area: ['400px', '320px'],
            content: prefix + '/resetPwd/' + id // iframe的url
        });
    }

    function batchRemove() {
        let rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
        if (rows.length == 0) {
            layer.msg("请选择要删除的数据");
            return;
        }
        layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
            btn: ['确定', '取消']
            // 按钮
        }, function () {
            let ids = new Array();
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
                        layer.msg(data.msg);
                        reload();
                    }
                }
            });
        }, function () {
        });
    }

    function getTreeData(load) {
        $.ajax({
            type: "POST",
            dataType: "json",
            url: _ctx + "/system/dept/tree",
            success: function (tree) {
                load(tree);
            }
        });
    }

    function loadTree(tree) {
        $('#jstree').jstree({
            'core': {
                'data': tree,
                'themes': {
                    'name': "proton"
                }
            },
            "plugins": ["search"]
        });
    }

    $("#jstree").on("loaded.jstree", function (event, data) {
        // 展开所有节点
        $('#jstree').jstree(true).open_all();
    });

    $('#jstree').on("changed.jstree", function (e, data) {
        if (data.selected == -1) {
            let opt = {
                query: {
                    deptId: '',
                }
            };
            $('#deptId').val('');
            $('#exampleTable').bootstrapTable('refresh', opt);
        } else {
            let opt = {
                query: {
                    deptId: data.selected[0],
                }
            };
            $('#deptId').val(data.selected[0]);
            $('#exampleTable').bootstrapTable('refresh', opt);
        }
    });

    $(function () {
        let config = {
            data: JSON.parse('${states}'),
            width: '80px'
        };
        select2Init('.js-example-basic-single', config);
        let panehHidden = false;
        if ($(this).width() < 769) {
            panehHidden = true;
        }
        //初始化layout
        $('body').layout({
            initClosed: panehHidden,
            west__size: 210
        });
        getTreeData(loadTree);
        load($('#deptId').val());
    });
</script>
</body>
<!-- Mirrored from www.zi-han.net/theme/hplus/login.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:18:23 GMT -->
</html>
