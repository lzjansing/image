<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ taglib prefix="shiro" uri="/WEB-INF/tlds/shiros.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}${fns:getFrontPath()}"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}${fns:getFrontPath()}/static"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"/>
	<title>我的首页  随时随地发现分享新鲜事</title>
	<link rel="shortcut icon" href="${ctxStatic}/static/image/weibo7.png" type="image/x-icon"/>
	<link type="text/css" rel="stylesheet" href="${ctxStatic}/css/lheader.css"/>
	<link type="text/css" rel="stylesheet" href="${ctxStatic}/css/lindex.css"/>
	<script type="application/javascript">
		var ctx = "${ctx}";
		var ctxStatic = "${ctxStatic}";
		var currentUserId = "${loginUser ne null?loginUser.id:''}";
	</script>
	<script type="text/javascript" src="${ctxStatic}/js/lindex.js"></script>

</head>
<body>
<div class="header"><!--header-->
	<form action="${ctx}/access/" method="get" id="searchForm">
	<div class="headerInner">
		<div class="logo"><!--logo-->
			<a href="${ctx}/access/"></a>
		</div><!--logo结束-->
		<div class="search relative" id="searchDiv"><!--search-->
			<span class="placeHolder" id="placeHolder">大家都在：<b>关注油价</b></span>
			<input type="text" name="searchInput" id="searchInput" value="${searchInput}"/>
			<input type="hidden" name="pageNo" value="1" id="pageNoInput"/>
			<input type="hidden" name="byPraise" value="${byPraise}" id="byPraiseInput"/>
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


<div id="main"><!--main内容-->
	<div class="mainLeft"><!--main左侧内容-->
		<div id="LeftMainNav"><!--左侧主要导航-->
			<div>
				<a href="${ctx}/access/">最新分享</a>
			</div>
			<div>
				<a href="${ctx}/access/?byPraise=1">点赞榜</a>
			</div>
		</div><!--左侧主要导航结束-->
		<shiro:authenticated>
		<div id="LeftMainOth"><!--左侧辅佐导航-->
				<div>
					<a href="${ctx}/account/personal?by=collect">我的收藏</a>
				</div>
				<div>
					<a href="${ctx}/account/personal?by=comment">我的评论</a>
				</div>
				<div>
					<a href="${ctx}/account/personal?by=praise">我的赞</a>
				</div>
				<div>
					<a href="${ctx}/account/personal?by=focus">我的关注</a>
				</div>
				<div>
					<a href="${ctx}/account/personal">我的微博</a>
				</div>
		</div>
		</shiro:authenticated>
	</div><!--main左侧内容结束-->

	<div class="mainRight"><!--main右侧内容-->
		<div class="RightMainCt"><!--main右侧主要内容-->
			<form:form modelAttribute="share" action="${ctx}/account/share" method="post" id="shareForm">
				<div class="publishWrap clearfix"><!--分享发布块-->
					<div>
						<p class="publishTbl"><!--publishTbl-->

						</p>
						<p class="numTips">
							还剩<span>300</span>个字
						</p>
						<p class="clearfix"></p>
					</div>
					<div class="publishDiv"><!--publish area-->
						<form:textarea path="content" cols="90" rows="6" id="publishInput" title="分享输入区" maxlength="300"/>
					</div>
					<div class="publishOth relative"><!--others-->
						<div class="kinds">
							<a href="javascript:void(0)">
								<span>表情</span>
								<div><!--表情显示区-->
								</div>
							</a>
							<a href="javascript:void(0)" id="upload">
								<span>图片</span>
							</a>
						</div>
						<div id="uploadDiv">
							<form:hidden path="image" id="imageInput"/>
							<div id="uploadBtn">选择图片
								<input type="file" id="files" multiple="multiple" title="请点击选择图片" onchange="imgchange(event)" accept="image/gif,image/jpeg,image/jpg,image/png,image/bmp"/>
							</div>

							<div id="picturePreView">
								<div style="display:none;"></div>
								<c:forEach items="${fns:split(share.image, fns:getConfig('string.split'))}" var="img" varStatus="status">
									<div><img id="uploadImg${status.index}" src="${img}" height="65px"/></div>
								</c:forEach>
							</div>
							<%--<div class="clearfix">--%>
								<%--<input type="button" name="uploadBtn" value="确认" id="confirmBtn" />--%>
							<%--</div>--%>
						</div>
						<form:hidden path="privated" id="privatedInput"/>
						<div class="funcs relative">
							<a href="javascript:void(0);" id="publicOrPrv">
								<span>公开</span>
							</a>
							<ul id="pulishPublicOrPrv">
								<li>
									<a href="javascript:void(0);">
										仅自己可见
									</a>
								</li>
								<li>
									<a href="javascript:void(0);">
										公开
									</a>
								</li>
							</ul>
							<a href="javascript:void(0)" id="publishBtn">分享</a>
						</div>
						<div class="clearfix"></div>
					</div>
				</div><!--分享发布块结束-->
			</form:form>

			<div class="shareShow"><!--分享显示区-->
				<c:forEach items="${page.list}" var="eachShare">
					<div class="showPn">
						<div class="list"><!--内容显示-->
							<a href="${ctx}/access/otherPersonal/${eachShare.user.id}">
								<img src="${ctxStatic}/image/portrait.jpg"/>
							</a>

							<div class="multiCt relative">
								<a href="${ctx}/access/otherPersonal/${eachShare.user.id}">
										${eachShare.user.nickname}
								</a>
								<c:if test="${eachShare.user.id eq fns:getAccount().user.id}">
									<a href="javascript:void(0);" class="publicOrPrv unlockedShare" share-id="${eachShare.id}" title="公开/不公开"></a>
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
											<div class="commentsList" style="display:none;"></div>
										</div>
									</div>
								</div><!--评论列表结束-->
							</div>
						</div>
					</div>
				</c:forEach>

				<c:if test="${not page.firstPage or not page.lastPage}">
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
				</c:if>
			</div>
		</div><!--main右侧主要内容结束-->
			<div class="otherMassage"><!--main右侧个人信息栏-->
				<shiro:authenticated>
				<div class="personalMassage">
					<div class="personalPortrait">
						<a href="${ctx}/account/personal">
							<img src="${ctxStatic}/image/portrait.jpg" />
						</a>
					</div>
					<div class="personalName">
						<a href="${ctx}/account/personal">
						<span>${fns:getAccount().user.nickname}</span>
						</a>
					</div>
					<div class="personalManage">
						<div>
							<a href="${ctx}/account/personal?by=focus">
								<p class="currentUserFocusCount">${focusCount}</p>
								<p>关注</p>
							</a>
						</div>
						<div>
							<a href="${ctx}/account/personal?by=beFocused">
								<p class="currentUserBeFocusedCount">${beFocusedCount}</p>
								<p>粉丝</p>
							</a>
						</div>
						<div>
							<a href="${ctx}/account/personal">
								<p class="currentUserShareCount">${shareCount}</p>
								<p>分享</p>
							</a>
						</div>
					</div>
				</div>
				</shiro:authenticated>
				<shiro:guest>
				<div class="registerPn">
					<div class="registerDiv">
						<div class="registerTbl">
							<a href="javascript:void(0)">账号登录</a>
							<a href="${ctx}/access/register">注册</a>
							<div class="clearfix"></div>
						</div>
						<form method="post" action="${ctx}/access/login">
						<div class="registerInput">
							<input type="text" name="username" id="userName"  placeholder="请输入邮箱"/>
							<input type="password" name="password"  id="password" placeholder="请输入密码"/>
						</div>
							<div class="extraDiv">
								<%--<div class="rememberMe">--%>
									<%--<input type="checkbox" id="checkbox"/>--%>
									<%--<label for="checkbox">记住我</label>--%>
								<%--</div>--%>
								<%--<div class="findPswd">--%>
									<%--<a href="javascript:void(0)">忘记密码</a>--%>
								<%--</div>--%>
							</div>
						<input type="submit" value="登录" name="submit" id="submitBtn"/>
						</form>
						<div class="registerTip">
							<span>还没有账号？</span><a href="${ctx}/access/register">立即注册！</a>
						</div>
					</div>
				</div>
				</shiro:guest>
			</div><!--main右侧个人信息栏结束-->
			<div class="clearfix"></div>
	</div>
	<div class="clearfix"></div>
</div>


<script type="text/javascript" src="${pageContext.request.contextPath}/front/plugins/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/default-share.js"></script>
<script type="application/javascript">
	//分享表单
	//是否公开
	$("#pulishPublicOrPrv > li:first").click(function(){
		$("#privatedInput").val(1);
	});
	$("#pulishPublicOrPrv > li:last").click(function(){
		$("#privatedInput").val(0);
	});

	var imgCount = 0;
	//图片上传
	var imgchange = function(event){
		var files = event.target.files, file;
		if(files && files.length>0){
			//限制6个
			if(imgCount+files.length>6){
				alert("超过啦，最多选6个～");
				return false;
			}
			//显示
			for(var i=0; i<files.length; i++){
				file = files[i];
				var URL = window.URL || window.webkitURL;
				var imgURL = URL.createObjectURL(file);
				var imgHTML = "<div><img id='uploadImg"+(imgCount+i)+"' src='"+imgURL+"' height='65px' /></div>";
				$("#picturePreView > div:last-child").after(imgHTML);
			}
			//逐个上传
			for(var i=0; i<files.length; i++){
				var data = new FormData();
				data.append('file', $(':file')[0].files[i]);
				data.append('id', "uploadImg"+(imgCount+i));
				$.ajax({
					url: '${pageContext.request.contextPath}/uploadAjax',
					type: 'POST',
					data: data,
					/**
					 * 必须false才会避开jQuery对 formdata 的默认处理
					 * XMLHttpRequest会对 formdata 进行正确的处理
					 */
					processData: false,
					/**
					 *必须false才会自动加上正确的Content-Type
					 */
					contentType: false
				}).done(function(msg){
					if (msg && msg.code=="success") {
						$('#'+msg.extra.id).attr("src", '${pageContext.request.contextPath}'+msg.extra.path);
					}else{
						alert("图片上传失败，请重试");
					}
				});
			}
			imgCount += files.length;
		}
	};

	//分享按钮
	$("#publishBtn").click(function(){
		if(!assertLogin()){
			alert("请先登录！");
			return false;
		}
		//处理表单参数
		var tmpArr = [];
		$("#picturePreView img").each(function(){
			tmpArr.push($(this).attr("src"));
		});
		if(tmpArr.length==0){
			alert("请选择要分享的图片～");
			return false;
		}
		$("#imageInput").val(tmpArr.join("${fns:getConfig('string.split')}"));
		//提交
		$("#shareForm").submit();
	});

	//搜索按钮
	$("#searchSubmitClick").click(function(){
		$("#byPraiseInput").val("");
		$("#searchForm").submit();
	});
	//分页，需要处理一般分页、搜索分页、点赞榜分页
	$("#prePage").click(function(){
		$("#pageNoInput").val("${page.prev}");
		$("#searchForm").submit();
	});
	$("#nextPage").click(function(){
		$("#pageNoInput").val("${page.next}");
		$("#searchForm").submit();
	});
</script>
</body>
</html>