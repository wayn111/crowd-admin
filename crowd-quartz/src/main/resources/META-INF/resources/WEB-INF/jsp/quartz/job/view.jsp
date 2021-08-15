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
                                <input type="hidden" name="id" value="${job.id}">
                                <input id="jobName" name="jobName" class="form-control" type="text"
                                       value="${job.jobName}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">任务分组：</label>
                            <div class="col-sm-8">
                                <select class="form-control m-b" name="jobGroup" id="jobGroup">
                                    <option value="default" ${job.jobGroup eq 'default'?'selected':''}>默认</option>
                                    <option value="system" ${job.jobGroup eq 'default'?'':'selected'}>系统</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label "><span
                                    class="wayn-required-span">*</span>调用目标字符串：</label>
                            <div class="col-sm-8">
                                <input class="form-control" type="text" name="invokeTarget" id="invokeTarget"
                                       value="${job.invokeTarget}">
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
                                       value="${job.cronExpression}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">执行策略：</label>
                            <div class="col-sm-8">
                                <c:forEach items="${misfirePolicys}" var="misfirePolicy">
                                    <label class="radio-inline i-checks">
                                        <input type="radio" name="misfirePolicy" disabled=""
                                               value="${misfirePolicy.id}" ${misfirePolicy.id eq job.misfirePolicy ?'checked':''}/> ${misfirePolicy.text}
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">并发执行：</label>
                            <div class="col-sm-8">
                                <label class="radio-inline i-checks"> <input type="radio" name="concurrent" value="1"
                                ${job.concurrent eq 1 ?'checked':''} disabled=""/> 允许 </label>
                                <label class="radio-inline i-checks"> <input type="radio" name="concurrent" value="-1"
                                ${job.concurrent eq 1 ?'':'checked'} disabled=""/> 禁止 </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">状态：</label>
                            <div class="col-sm-8">
                                <input type="hidden" id="jobState" name="jobState" value="${job.jobState}">
                                <input type="checkbox" name="jobStateSwicth">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">备注：</label>
                            <div class="col-sm-8">
                                <textarea id="remarks" name="remarks" class="form-control"
                                          rows="3">${job.remarks}</textarea>
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

    $(function () {
        var state = $('#jobState').val() == "1" ? true : false;
        var config = {
            onText: '执行',
            offText: '暂停',
            state: state,
            readonly: true
        };
        switchInit('jobState', config);
    })
</script>
</body>

</html>
