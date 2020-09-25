<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <%@ include file="/commom/taglib.jsp" %>
    <%@ include file="/commom/header.jsp" %>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>crowd-admin 个人资料</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
          name="viewport">
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-4">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>个人信息</h5>
                </div>
                <div class="ibox-content">
                    <div class="text-center">
                        <p><img class="img-circle img-lg" src="${user.userImg }"
                                style="width: 120px"></p>
                        <p><a href="javascript:avatar()">修改头像</a></p>
                    </div>
                    <ul class="list-group list-group-striped">
                        <li class="list-group-item wayn-li-item"><i class="fa fa-user"></i>
                            <b class="font-noraml">登录名称：</b>
                            <p class="pull-right">${user.userName}</p>
                        </li>
                        <li class="list-group-item wayn-li-item"><i class="fa fa-user"></i>
                            <b class="font-noraml">昵称：</b>
                            <p class="pull-right">${user.nickName}</p>
                        </li>
                        <li class="list-group-item wayn-li-item"><i class="fa fa-phone"></i>
                            <b class="font-noraml">手机号码：</b>
                            <p class="pull-right">${user.phone}</p>
                        </li>
                        <li class="list-group-item wayn-li-item"><i class="fa fa-group"></i>
                            <b class="font-noraml">所属部门：</b>
                            <p class="pull-right">${deptName}</p>
                        </li>
                        <li class="list-group-item wayn-li-item"><i class="fa fa-envelope-o"></i>
                            <b class="font-noraml">邮箱地址：</b>
                            <p class="pull-right">${user.email}</p>
                        </li>
                        <li class="list-group-item wayn-li-item"><i class="fa fa-calendar"></i>
                            <b class="font-noraml">创建时间：</b>
                            <p class="pull-right"><fmt:formatDate value="${user.createTime}" pattern="yyyy年MM月dd日"/></p>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-sm-8">
            <div class="ibox float-e-margins no-drop">
                <div class="ibox-title">
                    <h5>基本资料</h5>
                </div>
                <div class="ibox-content">
                    <div class="nav-tabs-custom">
                        <div class="tabs-container">
                            <ul class="nav nav-tabs">
                                <li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="false">基本资料</a>
                                </li>
                                <li class=""><a data-toggle="tab" href="#tab-2" aria-expanded="true">修改密码</a>
                                </li>
                            </ul>
                            <div class="tab-content">
                                <div id="tab-1" class="tab-pane active">
                                    <div class="panel-body wayn-border-left0 wayn-border-right0">
                                        <form class="form-horizontal m-t" id="user-form">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">用户名：</label>
                                                <div class="col-sm-10">
                                                    <input id="id" name="id" type="hidden" value="${user.id }">
                                                    <input id="userName" name="userName" class="form-control" readonly
                                                           type="text" value="${user.userName }">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">昵称：</label>
                                                <div class="col-sm-10">
                                                    <input id="nickName" name="nickName" class="form-control"
                                                           type="text" value="${user.nickName }">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">手机：</label>
                                                <div class="col-sm-10">
                                                    <input id="phone" name="phone" class="form-control"
                                                           type="text" value="${user.phone }">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">邮箱：</label>
                                                <div class="col-sm-10">
                                                    <input id="email" name="email" class="form-control"
                                                           type="email" value="${user.email }">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">备注：</label>
                                                <div class="col-sm-10">
                                                    <textarea id="remarks" name="remarks" class="form-control"
                                                              rows="3">${user.remarks }</textarea>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-offset-2 col-sm-10">
                                                    <button type="button" class="btn btn-sm btn-primary" id="submitBtn">
                                                        <i class="fa fa-check"></i>保 存
                                                    </button>&nbsp;
                                                    <button type="button" class="btn btn-sm btn-danger"
                                                            onclick="activeTabClose()"><i class="fa fa-reply-all"></i>关
                                                        闭
                                                    </button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <div id="tab-2" class="tab-pane">
                                    <div class="panel-body wayn-border-left0 wayn-border-right0">
                                        <form class="form-horizontal" id="form-user-resetPwd">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">旧密码：</label>
                                                <div class="col-sm-4">
                                                    <input type="password" class="form-control" name="oldPassword"
                                                           placeholder="请输入旧密码" id="oldPassword">
                                                    <%--<div class="input-group">
                                                        <input type="password" class="form-control" name="oldPassword"
                                                               placeholder="请输入旧密码" id="oldPassword">
                                                        <span class="input-group-btn">
                                                            <button type="button" class="btn btn-default"
                                                                    id="oldPasswordView">
                                                                <span class="fa fa-eye"></span>
                                                            </button>
                                                        </span>
                                                    </div>--%>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">新密码：</label>
                                                <div class="col-sm-4">
                                                    <input type="password" class="form-control" name="newPassword"
                                                           id="newPassword" placeholder="请输入新密码">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">确认密码：</label>
                                                <div class="col-sm-4">
                                                    <input type="password" class="form-control" name="confirmPassword"
                                                           placeholder="请确认密码">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-offset-2 col-sm-10">
                                                    <button type="submit" class="btn btn-sm btn-primary"><i
                                                            class="fa fa-check"></i>保 存
                                                    </button>&nbsp;
                                                    <button type="button" class="btn btn-sm btn-danger"
                                                            onclick="activeTabClose()"><i class="fa fa-reply-all"></i>关 闭
                                                    </button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/commom/footer.jsp" %>
<script>
    var prefix = _ctx + '/profile';

    function avatar() {
        layer.open({
            type: 2,
            title: '上传头像',
            maxmin: true,
            shadeClose: false,
            area: ['840px', '700px'],
            content: prefix + '/avatar',// iframe的url
            success: function (layero, index) {
                // 已发布后
                var iframeWin = layero.find('iframe')[0];
            },
            btn: ['确 定', '取 消'],
            yes: function (index, layero) {
                //按钮【按钮一】的回调
                var iframeWin = layero.find('iframe')[0];
                var contentWindow = iframeWin.contentWindow;
                contentWindow.submitHandler();
            },
            cancel: function (index) {
                return true;
            }
        });
    }

    function validateRule() {
        var icon = "<i class='fa fa-times-circle'></i> ";
        $("#form-user-resetPwd").validate({
            rules: {
                oldPassword: {
                    required: true,
                    minlength: 6,
                    remote: {
                        url: prefix + "/judgeOldPasswordSuccess", // 后台处理程序
                        type: "post", // 数据发送方式
                        dataType: "json", // 接受数据格式
                        data: { // 要传递的数据
                            oldPassword: function () {
                                return $("#oldPassword").val();
                            }
                        }
                    }
                },
                newPassword: {
                    required: true,
                    minlength: 6
                },
                confirmPassword: {
                    required: true,
                    equalTo: '#newPassword'
                }
            },
            messages: {
                oldPassword: {
                    required: icon + "请输入您的旧密码",
                    minlength: icon + "密码必须6个字符以上",
                    remote: icon + "旧密码错误"
                },
                newPassword: {
                    required: icon + "请输入您的新密码",
                    minlength: icon + "密码必须6个字符以上",
                },
                confirmPassword: {
                    required: icon + "请输入您的确认密码",
                    equalTo: '确认密码与新密码不一致'
                }
            },
            focusCleanup: true,
            submitHandler: function () {
                save();
            }
        })
    }

    function save() {
        $.post(prefix + '/userResetPwd', $('#form-user-resetPwd').serialize(), function (data) {
            if (data.code == 100) {
                $("#form-user-resetPwd")[0].reset();
                layer.alert(data.msg);
            } else {
                parent.layer.alert(data.msg);
            }
        })
    }

    /**
     * 查看旧密码
     */
    $("#oldPasswordView").hover(function () {
        $("#oldPassword").attr('type', 'text')
    }, function () {
        $("#oldPassword").attr('type', 'password')
    });

    /**
     * 基本资料修改
     */
    $('#submitBtn').on('click', function () {
        $.post(prefix + '/updateUser', $('#user-form').serialize(), function (data) {
            if (data.code == 100) {
                layer.alert(data.msg);
            } else {
                parent.layer.alert(data.msg);
            }
        })
    });


    $(function () {
        validateRule();
    })
</script>
</body>
</html>
