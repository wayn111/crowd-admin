<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<%@ include file="/commom/taglib.jsp" %>
<%@ include file="/commom/header.jsp" %>
<link href="${_ctx }/static/plugin/json-veiw/jquery.jsonview.css"
      rel="stylesheet">
<body class="gray-bg">
<div class="wrapper wrapper-content ">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <label class="margin-left10">参数信息：</label>
                <div class="form-group">
                    <div class="col-sm-12 form-control-static">
                        <pre id="paramJsonView"></pre>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <label class="margin-left10">浏览器信息：</label>
                <div class="form-group wayn-div-padding">
                    <div id="agent" class="col-sm-12 form-control-static wayn-div" rows="3" readonly></div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <label class="margin-left10">错误信息：</label>
                <div class="form-group wayn-div-padding">
                    <div id="errorMsg" class="col-sm-12 form-control-static wayn-div" rows="3" readonly></div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/commom/footer.jsp" %>
<script src="${_ctx }/static/plugin/json-veiw/jquery.jsonview.js"></script>
<script>
    $(function () {
        $('#paramJsonView').JSONView('${log.requestParams}');
        $('#agent').text('${log.agent}');
        var content = '${log.errorMsg}'.replace(/&quot;/g, '\'');
        $('#errorMsg').text(content);
    })
</script>
</body>
</html>
