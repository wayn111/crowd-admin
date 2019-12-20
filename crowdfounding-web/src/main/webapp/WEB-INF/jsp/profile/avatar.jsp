<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <%@ include file="/commom/taglib.jsp" %>
    <%@ include file="/commom/header.jsp" %>
    <link rel="stylesheet" href="${_ctx }/static/plugin/cropbox/cropbox.css">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>crowdfounding 上传头像</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
          name="viewport">
</head>
<body class="gray-bg">
<div class="container">
    <div class="imageBox">
        <div class="thumbBox"></div>
        <div class="spinner" style="display: none">Loading...</div>
    </div>
    <div class="action">
        <div class="new-contentarea tc">
            <a href="javascript:void(0)" class="upload-img"> <label for="avatar">上传图像</label> </a>
            <input type="file" class="" name="avatar" id="avatar" accept="image/*"/>
        </div>
        <input type="button" id="btnCrop" class="Btnsty_peyton" value="裁切"/>
        <input type="button" id="btnZoomIn" class="Btnsty_peyton" value="+"/>
        <input type="button" id="btnZoomOut" class="Btnsty_peyton" value="-"/>
    </div>
    <div class="cropped"></div>
</div>
<%@ include file="/commom/footer.jsp" %>
<script src="${_ctx }/static/plugin/cropbox/cropbox.js"></script>
<script>
    var prefix = _ctx + '/profile';

    var cropper;
    $(window).load(function () {
        var avatar = 'wayn';
        var options = {
            thumbBox: '.thumbBox',
            spinner: '.spinner',
            imgSrc: '${imgSrc}'
        };
        cropper = $('.imageBox').cropbox(options);
        $('#avatar').on('change', function () {
            var reader = new FileReader();
            reader.onload = function (e) {
                options.imgSrc = e.target.result;
                //根据MIME判断上传的文件是不是图片类型
                if ((options.imgSrc).indexOf("image/") == -1) {
                    $.modal.alertWarning("文件格式错误，请上传图片类型,如：JPG，PNG后缀的文件。");
                } else {
                    cropper = $('.imageBox').cropbox(options);
                }
            };
            reader.readAsDataURL(this.files[0]);
        });

        $('#btnCrop').on('click', function () {
            var img = cropper.getDataURL();
            $('.cropped').html('');
            $('.cropped').append('<img src="' + img + '" align="absmiddle" style="width:64px;margin-top:4px;border-radius:64px;box-shadow:0px 0px 12px #7E7E7E;" ><p>64px*64px</p>');
            $('.cropped').append('<img src="' + img + '" align="absmiddle" style="width:128px;margin-top:4px;border-radius:128px;box-shadow:0px 0px 12px #7E7E7E;"><p>128px*128px</p>');
            $('.cropped').append('<img src="' + img + '" align="absmiddle" style="width:180px;margin-top:4px;border-radius:180px;box-shadow:0px 0px 12px #7E7E7E;"><p>180px*180px</p>');
        });

        $('#btnZoomIn').on('click', function () {
            cropper.zoomIn();
        });

        $('#btnZoomOut').on('click', function () {
            cropper.zoomOut();
        })
    });

    function submitHandler() {
        var img = cropper.getBlob();
        var formdata = new FormData();
        formdata.append("avatarfile", img);
        $.ajax({
            url: prefix + "/updateAvatar",
            data: formdata,
            type: "post",
            processData: false,
            contentType: false,
            success: function (data) {
                parent.layer.msg(data.msg, {icon: 1});
                // 更换父页面和顶层页面图片路径
                $('.img-circle', parent.document).attr('src', data.map.imgSrc);
                $('.img-circle', top.document).attr('src', data.map.imgSrc);
                // 关闭当前窗口
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);
            }
        })
    }
</script>
</body>
</html>
