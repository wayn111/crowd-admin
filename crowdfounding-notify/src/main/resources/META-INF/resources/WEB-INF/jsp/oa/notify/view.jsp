<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<%@ include file="/commom/taglib.jsp" %>
<%@ include file="/commom/header.jsp" %>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInDown">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="notify-form">
                        <div class="form-group">
                            <label class="col-sm-3 control-label"><span class="wayn-required-span">*</span>通知标题：</label>
                            <div class="col-sm-8">
                                <input id="id" name="id" value="${notify.id}" type="hidden">
                                <input id="title" name="title" class="form-control" value="${notify.title}" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">通知类型：</label>
                            <div class="col-sm-8">
                                <select class="form-control m-b" name="type" id="type">
                                    <option value="2">通知</option>
                                    <option value="1">公告</option>

                                </select>
                                <span class="help-block m-b-none">
                                    <i class="fa fa-info-circle"></i>当通知类型为公告时，不需选择接收人
                                </span>
                            </div>
                        </div>
                        <div class="form-group animated" id="receive-user-group">
                            <label class="col-sm-3 control-label"><span class="wayn-required-span">*</span>接收人：</label>
                            <div class="col-sm-8">
                                <input type="hidden" name="receiveUserIds" id="receiveUserIds"
                                       value="${receiveUserIds}">
                                <input id="receiveUserName" name="receiveUserName" class="form-control ignore"
                                       value="${receiveUserNames}"
                                       type="text" style="cursor: pointer;" onclick="openUser()" readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">通知内容：</label>
                            <div class="col-sm-8">
                                <input name="content" id="content" class="hide" type="hidden">
                                <textarea id="content-textarea" class="hide">${notify.content}</textarea>
                                <div class="summernote"></div>
                            </div>
                        </div>
                        <div class="form-group animated" id="publish-time-group">
                            <label class="col-sm-3 control-label"><span class="wayn-required-span">*</span>发布时间：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="publishTime" name="publishTime"
                                       placeholder="发布时间"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">备注：</label>
                            <div class="col-sm-8">
                                <textarea id="remarks" name="remarks" class="form-control"
                                          rows="3">${notify.remarks}</textarea>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
<%@ include file="/commom/footer.jsp" %>
<script>

    $(function () {
        // 初始化页面数据 begin
        $('#type').val('${notify.type}');
        // 通知类型为公告时，隐藏接收人
        if ('${notify.type}' == 1) {
            $('#receive-user-group').hide('fast');
        }

        var config1 = {
            onText: 'on',
            offText: 'off',
            state: '${notify.notifyState}',
            readonly: true
        };

        switchInit('isPublishNow', config1);

        laydate.render({
            elem: '#publishTime',
            type: 'datetime',
            min: 0,
            value: '${notify.publishTime}'
        });
        textareaEditorInit('.summernote', {
            placeholder: '请输入通知内容',
            tabsize: 2,
            height: 249,
            followingToolbar: false,
            callbacks: {
                onImageUpload: function (files) {
                    summernoteSendFile(files[0], this);
                }
            }
        });
        // 对content内容进行转义
        var content = $('#content-textarea').val();
        content = content.replace(/&wayn;/g, '&quot;');
        $('.summernote').summernote('code', content);
        $('.summernote').summernote('disable');
        // 初始化页面数据 end
    })
</script>
</body>

</html>
