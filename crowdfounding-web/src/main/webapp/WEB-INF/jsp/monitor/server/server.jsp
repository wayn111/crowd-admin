<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>crowdfounding 服务监控</title>
    <meta name="keywords" content="crowdfounding,基于H+,后台HTML,响应式后台">
    <meta name="description"
          content="design by wayn">
    <%@ include file="/commom/taglib.jsp" %>
    <%@ include file="/commom/header.jsp" %>
    <style type="text/css">
        .table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td {
            border-bottom: 1px solid #e7eaec;
            border-top: 0px solid #e7eaec;
            line-height: 1.42857;
            padding: 8px;
            vertical-align: middle;
        }
    </style>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="col-sm-12">
        <div class="row">
            <div class="col-sm-6">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>CPU</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link"><i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="close-link"><i class="fa fa-times"></i></a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <table class="table table-hover no-margins">
                            <thead>
                            <tr>
                                <th>属性</th>
                                <th>值</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>核心数</td>
                                <td>${server.cpu.cpuNum}</td>
                            </tr>
                            <tr>
                                <td>用户使用率</td>
                                <td>${server.cpu.used}%</td>
                            </tr>
                            <tr>
                                <td>系统使用率</td>
                                <td>${server.cpu.sys}%</td>
                            </tr>
                            <tr>
                                <td>当前空闲率</td>
                                <td>${server.cpu.free}%</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <div class="col-sm-6">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>内存</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                            <a class="close-link"><i class="fa fa-times"></i></a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <table class="table table-hover no-margins">
                            <thead>
                            <tr>
                                <th>属性</th>
                                <th>内存</th>
                                <th>JVM</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>总内存</td>
                                <td>${server.mem.total}G</td>
                                <td>${server.jvm.total}M</td>
                            </tr>
                            <tr>
                                <td>已用内存</td>
                                <td>${server.mem.used}GB</td>
                                <td>${server.jvm.used}MB</td>
                            </tr>
                            <tr>
                                <td>剩余内存</td>
                                <td>${server.mem.free}GB</td>
                                <td>${server.jvm.free}MB</td>
                            </tr>
                            <tr>
                                <td>使用率</td>
                                <td class="${server.mem.usage lt 70?'':'text-danger'}">${server.mem.usage}</td>
                                <td class="${server.jvm.usage lt 70?'':'text-danger'}">${server.jvm.usage}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>服务器信息</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="row">
                            <div class="col-sm-12">
                                <table class="table table-hover margin bottom">
                                    <tbody>
                                    <tr>
                                        <td>服务器名称</td>
                                        <td>${server.sys.computerName}</td>
                                        <td>操作系统</td>
                                        <td>${server.sys.osName}</td>
                                    </tr>
                                    <tr>
                                        <td>服务器IP</td>
                                        <td>${server.sys.computerIp}</td>
                                        <td>系统架构</td>
                                        <td>${server.sys.osArch}</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Java虚拟机信息</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">

                        <div class="row">
                            <div class="col-sm-12">
                                <table class="table table-hover margin bottom">
                                    <tbody>
                                    <tr>
                                        <td>Java名称</td>
                                        <td>${server.jvm.name}</td>
                                        <td>Java版本</td>
                                        <td>${server.jvm.version}</td>
                                    </tr>
                                    <tr>
                                        <td>启动时间</td>
                                        <td>${server.jvm.startTime}</td>
                                        <td>运行时长</td>
                                        <td>${server.jvm.runTime}</td>
                                    </tr>
                                    <tr>
                                        <td colspan="1">安装路径</td>
                                        <td colspan="3">${server.jvm.home}</td>
                                    </tr>
                                    <tr>
                                        <td colspan="1">项目路径</td>
                                        <td colspan="3">${server.sys.userDir}</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>磁盘状态</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">

                        <div class="row">
                            <div class="col-sm-12">
                                <table class="table table-hover margin bottom">
                                    <thead>
                                    <tr>
                                        <th>盘符路径</th>
                                        <th>文件系统</th>
                                        <th>盘符类型</th>
                                        <th>总大小</th>
                                        <th>可用大小</th>
                                        <th>已用大小</th>
                                        <th>已用百分比</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${server.sysFiles}" var="sysFile">
                                        <tr>
                                            <td>${sysFile.dirName}</td>
                                            <td>${sysFile.sysTypeName}</td>
                                            <td>${sysFile.typeName}</td>
                                            <td>${sysFile.total}GB</td>
                                            <td>${sysFile.free}GB</td>
                                            <td>${sysFile.used}GB</td>
                                            <td class="${sysFile.usage lt 80?'':'text-danger'}">${sysFile.usage}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@ include file="/commom/footer.jsp" %>
<script>
    $(".modal").appendTo("body"), $("[data-toggle=popover]").popover(), $(".collapse-link").click(function () {
        var div_ibox = $(this).closest("div.ibox"),
            e = $(this).find("i"),
            i = div_ibox.find("div.ibox-content");
        i.slideToggle(200),
            e.toggleClass("fa-chevron-up").toggleClass("fa-chevron-down"),
            div_ibox.toggleClass("").toggleClass("border-bottom"),
            setTimeout(function () {
                div_ibox.resize();
            }, 50);
    }), $(".close-link").click(function () {
        var div_ibox = $(this).closest("div.ibox");
        div_ibox.remove()
    });
</script>
</body>
<!-- Mirrored from www.zi-han.net/theme/hplus/login.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:18:23 GMT -->
</html>
