<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<%@ include file="/common/taglib.jsp" %>
<%@ include file="/common/header.jsp" %>
<body class="gray-bg">
<div class="wrapper wrapper-content ">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins animated fadeInRight">
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="dict-form">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">标签名：</label>
                            <div class="col-sm-8">
                                <%--字典类别--%>
                                <input name="type" type="hidden" value="${type}">
                                <input id="name" name="name" class="form-control"
                                       type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">字典值：</label>
                            <div class="col-sm-8">
                                <input id="value" name="value" class="form-control"
                                       type="value">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">字典状态:</label>
                            <div class="col-sm-8">
                                <input type="hidden" id="dictState" name="dictState" value="1">
                                <input type="checkbox" name="dictStateSwicth" checked>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">排序：</label>
                            <div class="col-sm-8">
                                <input id="sort" name="sort" class="form-control" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">备注：</label>
                            <div class="col-sm-8">
                                <input id="remarks" name="remarks" class="form-control" type="text">
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
    var prefix = _ctx + "/common/dict/data"

    function save() {
        $.ajax({
            cache: false,
            type: "POST",
            url: prefix + "/addSave",
            dataType: 'json',
            data: $('#dict-form').serialize() + '&dictType=' + window.parent.dictType, // 字典类别
            error: function (request) {
                parent.layer.alert("Connection error");
            },
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
            }
        });
    }

    function validateRule() {
        var icon = "<i class='fa fa-times-circle'></i> ";
        $("#dict-form").validate({
            rules: {
                name: {
                    required: true,
                    minlength: 1

                },
                value: {
                    required: true,
                    remote: {
                        url: prefix + "/exists", // 后台处理程序
                        type: "post", // 数据发送方式
                        dataType: "json", // 接受数据格式
                        data: { // 要传递的数据
                            name: function () {
                                return $("#name").val();
                            },
                            value: function () {
                                return $("#value").val();
                            },
                            dictType: function () {
                                return window.parent.dictType;
                            },
                            type: 2
                        }
                    }
                }
            },
            messages: {
                name: {
                    required: icon + "请输入标签名",
                    minlength: icon + "标签名必须两个字符以上"
                },
                value: {
                    required: icon + "请输入字典值",
                    remote: icon + "字典值已经存在"
                }
            },
            focusCleanup: true,
            submitHandler: function () {
                save();
            }
        })
    }

    $(function () {
        switchInit('dictState');
        validateRule();
    })
</script>
</body>

</html>
