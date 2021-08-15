<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<%@ include file="/common/taglib.jsp" %>
<%@ include file="/common/header.jsp" %>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInDown">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="job-form">
                        <div class="form-group">
                            <label class="col-sm-3 control-label"><span class="wayn-required-span">*</span>任务名称：</label>
                            <div class="col-sm-8">
                                <input id="jobName" name="jobName" class="form-control" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">任务分组：</label>
                            <div class="col-sm-8">
                                <select class="form-control m-b" name="jobGroup" id="jobGroup">
                                    <option value="default" selected>默认</option>
                                    <option value="system">系统</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label "><span
                                    class="wayn-required-span">*</span>调用目标字符串：</label>
                            <div class="col-sm-8">
                                <input class="form-control" type="text" name="invokeTarget" id="invokeTarget" required>
                                <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> Bean调用示例：ryTask.ryParams('ry')</span>
                                <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> Class类调用示例：com.ruoyi.quartz.task.RyTask.ryParams('ry')</span>
                                <span class="help-block m-b-none"><i class="fa fa-info-circle"></i> 参数说明：支持字符串，布尔类型，长整型，浮点型，整型</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label "><span
                                    class="wayn-required-span">*</span>cron表达式：</label>
                            <div class="col-sm-8">
                                <input class="form-control" type="text" name="cronExpression" id="cronExpression"
                                       required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">执行策略：</label>
                            <div class="col-sm-8">
                                <c:forEach items="${misfirePolicys}" var="misfirePolicy">
                                    <label class="radio-inline i-checks">
                                        <input type="radio" name="misfirePolicy"
                                               value="${misfirePolicy.id}" ${misfirePolicy.id eq 1 ?'checked':''}/> ${misfirePolicy.text}
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">并发执行：</label>
                            <div class="col-sm-8">
                                <label class="radio-inline i-checks"> <input type="radio" name="concurrent" value="1"
                                                                             checked/> 允许 </label>
                                <label class="radio-inline i-checks"> <input type="radio" name="concurrent"
                                                                             value="-1"/> 禁止 </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">状态：</label>
                            <div class="col-sm-8">
                                <input type="hidden" id="jobState" name="jobState" value="1">
                                <input type="checkbox" name="jobStateSwicth" checked>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">备注：</label>
                            <div class="col-sm-8">
                                <textarea id="remarks" name="remarks" class="form-control" rows="3"></textarea>
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
    var prefix = _ctx + "/quartz/job";

    function save() {
        $.ajax({
            cache: true,
            type: "POST",
            url: prefix + "/addSave",
            dataType: 'json',
            data: $('#job-form').serialize(),// 你的formid
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


    function validateRule(index, layero) {
        var icon = "<i class='fa fa-times-circle'></i> ";
        validator = $("#job-form").validate({
            rules: {
                jobName: {
                    required: true
                },
                cronExpression: {
                    required: true,
                    remote: {
                        url: prefix + "/checkCronExpressionIsValid", // 后台处理程序
                        //contentType: 'application/json;charset=UTF-8',
                        type: "post", // 数据发送方式
                        dataType: "json", // 接受数据格式
                        data: { // 要传递的数据
                            cronExpression: function () {
                                return $("#cronExpression").val();
                            }
                        }
                    }
                },
                invokeTarget: {
                    required: true,
                    remote: {
                        url: prefix + "/checkInvokeTargetIsValid", // 后台处理程序
                        //contentType: 'application/json;charset=UTF-8',
                        type: "post", // 数据发送方式
                        dataType: "json", // 接受数据格式
                        data: { // 要传递的数据
                            cronExpression: function () {
                                return $("#cronExpression").val();
                            }
                        }
                    }
                }
            },
            messages: {
                jobName: {
                    required: icon + "请输入任务标题"
                },
                cronExpression: {
                    required: icon + "请输入cron表达式",
                    remote: icon + "请输入正确的cron表达式"
                },
                invokeTarget: {
                    required: icon + "请输入调用目标字符串",
                    remote: icon + "请输入有效的目标字符串"
                }
            },
            focusCleanup: true,
            submitHandler: function () {
                save();
            }
        })
    }

    $(function () {
        var config = {
            onText: '执行',
            offText: '暂停'
        };
        switchInit('jobState', config);
        validateRule();
    })
</script>
</body>

</html>
