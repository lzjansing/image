<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<link href="${ctxFront}/plugins/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css" rel="stylesheet" type="text/css"/>
<script src="${ctxFront}/plugins/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>
<div class="modal-dialog" style="width: 700px;">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
            <h4 class="modal-title">分配角色</h4>
        </div>
        <div class="modal-body">
            <div class="row">
                <div class="col-md-6">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h3 class="panel-title">待选人员</h3>
                        </div>
                        <div class="panel-body">
                            <div id="userTree" class="ztree"></div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <h3 class="panel-title">已选人员</h3>
                        </div>
                        <div class="panel-body">
                            <div id="selectedTree" class="ztree"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button id="roleButton" type="button" class="btn blue" data-dismiss="modal">确定分配</button>
            <button id="roleButtonClear" type="button" class="btn green" data-dismiss="modal">清除已选</button>
            <button type="button" class="btn default" data-dismiss="modal">关闭</button>
        </div>
    </div>
    <!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->

<script type="text/javascript">
    var userTree;
    var selectedTree;//zTree已选择对象

    var setting = {view: {selectedMulti:false,nameIsHTML:true,showTitle:false,dblClickExpand:false},
        data: {simpleData: {enable: true}},
        callback: {onClick: treeOnClick}};

    var userNodes=[
        <c:forEach items="${userList}" var="user">
        {id:"${user.id}",
            pId:"0",
            name:"<font color='red' style='font-weight:bold;'>${user.username}</font>"},
        </c:forEach>];

    var pre_selectedNodes =[
        <c:forEach items="${selectedList}" var="user">
        {id:"${user.id}",
            pId:"0",
            name:"<font color='red' style='font-weight:bold;'>${user.username}</font>"},
        </c:forEach>];

    var selectedNodes =[
        <c:forEach items="${selectedList}" var="user">
        {id:"${user.id}",
            pId:"0",
            name:"<font color='red' style='font-weight:bold;'>${user.username}</font>"},
        </c:forEach>];

    var pre_ids = "${selectIds}".split(",");
    var ids = "${selectIds}".split(",");

    //点击选择项回调
    function treeOnClick(event, treeId, treeNode, clickFlag) {
        $.fn.zTree.getZTreeObj(treeId).expandNode(treeNode);
        if ("userTree" == treeId) {
            if ($.inArray(String(treeNode.id), ids) < 0) {
                selectedTree.addNodes(null, treeNode);
                ids.push(String(treeNode.id));
            }
        }
        if ("selectedTree" == treeId) {
            if ($.inArray(String(treeNode.id), pre_ids) < 0) {
                selectedTree.removeNode(treeNode);
                ids.splice($.inArray(String(treeNode.id), ids), 1);
            } else {
                loading('角色原有成员不能清除！', 'info');
            }
        }
    }

    userTree = $.fn.zTree.init($("#userTree"), setting, userNodes);
    selectedTree = $.fn.zTree.init($("#selectedTree"), setting, selectedNodes);

    // 保存
    $('#roleButton').click(function() {
        // 删除''的元素
        if (ids[0] == '') {
            ids.shift();
            pre_ids.shift();
        }

        if(pre_ids.sort().toString() == ids.sort().toString()){
            loading('未给角色【${role.name}】分配新成员！', 'info');
            return false;
        }

        // 执行保存
        loading('正在提交，请稍等...');
        var userIds = "";
        for (var i = 0; i < ids.length; i++) {
            userIds = (userIds + ids[i]) + (((i + 1) == ids.length) ? '' : ',');
        }
        $('#userIds').val(userIds);
        $('#assignRoleForm').submit();
        return true;
    });

    function clearAssign() {
        if (confirm('确定清除角色【${role.name}】下的已选人员？')) {
            var tips = "";
            if (pre_ids.sort().toString() == ids.sort().toString()) {
                tips = "未给角色【${role.name}】分配新成员！";
            } else {
                tips = "已选人员清除成功！";
            }
            ids = pre_ids.slice(0);
            selectedNodes = pre_selectedNodes;
            $.fn.zTree.init($("#selectedTree"), setting, selectedNodes);
            loading(tips, 'info');
        } else {
            loading('取消清除操作！', 'info');
        }
    }

    // 清除
    $('#roleButtonClear').click(function() {
        clearAssign();
        return false;
    });
</script>