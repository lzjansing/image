<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>过滤规则管理</title>
    <meta name="decorator" content="default"/>
</head>
<body>
<sys:navbar/>
<div class="clearfix"></div>

<!-- BEGIN CONTAINER -->
<div class="page-container">
    <sys:sidebar tag="rule"/>
    <!-- BEGIN PAGE -->
    <div class="page-content">
        <!-- BEGIN PAGE HEADER-->
        <div class="row">
            <div class="col-md-12">
                <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                <h3 class="page-title">
                    过滤规则列表
                    <small><a href="${ctx}/rule/form" class="text-muted">过滤规则添加</a></small>
                </h3>
                <!-- BEGIN PAGE TITLE & BREADCRUMB-->
            </div>
        </div>
        <!-- END PAGE HEADER-->

        <!-- BEGIN PAGE CONTENT-->
        <div class="row">
            <div class="col-md-12">
                <div class="portlet-body form">
                    <form:form id="searchForm" modelAttribute="rule" action="${ctx}/rule/" method="post" class="form-inline">
                        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                        <div class="form-actions top">
                            <div class="form-group">
                                <label class="control-label">过滤策略：</label>
                            </div>
                            <div class="form-group">
                                <form:select path="type" class="form-control">
                                    <form:option value="" label=""/>
                                    <form:options items="${fns:getDictList('rule_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                                </form:select>
                            </div>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <div class="form-group">
                                <label class="control-label">状态：</label>
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
                            <th>过滤策略</th>
                            <th>过滤规则</th>
                            <th>更新时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${page.list}" var="rule">
                            <tr>
                                <td>
                                        ${rule.type}
                                </td>
                                <td><a href="${ctx}/rule/form?id=${rule.id}">
                                        ${rule.keyword}
                                </a></td>
                                <td>
                                    <fmt:formatDate value="${rule.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${rule.valid eq fns:getObjConst('com.us.image.entities.Rule','VALID_ENABLE')}">
                                            <a href="#confirm" data-toggle="modal" onclick="confirmDialog('确认要禁用该过滤规则吗？', '${ctx}/rule/disable?id=${rule.id}');">禁用</a>
                                        </c:when>
                                        <c:when test="${rule.valid eq fns:getObjConst('com.us.image.entities.Rule','VALID_DISABLE')}">
                                            <a href="#confirm" data-toggle="modal" onclick="confirmDialog('确认要启用该过滤规则吗？', '${ctx}/rule/enable?id=${rule.id}');">启用</a>
                                        </c:when>
                                    </c:choose>
                                    <c:if test="${rule.valid ne fns:getObjConst('com.us.image.entities.Rule','VALID_DELETE')}">
                                        <a href="#confirm" data-toggle="modal" onclick="confirmDialog('确认要删除该过滤规则吗？', '${ctx}/rule/delete?id=${rule.id}');">删除</a>
                                    </c:if>
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