<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@ include file="/commom/taglib.jsp" %>
    <%@ include file="/commom/header.jsp" %>
    <title>500</title>
</head>
<body class="gray-bg">
<div class="middle-box text-center animated fadeInDown">
    <h1>403</h1>
    <h3 class="font-bold">您没有访问权限！</h3>
    <div class="error-desc">
        对不起，您没有访问权限，请不要进行非法操作！
        <br/>您可以返回主页面<br/>
        <a href="${_ctx }" class="btn btn-primary m-t">主页</a>
    </div>
</div>
</body>
</html>