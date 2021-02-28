<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <%@ include file="/commom/taglib.jsp" %>
    <%@ include file="/commom/header.jsp" %>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>欢迎页</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
          name="viewport">
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-6">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>项目介绍</h5>
                    <%--<div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="draggable_panels.html#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="draggable_panels.html#">选项1</a>
                            </li>
                            <li><a href="draggable_panels.html#">选项2</a>
                            </li>
                        </ul>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>--%>
                </div>
                <div class="ibox-content">
                    <blockquote class="text-muted" style="font-size:14px">
                        面向学习型的开源框架，简洁高效，合理的模块化拆分，展现技术本质
                        <br>${sysName }是以SpringMVC+Shiro+Mybatis-Plus为核心开发的精简后台基础系统
                        <br>基于stomp协议实现后台消息推送以及指定用户通信的功能
                        <br>整合elfinder文件管理
                        <br>整合quartz实现任务调度级任务日志记录
                        <br>Ehcache/Redis作为权限缓存，可切换
                        <br>Druid数据源,数据库监控
                        <br>使用H+作为后台管理模板，基于jsp模板改造
                        <br>如果你先找一个简单易学，代码风格良好，基于现有功能快速上手开发的话
                        <h4 class="text-danger">那么，现在${sysName }来了</h4>
                    </blockquote>

                    <h3>&nbsp;&nbsp;&nbsp;获取源码</h3>
                    <ul>
                        <li>
                            <a href="https://gitee.com/wayn111/crowdfounding" target="_blank">${sysName } 码云</a>
                        </li>
                        <li>
                            <a href="https://github.com/wayn111/crowd-admin" target="_blank">${sysName } github</a>
                        </li>
                    </ul>

                    <h3>&nbsp;&nbsp;&nbsp;参考项目</h3>
                    <ul>
                        <li><a href="https://gitee.com/zhougaojun/KangarooAdmin/tree/master"
                               target="_blank"> AdminLTE-admin</a></li>
                        <li><a href="https://gitee.com/lcg0124/bootdo" target="_blank">
                            bootdo</a></li>
                        <li><a href="https://gitee.com/y_project/RuoYi" target="_blank">
                            ruoyi</a></li>
                        <li><a href="https://github.com/wuyouzhuguli/FEBS-Security" target="_blank">
                            FEBS-Security</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-sm-6">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>图表</h5>
                </div>
                <div class="ibox-content">
                    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
                    <div id="main" style="height:500px;"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>联系信息</h5>
                </div>
                <div class="ibox-content">
                    <%--<p><i class="fa fa-send-o"></i> 官网：<a href="http://www.ruoyi.vip" target="_blank">http://www.ruoyi.vip</a>
                    </p>--%>
                    <p><i class="fa fa-qq"></i>QQ：
                        <a target="_blank"
                           href="http://wpa.qq.com/msgrd?v=3&uin=1669738430&site=qq&menu=yes"><img
                                border="0" src="http://wpa.qq.com/pa?p=2:1669738430:52" alt="1669738430"
                                title="1669738430"/>1669738430</a>
                    </p>
                    <p><i class="fa fa-weixin"></i> 微信：<a href="javascript:;">/ *waynaqua</a>
                    </p>
                    <p><i class="fa fa-credit-card"></i> 支付宝：<a href="javascript:;" class="支付宝信息">/ *waynaqua</a>
                    </p>
                    <%--<p id="pay-qrcode">
                        <a href="javascript:;"><img src="${_ctx}/static/img/pay.png" width="100%" alt="请使用手机支付宝或者微信扫码支付">
                        </a>
                    </p>--%>
                </div>
            </div>
        </div>
        <div class="col-sm-6">
            <div class="ibox float-e-margins no-drop">
                <div class="ibox-title">
                    <h5>更新日志</h5>
                    <%--<div class="ibox-tools">
                        <label class="label label-danger">不可拖动</label>
                    </div>--%>
                </div>
                <div class="ibox-content">
                    <div class="panel-body">
                        <div class="panel-group" id="version">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h5 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#version" href="#v21">v2.1.0</a><code
                                            class="pull-right">2021.02.20</code>
                                    </h5>
                                </div>
                                <div id="v21" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <div class="alert alert-success">
                                            升级mybatis plus至3.4.2，优化移动端显示效果，修复bug
                                        </div>
                                        <ol>
                                            <li>升级mybatis plus至3.4.2</li>
                                            <li>优化移动端显示效果</li>
                                            <li>修复bug</li>
                                        </ol>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h5 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#version" href="#v20">v2.0.0</a><code
                                            class="pull-right">2020.12.17</code>
                                    </h5>
                                </div>
                                <div id="v20" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <div class="alert alert-success">
                                            优化前端资源,升级前端插件,性能优化,修复bug
                                        </div>
                                        <ol>
                                            <li>升级bootstrap-table版本至1.18.0</li>
                                            <li>升级metisMenu版本至3.0.6</li>
                                            <li>升级contextMenu版本至2.9.2</li>
                                            <li>升级summernote版本至0.8.18</li>
                                            <li>升级toastr版本</li>
                                            <li>修复登陆页面登陆bug</li>
                                        </ol>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h5 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#version" href="#v19">v1.9.0</a><code
                                            class="pull-right">2020.11.23</code>
                                    </h5>
                                </div>
                                <div id="v19" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <div class="alert alert-success">
                                            优化文件管理菜单
                                        </div>
                                        <ol>
                                            <li>升级elfinder版本至2.1.57</li>
                                            <li>修复文件管理首页树列表显示bug</li>
                                            <li>修复文件管理put命令写入图片bug</li>
                                            <li>修复文件管理get命令获取图片bug</li>
                                        </ol>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h5 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#version" href="#v18">v1.8.0</a><code
                                            class="pull-right">2020.9.17</code>
                                    </h5>
                                </div>
                                <div id="v18" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <div class="alert alert-success">
                                            添加登陆日志，系统参数管理
                                        </div>
                                        <ol>
                                            <li>添加登录日志菜单</li>
                                            <li>添加系统参数菜单</li>
                                            <li>优化主页左侧菜单显示</li>
                                            <li>优化导航栏与左侧菜单联动显示</li>
                                            <li>优化代码生成预览显示</li>
                                            <li>优化数插件显示效果</li>
                                            <li>用户列表添加部门名称显示</li>
                                            <li>用户列表添加昵称显示</li>
                                            <li>pom文件依赖升级</li>
                                        </ol>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h5 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#version" href="#v16">v1.6.0</a><code
                                            class="pull-right">2020.1.3</code>
                                    </h5>
                                </div>
                                <div id="v16" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <div class="alert alert-success">
                                            整合elfinder文件管理模块
                                        </div>
                                        <ol>
                                            <li>基础管理 -> 文件管理功能开发完成；</li>
                                            <li>优化部分代码；</li>
                                        </ol>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h5 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#version" href="#v15">v1.5.0</a><code
                                            class="pull-right">2019.9.6</code>
                                    </h5>
                                </div>
                                <div id="v15" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <div class="alert alert-success">
                                            整合quartz实现任务调度及任务日志记录，修复代码生成，办公通知，基础模块部分bug
                                        </div>
                                        <ol>
                                            <li>系统工具 -> 任务调度功能开发完成；</li>
                                            <li>系统工具 -> 任务日志功能开发完成；</li>
                                            <li>修复了代码生成，办公通知，基础模块的一些bug以及代码优化；</li>
                                        </ol>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h5 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#version" href="#v12">v1.2.0</a><code
                                            class="pull-right">2019.8.24</code>
                                    </h5>
                                </div>
                                <div id="v12" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <div class="alert alert-success">
                                            添加个人资料以及头像上传功能，完成头部导航通知栏实时提示办公消息功能,修复了一些消息通知的bug以及代码优化
                                        </div>
                                        <ol>
                                            <li>个人资料 -> 我的资料功能开发完成；</li>
                                            <li>个人资料 -> 上传头像功能开发完成；</li>
                                            <li>头部导航 -> 通知栏提示功能开发完成；</li>
                                            <li>修复了办公通知的一些bug以及代码优化；</li>
                                        </ol>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h5 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#version" href="#v11">v1.1.0</a><code
                                            class="pull-right">2019.8.21</code>
                                    </h5>
                                </div>
                                <div id="v11" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <div class="alert alert-success">
                                            添加办公通知模块，实现了基于stomp后台消息推送功能，方便系统用户进行消息通知发布与管理
                                        </div>
                                        <ol>
                                            <li>办公通知 -> 我的通知功能开发完成；</li>
                                            <li>办公通知 -> 通知管理功能开发完成；</li>
                                            <li>全模块代码优化重构；</li>
                                        </ol>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h5 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#version" href="#v10">v1.0.0</a><code
                                            class="pull-right">2019.8.10</code>
                                    </h5>
                                </div>
                                <div id="v10" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <div class="alert alert-success">正式发布V1.0版本</div>
                                        <ol>
                                            <li>系统工具 -> 代码生成功能开发完成；</li>
                                            <li>基础管理 -> 字典管理功能开发完成；</li>
                                            <li>系统监控 -> 字典管理功能开发完成；</li>
                                            <li>基础管理 -> 在线用户功能开发完成；</li>
                                            <li>基础管理 -> 数据监控功能开发完成；</li>
                                            <li>基础管理 -> 系统服务功能开发完成；</li>
                                            <li>代码优化重构；</li>
                                            <li>正式推送至gitee，github；</li>
                                        </ol>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h5 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#version" href="#v05">v0.0.5</a><code
                                            class="pull-right">2019.6.10</code>
                                    </h5>
                                </div>
                                <div id="v05" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <div class="alert alert-success">基本功能开发完成</div>
                                        <ol>
                                            <li>登陆/退出功能开发完成；</li>
                                            <li>系统管理 -> 用户功能开发完成；</li>
                                            <li>系统管理 -> 菜单功能开发完成；</li>
                                            <li>系统管理 -> 角色功能开发完成；</li>
                                            <li>系统管理 -> 部门功能开发完成；</li>
                                        </ol>
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h5 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#version" href="#v01">v0.0.1</a><code
                                            class="pull-right">2019.4.10</code>
                                    </h5>
                                </div>
                                <div id="v01" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        <div class="alert alert-success">项目立项</div>
                                        <ol>
                                            <li>crowd-admin项目初始化；</li>
                                        </ol>
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
<%--<%@ include file="/commom/footer.jsp" %>--%>
<a id="scroll-up" href="#" class="btn btn-sm display"><i class="fa fa-angle-double-up"></i></a>
<script src="${_ctx }/static/plugin/jquery/jquery.min.js"></script>
<script src="${_ctx }/static/plugin/bootstrap-v3.3.7/js/bootstrap.min.js?v=3.3.7"></script>
<script src="${_ctx }/static/plugin/echarts-v5.0.2/echarts.min.js?v=5.0.2"></script>
<script src="${_ctx }/static/js/common/common-ui.js"></script>
<script>
    $(function () {
        // 回到顶部绑定
        if ($.fn.toTop !== undefined) {
            $('#scroll-up').toTop();
        }

        var chartDom = document.getElementById('main');
        var myChart = echarts.init(chartDom);
        var option;

        option = {
            title: {
                text: '全国各省访问次数',
                subtext: '',
                left: 'right'
            },
            tooltip: {
                trigger: 'item'
            },
            legend: {
                type: 'scroll',
                left: 'left',
                orient: 'vertical',
                right: 10,
                top: 20,
                bottom: 20,
            },
            series: [
                {
                    name: '访问',
                    type: 'pie',
                    radius: '50%',
                    center: ['50%', '50%'],
                    data: [
                        {value: 108, name: '湖北'},
                        {value: 73, name: '湖南'},
                        {value: 58, name: '河北'},
                        {value: 48, name: '广东'},
                        {value: 30, name: '黑龙江'}
                    ],
                    emphasis: {
                        itemStyle: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };
        $.get(_ctx + '/main/countryProvinceCount', function (res) {
            if (res.code == 100) {
                option.series[0].data = res.map.data;
            }
            option && myChart.setOption(option);
        });

    })
</script>
</body>
</html>
