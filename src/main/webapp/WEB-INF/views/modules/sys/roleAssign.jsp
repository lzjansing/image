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
                    角色分配 <small><a href="${ctx}/sys/role/usertorole?id=${role.id}" id="assignButton" class="text-muted" data-target="#ajax" data-toggle="modal">分配角色</a></small>
                </h3>
                <!-- BEGIN PAGE TITLE & BREADCRUMB-->
            </div>
        </div>
        <!-- END PAGE HEADER-->

        <!-- BEGIN PAGE CONTENT-->
        <div class="row">
            <div class="col-md-12">
                <div class="portlet-body form">
                    <form class="form-horizontal" role="form">
                        <div class="form-body">
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-md-3">角色：</label>
                                        <div class="col-md-9">
                                            <p class="form-control-static">${role.name}</p>
                                        </div>
                                    </div>
                                </div>
                                <!--/span-->
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label class="control-label col-md-3">名称：</label>
                                        <div class="col-md-9">
                                            <p class="form-control-static">${role.enname}</p>
                                        </div>
                                    </div>
                                </div>
                                <!--/span-->
                            </div>
                        </div>
                    </form>
                </div>
                <sys:message content="${message}"/>
                <form id="assignRoleForm" action="${ctx}/sys/role/assignrole" method="post" class="hide">
                    <input type="hidden" name="id" value="${role.id}"/>
                    <input id="userIds" type="hidden" name="userIds" value=""/>
                </form>
                <div class="table-scrollable">
                    <table id="contentTable" class="table table-bordered table-striped table-condensed">
                        <thead>
                        <tr>
                            <th>登录名</th>
                            <shiro:hasPermission name="sys:user:edit"><th>操作</th></shiro:hasPermission>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${userList}" var="user">
                            <tr>
                                <td><a href="${ctx}/sys/user/form?id=${user.id}">${user.username}</a></td>
                                <shiro:hasPermission name="sys:role:edit"><td>
                                    <a href="#confirm" data-toggle="modal" onclick="confirmDialog('确认要将用户<b>[${user.username}]</b>从<b>[${role.name}]</b>角色中移除吗？', '${ctx}/sys/role/outrole?userId=${user.id}&roleId=${role.id}');">移除</a>
                                </td></shiro:hasPermission>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
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
    });
</script>
</body>
</html>