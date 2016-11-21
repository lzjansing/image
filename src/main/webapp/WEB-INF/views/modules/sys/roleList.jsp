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
                    角色列表 <shiro:hasPermission name="sys:role:edit"><small><a href="${ctx}/sys/role/form" class="text-muted">角色添加</a></small></shiro:hasPermission>
                </h3>
                <!-- BEGIN PAGE TITLE & BREADCRUMB-->
            </div>
        </div>
        <!-- END PAGE HEADER-->

        <!-- BEGIN PAGE CONTENT-->
        <div class="row">
            <div class="col-md-12">
                <sys:message content="${message}"/>
                <div class="table-scrollable">
                    <table id="contentTable" class="table table-bordered table-striped table-condensed">
                        <thead>
                        <tr>
                            <th>角色名称</th>
                            <th>英文名称</th>
                            <shiro:hasPermission name="sys:role:edit"><th>操作</th></shiro:hasPermission>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list}" var="role">
                            <tr>
                                <td><a href="form?id=${role.id}">${role.name}</a></td>
                                <td><a href="form?id=${role.id}">${role.enname}</a></td>
                                <shiro:hasPermission name="sys:role:edit"><td>
                                    <a href="${ctx}/sys/role/assign?id=${role.id}">分配</a>
                                    <a href="${ctx}/sys/role/form?id=${role.id}">修改</a>
                                    <a href="#confirm" data-toggle="modal" onclick="confirmDialog('确认要删除该角色吗？', '${ctx}/sys/role/delete?id=${role.id}');">删除</a>
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