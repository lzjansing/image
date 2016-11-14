<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ attribute name="tag" type="java.lang.String" required="true" description="选中菜单标记" %>
<!-- BEGIN SIDEBAR -->
<div class="page-sidebar navbar-collapse collapse">
    <!-- BEGIN SIDEBAR MENU -->
    <ul class="page-sidebar-menu">
        <li>
            <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
            <div class="sidebar-toggler hidden-phone"></div>
            <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
        </li>
        <li class="start">
            <a href="${ctx}">
                <i class="fa fa-home"></i>
                <span class="title">控制面板</span>
            </a>
        </li>
        <li class="">
            <a href="javascript:">
                <i class="fa"></i>
                <span class="title">通用功能</span>
                <span class="arrow "></span>
            </a>
            <ul class="sub-menu">
                <li class="${fn:indexOf('user', tag) != -1 ? 'active' : ''}">
                    <a href="${ctx}/user/list">
                        <i class="fa"></i>
                        用户管理</a>
                </li>
                <li class="${fn:indexOf('rule', tag) != -1 ? 'active' : ''}">
                    <a href="${ctx}/rule/list">
                        <i class="fa"></i>
                        过滤规则管理</a>
                </li>
            </ul>
        </li>
        <li class="">
            <a href="javascript:">
                <i class="fa"></i>
                <span class="title">系统功能</span>
                <span class="arrow "></span>
            </a>
            <ul class="sub-menu">
                <li class="${fn:indexOf('sys/dict', tag) != -1 ? 'active' : ''}">
                    <a href="${ctx}/sys/dict/list">
                        <i class="fa"></i>
                        字典管理</a>
                </li>
            </ul>
        </li>
    </ul>
    <!-- END SIDEBAR MENU -->
</div>
<!-- END SIDEBAR -->