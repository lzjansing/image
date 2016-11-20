<%--
  Created by IntelliJ IDEA.
  User: Chung Junbin
  Date: 2016-07-05 13:04
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <c:set var="ctx" value="${pageContext.request.contextPath}${fns:getFrontPath()}"/>
    <c:set var="basePath"
           value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>
    <title>注册</title>
    <link rel="stylesheet" href="${basePath}/static/css/font-awesome.css"/>
    <link rel="stylesheet" href="${basePath}/static/css/regular.css"/>
    <link rel="stylesheet" href="${basePath}/static/css/Regist.css"/>
    <link rel="shortcut icon" href="${basePath}/static/img/weibo7.png" type="image/x-icon"/>
    <script type="text/javascript" src="${basePath}/static/js/jquery-1.11.2.js"></script>
    <script type="text/javascript" src="${basePath}/static/js/regist.js"></script>
</head>
<body>
<div class="z-container">
    <!--页头-->
    <div class="z-header">
        <img src="${pageContext.request.contextPath}/static/img/weibo7.png"/>
        <h2>用户注册</h2>
        <p>已有帐号，</p>
        <a href="${pageContext.request.contextPath}/access/login">直接登录</a>
    </div>
    <span class="border-divider"></span>
    <!--内容-->
    <div id="z-left-content">
        <form method="post" modelAttribute="account">
            <div id="z-PhoneRegist">
                <label for="z-account" class="z-label">邮箱:</label>
                <input type="text" id="z-account" name="email" class="z-Linput" placeholder="请输入邮箱" maxlength="20"
                       path="email"/>
                <span id="emailInfo"></span>
            </div>
            <div id="z-setPassword">
                <label for="z-account0" class="z-label">密码:</label>
                <input type="password" id="z-account0" name="password" class="z-Linput" placeholder="请输入4-16位密码"
                       maxlength="16" path="password"/>
                <span id="z-progress"><i id="z-Real"></i></span>
            </div>
            <div id="msg">
                <span id="error" style="color: red;font-size: 32px;text-align: center;"></span>
            </div>
            <input type="submit" value="立即注册" class="z-regist">
        </form>
    </div>
    <!--页脚-->
    <div id="z-footer">
        <p>@2016Weibo</p>
    </div>
</div>
<c:if test="${not empty requestScope.errorMsg}">
    <script type="text/javascript">
        document.getElementById("error").innerHTML = "${requestScope.errorMsg}";
    </script>
</c:if>
</body>
</html>
