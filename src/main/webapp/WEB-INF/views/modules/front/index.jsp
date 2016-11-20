<%--
  Created by IntelliJ IDEA.
  User: Zhong Junbin
  Date: 2016/11/20 18:04
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
    <title>图片分享平台</title>
    <link rel="stylesheet" href="${basePath}/static/css/weibo.css"/>
    <link rel="shortcut icon" href="${basePath}/static/image/weibo7.png" type="image/x-icon"/>
    <script type="text/javascript" src="${basePath}/static/js/jquery-1.11.2.js"></script>
    <script type="text/javascript" src="${basePath}/static/js/weibo.js"></script>
</head>
<body>
<!--页眉-->
<div class="header">
    <div class="for-center-header">
        <a href=""><span class="logo-image"></span></a>
        <input class="search-input" type="text" value="" name="search-input" placeholder="大家正在搜：YES24"/>
        <button type="submit">搜索</button>

        <ul>
            <a class="home" href="#">首页</a>
            <a class="discover" href="">发现</a>
            <a class="game" href="">游戏</a>
            <a class="user" href="">${sessionScope.loginAccount.user.nickname}</a>
        </ul>

        <ul>
            <li><a class="massage" href="">message</a></li>
            <li><a class="setting" href="">setting</a>
                <div id="setSlide">
                    <span id="retangle"></span>
                    <ul>
                        <li><a href="#">帐号设置</a></li>
                        <li><a href="#">v认证</a></li>
                        <li><a href="#">帐号安全</a></li>
                        <li><a href="#">隐私设置</a></li>
                        <li><a href="#">消息设置</a></li>
                        <li><a href="#"> 帮助中心</a></li>
                        <li><a href="${ctx}/access/logout">退出</a></li>
                    </ul>
                </div>
            </li>
            <li><a class="compose" href="">compose</a></li>
        </ul>
        <div class="clear"></div>
    </div>
</div>

<!--内容区域-->
<div class="container">
    <div class="vertical-navigation-bar">
        <ul>
            <li><a href="">首页</a></li>
            <li><a href="">消息</a></li>
            <li><a href="">收藏</a></li>
        </ul>
        <ul>
            <li><a href="">好友圈</a></li>
            <li><a href="">群微博</a></li>
            <li><a href="">特别关注</a></li>
            <li><a href="">本地生活</a></li>
            <li><a href="">华农</a></li>
            <li><a href="">悄悄关注</a></li>
        </ul>
    </div>
    <div class="secondary">
        <div class="content">
            <div class="publish">

                <p>有什么新鲜事想告诉大家?</p>

                <a class="news" href="">陈佩斯新剧重返央视，你期待吗？(图)　热门微博</a>

                <textarea class="publish-input" type="text" value="" name="publish-input"></textarea>

                <div class="kind">
                    <a class="expression" href="">表情</a>
                    <a class="picture" href="">图片</a>
                    <a class="vedio" href="">视频</a>
                    <a class="topic" href="">话题</a>
                    <a class="long-weibo" href="">长微博</a>
                    <a href="">……</a>
                </div>

                <div class="button">
                    <a class="who-can-see" href="">公开<span>◢</span></a>
                    <a class="publish-button" href="">发布</a>
                </div>
            </div>

            <div class="classify">
                <a class="all" href="">全部</a>
                <a class="original" href="">原创</a>
                <a class="image" href="">图片</a>
                <a class="vedio" href="">视频</a>
                <a class="music" href="">音乐</a>

                <input type="text" value="" name="content-search-input" placeholder="搜索我关注人的微博">
                <button type="submit">搜索</button>
            </div>

            <div class="all-massage">
                <img class="portrait"
                     src="${basePath}/static/image/Guang-dong-news-portrait.jpg"/>

                <div class="group">
                    <a class="title-of-massage" href="">浩祥</a>

                    <p class="information">

                        <a class="Guang-dong-guanzhu" href="">#广东关注#</a>

                        【珠海去年GDP增长10.3%
                        居珠三角首位】2014年，广东GDP达6.78万亿元，同比增长7.8%。其中，2014年珠海完成地区生产总值1857.32亿元，增长10.3%；固定资产投资额1135.05亿元，增长23.5%；一般公共预算收入224.31亿元，增长23.6%。三项增速指标均居珠三角首位。

                        <a href="">网页链接</a>

                    </p>

                    <img src="${basePath}/static/image/Guang-dong-news.jpg"/>

                    <p class="time-and-address">

                        <a href="">今天 11:44</a>

                        <span>来自</span>

                        <a href="">微博 weibo.com</a>

                    </p>
                    <!--@knowledge为了整齐,换行、空行,不影响表现-->
                    <div class="clear"></div>

                </div>

                <div class="footer">

                    <ul>
                        <li><a class="collection" href="">收藏</a></li>
                        <li><a class="transmit" href="">转发 5</a></li>
                        <li><a class="comment" href="">评论</a></li>
                        <li><a class="praise" href="">赞 1</a></li>
                    </ul>

                </div>

            </div>


            <!--massage2-->
            <div class="all-massage">
                <img class="portrait" src="${basePath}/static/image/massage2-portrait.jpg"/>

                <div class="group">
                    <a class="title-of-massage" href="">新良广东美食</a>

                    <p class="information">

                        <a class="" href="">#下午茶时光#</a>

                        【草莓班戟】你是我的小呀小草莓，加上奶油就是绝配组合！♪(^∇^*)
                        这道甜品很有节日气氛，全家老少皆宜，酸酸甜甜好滋味。这个方子比较简单，所以一般都会成功，制作班戟皮的时候要全程小火，还要注意不要被烫到！via

                        <a href="">@honey小萌主</a>

                    </p>

                    <img class="mas2-img1" src="${basePath}/static/image/massage2-img1.jpg"/>
                    <img class="mas2-img2" src="${basePath}/static/image/massage2-img2.jpg"/>
                    <img class="mas2-img3" src="${basePath}/static/image/massage2-img3.jpg"/>
                    <img class="mas2-img4" src="${basePath}/static/image/massage2-img4.jpg"/>
                    <img class="mas2-img5" src="${basePath}/static/image/massage2-img5.jpg"/>
                    <img class="mas2-img6" src="${basePath}/static/image/massage2-img6.jpg"/>


                    <p class="time-and-address">

                        <a href="">今天 15:30</a>

                        <span>来自</span>

                        <a href="">微博 weibo.com</a>

                    </p>
                    <!--@knowledge为了整齐,换行、空行,不影响表现-->
                    <div class="clear"></div>

                </div>

                <div class="footer">

                    <ul>
                        <li><a class="collection" href="">收藏</a></li>
                        <li><a class="transmit" href="">转发 13</a></li>
                        <li><a class="comment" href="">评论 1</a></li>
                        <li><a class="praise" href="">赞 8</a></li>
                    </ul>

                </div>

            </div><!--massage2-->

            <!--massage3-->
            <div class="all-massage">
                <img class="portrait" src="${basePath}/static/image/singapore-portrait.jpg"/>

                <div class="group">
                    <a class="title-of-massage" href="">新加坡航空</a>

                    <p class="information">

                        <a class="" href="">#"新"动之旅"澳"世无双</a>

                        味蕾之旅将于1月25日结束！参与游戏即可赢得众多奖品及往返澳大利亚的机票，新航即刻带你亲自品尝澳大利亚众多美味。1月5日起至3月31日期间还有往返澳大利亚票价4500元起超值优惠哟！点击图片立刻参与游戏！味蕾之旅将于1月25日结束！参与游戏即可赢得众多奖品及往返澳大利亚的机票，新航即刻带你亲自品尝澳大利亚众多美味。

                    </p>

                    <div class="singapore-detail">
                        <img src="${basePath}/static/image/singapore-img1.jpg"/>
                        <a href="">
                            <span>“新”动之旅“澳”世无双第一站倒计时</span>
                            <span>味蕾之旅即将结束，往返澳大利亚机票等你来领，点击图片立刻参与活动！</span>
                        </a>
                    </div>

                    <p class="time-and-address">

                        <a href="">推荐</a>

                        <span>来自</span>

                        <a href="">微博 weibo.com</a>

                    </p>
                    <!--@knowledge为了整齐,换行、空行,不影响表现-->
                    <div class="clear"></div>

                </div>

                <div class="footer">

                    <ul>
                        <li><a class="collection" href="">收藏</a></li>
                        <li><a class="transmit" href="">转发 63</a></li>
                        <li><a class="comment" href="">评论 31</a></li>
                        <li><a class="praise" href="">赞 695</a></li>
                    </ul>

                </div>
            </div><!--massage3-->

        </div><!--content-->

        <div class="selfportrait-and-ad">
            <div class="selfportrait">
                <img class="background"
                     src="${basePath}/static/image/selfportrait-background.jpg"/>
                <img class="portrait" src="${basePath}/static/image/selfportrait.jpg"/>
                <%-- TODO 个人中心 --%>
                <a class="name" href="${ctx}/account/center">${sessionScope.loginAccount.user.nickname}</a>
                <ul class="footer">
                    <li>
                        <a href="">
                            <span class="num-of-focus">15</span><span class="focus">关注</span>
                        </a>
                    </li>

                    <li>
                        <a href="">
                            <span class="num-of-follow">3</span><span class="follow">粉丝</span>
                        </a>
                    </li>

                    <li>
                        <%-- TODO 个人中心 --%>
                        <a href="${ctx}/account/center">
                            <span class="num-of-weibo">2</span><span class="weibo">微博</span>
                        </a>
                    </li>
                </ul>
            </div>

            <div class="insteresting">

                <div class="header-of-insteresting">
                    <a class="title" href="">可能感兴趣的人</a>
                    <a href="">换一换</a>
                </div>

                <div class="suggest">
                    <a href="">
                        <img class="portrait" src="${basePath}/static/image/scau-portrait.jpg"/>
                    </a>
                    <div class="group">
                        <a class="weibo" href="">华农红满堂</a>
                        <a class="add" href="">关注</a>
                    </div>
                    <p>华南农业大学著名校友...<br/>关注<a href="">华农AOE英...</a>的人还关注</p>
                </div>

                <div class="suggest">
                    <a href="">
                        <img class="portrait"
                             src="${basePath}/static/image/hongmantang-portrait.jpg"/>
                    </a>
                    <div class="group">
                        <a class="weibo" href="">Enactus</a>
                        <a class="add" href="">关注</a>
                    </div>
                    <p>关注<a href="">华农AOE英...</a>的人还关注</p>
                </div>

                <div class="suggest">
                    <a href="">
                        <img class="portrait"
                             src="${basePath}/static/image/honghui-portrait.jpg"/>
                    </a>
                    <div class="group">
                        <a class="weibo" href="">华南农业大...</a>
                        <a class="add" href="">关注</a>
                    </div>
                    <p>关注<a href="">华农AOE英...</a>的人还关注</p>
                </div>

                <a class="more" href="">查看更多 ></a>

            </div><!--insteresting-->

            <div class="hot-topic">

                <div class="header-of-hot-topic">
                    <a class="title" href="">热门话题</a>
                    <a href="">换一换</a>
                </div>

                <div class="hot-topic-list">
                    <ul>
                        <li><a href="">#陈赫承认离婚#</a><span>4592万</span></li>
                        <li><a href="">#环卫工被打破眼球#</a><span>453万</span></li>
                        <li><a href="">#在押犯用微信骗裸聊#</a><span>4667万</span></li>
                        <li><a href="">#中国好歌曲#</a><span>11.2亿</span></li>
                        <li><a href="">#郑恺夜店小王子#</a><span>74万</span></li>
                        <li><a href="">#何以琛原型#</a><span>213万</span></li>
                        <li><a href="">#现实版匹诺曹#</a><span>82万</span></li>
                        <li><a href="">#许婧强势#</a><span>56万</span></li>
                    </ul>
                </div>

                <a class="more" href="">查看更多 ></a>
            </div><!--热门话题-->

            <div class="goods-recommend">
                <div class="header-of-hot-goods-recommend">
                    <a class="title" href="">热门商品推荐</a>
                    <a href="">换一换</a>
                </div>

                <a href=""><img src="${basePath}/static/image/goods-recommend.jpg"/></a>
            </div><!--热门商品推荐-->
        </div><!--selfportrait-and-ad-->
    </div><!--secondary-->
</div><!--container-->

<!--页脚-->
<div class="footer">
    <div class="top">
        <div class="for-center-top">
            <ul class="splendid-weibo">
                <h2>微博精彩</h2>
                <li><a href="">热门微博</a></li>
                <li><a href="">热门话题</a></li>
                <li><a href="">名人堂</a></li>
                <li><a href="">微博会员</a></li>
                <li><a href="">微相册</a></li>
                <li><a href="">微游戏</a></li>
            </ul>
            <ul class="phone-weibo">
                <h2>手机玩转微博</h2>
                <li><a href="">WAP版</a></li>
                <li><a href="">短彩信发微博</a></li>
                <li><a href="">iPhone客户端</a></li>
                <li><a href="">ipad客户端</a></li>
                <li><a href="">Android客户端</a></li>
            </ul>
            <ul class="cooperation">
                <h2>认证&合作</h2>
                <li><a href="">申请合作</a></li>
                <li><a href="">开放平台</a></li>
                <li><a href="">企业微博</a></li>
                <li><a href="">链接网站</a></li>
                <li><a href="">微博标识</a></li>
                <li><a href="">广告服务</a></li>
                <li><a href="">微博商学院</a></li>
            </ul>
            <ul class="help">
                <h2>微博帮助</h2>
                <li><a href="">常见问题</a></li>
                <li><a href="">自助服务</a></li>
            </ul>
        </div>
    </div><!--top-->
    <div class="bottom">
        <div class="for-center-bottom">
            <ul>
                <li><a href="">微博客服</a></li>
                <li><a href="">意见反馈</a></li>
                <li><a href="">开放平台</a></li>
                <li><a href="">微博招聘</a></li>
                <li><a href="">新浪网导航</a></li>
                <li><a href="">举报处理大厅</a></li>
                <li><a href="">京ICP证100780号</a></li>
            </ul>

            <p class="copyright">
                Copyright © 2009-2015 WEIBO 北京微梦创科网络技术有限公司
            </p>

            <a href="">京网文[2014]2046-296号</a>
            <a href="">京ICP备12002058号</a>
            <a href="">增值电信业务经营许可证B2-20140447</a>
            <select name="language" id="language">
                <option value="1">中文(简体)</option>
                <option value="2">中文(台湾)</option>
                <option value="3">中文(香港)</option>
                <option value="3">English</option>
            </select>
        </div>    <!--for-center-bottom-->
    </div><!--bottom-->
</div><!--footer-->
<a href="#" id="backTop"></a>
<div id="privateMassage">
    <a href="#">
        <span></span>
        私信聊天
    </a>
</div>
</body>
</html>
