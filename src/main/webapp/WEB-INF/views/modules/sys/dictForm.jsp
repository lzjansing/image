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
    <sys:sidebar tag="sys/dict" />
    <!-- BEGIN PAGE -->
    <div class="page-content">
        <!-- BEGIN PAGE HEADER-->
        <div class="row">
            <div class="col-md-12">
                <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                <h3 class="page-title">
                    字典${not empty dict.id ? '修改' : '添加'}
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
                    <form:form id="inputForm" modelAttribute="dict" action="${ctx}/sys/dict/save" class="form-horizontal form-row-seperated" method="post">
                        <form:hidden path="id"/>
                        <sys:message content="${message}"/>
                        <div class="form-body">
                            <div class="form-group">
                                <label class="control-label col-md-2"><span class="required">*</span>&nbsp;键值：</label>
                                <div class="col-md-9">
                                    <form:input path="value" htmlEscape="false" maxlength="50" class="required form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2"><span class="required">*</span>&nbsp;标签：</label>
                                <div class="col-md-9">
                                    <form:input path="label" htmlEscape="false" maxlength="50" class="required form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2"><span class="required">*</span>&nbsp;类型：</label>
                                <div class="col-md-9">
                                    <form:input path="type" htmlEscape="false" maxlength="50" class="required form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2"><span class="required">*</span>&nbsp;描述：</label>
                                <div class="col-md-9">
                                    <form:input path="description" htmlEscape="false" maxlength="50" class="required form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2"><span class="required">*</span>&nbsp;排序：</label>
                                <div class="col-md-9">
                                    <form:input path="sort" htmlEscape="false" maxlength="11" class="required form-control"/>
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
                            <shiro:hasPermission name="sys:dict:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
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
    });
</script>
</body>
</html>