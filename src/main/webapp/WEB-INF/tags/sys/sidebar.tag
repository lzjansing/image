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
        <c:forEach items="${fns:getMenuList()}" var="menu" varStatus="idxStatus">
            <c:if test="${menu.parent.id eq fns:getRootMenuId() && menu.isShow eq '1'}">
                <li class="">
                    <c:if test="${empty menu.href}">
                        <a href="javascript:">
                            <i class="fa ${menu.icon}"></i>
                            <span class="title">${menu.name}</span>
                            <span class="arrow "></span>
                        </a>
                    </c:if>
                    <ul class="sub-menu">
                        <c:forEach items="${fns:getMenuList()}" var="menuItem" varStatus="idxItemStatus">
                            <c:if test="${menuItem.parent.id eq menu.id && menuItem.isShow eq '1'}">
                                <li class="${fn:indexOf(menuItem.href, tag) != -1 ? 'active' : ''}">
                                    <a href="${fn:indexOf(menuItem.href, '://') eq -1 ? ctx : ''}${menuItem.href}">
                                        <i class="fa ${menuItem.icon}"></i>
                                            ${menuItem.name}</a>
                                </li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </li>
            </c:if>
        </c:forEach>
    </ul>
    <!-- END SIDEBAR MENU -->
</div>
<!-- END SIDEBAR -->