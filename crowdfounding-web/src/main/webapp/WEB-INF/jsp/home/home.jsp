<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>crowdfounding 登陆</title>
    <meta name="keywords" content="wayn,基于H+,后台HTML,响应式后台">
    <meta name="description"
          content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">
    <%@ include file="/commom/taglib.jsp" %>
    <%@ include file="/commom/header.jsp" %>
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
                    <div class="dropdown profile-element">
							<span>
								<img alt="image" class="img-circle" src="${_ctx }/static/img/profile_small.jpg"/>
							</span>
                        <a <%--data-toggle="dropdown"--%> class="dropdown-toggle" href="#">
							<span class="clear">
								<span class="block m-t-xs">
									<strong class="font-bold">${user.userName }</strong>
								</span>
								<span class="text-muted text-xs block">欢迎您<b class="caret"></b></span>
							</span>
                        </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li><a class="J_menuItem" href="profile.html">个人资料</a></li>
                            <li class="divider"></li>
                            <li><a href="${_ctx }/home/logout">安全退出</a></li>
                        </ul>
                    </div>
                    <div class="logo-element">H+</div>
                </li>
                <c:forEach var="menu" items="${treeMenus }">
                    <li><c:if test="${menu.type eq 1 }">
                        <a href="#"> <i class="${menu.icon }"></i> <span
                                class="nav-label">${menu.menuName }</span> <span
                                class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <c:forEach var="childMenu" items="${menu.children }" varStatus="status">
                                <li><c:choose>
                                    <c:when test="${empty childMenu.url}">
                                        <a class="J_menuItem" href="javascript:void(0)"
                                           data-index="${status.index}">${childMenu.menuName }</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="J_menuItem" href="${_ctx }${childMenu.url }"
                                           data-index="${status.index}">${childMenu.menuName }</a>
                                    </c:otherwise>
                                </c:choose></li>
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
                    <a class="navbar-minimalize minimalize-styl-2 btn btn-primary "
                       href="#"><i class="fa fa-bars"></i> </a>
                    <form role="search" class="navbar-form-custom" method="get"
                          action="https://www.baidu.com/s">
                        <div class="form-group">
                            <input type="text" placeholder="百度一下，了解更多"
                                   class="form-control" id="wd" name="wd">
                        </div>
                    </form>
                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <li onclick="fullScreen()">
                        <a title="全屏显示"><i class="fa fa-arrows-alt"></i> 全屏显示</a>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle count-info"
                           data-toggle="dropdown" href="#"> <i class="fa fa-envelope"></i>
                            <span class="label label-warning">{{total}}</span>通知
                        </a>
                        <ul class="dropdown-menu dropdown-messages">
                            <li v-for="row in rows" class="m-t-xs">
                                <div class="dropdown-messages-box">
                                    <a class="pull-left" v-on:click="viewNotifyRecord(row.notifyRecordId)">
                                        <i class="fa fa-paper-plane"></i>
                                    </a>
                                    <div class="media-body">
                                        <small class="pull-right">{{row.before}}</small>
                                        <strong>{{row.createBy}}</strong>
                                        {{row.title}} <br>
                                        <small class="text-muted">{{row.updateTime}}</small>
                                    </div>
                                </div>
                                <div class="divider"></div>
                            </li>
                            <li>
                                <div class="text-center link-block">
                                    <a class="J_menuItem" href="${_ctx}/oa/notifyRecord">
                                        <i class="fa fa-envelope"></i> <strong> 我的通知</strong>
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown hidden-xs"><a
                            class="right-sidebar-toggle" aria-expanded="false"> <i
                            class="fa fa-tasks"></i> 主题
                    </a></li>


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
                <button class="dropdown J_tabClose" data-toggle="dropdown">
                    关闭操作<span class="caret"></span>

                </button>
                <ul role="menu" class="dropdown-menu dropdown-menu-right">
                    <!-- <li class="J_tabShowActive"><a>定位当前选项卡</a></li>
                    <li class="divider"></li> -->
                    <li class="J_tabRefresh"><a>刷新当前选项卡</a></li>
                    <li class="J_tabCloseOther"><a>关闭其他选项卡</a></li>
                    <li class="J_tabCloseAll"><a>关闭全部选项卡</a></li>
                </ul>
            </div>
            <a href="${_ctx }/home/logout" class="roll-nav roll-right J_tabExit"><i
                    class="fa fa fa-sign-out"></i> 退出</a>
        </div>
        <div class="row J_mainContent" id="content-main">
            <iframe class="J_iframe" name="iframe0" width="100%" height="100%"
                    src="${_ctx }/main/mainIndex1" frameborder="0"
                    data-id="${_ctx }/main/mainIndex1" seamless></iframe>
        </div>
        <div class="footer">
            <div class="pull-right">
                © 2019 WAYN Copyright
            </div>
        </div>
    </div>
    <!--右侧部分结束-->
    <!--右侧边栏开始-->
    <div id="right-sidebar">
        <div class="sidebar-container">
            <ul class="nav nav-tabs navs-3">
                <li class="active"><a data-toggle="tab" href="#tab-1"> <i
                        class="fa fa-gear"></i> 主题
                </a></li>
            </ul>
            <div class="tab-content">
                <div id="tab-1" class="tab-pane active">
                    <div class="sidebar-title">
                        <h3>
                            <i class="fa fa-comments-o"></i> 主题设置
                        </h3>
                        <small><i class="fa fa-tim"></i>
                            你可以从这里选择和预览主题的布局和样式，这些设置会被保存在本地，下次打开的时候会直接应用这些设置。 </small>
                    </div>
                    <div class="skin-setttings">
                        <div class="title">主题设置</div>
                        <div class="setings-item">
                            <span>收起左侧菜单</span>
                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="collapsemenu"
                                           class="onoffswitch-checkbox" id="collapsemenu"> <label
                                        class="onoffswitch-label" for="collapsemenu"> <span
                                        class="onoffswitch-inner"></span> <span
                                        class="onoffswitch-switch"></span>
                                </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                            <span>固定顶部</span>

                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="fixednavbar"
                                           class="onoffswitch-checkbox" id="fixednavbar"> <label
                                        class="onoffswitch-label" for="fixednavbar"> <span
                                        class="onoffswitch-inner"></span> <span
                                        class="onoffswitch-switch"></span>
                                </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                            <span> 固定宽度 </span>

                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="boxedlayout"
                                           class="onoffswitch-checkbox" id="boxedlayout"> <label
                                        class="onoffswitch-label" for="boxedlayout"> <span
                                        class="onoffswitch-inner"></span> <span
                                        class="onoffswitch-switch"></span>
                                </label>
                                </div>
                            </div>
                        </div>
                        <div class="title">皮肤选择</div>
                        <div class="setings-item default-skin nb">
								<span class="skin-name "> <a href="#" class="s-skin-0">
										默认皮肤 </a>
								</span>
                        </div>
                        <div class="setings-item blue-skin nb">
								<span class="skin-name "> <a href="#" class="s-skin-1">
										蓝色主题 </a>
								</span>
                        </div>
                        <div class="setings-item yellow-skin nb">
								<span class="skin-name "> <a href="#" class="s-skin-3">
										黄色/紫色主题 </a>
								</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--右侧边栏结束-->
</div>
<%@ include file="/commom/footer.jsp" %>
<script src="${_ctx }/static/plugin/metisMenu/jquery.metisMenu.js"></script>
<script src="${_ctx }/static/plugin/slimscroll/jquery.slimscroll.min.js"></script>
<script src="${_ctx }/static/js/hplus/hplus.js?v=4.1.0"></script>
<script src="${_ctx }/static/js/hplus/contabs.js"></script>
<script src="${_ctx }/static/plugin/pace/pace.min.js"></script>
<script src="${_ctx }/static/plugin/fullscreen/jquery.fullscreen.js"></script>
<script src="${_ctx }/static/plugin/toastr/toastr.min.js"></script>
<script src="${_ctx }/static/plugin/socket/sockjs.min.js"></script>
<script src="${_ctx }/static/plugin/socket/stomp.min.js"></script>
<script src="${_ctx }/static/plugin/vue-2.2.2/vue.min.js"></script>
<script>

    var prefix = _ctx + '/oa/notifyRecord';

    toastr.options = {
        "closeButton": true,
        "debug": false,
        "progressBar": true,
        "positionClass": "toast-bottom-right",
        "onclick": null,
        "showDuration": "400",
        "hideDuration": "1000",
        "timeOut": "14000",
        "extendedTimeOut": "5000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    };

    /**
     * 全屏显示
     *
     */
    function fullScreen() {
        $("#wrapper").fullScreen();
    }

    $(function () {
        connect();
    });
    var stomp = null;

    function connect() {
        var sock = new SockJS(_ctx + "/notify");
        var stomp = Stomp.over(sock);
        stomp.connect('guest', 'guest', function (frame) {
            /**  订阅了/user/queue/notifications 发送的消息,这里于在控制器的 convertAndSendToUser 定义的地址保持一致, 
             *  这里多用了一个/user,并且这个user 是必须的,使用user 才会发送消息到指定的用户。 
             */
            stomp.subscribe("/user/queue/notifications", handleNotification);
            stomp.subscribe('/topic/getResponse', function (response) { //订阅/topic/getResponse 目标发送的消息。这个是在控制器的@SendTo中定义的。
                toastr.info(JSON.parse(response.body).responseMessage);
            });
        });

        function handleNotification(message) {
            wrapper.notify();
            toastr.info(message.body);
        }
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
                    wrapper.total = r.total;
                    wrapper.rows = r.records;
                });
            },
            personal: function () {
                layer.open({
                    type: 2,
                    title: '个人设置',
                    maxmin: true,
                    shadeClose: false,
                    area: ['800px', '600px'],
                    content: '/sys/user/personal'
                });
            }
        },
        created: function () {
            this.notify()
        }
    });

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
                wrapper.notify();
            },
            btn: ['取消'],
            cancel: function (index) {
                return true;
            }
        });
        layer.full(index);
    }
</script>
</body>


<!-- Mirrored from www.zi-han.net/theme/hplus/ by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:17:11 GMT -->
</html>
