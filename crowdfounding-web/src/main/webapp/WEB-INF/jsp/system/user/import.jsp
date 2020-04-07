<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<%@ include file="/commom/taglib.jsp" %>
<%@ include file="/commom/header.jsp" %>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox-content">
                <form class="form-horizontal m-t" id="user-form">
                    <div class="form-group">
                        <input type="file" id="file" name="file" onchange="fileChange()"/>
                        <div class="m-t-sm  p-xxs ">
                            <a class="btn btn-default btn-xs" onclick="downTemplate()"><i
                                    class="fa fa-file-excel-o"></i> 下载模板</a>
                        </div>
                        <div class="pull-left m-t-sm" style="color: red; ">
                            <p>1,仅允许导入“xls”或“xlsx”格式文件！</p>
                            <p>2,导入用户密码默认为123456</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-8">
                            <button type="button" class="btn btn-primary" onclick="importExcel()">导入</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="/commom/footer.jsp" %>
<script>
    var prefix = _ctx + '/system/user';

    function fileChange() {
        var $file1 = $("#file").val(); //用户文件内容(文件)
        // 判断文件是否为空
        if ($file1 == "") {
            layer.alert("请选择上传的目标文件!", {icon: 6});
            $("#file").val('');
            return false;
        }
        //判断文件类型,我这里根据业务需求判断的是Excel文件
        var ext = $file1.substring($file1.lastIndexOf(".") + 1).toLowerCase();
        if (ext != "xls" && ext != "xlsx") {
            layer.alert("请选择Execl文件!", {icon: 6});
            $("#file").val('');
            return false;
        }
        //判断文件大小
        var size1 = $("#file")[0].files[0].size;
        if (size1 > 104857600) {
            alert("上传文件不能大于100M!");
            $("#file").val('');
            return false;
        }
        return true;
    }

    function importExcel() {
        if (fileChange()) {
            var formData = new FormData();//这里需要实例化一个FormData来进行文件上传
            formData.append('file', $("#file")[0].files[0]);
            $.ajax({
                type: "POST",
                url: prefix + "/upload",
                cache: false,
                data: formData,
                contentType: false,
                processData: false,
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
    }

    function downTemplate() {
        window.location.href = _ctx + "/commom/downTemplate?fileName=用户列表.xls";
    }

    $(function () {
    })
</script>
</body>

</html>
