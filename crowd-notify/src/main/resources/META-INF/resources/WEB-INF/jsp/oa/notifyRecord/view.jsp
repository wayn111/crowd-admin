<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>crowd-admin 通知页面</title>
    <%@ include file="/commom/taglib.jsp" %>
    <link href="${_ctx }/static/plugin/bootstrap-v3.3.7/css/bootstrap.min.css?v=3.3.7"
          rel="stylesheet">
<%--    <link href="${_ctx }/static/css/font-awesome.css?v=4.7.0" rel="stylesheet">--%>
    <link href="https://cdn.bootcdn.net/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<%--    <link href="${_ctx }/static/css/animate.min.css" rel="stylesheet">--%>
    <link href="https://cdn.bootcdn.net/ajax/libs/animate.css/4.1.1/animate.min.css" rel="stylesheet">
    <link href="${_ctx }/static/css/style.min.css?v=4.0" rel="stylesheet">
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content  animated fadeInRight article">
    <div class="row">
        <div class="col-lg-10 col-lg-offset-1">
            <div class="ibox">
                <div class="ibox-content">
                    <div class="pull-right">
                        <button class="btn btn-white btn-xs" type="button">${notifyRecordVO.type == 1?'公告':'通知'}</button>
                        <%--<button class="btn btn-white btn-xs" type="button">BeginOne</button>
                        <button class="btn btn-white btn-xs" type="button">乐视超级自行车</button>--%>
                    </div>
                    <div class="text-center article-title">
                        <span class="text-muted"><i class="fa fa-clock-o"></i>
                            <fmt:formatDate value="${notifyRecordVO.createTime}" pattern="yyyy年MM月dd日" />
                        </span>
                        <h1>
                            ${notifyRecordVO.title}
                        </h1>
                    </div>
                    ${notifyRecordVO.content}
                    <div class="pull-right" style="font-size: 14px;">
                        ${notifyRecordVO.createBy}
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<%--<script src="${_ctx }/static/plugin/jquery/jquery.min.js"></script>--%>
<script src="https://cdn.bootcdn.net/ajax/libs/jquery/2.2.4/jquery.js"></script>
<script src="${_ctx }/static/plugin/bootstrap-v3.3.7/js/bootstrap.min.js?v=3.3.7"></script>
</body>
<!-- Mirrored from www.zi-han.net/theme/hplus/article.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:47 GMT -->
</html>
