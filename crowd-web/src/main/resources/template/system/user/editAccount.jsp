<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<%@ include file="/common/taglib.jsp" %>
<%@ include file="/common/header.jsp" %>
<style>
    .danger {
        color: red;
        font-size: 5px;
    }
</style>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox-content">
                <form class="form-horizontal m-t" id="user-form">
                    <input id="id" name="id" value="${id}" type="hidden">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">请输入用户名称：</label>
                        <div class="col-sm-8">
                            <input id="userName" name="userName" class="form-control" value="${userName}"
                                   type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-12 control-label danger">修改用户名称后，密码将重置为${initPassWord }</label>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-8 col-sm-offset-3">
                            <button type="submit" class="btn btn-primary">提交</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="/common/footer.jsp" %>
<script>
    var prefix = _ctx + '/system/user';

    function save() {
        $.ajax({
            cache: true,
            type: "POST",
            url: prefix + "/editAccount",
            data: $('#user-form').serialize(),// 你的formid
            async: false,
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
        $("#user-form").validate({
            rules: {
                userName: {
                    required: true,
                    minlength: 2,
                    remote: {
                        url: prefix + "/exists", // 后台处理程序
                        type: "post", // 数据发送方式
                        dataType: "json", // 接受数据格式
                        data: { // 要传递的数据
                            userName: function () {
                                return $("#userName").val();
                            }
                        }
                    }
                }
            },
            messages: {

                userName: {
                    required: icon + "请输入您的用户名",
                    minlength: icon + "用户名必须两个字符以上",
                    remote: icon + "用户名已经存在"
                }
            },
            submitHandler: function () {
                save();
            }
        })
    }

    $(function () {
        validateRule();
    })
</script>
</body>

</html>
