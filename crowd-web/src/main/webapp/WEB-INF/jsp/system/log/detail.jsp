<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<%@ include file="/common/taglib.jsp" %>
<%@ include file="/common/header.jsp" %>
<link href="${_ctx }/static/plugin/json-veiw/jquery.jsonview.css"
      rel="stylesheet">
<body class="gray-bg">
<div class="wrapper wrapper-content ">
    <form class="form-horizontal m-t" id="user-form">
        <div class="form-group">
            <label class="col-sm-2 control-label">操作用户：</label>
            <div class="form-control-static">
                <strong>${log.userName}</strong>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">模块名称：</label>
            <div class="form-control-static">
                <strong>${log.moduleName}</strong>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">请求状态：</label>
            <div class="form-control-static">
                <span class="badge ${log.operState == 1 ? 'badge-primary': 'badge-danger' }">${log.operState == 1 ? '成功': '失败' }</span>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">操作类型：</label>
            <div class="form-control-static">
                <strong>${log.operation}</strong>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">请求方法：</label>
            <div class="form-control-static">
                <strong>${log.method}</strong>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">请求类型：</label>
            <div class="form-control-static">
                <strong>${log.requestMethod}</strong>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">执行时间：</label>
            <div class="form-control-static">
                <strong>${log.executeTime}</strong>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">请求url：</label>
            <div class="form-control-static">
                <strong>${log.url}</strong>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">请求ip：</label>
            <div class="form-control-static">
                <strong>${log.ip}</strong>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">浏览器信息：</label>
            <div class="form-control-static">
                <strong>${log.agent}</strong>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">错误信息：</label>
            <div class="form-control-static">
                <strong>${log.errorMsg}</strong>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">参数信息：</label>
            <div class="form-control-static">
                <pre id="paramJsonView"></pre>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">请求响应：</label>
            <div class="form-control-static">
                <pre id="responseJsonView"></pre>
            </div>
        </div>
    </form>
</div>
<%@ include file="/common/footer.jsp" %>
<script src="${_ctx }/static/plugin/json-veiw/jquery.jsonview.js"></script>
<script>
    $(function () {
        $('#paramJsonView').JSONView('${log.requestParams}');
        $('#responseJsonView').JSONView('${log.requestResponse}');
    })
</script>
</body>
</html>
