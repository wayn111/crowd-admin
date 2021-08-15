<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<%@ include file="/common/taglib.jsp"%>
<%@ include file="/common/header.jsp"%>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>欢迎页</title>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
</head>
<body>
	<div class="panel panel-default">
		<%--<div class="panel-heading">了解crowd-admin</div>--%>
		<div style="padding: 10px 0 20px 10px;">
			<h3>&nbsp;&nbsp;&nbsp;项目介绍</h3>
			<ul>
				<li>面向学习型的开源框架，简洁高效，合理的模块化拆分，展现技术本质</li>
				<li>${sysName }是以SpringMVC+Shiro+Mybatis-Plus为核心开发的精简后台基础系统。</li>
				<li>Encache权限缓存。</li>
				<li>Druid数据源,数据库监控。</li>
				<li>使用H+作为后台管理模板，基于jsp模板改造</li>
				<li>集成字典管理和在线用户以及日志记录。</li>
				<li>添加代码生成模块。</li>
			</ul>

			<h3>&nbsp;&nbsp;&nbsp;获取源码</h3>
			<ul>
				<li>
					<a href="https://gitee.com/wayn111/crowd-admin" target="_blank">${sysName } 码云</a>
				</li>
				<li>
					<a href="https://github.com/wayn111/crowd-admin" target="_blank">${sysName } github</a>
				</li>
			</ul>

			<h3>&nbsp;&nbsp;&nbsp;参考项目</h3>
			<ul>
				<li><a
					href="https://gitee.com/zhougaojun/KangarooAdmin/tree/master"
					target="_blank"> AdminLTE-admin</a></li>
				<li><a href="https://gitee.com/lcg0124/bootdo" target="_blank">
						bootdo</a></li>
				<li><a href="https://gitee.com/y_project/RuoYi" target="_blank">
						ruoyi</a></li>
			</ul>
		</div>
	</div>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>
