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
    <h1>500</h1>
    <h3 class="font-bold">业务异常</h3>
    <div class="error-desc">
        ${msg }
        <br/>您可以返回主页看看 <br/>
        <a href="${_ctx }" class="btn btn-primary m-t">主页</a>
    </div>
</div>

</body>
</html>