<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<header>
    <title>字典管理</title>
    <meta name="decorator" content="default"/>
</header>
<body>
<sys:navbar/>
<div class="clearfix"></div>

<!-- BEGIN CONTAINER -->
<div class="page-container">
    <sys:sidebar tag="sys/dict" />
    <!-- BEGIN PAGE -->
    <div class="page-content">
        <!-- BEGIN PAGE HEADER-->
        <div class="row">
            <div class="col-md-12">
                <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                <h3 class="page-title">
                    字典列表
                    <small><a href="${ctx}/sys/dict/form?sort=10" class="text-muted">字典添加</a></small>
                </h3>
                <!-- BEGIN PAGE TITLE & BREADCRUMB-->
            </div>
        </div>
        <!-- END PAGE HEADER-->

        <!-- BEGIN PAGE CONTENT-->
        <div class="row">
            <div class="col-md-12">
                <div class="portlet-body form">
                    <form:form id="searchForm" modelAttribute="dict" action="${ctx}/sys/dict/" method="post" class="form-inline">
                        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                        <div class="form-actions top">
                            <div class="form-group">
                                <label class="control-label">类型：</label>
                            </div>
                            <div class="form-group">
                                <form:select id="type" path="type" class="form-control">
                                    <form:option value="" label=""/>
                                    <form:options items="${typeList}" htmlEscape="false"/>
                                </form:select>
                            </div>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <div class="form-group">
                                <label class="control-label">描述：</label>
                            </div>
                            <div class="form-group">
                                <form:input path="description" htmlEscape="false" maxlength="50" class="form-control"/>
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
                            <th>键值</th>
                            <th>标签</th>
                            <th>类型</th>
                            <th>描述</th>
                            <th>排序</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${page.list}" var="dict">
                            <tr>
                                <td>${dict.value}</td>
                                <td><a href="${ctx}/sys/dict/form?id=${dict.id}">${dict.label}</a></td>
                                <td><a href="javascript:" onclick="$('#type').val('${dict.type}');$('#searchForm').submit();return false;">${dict.type}</a></td>
                                <td>${dict.description}</td>
                                <td>${dict.sort}</td>
                                <td>
                                    <a href="${ctx}/sys/dict/form?id=${dict.id}">修改</a>
                                    <a href="#confirm" data-toggle="modal" onclick="confirmDialog('确认要删除该字典吗？', '${ctx}/sys/dict/delete?id=${dict.id}&type=${dict.type}');">删除</a>
                                    <a href="<c:url value='${fns:getAdminPath()}/sys/dict/form?type=${dict.type}&sort=${dict.sort+10}'><c:param name='description' value='${dict.description}'/></c:url>">添加键值</a>
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

<sys:footer />

<script>
    jQuery(document).ready(function() {
        App.init();
    });

    function page(n,s){
        $("#pageNo").val(n);
        $("#pageSize").val(s);
        $("#searchForm").submit();
        return false;
    }
</script>
</body>
</html>