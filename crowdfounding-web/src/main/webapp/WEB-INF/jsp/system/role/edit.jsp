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
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form class="form-horizontal m-t" id="role-form">
                        <div class="form-group">
                            <label class="col-sm-3 control-label"><span class="wayn-required-span">*</span>角色名：</label>
                            <div class="col-sm-8">
                                <input type="hidden" id="roleId" name="id" value="${role1.id }">
                                <input id="roleName" name="roleName" class="form-control"
                                       type="text" value="${role1.roleName }">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">状态：</label>
                            <div class="col-sm-8">
                                <input type="hidden" id="roleState" name="roleState"
                                       value="${role1.roleState }"> <input type="checkbox"
                                                                           name="roleStateSwicth" checked>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">角色备注：</label>
                            <div class="col-sm-8">
                                <input id="remarks" name="remarks" class="form-control"
                                       type="text" value="${role1.remarks }">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">菜单权限：</label>
                            <div class="col-sm-8">
                                <div id="menuTree"></div>
                                <span class="help-block m-b-none"><i class="fa fa-info-circle"></i>勾选角色对应权限</span>
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
<%@ include file="/commom/footer.jsp" %>
<script type="text/javascript"
        src="${_ctx }/static/plugin/jsTree/jstree.js"></script>
<script>
    var prefix = _ctx + '/system/role';

    function getMenuTreeData() {
        $.ajax({
            type: "POST",
            dataType: "json",
            url: _ctx + '/system/menu/tree/' + $('#roleId').val(),
            success: function (menuTree) {
                loadMenuTree(menuTree);
            }
        });
    }

    function loadMenuTree(menuTree) {
        $('#menuTree').jstree({
            "plugins": ["wholerow", "checkbox"],
            'core': {
                'data': menuTree,
                'themes': {
                    'name': "proton"
                }
            },
            "checkbox": {
                //"keep_selected_style" : false,
                //"undetermined" : true,
                //"three_state" : false,
                //"cascade" : ' up'
            }
        });
        $('#menuTree').jstree('open_all');
    }

    $("#menuTree").on("loaded.jstree", function (event, data) {
        // 展开所有节点
        //$('#menuTree').jstree(true).open_all();
    });

    var menuIds = null;

    function getAllSelected() {
        var ref = $('#menuTree').jstree(true); // 获得整个树
        var arr = ref.get_selected();
        $("#menuTree").find(".jstree-undetermined").each(function (i, element) {
            arr.push($(element).closest('.jstree-node').attr("id"));
        });
        menuIds = arr.filter(function (item) {
            if (item != -1) {
                return true;
            }
        }).join(',');

    }

    function validateRule() {
        var e = '<i class="fa fa-times-circle"></i> ';
        $("#role-form").validate({
            rules: {
                "roleName": {
                    required: true
                },
            },
            messages: {
                "roleName": {
                    required: e + "请输入角色名"
                },
            },
            ignore: ".ignore",
            focusCleanup: true,
            submitHandler: function () {
                getAllSelected();
                save();
            }
        });
    }

    function save() {
        var config = {
            url: prefix + "/editSave",
            data: $('#role-form').serialize() + "&menuIds=" + menuIds,
            type: "POST",
            dataType: "json",
            success: function (data) {
                if (data.code != 100) {
                    layer.alert(data.msg);
                } else {
                    parent.layer.msg(data.msg);
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
        var state = $('#roleState').val() == "1" ? true : false;
        switchInit('roleState', {state});
        validateRule();
        getMenuTreeData();
    })
</script>
</body>

</html>
