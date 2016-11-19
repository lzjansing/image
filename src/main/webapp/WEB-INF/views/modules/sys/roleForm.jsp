<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<header>
    <title>${fns:getConfig('productName')}</title>
    <meta name="decorator" content="default"/>
</header>
<body>
<sys:navbar />
<div class="clearfix"></div>

<!-- BEGIN CONTAINER -->
<div class="page-container">
    <sys:sidebar tag="sys/role" />
    <!-- BEGIN PAGE -->
    <div class="page-content">
        <!-- BEGIN PAGE HEADER-->
        <div class="row">
            <div class="col-md-12">
                <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                <h3 class="page-title">
                    角色${not empty role.id ? '修改' : '添加'}
                </h3>
                <!-- BEGIN PAGE TITLE & BREADCRUMB-->
            </div>
        </div>
        <!-- END PAGE HEADER-->

        <!-- BEGIN PAGE CONTENT-->
        <div class="row">
            <div class="col-md-12">
                <div class="portlet-body form">
                    <!-- BEGIN FORM-->
                    <form:form id="inputForm1" modelAttribute="role" action="${ctx}/sys/role/save" class="form-horizontal form-row-seperated" method="post">
                        <form:hidden path="id"/>
                        <sys:message content="${message}"/>
                        <div class="form-body">
                            <div class="form-group">
                                <label class="control-label col-md-2"><span class="required">*</span>&nbsp;角色名称：</label>
                                <div class="col-md-9">
                                    <input id="oldName" name="oldName" type="hidden" value="${role.name}">
                                    <form:input path="name" htmlEscape="false" maxlength="50" class="required form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2"><span class="required">*</span>&nbsp;英文名称：</label>
                                <div class="col-md-9">
                                    <input id="oldEnname" name="oldEnname" type="hidden" value="${role.enname}">
                                    <form:input path="enname" htmlEscape="false" maxlength="50" class="required form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2">是否可用：</label>
                                <div class="col-md-9">
                                    <form:select path="useable" class="form-control">
                                        <form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                                    </form:select>
                                    <span class="help-block">“是”代表此数据可用，“否”则表示此数据不可用</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2">角色授权：</label>
                                <div class="col-md-9">
                                    <div id="menuTree" class="ztree" style="margin-top:3px;float:left;"></div>
                                    <form:hidden path="menuIds"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2">备注：</label>
                                <div class="col-md-9">
                                    <form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="form-control"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-actions right">
                            <shiro:hasPermission name="sys:role:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
                        </div>
                    </form:form>
                    <!-- END FORM-->
                </div>
            </div>
        </div>
        <!-- END PAGE CONTENT-->
    </div>
    <!-- END PAGE -->
</div>
<!-- END CONTAINER -->

<sys:footer />

<script>
    jQuery(document).ready(function() {
        App.init();

        $("#inputForm1").validate({
            errorClass: 'help-block',
            rules: {
//                分别对name、enname远程异步验证，
// 在修改其中一个后失去焦点时，自动获取inputForm1中的对应值，并拼接到url中，
// 如果url包含参数，则自动拼接&。
                name: {remote: "${ctx}/sys/role/checkName"},
                enname: {remote: "${ctx}/sys/role/checkEnname"}
            },
            messages: {
                name: {remote: "角色名已存在"},
                enname: {remote: "英文名已存在"}
            },

            highlight: function (element) { // hightlight error inputs
                $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function (label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            submitHandler: function(form){
                var ids = [], nodes = tree.getCheckedNodes(true);
                for(var i=0; i<nodes.length; i++) {
                    ids.push(nodes[i].id);
                }
                $("#menuIds").val(ids);
                loading('正在提交，请稍等...');
                form.submit();
            },
            errorContainer: "#messageBox",
            errorPlacement: function(error, element) {
                $("#messageBox").text("输入有误，请先更正。");
                if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
                    error.appendTo(element.parent().parent());
                } else {
                    error.insertAfter(element);
                }
            }
        });

        var setting = {
            check: {enable: true, nocheckInherit: true}, view: {selectedMulti: false},
            data: {simpleData: {enable: true}}, callback: {
                beforeClick: function (id, node) {
                    tree.checkNode(node, !node.checked, true, true);
                    return false;
                }
            }
        };

        // 用户-菜单
        var zNodes = [
                <c:forEach items="${menuList}" var="menu">{
                id: "${menu.id}",
                pId: "${not empty menu.parent.id?menu.parent.id:0}",
                name: "${not empty menu.parent.id?menu.name:'权限列表'}"
            },
            </c:forEach>];

        // 初始化树结构
        var tree = $.fn.zTree.init($("#menuTree"), setting, zNodes);

        // 不选择父节点
        tree.setting.check.chkboxType = { "Y" : "ps", "N" : "s" };

        // 默认选择节点
        var ids = "${role.menuIds}".split(",");
        for (var i = 0; i < ids.length; i++) {
            var node = tree.getNodeByParam("id", ids[i]);
            try {
                tree.checkNode(node, true, false);
            } catch (e) {
            }
        }

        // 默认展开全部节点
        tree.expandAll(true);
    });

</script>
</body>
</html>