<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<%@ include file="/commom/taglib.jsp" %>
<%@ include file="/commom/header.jsp" %>
<link href="${_ctx }/static/plugin/summernote/summernote.min.css" rel="stylesheet">
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
                                <input id="title" name="title" class="form-control"
                                       type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">通知类型：</label>
                            <div class="col-sm-8">
                                <select class="form-control m-b" name="type" id="type">
                                    <option value="2" selected>通知</option>
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
                                <input type="hidden" name="receiveUserIds" id="receiveUserIds">
                                <input id="receiveUserName" name="receiveUserName" class="form-control ignore" placeholder="请选择接收人"
                                       type="text" style="cursor: pointer;" onclick="openUser()" readonly>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">通知内容：</label>
                            <div class="col-sm-8">
                                <input name="content" id="content" class="hide" type="hidden">
                                <div class="summernote"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">立即发布：</label>
                            <div class="col-sm-8">
                                <input type="hidden" id="isPublishNowState" name="notifyState" value="1">
                                <input type="checkbox" name="isPublishNowStateSwicth" checked>
                                <span class="help-block m-b-none"><i
                                        class="fa fa-info-circle"></i> 取消【立即发布】后，需要重新选择发布时间，启用【立即发布】保存后，无法在更改</span>
                            </div>
                        </div>
                        <div class="form-group animated" id="publish-time-group">
                            <label class="col-sm-3 control-label"><span class="wayn-required-span">*</span>发布时间：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="publishTime" name="publishTime"
                                       placeholder="发布时间">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">备注：</label>
                            <div class="col-sm-8">
                                <textarea id="remarks" name="remarks" class="form-control" rows="3"></textarea>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
<%@ include file="/commom/footer.jsp" %>
<script src="${_ctx }/static/plugin/summernote/summernote.min.js"></script>
<script src="${_ctx }/static/plugin/summernote/lang/summernote-zh-CN.js"></script>
<script>
    var prefix = _ctx + "/oa/notify";

    var openUser = function () {
        layer.open({
            type: 2,
            title: "选择用户",
            area: ['320px', '590px'],
            content: _ctx + "/system/user/treeView",
            btn: ['保存', '取消'],
            yes: function (index, layero) {
                var iframeWin = layero.find('iframe')[0];
                iframeWin.contentWindow.getCheckedUser(index, layero);
            },
            cancel: function (index) {
                return true;
            }
        })
    };

    function getReceiveUserIds() {
        return $('#receiveUserIds').val();
    }

    function loadUser(userIds, userNames) {
        $("#receiveUserIds").val(userIds);
        $("#receiveUserName").val(userNames);
    }

    /**
     * 通知状态变更
     */
    function typeChange() {
        $('#type').on('change', function () {
            if ($(this).val() == 2) {
                $('#receive-user-group').slideToggle().removeClass('rollOut').addClass('rollIn');
            } else {
                $('#receive-user-group').removeClass('rollIn').addClass('rollOut').slideToggle();
            }
            $('#receiveUserIds, #receiveUserName').val('');
        });
    }

    function save() {
        $.ajax({
            cache: true,
            type: "POST",
            url: prefix + "/addSave",
            dataType: 'json',
            data: $('#notify-form').serialize(),// 你的formid
            error: function (request) {
                parent.layer.alert("Connection error");
            },
            success: function (data) {
                if (data.code != 100) {
                    layer.alert(data.msg);
                } else {
                    parent.layer.msg(data.msg, {icon: 1});
                    parent.reload();
                    // 关闭当前窗口
                    var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                    parent.layer.close(index);
                }
            }
        });
    }

    var validator = null;

    function validateRule(index, layero) {
        var icon = "<i class='fa fa-times-circle'></i> ";
        validator = $("#notify-form").validate({
            rules: {
                title: {
                    required: true

                },
                receiveUserName: {
                    required: true
                },
                publishTime: {
                    required: true
                }
            },
            messages: {
                title: {
                    required: icon + "请输入通知标题",
                },
                receiveUserName: {
                    required: icon + "请选择接收人",
                },
                publishTime: {
                    required: icon + "请选择发布日期",
                }
            },
            // focusCleanup: true,
            submitHandler: function () {
                save();
            }
        })
    }

    function submitHandler() {
        if (validator && validator.form()) {
            var sHTML = $('.summernote').summernote('code').trim();
            $("#content").val(sHTML);
            save();
        }
    }

    $(function () {
        $('#publish-time-group').slideUp();
        var config1 = {
            onText: 'on', offText: 'off', state: true, onSwitchChange(event, state) {
                if (state) {
                    $('#isPublishNowState').val('1');
                    $('#publishTime').val('');
                    $('#publish-time-group').removeClass('fadeInUp').addClass('fadeOutDown').slideToggle();
                } else {
                    $('#isPublishNowState').val('-1');
                    $('#publish-time-group').slideToggle().removeClass('fadeOutDown').addClass('fadeInUp');
                }
            }
        };

        switchInit('isPublishNowState', config1);

        laydate.render({
            elem: '#publishTime',
            type: 'datetime',
            min: 0
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
        typeChange();
        validateRule();
    })
</script>
</body>

</html>
