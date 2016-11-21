<%--
  Created by IntelliJ IDEA.
  User: Zhong Junbin
  Date: 2016/11/20 17:56
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
    <title>登陆</title>
    <link rel="shortcut icon" href="${basePath}/static/img/weibo7.png" type="image/x-icon"/>
    <link rel="stylesheet" href="${basePath}/static/css/login.css"/>
    <script type="text/javascript" src="${basePath}/static/js/jquery-1.11.2.js"></script>
    <script type="text/javascript" src="${basePath}/static/js/login.js"></script>
</head>
<body>
<div id="container">
    <div id="logo">
    </div>
    <div id="header">
        <a href="#">
        </a>
    </div>
    <div id="main">
        <div id="mainLeft">
            <div id="top">
                <i>还没有微博帐号？现在加入</i>
                <a href="${ctx}/access/register">

                    立即注册

                </a>
            </div>
            <div id="bottom">
                <div id="mainPic"></div>
            </div>
        </div>
        <div id="mainRight">
            <div id="loginForm">
                <ul>
                    <li>
                        <a href="#">普通登录</a>
                    </li>
                    <em class="shu">|</em>
                    <li>
                        <a href="#">二维码登录</a>
                    </li>
                    <em class="shu">|</em>
                    <li>
                        <a href="#">短信登录</a>
                    </li>
                </ul>
                <form method="post" action="${ctx}/access/login">
                    <div class="filed" id="xing">
                        <input type="text" class="input" id="name" name="username" placeholder="邮箱"/>
                    </div>
                    <div class="filed pw" id="mima">
                        <input type="password" class="input" id="password" name="password" placeholder="请输入密码"/>
                    </div>
                    <div id="forget">
                        <%--<label for="tip" title="建议在网吧或公共电脑上取消该选项.">--%>
                        <%--<input id="tip" type="checkbox" checked="checked"/> 下次自动登录--%>
                        <%--</label>--%>
                        <a href="#" content="记不住登录状态，怎么办？" title="记不住登录状态，怎么办？" id="a_icon"></a>
                        <a href="#" id="forgetRight">忘记密码</a>
                    </div>
                    <div id="msg">
                        <span id="error" style="color: red;font-size: 32px;text-align: center;"></span>
                        <span id="register" style="color: blue;font-size: 32px;text-align: center;"></span>
                    </div>
                    <div id="yellowLogin">
                        <input type="submit" class="submit" id="submit" name="submit" value="登录"/>
                    </div>
                </form>
            </div>
            <div id="regist">
                还没有微博？
                <a href="${ctx}/access/register">立即注册！</a>
            </div>
            <div id="way">
                <a href="#" id="weibo">
                    <span></span> QQ登录微博

                </a><i></i>
                <a href="#" id="other">其他登录方式

                    <em title="展开"></em>

                </a>

                <div id="hide">
                    <ul>
                        <li><a href="#"><span class="con" id="tian"></span>天翼</a>
                        </li>
                        <li><a href="#"><span class="con" id="lian"></span>联通</a>
                        </li>
                        <li><a href="#"><span class="con" id="san"></span>360</a>
                        </li>
                        <li><a href="#"><span class="con" id="bai"></span>百度</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div id="footer">
        <ul>
            <li><a href="#"><span class="icon" id="iPhone"></span>iPhone/iPad</a>
            </li>
            <li><a href="#"><span class="icon" id="Android"></span>Android</a>
            </li>
            <li><a href="#"><span class="icon" id="Windows"></span>Windows Phone</a>
            </li>
            <li><a href="#"><span class="icon" id="otherPhone"></span>其他手机端</a>
            </li>
            <li><a href="#"><span class="icon" id="weiboIcon"></span>微博桌面</a>
            </li>
        </ul>
        <div id="last">
            <ul>
                <li>
                    <a href="#">关于微博</a>
                </li>
                <em class="last">|</em>
                <li>
                    <a href="#">微博帮助</a>
                </li>
                <em class="last">|</em>
                <li>
                    <a href="# ">意见反馈</a>
                </li>
                <em class="last">|</em>
                <li>
                    <a href="#">微博认证及合作</a>
                </li>
                <em class="last">|</em>
                <li>
                    <a href="# ">开放平台</a>
                </li>
                <em>|</em>

                <li>
                    <a href="#">微博招聘</a>
                </li>
                <em class="last">|</em>
                <li>
                    <a href="# ">新浪网导航</a>
                </li>
                <li>
                    <select>
                        <option selected=" " value=" ">中文(简体)</option>
                        <option selected=" " value=" ">中文(台湾)</option>
                        <option selected=" " value=" ">中文(香港)</option>
                        <option selected=" " value=" ">English</option>
                    </select>
                </li>
            </ul>
        </div>
        <div id="finally">
            <ul>
                <li>Copyright © 2009-2015 WEIBO</li>
                <li>北京微梦创科网络技术有限公司</li>
                <li>京网文[2011]0398-130号京ICP备12002058号</li>
                <li><a href="# ">全国人大常委会关于加强网络信息保护的决定</a>
                </li>
            </ul>
        </div>
    </div>
</div>
<c:if test="${not empty requestScope.errorMsg}">
    <script type="text/javascript">
        document.getElementById("error").innerHTML = "${requestScope.errorMsg}";
    </script>
</c:if>
<c:if test="${not empty requestScope.registerMsg}">
    <script type="text/javascript">
        document.getElementById("register").innerHTML = "${requestScope.registerMsg}";
    </script>
</c:if>
</body>
</html>