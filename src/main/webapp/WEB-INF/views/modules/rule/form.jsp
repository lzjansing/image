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
                    过滤规则${not empty rule.id?'修改':'添加'}
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
                    <form:form id="inputForm" modelAttribute="rule" action="${ctx}/rule/save" class="form-horizontal form-row-seperated" method="post">
                        <form:hidden path="id"/>
                        <sys:message content="${message}"/>
                        <div class="form-body">
                            <div class="form-group">
                                <label class="control-label col-md-2"><span class="required">*</span>&nbsp;过滤策略：</label>
                                <div class="col-md-9">
                                    <form:select path="type" class="form-control required">
                                        <form:option value="${fns:getObjConst('com.us.image.entities.Rule','RULETYPE_NORMAL')}" label="精确匹配"/>
                                        <form:option value="${fns:getObjConst('com.us.image.entities.Rule','RULETYPE_REGEXP')}" label="正则匹配"/>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2">过滤规则：</label>
                                <div class="col-md-9">
                                    <form:input path="keyword" htmlEscape="false" maxlength="50" class="form-control required"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-actions right">
                            <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
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

<sys:footer/>

<script>
    jQuery(document).ready(function () {
        App.init();
    });
</script>
</body>
</html>