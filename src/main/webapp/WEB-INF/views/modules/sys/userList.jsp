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
    <sys:sidebar tag="sys/user"/>
    <!-- BEGIN PAGE -->
    <div class="page-content">
        <!-- BEGIN PAGE HEADER-->
        <div class="row">
            <div class="col-md-12">
                <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                <h3 class="page-title">
                    用户列表
                </h3>
                <!-- BEGIN PAGE TITLE & BREADCRUMB-->
            </div>
        </div>
        <!-- END PAGE HEADER-->

        <!-- BEGIN PAGE CONTENT-->
        <div class="row">
            <div class="col-md-12">
                <div class="portlet-body form">
                    <form:form id="searchForm" modelAttribute="user" action="${ctx}/sys/user/" method="post" class="form-inline">
                        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                        <div class="form-actions top">
                            <div class="form-group">
                                <label class="control-label">用户名：</label>
                            </div>
                            <div class="form-group">
                                <form:input path="username" htmlEscape="false" maxlength="30" class="form-control"/>
                            </div>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <div class="form-group">
                                <label class="control-label">账户状态：</label>
                            </div>
                            <div class="form-group">
                                <form:select path="valid" class="form-control">
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
                            <th>用户名</th>
                            <th>用户类型</th>
                            <th>关注量</th>
                            <th>更新时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${page.list}" var="user">
                            <tr>
                                <td>
                                        ${user.username}
                                </td>
                                <td>
                                        ${user.userType}
                                </td>
                                <td>
                                        ${user.focus}
                                </td>
                                <td>
                                        ${fns:formatDateTimeLDT(user.createDate)}
                                </td>
                                <td>
                                    <shiro:hasPermission name="sys:user:edit">
                                    <c:choose>
                                        <c:when test="${user.valid eq fns:getObjConst('com.us.image.entities.User','VALID_ENABLE')}">
                                            <a href="#confirm" data-toggle="modal" onclick="confirmDialog('确认要禁用该用户吗？', '${ctx}/sys/user/disable?id=${user.id}');">禁用</a>
                                        </c:when>
                                        <c:when test="${user.valid eq fns:getObjConst('com.us.image.entities.User','VALID_DISABLE')}">
                                            <a href="#confirm" data-toggle="modal" onclick="confirmDialog('确认要启用该用户吗？', '${ctx}/sys/user/enable?id=${user.id}');">启用</a>
                                        </c:when>
                                    </c:choose>
                                    <c:if test="${user.valid ne fns:getObjConst('com.us.image.entities.User','VALID_DELETE')}">
                                        <a href="#confirm" data-toggle="modal" onclick="confirmDialog('确认要删除该用户吗？', '${ctx}/sys/user/delete?id=${user.id}');">删除</a>
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