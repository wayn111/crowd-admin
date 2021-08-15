<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<%@ include file="/common/taglib.jsp" %>
<%@ include file="/common/header.jsp" %>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form class="form-horizontal m-t layui-form" id="config-form">
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="configName"><span
                                    class="wayn-required-span">*</span>参数名称：</label>
                            <div class="col-sm-8">
                                <input name="configId" class="form-control"
                                       type="hidden" value="${config.configId }">
                                <input id="configName" name="configName" class="form-control"
                                       type="text" value="${config.configName }">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="configKey"><span
                                    class="wayn-required-span">*</span>参数键名：</label>
                            <div class="col-sm-8">
                                <input id="configKey" name="configKey" class="form-control" <c:if test="${config.configType == 'Y'}" >readonly</c:if>
                                       type="text" value="${config.configKey }">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="configValue"><span
                                    class="wayn-required-span">*</span>参数键值：</label>
                            <div class="col-sm-8">
                                <input id="configValue" name="configValue" class="form-control"
                                       type="text" value="${config.configValue }">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">系统内置：</label>
                            <div class="col-sm-8">
                                <c:forEach items="${sysBuildIn}" var="item">
                                    <label class="checkbox-inline i-checks">
                                        <input name="configType" type="radio" value="${item.id}"
                                               <c:if test="${item.id == config.configType }">checked</c:if>>${item.text }
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">参数备注：</label>
                            <div class="col-sm-8">
                                <textarea id="remarks" name="remarks" class="form-control" rows="3">${config.remarks }</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-8 col-sm-offset-3">
                                <button type="submit" class="btn btn-primary">提 交</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/common/footer.jsp" %>
<script>
    var prefix = _ctx + '/system/config';

    function validateRule() {
        var e = '<i class="fa fa-times-circle"></i> ';
        $("#config-form").validate({
            rules: {
                "configName": {
                    required: true
                },
                "configKey": {
                    required: true
                },
                "configValue": {
                    required: true
                },
            },
            messages: {
                "configName": {
                    required: e + "请输入参数名称"
                },
                "configKey": {
                    required: e + "请输入参数键名"
                },
                "configValue": {
                    required: e + "请输入角色键值"
                },
            },
            ignore: ".ignore",
            focusCleanup: true,
            submitHandler: function () {
                update();
            }
        });
    }

    function update() {
        var config = {
            url: prefix + "/editSave",
            data: $('#config-form').serialize(),
            type: "POST",
            dataType: "json",
            success: function (data) {
                if (data.code != 100) {
                    layer.alert(data.msg);
                } else {
                    parent.layer.msg(data.msg, {icon: 1});
                    parent.reload();
                    //关闭当前窗口
                    var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                    parent.layer.close(index);
                }
            },
            error: function (err) {
                console.log(err)
            }
        };
        $.ajax(config);
    }

    $(function () {
        validateRule();
    })
</script>
</body>

</html>
