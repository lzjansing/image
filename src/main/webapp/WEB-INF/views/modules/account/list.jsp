<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>用户管理</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<sys:navbar/>
<div class="clearfix"></div>

<!-- BEGIN CONTAINER -->
<div class="page-container">
    <sys:sidebar tag="account"/>
    <!-- BEGIN PAGE -->
    <div class="page-content">
        <!-- BEGIN PAGE HEADER-->
        <div class="row">
            <div class="col-md-12">
                <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                <h3 class="page-title">
                    用户列表
                    <%--<shiro:hasPermission name="account:edit"><small><a href="${ctx}/account/form" class="text-muted">用户添加</a></small></shiro:hasPermission>--%>
                </h3>
                <!-- BEGIN PAGE TITLE & BREADCRUMB-->
            </div>
        </div>
        <!-- END PAGE HEADER-->

        <!-- BEGIN PAGE CONTENT-->
        <div class="row">
            <div class="col-md-12">
                <div class="portlet-body form">
                    <form:form id="searchForm" modelAttribute="account" action="${ctx}/account/" method="post" class="form-inline">
                        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                        <div class="form-actions top">
                            <div class="form-group">
                                <label class="control-label">登录名：</label>
                            </div>
                            <div class="form-group">
                                <form:input path="email" htmlEscape="false" maxlength="30" class="form-control"/>
                            </div>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <div class="form-group">
                                <label class="control-label">状态：</label>
                            </div>
                            <div class="form-group">
                                <form:select path="locked" class="form-control">
                                    <form:options items="${fns:getDictList('valid')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                                </form:select>
                            </div>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <div class="form-group">
                                <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
                            </div>
                        </div>
                    </form:form>
                </div>
                <sys:message content="${message}"/>
                <div class="table-scrollable">
                    <table id="contentTable" class="table table-bordered table-striped table-condensed">
                        <thead>
                        <tr>
                            <th>登录名</th>
                            <th>更新时间</th>
                            <shiro:hasPermission name="account:edit"><th>操作</th></shiro:hasPermission>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${page.list}" var="account">
                            <tr>
                                <td>
                                        ${account.email}
                                </td>
                                <td>
                                        ${fns:formatDateTimeLDT(account.createDate)}
                                </td>
                                <td>
                                    <shiro:hasPermission name="account:edit">
                                        <c:choose>
                                            <c:when test="${account.locked eq fns:getDictValue('正常', 'valid', '')}">
                                                <a href="#confirm" data-toggle="modal" onclick="confirmDialog('确认要禁用该用户吗？', '${ctx}/account/disable?id=${account.id}');">禁用</a>
                                            </c:when>
                                            <c:when test="${account.locked eq fns:getDictValue('禁用', 'valid', '')}">
                                                <a href="#confirm" data-toggle="modal" onclick="confirmDialog('确认要启用该用户吗？', '${ctx}/account/enable?id=${account.id}');">启用</a>
                                            </c:when>
                                        </c:choose>
                                        <c:if test="${account.valid ne fns:getObjConst('com.us.image.entities.Rule','VALID_DELETE')}">
                                            <a href="#confirm" data-toggle="modal" onclick="confirmDialog('确认要删除该用户吗？', '${ctx}/account/delete?id=${account.id}');">删除</a>
                                        </c:if>
                                    </shiro:hasPermission>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div>${page}</div>
            </div>
        </div>
        <!-- END PAGE CONTENT-->
    </div>
    <!-- END PAGE -->
</div>
<!-- END CONTAINER -->

<sys:footer/>

<script>
    jQuery(document).ready(function () {
        App.init();
    });

    function page(n, s) {
        $("#pageNo").val(n);
        $("#pageSize").val(s);
        $("#searchForm").submit();
        return false;
    }
</script>
</body>
</html>