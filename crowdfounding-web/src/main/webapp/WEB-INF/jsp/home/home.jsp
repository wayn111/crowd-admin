<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${sysName } 首页</title>
    <meta name="keywords" content="crowd-admin是一个后台权限管理系统脚手架，集成了rbac权限管理、消息推送、邮件发送、任务调度、代码生成、elfinder文件管理等常用功能，系统内各个业务按照模块划分，前台使用H+模板。是一个java新人易于上手，学习之后能够快速融入企业开发的指导项目">
    <meta name="description"
          content="design by wayn">
    <%@ include file="/commom/taglib.jsp" %>
    <%@ include file="/commom/header.jsp" %>
    <link href="${_ctx }/static/plugin/contextMenu-v2.9.2/jquery.contextMenu.min.css" rel="stylesheet">
    <link href="${_ctx }/static/plugin/toastr/toastr.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
    <script>
        if (window.top !== window.self) {
            window.top.location = window.location;
        }
    </script>
</head>

<body class="fixed-sidebar full-height-layout gray-bg"
      style="overflow: hidden">
<div id="wrapper">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close">
            <i class="fa fa-times-circle"></i>
        </div>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                    <div class="wayn-profile animated">
                        <h3 class="wayn-h3">${sysName }</h3>
                        <div id="user-avatar" class="pull-left" style="margin-top: 6px">
                            <img alt="avatar" class="img-circle" src="${user.userImg}"
                                 style="cursor:pointer;height: 64px"
                                 onclick="javascript:menuItemCreate('${_ctx}/profile','个人资料')"/>
                        </div>
                        <div id="user-state" class="pull-left" style="margin: 15px 0 0 15px">
                            <p style="color: white">
                                <strong class="font-bold">${user.nickName }</strong>
                            </p>
                            <span>
                                <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
                                <a href="${_ctx }/home/logout" style="padding-left:5px;"><i
                                        class="fa fa-sign-out text-danger"></i> 注销</a>
                            </span>
                        </div>
                    </div>
                </li>
                <c:forEach var="menu" items="${treeMenus }">
                    <li>
                        <c:if test="${menu.type eq 1 }">
                            <a href="#">
                                <i class="${menu.icon }"></i>
                                <span class="nav-label">${menu.menuName }</span>
                                <span class="fa arrow"></span>
                            </a>
                            <ul class="nav nav-second-level collapse">
                                <c:forEach var="childMenu" items="${menu.children }" varStatus="status">
                                    <li>
                                        <c:choose>
                                            <c:when test="${childMenu.type eq 1}">
                                                <a href="#">${childMenu.menuName}<span class="fa arrow"></span></a>
                                                <ul class="nav nav-third-level collapse" aria-expanded="false"
                                                    style="height: 0px;">
                                                    <c:forEach var="childMenu2" items="${childMenu.children}"
                                                               varStatus="status2">
                                                        <c:choose>
                                                            <c:when test="${childMenu2.type eq 1}">
                                                            </c:when>
                                                            <c:otherwise>
                                                                <li>
                                                                    <a class="J_menuItem"
                                                                       href="${_ctx }${childMenu2.url }"
                                                                       data-index="${status2.index}">${childMenu2.menuName }</a>
                                                                </li>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </ul>
                                            </c:when>
                                            <c:otherwise>
                                                <a class="J_menuItem" href="${_ctx }${childMenu.url }"
                                                   data-index="${status.index}">${childMenu.menuName }</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </li>
                                </c:forEach>
                            </ul>
                        </c:if>
                        <c:if test="${menu.type eq 2 }">
                            <a href="#"> <i class="fa fa-home"></i> <span
                                    class="nav-label">${menu.menuName }</span> <span
                                    class="fa arrow"></span>
                            </a>
                        </c:if>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation"
                 style="margin-bottom: 0">
                <div class="navbar-header">
                    <a class="navbar-minimalize minimalize-styl-2"
                       href="#"><i class="fa fa-bars"></i> </a>
                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <li onclick="fullScreen()">
                        <a title="全屏显示"><i class="fa fa-arrows-alt"></i> 全屏显示</a>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle count-info" data-hover="dropdown" data-delay="0"
                           data-toggle="dropdown" href="#"> <i class="fa fa-envelope"></i>
                            <span class="label label-warning">{{total}}</span>通知
                        </a>
                        <ul class="dropdown-menu dropdown-messages">
                            <li v-for="row in rows" v-on:click="viewNotifyRecord(row.notifyRecordId)"
                                class="m-t-xs wayn-m-t-xs">
                                <div class="dropdown-messages-box">
                                    <a class="pull-left">
                                        <i class="fa fa-paper-plane"></i>
                                    </a>
                                    <div class="media-body">
                                        <small class="pull-right">{{row.before}}</small>
                                        <strong>{{row.updateBy?row.updateBy:row.createBy}}</strong>
                                        {{row.title}} <br>
                                        <small class="text-muted">{{row.updateTime?row.updateTime:row.createTime}}</small>
                                    </div>
                                </div>
                                <div class="divider"></div>
                            </li>
                            <li>
                                <div class="text-center link-block">
                                    <a class="J_menuItem noactive" href="${_ctx}/oa/notifyRecord">
                                        <i class="fa fa-envelope"></i> <strong> 我的通知</strong>
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" target="_blank" href="https://github.com/wayn111/newbee-mall">
                            newbee商城
                        </a>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-hover="dropdown" data-delay="0"
                           data-toggle="dropdown" href="https://github.com/wayn111/crowd-admin">
                            源码
                        </a>
                        <ul class="dropdown-menu">
                            <li class="mt5">
                                <a href="http://github.com/wayn111/crowd-admin">
                                    <i class="fa fa-github"></i> github</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href=http://gitee.com/wayn111/crowdfounding>
                                    <i class="fa fa-gg"></i> gitee
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown user-menu">
                        <a href="javascript:void(0)" class="dropdown-toggle" data-hover="dropdown" data-delay="0">
                            <span class="hidden-xs">${user.nickName}</span>
                        </a>
                        <ul class="dropdown-menu">
                            <li class="mt5">
                                <a href="javascript:void(0);" class="noactive"
                                   onclick="javascript:menuItemCreate('${_ctx}/profile','个人资料')">
                                    <i class="fa fa-user"></i> 个人中心</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="${_ctx }/home/logout">
                                    <i class="fa fa-sign-out"></i> 退出登录</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="row content-tabs">
            <button class="roll-nav roll-left J_tabLeft">
                <i class="fa fa-backward"></i>
            </button>
            <nav class="page-tabs J_menuTabs">
                <div class="page-tabs-content">
                    <a href="javascript:;" class="active J_menuTab"
                       data-id="${_ctx }/main/mainIndex1">首页</a>
                </div>
            </nav>
            <button class="roll-nav roll-right J_tabRight">
                <i class="fa fa-forward"></i>
            </button>
            <div class="btn-group roll-nav roll-right">
                <button class="dropdown J_tabClose" data-toggle="dropdown" data-hover="dropdown" data-delay="0">
                    关闭操作<span class="caret"></span>
                </button>
                <ul role="menu" class="dropdown-menu dropdown-menu-right">
                    <li class="J_tabCloseActive"><a>关闭当前选项卡</a></li>
                    <li class="divider"></li>
                    <li class="J_tabCloseLeft"><a>关闭左侧选项卡</a></li>
                    <li class="J_tabCloseRight"><a>关闭右侧选项卡</a></li>
                    <li class="J_tabCloseOther"><a>关闭其他选项卡</a></li>
                    <li class="J_tabCloseAll"><a>关闭全部选项卡</a></li>
                </ul>
            </div>
            <a href="javascript:void(0);" class="roll-nav roll-right J_tabExit J_tabRefresh"><i
                    class="fa fa fa-refresh"></i> 刷新</a>
        </div>
        <div class="row J_mainContent" id="content-main">
            <iframe class="J_iframe" name="iframe0" width="100%" height="100%"
                    src="${_ctx }/main/mainIndex1" frameborder="0"
                    data-id="${_ctx }/main/mainIndex1" seamless></iframe>
        </div>
        <div class="footer">
            <div class="pull-right">
                ${sysFooter }
            </div>
        </div>
    </div>
    <!--右侧部分结束-->
</div>
<%--<script src="${_ctx }/static/plugin/jquery/jquery.min.js"></script>--%>
<script src="https://cdn.bootcdn.net/ajax/libs/jquery/2.2.4/jquery.js"></script>
<script src="${_ctx }/static/plugin/validate/jquery.validate.min.js?v=1.13"></script>
<script src="${_ctx }/static/plugin/validate/messages_zh.min.js"></script>
<script src="${_ctx }/static/plugin/bootstrap-v3.3.7/js/bootstrap.min.js?v=3.3.7"></script>
<script src="${_ctx }/static/plugin/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"></script>
<script src="${_ctx }/static/plugin/layer/layer.min.js"></script>
<script src="${_ctx }/static/js/common/common-ui.js"></script>
<script src="${_ctx }/static/plugin/pace/pace.min.js"></script>
<script src="${_ctx }/static/plugin/fullscreen/jquery.fullscreen.js"></script>
<script src="${_ctx }/static/plugin/metisMenu/jquery.metisMenu.js"></script>
<script src="${_ctx }/static/plugin/slimscroll/jquery.slimscroll.min.js"></script>
<script src="${_ctx }/static/plugin/contextMenu-v2.9.2/jquery.contextMenu.min.js"></script>
<script src="${_ctx }/static/plugin/contextMenu-v2.9.2/jquery.ui.position.min.js"></script>
<script src="${_ctx }/static/js/hplus/hplus.js?v=4.1.0"></script>
<script src="${_ctx }/static/js/hplus/contabs.js"></script>
<%--<script src="${_ctx }/static/plugin/vue-2.6.10/vue.min.js"></script>--%>
<script src="https://cdn.bootcdn.net/ajax/libs/vue/2.6.9/vue.min.js"></script>
<%--<script src="${_ctx }/static/plugin/socket/sockjs.min.js"></script>--%>
<script src="https://cdn.bootcdn.net/ajax/libs/sockjs-client/1.5.0/sockjs.min.js"></script>
<script src="${_ctx }/static/plugin/socket/stomp.min.js"></script>
<script src="${_ctx }/static/plugin/toastr/toastr.min.js"></script>
<script>
    var isLinkage = true;

    var prefix = _ctx + '/oa/notifyRecord';

    var toastrDefaultConfig = {
        "closeButton": true,
        "debug": false,
        "progressBar": true,
        "positionClass": "toast-bottom-right",
        "onclick": null,
        "showDuration": "400",
        "hideDuration": "1000",
        "timeOut": "10000",
        "extendedTimeOut": "5000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    };

    toastr.options = toastrDefaultConfig;

    /**
     * 全屏显示
     */
    function fullScreen() {
        $("#wrapper").fullScreen();
    }

    $(function () {
        connect();
        toastr['success']('欢迎来${sysName }！');
        // tab栏右键菜单实现
        $.contextMenu({
            selector: ".J_menuTab",
            trigger: 'right',
            autoHide: true,
            items: {
                "close_current": {
                    name: "关闭当前",
                    icon: "fa-close",
                    callback: function (key, opt) {
                        opt.$trigger.find('i').trigger("click");
                    }
                },
                "close_other": {
                    name: "关闭其他",
                    icon: "fa-window-close-o",
                    callback: function (key, opt) {
                        opt.$trigger.trigger('click');
                        $('.J_tabCloseOther').trigger('click');
                    }
                },
                "close_left": {
                    name: "关闭左侧",
                    icon: "fa-reply",
                    callback: function (key, opt) {
                        opt.$trigger.trigger('click');
                        $('.J_tabCloseLeft').trigger('click');
                    }
                },
                "close_right": {
                    name: "关闭右侧",
                    icon: "fa-share",
                    callback: function (key, opt) {
                        opt.$trigger.trigger('click');
                        $('.J_tabCloseRight').trigger('click');
                    }
                },
                "close_all": {
                    name: "全部关闭",
                    icon: "fa-window-close",
                    callback: function (key, opt) {
                        $('.J_tabCloseAll').trigger('click');
                    }
                },
                "step": "---------",
                "full": {
                    name: "全屏显示",
                    icon: "fa-arrows-alt",
                    callback: function (key, opt) {
                        opt.$trigger.trigger('click');
                        var target = $('.J_iframe[data-id="' + this.data('id') + '"]');
                        target.fullScreen(true);
                    }
                },
                "refresh": {
                    name: "刷新页面",
                    icon: "fa-refresh",
                    callback: function (key, opt) {
                        opt.$trigger.trigger('click');
                        $('.J_tabRefresh').trigger('click');
                    }
                },
                "open": {
                    name: "新窗口打开",
                    icon: "fa-link",
                    callback: function (key, opt) {
                        var target = $('.J_iframe[data-id="' + this.data('id') + '"]');
                        window.open(target.attr('src'));
                    }
                },
            }
        })
    });

    /**
     * 建立stomp连接
     */
    function connect() {
        var sock = new SockJS("http://wayn.xin:8080/crowd/ws/notify");
        var stompClient = Stomp.over(sock);
        stompClient.connect('guest', 'guest', function (frame) {
            /** 
             * 订阅了/user/queue/notifications 发送的消息,这里于在控制器的 convertAndSendToUser 定义的地址保持一致, 
             * 这里多用了一个/user,并且这个user 是必须的,使用user 才会发送消息到指定的用户。 
             */
            stompClient.subscribe("/user/queue/notifications", function (response) {
                wrapper.notify();
                showToastr({
                    type: 'info',
                    msg: response.body
                });
            });
            stompClient.subscribe('/user/queue/notifyRecordTip', function (response) {
                wrapper.notify();
            });
            stompClient.subscribe('/user/queue/getResponse', function (response) {
                showToastr({
                    type: 'info',
                    msg: response.body,
                    timeOut: -1,
                    extendedTimeOut: -1
                });
            });
            stompClient.subscribe('/topic/getResponse', function (response) { //订阅/topic/getResponse 目标发送的消息。这个是在控制器的@SendTo中定义的。
                showToastr({
                    type: 'info',
                    msg: response.body,
                    timeOut: -1,
                    extendedTimeOut: -1
                });
            });
            // 定义消息载体
            var payload = JSON.stringify({'code': 100, 'msg': 'tip'});
            // 定义消息头
            var head = {};
            // 测试发送消息
            stompClient.send('/app/message', head, payload);
        })
    }

    var wrapper = new Vue({
        el: '#page-wrapper',
        data: {
            total: '',
            rows: '',
        },
        methods: {
            notify: function () {
                $.getJSON(prefix + '/notifyRecordTip', function (r) {
                    console.log(r);
                    wrapper.total = r.total;
                    wrapper.rows = r.records;
                });
            }
        },
        mounted: function () {
            this.notify()
        }
    });


    /**
     * 查看通知记录
     */
    function viewNotifyRecord(id) {
        var index = layer.open({
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
        layer.full(index);
    }

    /**
     * 显示通知提示
     */
    function showToastr(config) {
        toastr.options = $.extend({}, toastrDefaultConfig, config);
        toastr[config.type](config.msg);
        toastr.options = toastrDefaultConfig;
    }

    /**
     * 检查用户认证信息
     */
    function judgeUserAuthentication(msg) {
        $.post(_ctx + "/profile/auth", function (data) {
            if (data.code != 100) {
                toastr.info(msg);
            }
        })
    }

</script>
</body>
</html>
