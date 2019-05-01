<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="_ctx" value="${pageContext.request.contextPath}" />
<c:set var="_session" value="<%=session.getId()%>" />
<script type="text/javascript">
	let _ctx = '${_ctx}';
	let _session = '${_session}';
</script>