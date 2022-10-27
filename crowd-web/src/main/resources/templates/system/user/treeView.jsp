<!DOCTYPE html>
<html lang="zh" xmlns:th="http://www.thymeleaf.org" xmlns:shiro="http://www.pollix.at/thymeleaf/shiro">
<head>
    <th:block th:include="include :: header('用户数')"/>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="tree-search-content">
                <label for="keyword">关键字：</label><input type="text" class="empty" id="keyword" maxlength="50">
                <button class="btn  btn-xs" onclick="search()">搜索</button>
            </div>
            <div class="ibox-content">
                <div id="userTree"></div>
            </div>
            <div class="form-group hidden">
                <div class="col-sm-12 col-sm-offset-12">
                    <button type="submit" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>
</div>
<th:block th:include="include :: common-js"/>
<script th:inline="javascript">
    var prefix = _ctx + '/system/user';

    function search() {
        $('#userTree').jstree(true).search($('#keyword').val())
    }

    function getTreeData() {
        $.ajax({
            type: "POST",
            dataType: 'json',
            url: prefix + "/tree",
            beforSend: function () {
                layer.load(2);
            },
            success: function (tree) {
                loadTree(tree);
            },
            error: function (e) {
                layer.close(layer.index)
            }
        });
    }

    function loadTree(tree) {
        $('#userTree').jstree({
            'core': {
                'data': tree,
                'themes': {
                    'name': "default"
                }
            },
            "types": {
                "root": {
                    "icon": "fa fa-folder icon-state-default"
                },
                "dir": {
                    "icon": "fa fa-folder icon-state-default"
                },
                "file": {
                    "icon": "fa fa-folder icon-state-success"
                }
            },
            // 'checkbox': {"tie_selection": false},
            "plugins": ["search", "checkbox", "types"]
        });
    }

    $("#userTree").on("ready.jstree", function (event, data) {
        var ref = $('#userTree').jstree(true); // 获得整个树
        // ref.check_all();
        ref.check_node(parent.getReceiveUserIds().split(','), true);
        layer.close(layer.index)
    });

    /*$('#userTree').on("changed.jstree", function (e, data) {
        if (data.node.id == '-1') {
            // parent.loadMenu(0, '顶级节点');
        } else {
            // parent.loadMenu(data.node.id, data.node.text);
        }
        var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
        parent.layer.close(index);
    });*/

    function getCheckedUser() {
        var userNames, userIds;
        var ref = $('#userTree').jstree(true); // 获得整个树
        userIds = ref.get_bottom_selected();
        users = ref.get_bottom_checked('true');
        var txt = [];
        for (var user in users) {
            if (users[user].state.mType == "user") {
                txt.push(users[user].text);
            }
        }
        parent.loadUser(userIds, txt.join(','));
        var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
        parent.layer.close(index);
    }

    $(function () {
        getTreeData();
    })
</script>
</body>

</html>
