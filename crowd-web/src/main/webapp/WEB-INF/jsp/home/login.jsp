<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>crowd-admin 登陆</title>
    <meta name="keywords" content="crowd-admin是一个后台权限管理系统脚手架，集成了rbac权限管理、消息推送、邮件发送、任务调度、代码生成、elfinder文件管理等常用功能，系统内各个业务按照模块划分，前台使用H+模板。是一个java新人易于上手，学习之后能够快速融入企业开发的指导项目">
    <meta name="description"
          content="design by wayn">
    <%@ include file="/common/taglib.jsp" %>
    <%@ include file="/common/header.jsp" %>
    <link href="${_ctx }/static/css/login.css?v=4.0"
          rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
    <script>
        if (window.top !== window.self) {
            window.top.location = window.location;
        }
    </script>
</head>

<body class="signin">
<div class="signinpanel">
    <div class="row">
        <div class="col-sm-7">
            <div class="signin-info">
                <div class="logopanel m-b">
                    <h1>[ ${sysName} ]</h1>
                </div>
                <div class="m-b"></div>
                <h4>欢迎使用 <strong>${sysName}</strong></h4>
                <ul class="m-b">
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> spring</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> shiro</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> mybatis</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 消息推送</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 文件管理</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 代码生成</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 系统监控</li>
                </ul>
            </div>
        </div>
        <div class="col-sm-5 animated bounceInDown">
            <form id="login-form" action="#">
                <h4 class="no-margins">登录：</h4>
                <p class="m-t-md">登录到${sysName }后台</p>
                <input type="text" class="form-control" name="userName" placeholder="用户名" value="crowd"/>
                <input type="password" class="form-control m-b" name="password" placeholder="密码" value="123456"/>
                <div class="row">
                    <div class="col-xs-7">
                        <div class="form-group">
                            <input class="form-control" id="kaptcha-input" name="clientKaptcha" maxlength="4"
                                   placeholder="验证码">
                        </div>
                    </div>
                    <div class="col-xs-5">
                        <a href="javascript:void(0);" rel="external nofollow" title="点击更换验证码">
                            <img id="verify-img" alt="更换验证码" height="37" width="100%"
                                 onclick="getVerify(this);">
                        </a>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-7">
                        <input type="checkbox" id="rememberMe" name="rememberMe"> <label for="rememberme">记住我</label>
                    </div>
                </div>
                <button id="login-button" class="btn btn-success btn-block" type="submit">登录</button>
                <div class="outside-login">
                    <div class="outside-login-tit">
                        <span>代码链接</span>
                    </div>
                    <div class="outside-login-cot">
                        <a class="outside-login-btn gitee" target="_Blank"
                           href="https://gitee.com/wayn111/crowd-admin">
                            <em><i class="fa fa-git-square"></i></em>
                            <span>码云仓库</span>
                        </a>
                        <a class="outside-login-btn git" target="_Blank"
                           href="https://github.com/wayn111/crowd-admin">
                            <em><i class="fa fa-github"></i></em>
                            <span>GitHub仓库</span>
                        </a>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="signup-footer">
        <div class="pull-left">
            ${sysFooter }
        </div>
    </div>
</div>
<%@ include file="/common/footer.jsp" %>
<script>
    var prefix = _ctx + "/home";

    function validateRule() {
        var e = '<i class="fa fa-times-circle"></i> ';
        $("#login-form").validate({
            rules: {
                userName: {
                    required: true,
                    minlength: 2,
                },
                password: {
                    required: true
                },
                clienkaptcha: {
                    required: true
                }

            },
            messages: {
                userName: {
                    required: e + "请输入用户名",
                    minlength: e + "用户名最少由二个字母组成"
                },
                password: {
                    required: e + "请输入密码",
                },
                clienkaptcha: {
                    required: e + "请输入验证码"
                }
            },
            focusCleanup: true,
            submitHandler: function () {
                login();
            }
        });
    }

    function login() {
        var index = layer.msg('正在登陆...', {
            icon: 16,
            shade: 0.1
        });
        var config = {
            url: prefix + "/doLogin",
            data: $('#login-form').serialize(),
            type: "POST",
            dataType: "json",
            success: function (data) {
                layer.close(index);
                if (data.code != 100) {
                    layer.msg(data.msg, {icon: 5});
                    $('#kaptcha-input').val();
                } else {
                    location = _ctx + "/main";
                }
            },
            error: function (err) {
                layer.close(index);
                console.log(err)
            }
        };
        $.ajax(config);
    }

    function getVerify(_this) {
        $(_this).attr('src', _ctx + "/home/captcha?r=" + Math.random());
    }

    $(function () {
        getVerify(document.getElementById('verify-img'));
        validateRule();
    })

</script>
</body>
</html>
