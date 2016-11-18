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
    <sys:sidebar tag="sys/menu" />
    <!-- BEGIN PAGE -->
    <div class="page-content">
        <!-- BEGIN PAGE HEADER-->
        <div class="row">
            <div class="col-md-12">
                <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                <h3 class="page-title">
                    菜单列表 <small><a href="${ctx}/sys/menu/form" class="text-muted">菜单添加</a></small>
                </h3>
                <!-- BEGIN PAGE TITLE & BREADCRUMB-->
            </div>
        </div>
        <!-- END PAGE HEADER-->

        <!-- BEGIN PAGE CONTENT-->
        <div class="row">
            <div class="col-md-12">
                <sys:message content="${message}"/>
                <div class="portlet-body form">
                    <form id="listForm" method="post">
                        <div class="table-scrollable">
                            <table id="treeTable" class="table table-striped table-bordered table-condensed">
                                <thead>
                                <tr>
                                    <th>名称</th>
                                    <th>链接</th>
                                    <th style="text-align:center;">排序</th>
                                    <th>可见</th>
                                    <th>权限标识</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody><c:forEach items="${list}" var="menu">
                                    <tr id="${menu.id}" pId="${menu.parent.id ne fns:getRootMenuId() ? menu.parent.id:'0'}">
                                        <td nowrap><i class="fa ${not empty menu.icon?menu.icon:' hide'}"></i>&nbsp;<a href="${ctx}/sys/menu/form?id=${menu.id}">${menu.name}</a></td>
                                        <td title="${menu.href}">${fns:abbr(menu.href,30)}</td>
                                        <td style="text-align:center;">
                                            <%--TODO 两个input是干嘛用的？--%>
                                            <input type="hidden" name="ids" value="${menu.id}"/>
                                            <input name="sorts" type="text" value="${menu.sort}" style="width:50px;margin:0;padding:0;text-align:center;border: 1px solid #dddddd;">
                                            ${menu.sort}
                                        </td>
                                        <td>${menu.isShow eq fns:getDictValue('是', 'yes_no', '') ?'显示':'隐藏'}</td>
                                        <td title="${menu.permission}">${fns:abbr(menu.permission,30)}</td>
                                        <td nowrap>
                                            <a href="${ctx}/sys/menu/form?id=${menu.id}">修改</a>
                                            <a href="#confirm" data-toggle="modal" onclick="confirmDialog('要删除该菜单及所有子菜单项吗？', '${ctx}/sys/menu/delete?id=${menu.id}');">删除</a>
                                            <a href="${ctx}/sys/menu/form?parent.id=${menu.id}">添加下级菜单</a>
                                        </td>
                                    </tr>
                                </c:forEach></tbody>
                            </table>
                        </div>
                        <div class="form-actions right">
                            <input id="btnSubmit" class="btn btn-primary" type="button" value="保存排序" onclick="updateSort();"/>
                        </div>
                    </form>
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
        $("#treeTable").treeTable({expandLevel : 2}).show();
    });

    function updateSort() {
        loading('正在提交，请稍等...', 'info');
        $("#listForm").attr("action", "${ctx}/sys/menu/updateSort");
        $("#listForm").submit();
    }
</script>
</body>
</html>