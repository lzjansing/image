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
                    菜单${not empty menu.id ? '修改' : '添加'}
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
                    <form:form id="inputForm" modelAttribute="menu" action="${ctx}/sys/menu/save" class="form-horizontal form-row-seperated" method="post">
                        <form:hidden path="id"/>
                        <sys:message content="${message}"/>
                        <div class="form-body">
                            <div class="form-group">
                                <label class="control-label col-md-2">上级菜单：</label>
                                <div class="col-md-9">
                                    <sys:treeselect id="menu" name="parent.id" value="${menu.parent.id}" labelName="parent.name" labelValue="${menu.parent.name}" title="菜单" url="/sys/menu/treeData" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2"><span class="required">*</span>&nbsp;名称：</label>
                                <div class="col-md-9">
                                    <form:input path="name" htmlEscape="false" maxlength="50" class="required form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2">链接：</label>
                                <div class="col-md-9">
                                    <form:input path="href" htmlEscape="false" maxlength="2000" class="form-control"/>
                                    <span class="help-block">点击菜单跳转的页面</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2">目标：</label>
                                <div class="col-md-9">
                                    <form:input path="target" htmlEscape="false" maxlength="10" class="form-control"/>
                                    <span class="help-block">链接地址打开的目标窗口</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2">图标：</label>
                                <div class="col-md-9">
                                    <sys:iconselect id="icon" name="icon" value="${menu.icon}"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2">排序：</label>
                                <div class="col-md-9">
                                    <form:input path="sort" htmlEscape="false" maxlength="50" class="required form-control"/>
                                    <span class="help-block">排序顺序，升序</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2">可见：</label>
                                <div class="col-md-9">
                                    <div class="radio-list">
                                        <label class="radio-inline">
                                            <input type="radio" name="isShow" value="${fns:getDictValue('是', 'yes_no', '')}" ${menu.isShow eq fns:getDictValue('是', 'yes_no', '') ? 'checked' : ''}> 显示
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="isShow" value="${fns:getDictValue('否', 'yes_no', '')}" ${menu.isShow eq fns:getDictValue('否', 'yes_no', '') ? 'checked' : ''}> 隐藏
                                        </label>
                                    </div>
                                    <span class="help-block">该菜单或操作是否显示到系统菜单中</span>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-2">权限标识：</label>
                                <div class="col-md-9">
                                    <form:input path="permission" htmlEscape="false" maxlength="100" class="form-control"/>
                                    <span class="help-block">控制器中定义的权限标识，如：@RequiresPermissions("权限标识")</span>
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

<sys:footer />

<script>
    jQuery(document).ready(function() {
        App.init();
    });
</script>
</body>
</html>