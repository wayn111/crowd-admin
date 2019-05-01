<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/commom/taglib.jsp"%>
<%@ include file="/commom/header.jsp"%>
<title>403</title>
</head>
<body class="gray-bg">
	<div class="middle-box text-center animated fadeInDown">
		<h1>403</h1>
		<h3 class="font-bold">您没有访问权限！</h3>
		<div class="error-desc">
			抱歉，请联系管理员哦~
			<form class="form-inline m-t" role="form">
				<div class="form-group">
					<input type="email" class="form-control"
						placeholder="请输入您需要查找的内容 …">
				</div>
				<button type="submit" class="btn btn-primary">搜索</button>
			</form>
		</div>
	</div>
	<!-- 全局js -->
	<script src="/js/jquery.min.js?v=2.1.4"></script>
	<script src="/js/bootstrap.min.js?v=3.3.6"></script>
</body>
</html>