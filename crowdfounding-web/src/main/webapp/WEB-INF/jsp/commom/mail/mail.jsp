<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <%@ include file="/commom/taglib.jsp" %>
    <%@ include file="/commom/header.jsp" %>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>crowd-admin 邮件配置</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
          name="viewport">
    <style>
        .my-blockquote {
            margin: 0 0 10px;
            padding: 15px;
            line-height: 22px;
            border-left: 5px solid #00437B;
            border-radius: 0 2px 2px 0;
            background-color: #f2f2f2;
        }

        .my-code {
            position: relative;
            padding: 15px;
            line-height: 20px;
            border-left: 5px solid #ddd;
            color: #333;
            font-family: Courier New, serif;
            font-size: 12px
        }
    </style>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-8">
            <div class="ibox float-e-margins no-drop">
                <div class="ibox-title">
                    <h5>邮件配置</h5>
                </div>
                <div class="ibox-content">
                    <div class="nav-tabs-custom">
                        <div class="tabs-container">
                            <ul class="nav nav-tabs">
                                <li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="false">配置信息</a>
                                </li>
                                <li><a data-toggle="tab" href="#tab-2" aria-expanded="false">发送邮件</a>
                                </li>
                                <li><a data-toggle="tab" href="#tab-3" aria-expanded="true">用户配置</a>
                                </li>
                                <li><a data-toggle="tab" href="#tab-4" aria-expanded="true">说明配置</a>
                                </li>
                            </ul>
                            <div class="tab-content">
                                <div id="tab-1" class="tab-pane active">
                                    <div class="panel-body wayn-border-left0 wayn-border-right0">
                                        <form class="form-horizontal m-t" id="mail-form">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">smtp地址：</label>
                                                <div class="col-sm-10">
                                                    <input name="id" class="form-control"
                                                           type="hidden" value="${mail.id }">
                                                    <input id="host" name="host" class="form-control"
                                                           type="text" value="${mail.host }">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">smtp端口：</label>
                                                <div class="col-sm-10">
                                                    <input id="port" name="port" class="form-control"
                                                           type="text" value="${mail.port }">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">发件者邮箱：</label>
                                                <div class="col-sm-10">
                                                    <input id="fromUser" name="fromUser" class="form-control"
                                                           type="email" value="${mail.fromUser }">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">发件者密码：</label>
                                                <div class="col-sm-10">
                                                    <input id="pass" name="pass" class="form-control"
                                                           type="text" value="${mail.pass }">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-offset-2 col-sm-10">
                                                    <button type="button" class="btn btn-sm btn-primary" id="mailBtn">
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
                                        <form class="form-horizontal m-t" id="sendMail-form">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">接收人名称：</label>
                                                <div class="col-sm-10">
                                                    <input id="receiverUser" name="receiverUser" class="form-control"
                                                           type="text">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">接收人邮箱：</label>
                                                <div class="col-sm-10">
                                                    <input id="sendMail" name="sendMail" class="form-control"
                                                           type="text">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">标题：</label>
                                                <div class="col-sm-10">
                                                    <input id="title" name="title" class="form-control"
                                                           type="text">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">内容：</label>
                                                <div class="col-sm-10">
                                                    <textarea id="content" name="content" class="form-control"
                                                              rows="3"></textarea>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-offset-2 col-sm-10">
                                                    <button type="submit" class="btn btn-sm btn-primary"
                                                            id="sendMailBtn">
                                                        <i class="fa fa-check"></i>发 送
                                                    </button>&nbsp;
                                                    <button type="button" class="btn btn-sm btn-danger"
                                                            onclick="activeTabClose()">
                                                        <i class="fa fa-reply-all"></i>关 闭
                                                    </button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <div id="tab-3" class="tab-pane">
                                    <div class="panel-body wayn-border-left0 wayn-border-right0">
                                        <form class="form-horizontal" id="user-form">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">是否发送用户邮件：</label>
                                                <div class="col-sm-8">
                                                    <input type="hidden" name="id"
                                                           value="${mail.id }">
                                                    <input type="hidden" id="userSendState" name="userSendState"
                                                           value="${mail.userSendState }">
                                                    <input type="checkbox" name="userSendStateSwicth" checked>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-offset-2 col-sm-10">
                                                    <button type="button" class="btn btn-sm btn-primary" id="userBtn"><i
                                                            class="fa fa-check"></i>保 存
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
                                <div id="tab-4" class="tab-pane">
                                    <div class="panel-body wayn-border-left0 wayn-border-right0">
                                        <div>
                                            <blockquote class="my-blockquote"> 邮件服务器配置</blockquote>
                                            <pre class="my-code">
 # 邮件服务器的SMTP地址，可选，默认为smtp
 # 邮件服务器的SMTP端口，可选，默认465或者25
 # 发件人（必须正确，否则发送失败）
 # 密码（注意，某些邮箱需要为SMTP服务单独设置密码，如QQ和163等等）
 # 是否开启ssl，默认开启</pre>
                                            <blockquote class="my-blockquote">更多帮助</blockquote>
                                            <pre class="my-code">更多帮助请查看文档：<a style="color:#009688"
                                                                              href="http://baidu.com"
                                                                              target="_black">百度</a></pre>
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
</div>
<%@ include file="/commom/footer.jsp" %>
<script>
    var prefix = _ctx + '/commom/mail';

    function update(dom) {
        var config = {
            url: prefix + "/update",
            data: $('#' + dom + '').serialize(),
            type: "POST",
            dataType: "json",
            success: function (data) {
                if (data.code != 100) {
                    layer.alert(data.msg);
                } else {
                    parent.layer.msg(data.msg, {icon: 1});
                }
            },
            error: function (err) {
                console.log(err)
            }
        };
        $.ajax(config);
    }

    $('#userBtn').on('click', function () {
        update('user-form')
    });

    /**
     * 基本资料修改
     */
    $('#mailBtn').on('click', function () {
        update('mail-form')
    });

    function validateRule() {
        var icon = "<i class='fa fa-times-circle'></i> ";
        $("#sendMail-form").validate({
            rules: {
                receiverUser: {
                    required: true
                },
                sendMail: {
                    required: true,
                    checkEmail: true
                },
                title: {
                    required: true,
                },
                content: {
                    required: true,
                }
            },
            messages: {
                receiverUser: {
                    required: icon + "请输入接收人名称",
                },
                sendMail: {
                    required: icon + "请输入接收人邮箱",
                    checkEmail: "请输入正确的邮箱！",
                },
                title: {
                    required: icon + "请输入邮件标题",
                },
                content: {
                    required: icon + "请输入邮件内容",
                }
            },
            focusCleanup: true,
            submitHandler: function () {
                save();
            }
        })
    }

    function save() {
        $.post(prefix + '/sendMail', $('#sendMail-form').serialize(), function (data) {
            if (data.code == 100) {
                $("#sendMail-form")[0].reset();
                layer.alert(data.msg);
            } else {
                parent.layer.alert(data.msg);
            }
        })
    }

    $(function () {
        var state = $('#userSendState').val() == "1" ? true : false;
        switchInit('userSendState', {state});
        validateRule()
    })
</script>
</body>
</html>
