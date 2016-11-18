<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <meta charset="utf-8" />
    <title>${fns:getConfig('productName')} 登录</title>
    <%@include file="/WEB-INF/views/include/head.jsp" %>

    <!-- BEGIN PAGE LEVEL SCRIPTS -->
    <link rel="stylesheet" type="text/css" href="${ctxFront}/plugins/select2/select2_metro.css"/>
    <!-- END PAGE LEVEL SCRIPTS -->

    <!-- BEGIN THEME STYLES -->
    <link rel="stylesheet" type="text/css" href="${ctxFront}/css/pages/login-soft.css"/>
    <!-- END THEME STYLES -->
</head>
<body class="login">
<%@include file="/WEB-INF/views/include/js.jsp" %>
<!-- BEGIN LOGO -->
<div class="logo">
    <img src="${ctxStatic}/java.eps" alt="" />
</div>
<!-- END LOGO -->

<!-- BEGIN LOGIN -->
<div class="content">
    <!-- BEGIN LOGIN FORM -->
    <form class="login-form" action="${ctx}/login" method="post">
        <h3 class="form-title">登录你的账号</h3>
        <div class="alert alert-danger display-hide">
            <button class="close" data-close="alert"></button>
            <span>输入用户名或密码</span>
        </div>
        <div class="alert alert-info ${empty message ? 'display-hide' : ''}">
            <button class="close" data-close="alert"></button>
            <span>${message}</span>
        </div>
        <div class="form-group">
            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
            <label class="control-label visible-ie8 visible-ie9">用户名</label>
            <div class="input-icon">
                <i class="fa fa-user"></i>
                <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="用户名" name="username"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">密码</label>
            <div class="input-icon">
                <i class="fa fa-lock"></i>
                <input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="密码" name="password"/>
            </div>
        </div>
        <c:if test="${isValidateCodeLogin}">
            <sys:validateCode name="validateCode" inputClass="form-control placeholder-no-fix" imageCssStyle="height: 34px;" />
        </c:if>
        <div class="form-actions">
            <label class="checkbox">
                <%--<input type="checkbox" name="rememberMe" ${rememberMe ? 'checked' : ''} /> 记住我--%>
            </label>
            <button type="submit" class="btn blue pull-right">
                登录 <i class="m-icon-swapright m-icon-white"></i>
            </button>
        </div>
        <div class="forget-password">
            <h4>忘记密码 ?</h4>
            <p>
                不要紧张, 点击 <a href="javascript:;"  id="forget-password">这里</a>
                重新设置你的密码
            </p>
        </div>
    </form>
    <!-- END LOGIN FORM -->

    <!-- BEGIN FORGOT PASSWORD FORM -->
    <form class="forget-form" action="index.html" method="post">
        <h3 >忘记密码 ?</h3>
        <p>输入邮箱地址重新设置密码</p>
        <div class="form-group">
            <div class="input-icon">
                <i class="fa fa-envelope"></i>
                <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Email" name="email" />
            </div>
        </div>
        <div class="form-actions">
            <button type="button" id="back-btn" class="btn">
                <i class="m-icon-swapleft"></i> 返回
            </button>
            <button type="submit" class="btn blue pull-right">
                提交 <i class="m-icon-swapright m-icon-white"></i>
            </button>
        </div>
    </form>
    <!-- END FORGOT PASSWORD FORM -->
</div>
<!-- END LOGIN -->

<!-- BEGIN COPYRIGHT -->
<div class="copyright">
    2016 &copy; us
</div>
<!-- END COPYRIGHT -->

<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript" src="${ctxFront}/plugins/backstretch/jquery.backstretch.min.js"></script>
<script type="text/javascript" src="${ctxFront}/plugins/select2/select2.min.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script type="text/javascript" src="${ctxFront}/scripts/login-soft.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    jQuery(document).ready(function() {
        App.init();
        Login.init();
    });
</script>
<!-- END JAVASCRIPTS -->
</body>
</html>