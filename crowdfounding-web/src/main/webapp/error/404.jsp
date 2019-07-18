<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="/commom/taglib.jsp" %>
    <%@ include file="/commom/header.jsp" %>
    <title>403</title>
</head>
<body class="gray-bg">
<div class="middle-box text-center animated fadeInDown">
    <h1>403</h1>
    <h3 class="font-bold">找不到网页！</h3>
    <div class="error-desc">
        对不起，您正在寻找的页面不存在。尝试检查URL的错误，然后按浏览器上的刷新按钮或尝试在我们的应用程序中找到其他内容。<br/>
        <a href="${_ctx }" class="btn btn-primary m-t">主页</a>
    </div>
</div>
<!-- 全局js -->
<script src="/js/jquery.min.js?v=2.1.4"></script>
<script src="/js/bootstrap.min.js?v=3.3.6"></script>
</body>
</html>