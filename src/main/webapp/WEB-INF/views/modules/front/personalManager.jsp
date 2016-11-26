<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ taglib prefix="shiro" uri="/WEB-INF/tlds/shiros.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}${fns:getFrontPath()}"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}${fns:getFrontPath()}/static"/>
<script type="application/javascript">
    var ctx = "${ctx}";
    var ctxStatic = "${ctxStatic}";
    var currentUserId = "${loginUser ne null?loginUser.id:''}";
</script>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>个人中心</title>
    <link type="text/css" rel="stylesheet" href="${ctxStatic}/css/lheader.css"/>
    <link type="text/css" rel="stylesheet" href="${ctxStatic}/css/lindex.css"/>
    <link type="text/css" rel="stylesheet" href="${ctxStatic}/css/lpersonalManager.css"/>
    <script type="text/javascript" src="${ctxStatic}/js/lpersonalManager.js"></script>
</head>
<body>
<div class="header"><!--header-->
    <form action="${ctx}/access/" method="get" id="searchForm">
        <div class="headerInner">
            <div class="logo"><!--logo-->
                <a href="${ctx}/access/"></a>
            </div><!--logo结束-->
            <div class="search relative" id="searchDiv"><!--search-->
                <input type="text" name="searchInput" id="searchInput" value="${searchInput}"/>
                <a href="javascript:void(0);" id="searchSubmitClick"></a>
            </div><!--search结束-->
            <div class="navConfg"><!--导航以及设置-->
                <div class="nav">
                    <ul>
                        <li><a href="${ctx}/access/" class="home"></a></li>
                        <li><a href="#" class="discover"></a></li>
                        <shiro:authenticated>
                            <li>
                                <a href="${ctx}/account/center" id="loginUser" class="relative">
                                    <span class="relative" id="loginUserName">${fns:getAccount().user.nickname}</span>
                                </a>
                            </li>
                        </shiro:authenticated>
                    </ul>
                </div>
                <shiro:guest>

                    <div class="register relative">
                        <ul class="registerUl">
                            <li>
                                <a href="${ctx}/access/login">登录</a>
                            </li>
                            <li>
                                <span id="">|</span>
                            </li>
                            <li>
                                <a href="${ctx}/access/register">注册</a>
                            </li>
                        </ul>
                    </div>
                </shiro:guest>
                <shiro:authenticated>
                    <div class="confg relative">
                        <div id="massageConfg">
                            <div id="massageConfgHd" >
                                <ul>
                                    <li><a>评论我的分享</a></li>
                                    <li><a>赞我的分享</a></li>
                                    <li><a>收藏我的分享</a></li>
                                </ul>
                            </div>
                        </div>
                        <div id="userConfg" >
                            <div id="userConfgHd">
                                <ul>
                                    <li>消息设置</li>
                                    <li>隐私设置</li>
                                    <li><a href="${ctx}/access/logout">退出</a></li>
                                </ul>
                            </div>
                        </div>
                        <div id="editFast">
                        </div>
                    </div>
                </shiro:authenticated>
            </div><!--导航以及设置结束-->
        </div>
    </form>
</div><!--header结束-->
<div class="main clearfix">
    <div><!--mainWrap-->
        <div class="personalInfo"><!--个人信息块-->
            <div id="personalImg"><img src="${ctxStatic}/image/portrait.jpg" /></div>
            <div><h1 id="personalName">${pageUser.nickname}</h1><span id="sexIcon"></span></div>
            <div id="motto">Just do it</div>
        </div><!--个人信息块结束-->

        <div class="myHome"><a>我的主页</a></div><!--主页标识-->
        <div class="multiInfo"><!--多信息展示区-->
            <div class="leftNav"><!--leftNav-->
                <div>
                    <a href="${ctx}/account/personal?by=collect">收藏</a>
                    <p>${collectCount}</p>
                </div>
                <div>
                    <a href="${ctx}/account/personal?by=comment">评论</a>
                    <p>${commentCount}</p>
                </div>
                <div>
                    <a href="${ctx}/account/personal?by=praise">点赞</a>
                    <p>${praiseCount}</p>
                </div>
                <div>
                    <a href="${ctx}/account/personal">分享</a>
                    <p>${shareCount}</p>
                </div>
                <div>
                    <p>关注</p>
                    <p>${focusCount}</p>
                </div>
            </div><!--leftNav结束-->
            <div class="attendDetailShow">
                <c:choose>
                    <c:when test="${isShare eq null or not isShare}">
                <div class="lable">
                        <a>全部关注</a>
                </div>
                    </c:when>
                </c:choose>
                <div class="wrapDiv">
                    <c:choose>
                        <c:when test="${isShare eq null or not isShare}">
                    <div class="attendLists">
                        <div class="attendList">
                            <div class="attendImg">
                                <img src="${ctxStatic}/image/portrait7.jpg"/>
                            </div>
                            <div class="words">
                                <p>昵称：<strong>杨咩咩咩</strong></p>
                                <p>个性签名：<strong>怀一颗平常心~</strong></p>
                            </div>

                        </div>
                        <div class="attendList">
                            <div class="attendImg">
                                <img src="${ctxStatic}/image/portrait6.jpeg"/>
                            </div>
                            <div class="words">
                                <p>昵称：<strong>阳光浴</strong></p>
                                <p>个性签名：<strong>向着阳光奔跑~</strong></p>
                            </div>

                        </div>
                        <div class="attendList">
                            <div class="attendImg">
                                <img src="${ctxStatic}/image/portrait2.jpg" />
                            </div>
                            <div class="words">
                                <p>昵称：<strong>haha，我是帅帅</strong></p>
                                <p>个性签名：<strong>自然风景，美不胜收~自然风景，美不胜收~自然风景，美不胜收~</strong></p>
                            </div>

                        </div>
                        <div class="attendList">
                            <div class="attendImg">
                                <img src="${ctxStatic}/image/portrait4.jpg" />
                            </div>
                            <div class="words">
                                <p>昵称：<strong>夏目友人帐</strong></p>
                                <p>个性签名：<strong>自然风景，美不胜收~自然风景，美不胜收~</strong></p>
                            </div>
                        </div>
                        <div class="attendList">
                            <div class="attendImg">
                                <img src="${ctxStatic}/image/portrait5.jpg" />
                            </div>
                            <div class="words">
                                <p>昵称：<strong>航海梦</strong></p>
                                <p>个性签名：<strong>被梦想鞭笞的人~</strong></p>
                            </div>
                        </div>
                        <div class="attendList">
                            <div class="attendImg">
                                <img src="${ctxStatic}/image/portrait3.jpg" />
                            </div>
                            <div class="words">
                                <p>昵称：<strong>爱笑的姑娘</strong></p>
                                <p>个性签名：<strong>喜欢自娱自乐~心有一份欢喜~</strong></p>
                            </div>
                        </div>
                        <div class="attendList">
                            <div class="attendImg">
                                <img src="${ctxStatic}/image/portrait7.jpg"/>
                            </div>
                            <div class="words">
                                <p>昵称：<strong>杨咩咩咩</strong></p>
                                <p>个性签名：<strong>怀一颗平常心~</strong></p>
                            </div>

                        </div>
                        <div class="attendList">
                            <div class="attendImg">
                                <img src="${ctxStatic}/image/portrait6.jpeg"/>
                            </div>
                            <div class="words">
                                <p>昵称：<strong>阳光浴</strong></p>
                                <p>个性签名：<strong>向着阳光奔跑~</strong></p>
                            </div>

                        </div>
                        <div class="attendList">
                            <div class="attendImg">
                                <img src="${ctxStatic}/image/portrait2.jpg" />
                            </div>
                            <div class="words">
                                <p>昵称：<strong>haha，我是帅帅</strong></p>
                                <p>个性签名：<strong>自然风景，美不胜收~自然风景，美不胜收~自然风景，美不胜收~</strong></p>
                            </div>

                        </div>
                        <div class="attendList">
                            <div class="attendImg">
                                <img src="${ctxStatic}/image/portrait4.jpg" />
                            </div>
                            <div class="words">
                                <p>昵称：<strong>夏目友人帐</strong></p>
                                <p>个性签名：<strong>自然风景，美不胜收~自然风景，美不胜收~</strong></p>
                            </div>
                        </div>
                        <div class="attendList">
                            <div class="attendImg">
                                <img src="${ctxStatic}/image/portrait5.jpg" />
                            </div>
                            <div class="words">
                                <p>昵称：<strong>航海梦</strong></p>
                                <p>个性签名：<strong>被梦想鞭笞的人~</strong></p>
                            </div>
                        </div>
                        <div class="attendList">
                            <div class="attendImg">
                                <img src="${ctxStatic}/image/portrait3.jpg" />
                            </div>
                            <div class="words">
                                <p>昵称：<strong>爱笑的姑娘</strong></p>
                                <p>个性签名：<strong>喜欢自娱自乐~心有一份欢喜~</strong></p>
                            </div>
                        </div>

                    </div><!--detail展示区结束-->
                        </c:when>
                        <c:otherwise>
                    <div class="shareShow"><!--分享显示区-->
                        <c:forEach items="${page.list}" var="eachShare">
                            <div class="showPn">
                                <div class="list"><!--内容显示-->
                                    <a href="javascript:void(0);">
                                        <img src="${ctxStatic}/image/portrait.jpg"/>
                                    </a>

                                    <div class="multiCt relative">
                                        <a href="javascript:void(0);">
                                                ${eachShare.user.nickname}
                                        </a>
                                        <c:if test="${eachShare.user.id eq fns:getAccount().user.id}">
                                            <a href="javascript:void(0);" class="publicOrPrv ${eachShare.privated eq fns:getDictValue('否', 'yes_no', '')?'unlockedShare':'lockedShare'}" share-id="${eachShare.id}" title="公开/不公开"></a>
                                        </c:if>

                                        <p>
                                                ${fns:pastLTD(eachShare.createDate)}
                                        </p>
                                        <div class="shareCt">
                                            <p>${eachShare.content}</p>
                                            <div class="shareImg clearfix">
                                                <c:forEach items="${fns:split(eachShare.image, fns:getConfig('string.split'))}" var="img" varStatus="status">
                                                    <div><img height="150px" src="${img}" alt=""/></div>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="clearfix showPnFuncs"><!--功能选区-->
                                    <a href="javascript:void(0)" class="collection collection1 ${eachShare.hadCollected eq fns:getDictValue('是', 'yes_no', '')?'collected':'uncollected'}" share-id="${eachShare.id}">
                                        收藏 <span>${eachShare.collect}</span>
                                    </a>
                                    <a href="javascript:void(0)" class="collection collection2 unopen" share-id="${eachShare.id}">
                                        评论 <span>${eachShare.comment}</span>
                                    </a>
                                    <a href="javascript:void(0)" class="collection collection3 ${eachShare.hadPraised eq fns:getDictValue('是', 'yes_no', '')?'praised':'unpraised'}" share-id="${eachShare.id}">
                                        点赞 <span>${eachShare.praise}</span>
                                    </a>
                                    <!--评论-->
                                    <div class="comments">
                                        <div class="commentsPersonalInfo"><!--评论个人信息-->
                                            <form action="${ctx}/account/comment" method="post">
                                                <div >
                                                    <div class="commentsImg">
                                                        <a href="#"><img src="${ctxStatic}/image/portrait.jpg"/></a>
                                                    </div>
                                                    <input type="hidden" name="share.id" value="${eachShare.id}"/>
                                                    <div class="commentsInput">
                                                        <div class="commentsTip">评论剩<span>100</span>个字</div>
                                                        <textarea name="content" maxlength="100" placeholder="评论"></textarea>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                    <div class="commentsFun">
                                                        <a class="commentsPublish" href="javascript:void(0);">评论</a>
                                                        <div class="clearfix"></div>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                        <div class="commentsLists"><!--评论列表-->
                                            <div>
                                                <div class="tipsDiv">
                                                    全部评论
                                                </div>
                                                <div class="commentsListDiv">
                                                    <div class="commentsList" style="display:none;"></div>
                                                </div>
                                            </div>
                                        </div><!--评论列表结束-->
                                    </div>
                                </div>
                            </div>
                        </c:forEach>


                        <div id="remainShare">
                            <div>
                                <c:if test="${not page.firstPage}">
                                    <a id="prePage" href="javascript:void(0);">上一页</a>
                                </c:if>
                            </div>
                            <div class="relative selectedDiv">
                                <%--<a id="selectedPage" href="#">第一页</a>--%>
                                <%--<a id="selectBtn"></a>--%>
                                <%--<div id="selectDiv">--%>
                                <%--<ul id="selectUl">--%>
                                <%--<li><a href="#">第二页</a></li>--%>
                                <%--<li><a href="#">第三页</a></li>--%>
                                <%--<li><a href="#">第四页</a></li>--%>
                                <%--<li><a href="#">最后一页</a></li>--%>
                                <%--</ul>--%>
                                <%--</div>--%>
                            </div>
                            <div>
                                <c:if test="${not page.lastPage}">
                                    <a id="nextPage" href="javascript:void(0);">下一页</a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                        </c:otherwise>
                    </c:choose>
                    <div class="clearfix"></div>
                </div><!--wrapDiv结束-->
            </div><!--attendDetailShow结束-->
        </div><!--多信息展示区结束-->
        <div class="clearfix"></div>
    </div><!--mainWrap结束-->
</div><!--main结束-->
<div class="footer">
    <h1><a href="#">copyRight@</a></h1>
</div>
<form id="pageForm" action="${ctx}${acction}" method="get" hidden>
    <input hidden id="pageNoInput" name="pageNo">
    <input hidden name="by" value="${by}">
</form>

<script type="text/javascript" src="${pageContext.request.contextPath}/front/plugins/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/default-share.js"></script>
<script type="application/javascript">

    //搜索按钮
    $("#searchSubmitClick").click(function(){
        $("#searchForm").submit();
    });
    //分页，需要处理一般分页、搜索分页、点赞榜分页
    $("#prePage").click(function(){
        $("#pageNoInput").val("${page.prev}");
        $("#pageForm").submit();
    });
    $("#nextPage").click(function(){
        $("#pageNoInput").val("${page.next}");
        $("#pageForm").submit();
    });
</script>
</body>
</html>

