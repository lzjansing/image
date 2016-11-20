<%--
  Created by IntelliJ IDEA.
  User: Chung Junbin
  Date: 2016-07-08 10:01
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
    <title>${requestScope.user.nickname}的微博</title>
    <link rel="stylesheet" href="${basePath}/static/css/personalCenter.css"/>
    <link rel="shortcut icon" href="${basePath}/static/image/weibo7.png" type="image/x-icon"/>
    <script type="text/javascript" src="${basePath}/static/js/jquery-1.11.2.js"></script>
    <script type="text/javascript" src="${basePath}/static/js/weibo.js"></script>
    <script type="text/javascript" src="${basePath}/static/js/personalCenter.js"></script>
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
            <a class="user" href="#">${sessionScope.loginAccount.user.nickname}</a>
        </ul>

        <ul>
            <li><a class="massage" href="">message</a>
            </li>
            <li><a class="setting" href="">setting</a>
                <div id="setSlide">
                    <span id="retangle"></span>
                    <ul>
                        <li><a href="#">帐号设置</a>
                        </li>
                        <li><a href="#">v认证</a>
                        </li>
                        <li><a href="#">帐号安全</a>
                        </li>
                        <li><a href="#">隐私设置</a>
                        </li>
                        <li><a href="#">消息设置</a>
                        </li>
                        <li><a href="#"> 帮助中心</a>
                        </li>
                        <li><a href=href="${ctx}/access/logout">退出</a>
                        </li>
                    </ul>
                </div>
            </li>
            <li><a class="compose" href="">compose</a>
            </li>
        </ul>
        <div class="clear"></div>
    </div>
</div>
<!--内容部分-->
<div id="personal_container">
    <div id="personal_header">
        <div id="mask"></div>
        <a href="${ctx}/account/center">
            <img class="photo" alt="文婷婷" src="http://tp3.sinaimg.cn/3774621790/180/5707665503/0">
        </a>
        <h5><i>${sessionScope.loginAccount.user.nickname}</i></h5>
        <p>希望改变。。。</p>
    </div>
    <div id="personal_headerBottom">
        <ul>
            <%-- TODO --%>
            <%--<li><a href="${basePath}/pm/list/send">已发出的私信</a>--%>
            <%--</li>--%>
            <%--<li><a href="${basePath}/pm/list/resolved">已处理的私信</a>--%>
            <%--</li>--%>
            <%--<li><a href="${basePath}/pm/list/unprocessed">未处理的私信</a>--%>
            <%--</li>--%>
        </ul>
    </div>
    <!--左边内容-->
    <div id="container_left">
        <div id="container_left_first">
            <ul>
                <li>
                    <a href="${basePath}" class="border">
                        <span>185</span>
                        <b>关注</b>
                    </a>
                </li>
                <li>
                    <a href="${basePath}" class="border">
                        <span>205</span>
                        <b>粉丝</b>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <span>239</span>
                        <b>微博</b>
                    </a>
                </li>
            </ul>
        </div>
        <div class="container_left_second">
            <ul>
                <li>
                    <a href="#" id="sub">申请认证</a>
                    <a href="#" id="lv">
                        <span>Lv.13</span>
                    </a>
                </li>
                <li class="second_child">
                    <p>个人资料完成度</p>
                    <h3>55%</h3>
                    <span id="blow">
            			<span id="above"></span>
            			</span>
                </li>
                <li class="second_third">
                    <%-- TODO 修改个人信息 --%>
                    <%--<a href="${basePath}/u/detail" class="bottom_a">编辑个人资料&nbsp;></a></li>--%>
            </ul>
        </div>
        <div class="container_left_second">
            <ul>
                <li>
                    <a href="#" class="album">相册</a>
                </li>
                <li class="second_child">
                    <div id="big_album">
                        <img src="${basePath}/static/image/e0fc285ejw1ehcyhesq9cj218g0xc45e.jpg"/>
                    </div>
                    <div id="left_small_album">
                        <ul>
                            <li>
                                <a href="#"><img src="${basePath}/static/image/massage2-img4.jpg"
                                                 class="small_album"></a>
                            </li>
                            <li><a href="#"><img src="${basePath}/static/image/massage2-img4.jpg"
                                                 class="small_album"></a></li>
                        </ul>
                    </div>
                    <ul>

                        <li><a href="#"><img src="${basePath}/static/image/massage2-img4.jpg" class="small_album"></a>
                        </li>
                        <li><a href="#"><img src="${basePath}/static/image/massage2-img4.jpg" class="small_album"></a>
                        </li>
                        <li><a href="#"><img src="${basePath}/static/image/massage2-img4.jpg" class="small_album"></a>
                        </li>

                    </ul>
                </li>
                <li class="second_third">
                    <a href="#" class="bottom_a">查看更多&nbsp;></a></li>
            </ul>
        </div>
        <div class="container_left_second">
            <ul>
                <li>
                    <p class="Nota">公开分组</p>
                </li>
                <li class="second_child">
                    <div class="left_right">
                        <a href="#" class="album" id="emotion">情感生活3</a>
                        <a href="#" class="recommend">推荐</a>
                    </div>
                    <ul id="radical_photo">
                        <li>
                            <a href="#"><img src="${basePath}/static/image/0.jpg" class="radical"></a>
                            <a href="#" title="唯美式情感语录" alt="唯美式情感语录" class="comma">唯美式情感语录</a>
                        </li>
                        <li>
                            <a href="#"><img src="${basePath}/static/image/1.jpg" class="radical"></a>
                            <a href="#" title="999道私房菜" alt="999道私房菜" class="comma">999道私房菜</a>
                        </li>
                        <li>
                            <a href="#"><img src="${basePath}/static/image/2.jpg" class="radical"></a>
                            <a href="#" title="英文进修班" alt="英文进修班" class="comma">英文进修班</a>
                        </li>
                    </ul>
                </li>
                <li class="second_third">
                    <a href="#" class="bottom_a">查看更多&nbsp;></a></li>
            </ul>
        </div>
        <div class="container_left_second">
            <ul>
                <li>
                    <p class="Nota">赞</p>
                </li>
                <li class="second_child">
                    <div id="delet">
                        <a href="#" class="sorry">抱歉，此微博已被删除。查看帮助：http://t.cn/8sYl7Qb</a>
                    </div>
                </li>
                <li class="second_third">
                    <a href="#" class="bottom_a">查看更多&nbsp;></a></li>
            </ul>
        </div>
        <div class="container_left_second">
            <ul>
                <li>
                    <p class="Nota">我的微博人气</p>
                </li>
                <li class="second_child">
                    <div id="error">
                        <i></i>
                        <p>出了一点小错，明天再来吧。</p>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <!--右边内容-->
    <div id="container_right">
        <div id="container_right_head">
            <div id="head_left">
                <a href="#">全部
                    <span id="litter_retangle"></span>
                </a>
                <span id="transparent"></span>
            </div>
            <div id="head_right">
                <%-- TODO 搜索 --%>
                <%--<form method="post" action="${basePath}/u/lookup">--%>
                <%--<input type="text" class="input" id="search" name="nickname" placeholder="搜索微博用户"/>--%>
                <%--<button type="submit" class="search_icon" title="搜索"></button>--%>
                <%--</form>--%>
            </div>
        </div>
        <div class="container_right_context">
            <div class="context_top">
                发个微博，标明状态，在lu代码，
                <img src="${basePath}/static/image/j_org.gif" class="gif"/>
                <img src="${basePath}/static/image/j_org.gif" class="gif"/>
                <img src="${basePath}/static/image/j_org.gif" class="gif"/>
                <img src="${basePath}/static/image/j_org.gif" class="gif"/>
                ，剩半条命。
                <a href="#" title="五山路" class="adress">
                    <span></span>
                    <i></i>
                    五山路
                </a>
                <a href="#" class="context_right"></a>
            </div>
            <div class="context_middle">
                <a href="#"><img src="${basePath}/static/image/4e704b16jw1eo28stbp4hj204g04g74c.jpg"/></a>
                <div class="detail_adress">
                    <a href="#" class="Road">五山路</a>
                    <p>广东省广州市天河区五山路</p>
                    <a href="#" class="bottom_adress">地点详情</a>
                    <a href="#" class="zan" title="赞"><span></span></a>
                </div>
                <div class="comefrom">
                    <a href="#" title="2015-09-05-13 09:20" class="date">5月13日&nbsp;09:20</a>
                    <p>来自</p>
                    <a href="#">Ascend&nbsp;P6</a>
                </div>
            </div>
            <div class="context_bottom">
                <ul>
                    <li>
                        <p>阅读301</p>
                        <a href="#" class="tuiguang">推广</a></li>
                    <i></i>
                    <li><a href="#">转发</a></li>
                    <i></i>
                    <li><a href="#">评论2</a></li>
                    <i></i>
                    <li><a href="#"><span></span>1</a></li>
                </ul>
            </div>
        </div>
        <div class="container_right_context">
            <div class="context_top">
                不知道想要什么
                <a href="#" title="五山路" class="adress">
                    <span></span>
                    <i></i>
                    五山路
                </a>
                <a href="#" class="context_right"></a>
            </div>
            <div class="context_middle">
                <a href="#"><img src="${basePath}/static/image/33338.png"/></a>
                <div class="detail_adress">
                    <a href="#" class="Road">五山路</a>
                    <p>广东省广州市天河区五山路</p>
                    <a href="#" class="bottom_adress">地点详情</a>
                    <a href="#" class="zan" title="赞"><span></span></a>
                </div>
                <div class="comefrom">
                    <a href="#" title="2015-09-05-13 09:20" class="date">4月13日&nbsp;08:20</a>
                    <p>来自</p>
                    <a href="#">Ascend&nbsp;P6</a>
                </div>
            </div>
            <div class="context_bottom">
                <ul>
                    <li>
                        <p>阅读301</p>
                        <a href="#" class="tuiguang">推广</a></li>
                    <i></i>
                    <li><a href="#">转发</a></li>
                    <i></i>
                    <li><a href="#">评论2</a></li>
                    <i></i>
                    <li><a href="#"><span></span>1</a></li>
                </ul>
            </div>
        </div>
        <div class="container_right_context">
            <div class="context_top">
                <p>
                    <a href="#" class="hongbao">#李冰冰的红包#</a>
                    挣钱是一种能力，抢红包拼的是技术。我抢到了李冰冰 和
                    <a href="#" class="aite">@支付宝钱包 </a>
                    一起发出的现金红包，羊年好运就此开启！你也来试试手气吧~╮ (￣ 3￣) ╭ </p>
                <a href="#" title="李冰冰的红包" class="adress">
                    <span class="link"></span>
                    <i></i>
                    李冰冰的红包
                </a>
                <a href="#" class="context_right"></a>
            </div>
            <div class="context_big_middle">
                <a href="#"><img src="${basePath}/static/image/li.jpg"/></a>
                <ul>
                    <li><a href="" class="aite">@李冰冰&nbsp;的红包</a></li>
                    <li><a href="#" class="money">176554元</a></li>
                    <li><a href="#" class="receive_person">已有182823人领取</a></li>
                </ul>
                <a href="#" class="qiang"></a>
            </div>
            <div class="comefrom">
                <a href="#" title="2015-09-05-13 09:20" class="date">5月13日&nbsp;09:20</a>
                <p>来自</p>
                <a href="#">Ascend&nbsp;P6</a>
            </div>

            <div class="context_bottom">
                <ul>
                    <li>
                        <p>阅读218</p>
                        <a href="#" class="tuiguang">推广</a></li>
                    <i></i>
                    <li><a href="#">转发</a></li>
                    <i></i>
                    <li><a href="#">评论</a></li>
                    <i></i>
                    <li><a href="#"><span></span></a></li>
                </ul>
            </div>
        </div>
        <div class="container_right_context">
            <div class="context_top">
                做自己，认为对的！晚安～
                <a href="#" title="五山路" class="adress">
                    <span></span>
                    <i></i>
                    南宛路
                </a>
                <a href="#" class="context_right"></a>
            </div>
            <div class="context_middle">
                <a href="#"><img src="${basePath}/static/image/33338.png"/></a>
                <div class="detail_adress">
                    <a href="#" class="Road">南宛路</a>
                    <p>广东省茂名市高州市南苑路</p>
                    <a href="#" class="bottom_adress">地点详情</a>
                    <a href="#" class="zan" title="赞"><span></span></a>
                </div>
                <div class="comefrom">
                    <a href="#" title="2015-09-05-13 09:20" class="date">3月20日&nbsp;09:20</a>
                    <p>来自</p>
                    <a href="#">Ascend&nbsp;P6</a>
                </div>
            </div>
            <div class="context_bottom">
                <ul>
                    <li>
                        <p>阅读251</p>
                        <a href="#" class="tuiguang">推广</a></li>
                    <i></i>
                    <li><a href="#">转发2</a></li>
                    <i></i>
                    <li><a href="#">评论4</a></li>
                    <i></i>
                    <li><a href="#"><span></span>6</a></li>
                </ul>
            </div>
        </div>

        <div class="container_right_context">
            <div class="context_top">
                <p> 我正在参加中国邮政
                    <a href="#" class="hongbao">#春节友你#</a>
                    活动，集齐邮戳就能召唤iPhone 6！更有羊年邮票、邮储话费、邮乐网礼券、中邮保险、明信片、报刊订阅卡、EMS券等奖品等你领！快戳!</p>
                <a href="#" title="网页链接" class="adress">
                    <span class="link"></span>
                    <i></i>
                    网页链接
                </a>
                <a href="#" class="context_right"></a>
            </div>
            <div class="context_middle">
                <a href="#"><img src="${basePath}/static/image/e0fc285ejw1epdh4l4b4uj20dm06ugmh.jpg"
                                 class="middle_pic"/></a>
                <div class="comefrom">
                    <a href="#" title="2015-09-05-13 09:20" class="date">5月13日&nbsp;09:20</a>
                    <p>来自</p>
                    <a href="#">Ascend&nbsp;P6</a>
                </div>
            </div>
            <div class="context_bottom">
                <ul>
                    <li>
                        <p>阅读145</p>
                        <a href="#" class="tuiguang">推广</a></li>
                    <i></i>
                    <li><a href="#">转发</a></li>
                    <i></i>
                    <li><a href="#">评论</a></li>
                    <i></i>
                    <li><a href="#"><span></span></a></li>
                </ul>
            </div>
        </div>
        <div class="container_right_context">
            <div class="context_top">
                <p>转转转</p>
                <a href="#" class="context_right"></a>
            </div>
            <div class="zhuanfa">
                <a href="#" class="zhuanfa_name">@广州兼职全职广州招聘周末兼职网</a>
                <p>
                    其实，每天在宿舍和各种教室间奔波占座时，我特怀念那时有个教室有个座位属于我的日子。每天在一群陌生又熟悉的面孔间听课时，我特别怀念那时可以和前后左右打打闹闹的日子。每天急急忙忙出宿舍去上课时，我特别怀念坐在教室上自习的日子。当我再也不能一扭头就看到你们时，我才知道我失去了点什么。
                </p>
                <img src="${basePath}/static/image/6c65c58dgw1eela4e1kelj20c80hdjsn.jpg"/>
                <div class="comefrom">
                    <a href="#" title="2015-09-05-13 09:20" class="date">2014-3-19&nbsp;19:20</a>
                    <p>来自</p>
                    <a href="#">微博 weibo.com</a>
                </div>
                <div class="zhuanfa_ul">
                    <ul>
                        <li><a href="#">转发</a></li>
                        <i></i>
                        <li><a href="#">评论2</a></li>
                        <i></i>
                        <li><a href="#"><span></span>1</a></li>
                    </ul>
                </div>
            </div>
            <div class="comefrom">
                <a href="#" title="2015-09-05-13 09:20" class="date">5月13日&nbsp;09:20</a>
                <p>来自</p>
                <a href="#">Ascend&nbsp;P6</a>

            </div>
            <div class="context_bottom">
                <ul>
                    <li>
                        <p>阅读257</p>
                        <a href="#" class="tuiguang">推广</a></li>
                    <i></i>
                    <li><a href="#">转发</a></li>
                    <i></i>
                    <li><a href="#">评论2</a></li>
                    <i></i>
                    <li><a href="#"><span></span>1</a></li>
                </ul>
            </div>
        </div>
        <div class="container_right_context">
            <div class="context_top">
                01的世界中。。。 我在:
                <a href="#" title="汇景北路" class="adress">
                    <span></span>
                    <i></i>
                    汇景北路
                </a>
                <a href="#" class="context_right"></a>
            </div>
            <div class="context_middle">
                <a href="#"><img src="${basePath}/static/image/e0fc285ejw1eee8wcxl9aj20c80c8mxx.jpg"/></a>
                <div class="comefrom">
                    <a href="#" title="2015-09-05-13 09:20" class="date">5月13日&nbsp;09:20</a>
                    <p>来自</p>
                    <a href="#">Ascend&nbsp;P6</a>
                </div>
            </div>
            <div class="context_bottom">
                <ul>
                    <li>
                        <p>阅读351</p>
                        <a href="#" class="tuiguang">推广</a></li>
                    <i></i>
                    <li><a href="#">转发</a></li>
                    <i></i>
                    <li><a href="#">评论</a></li>
                    <i></i>
                    <li><a href="#"><span></span></a></li>
                </ul>
            </div>
        </div>
        <div id="container_right_footer">
            <div class="container_right_context">
                <div class="context_bottom" id="fanye">
                    <ul>
                        <li><a href="#">上一页</a></li>
                        <i></i>
                        <li><a href="#">第2页<span id="fanye_pic"></span></a></li>
                        <i></i>
                        <li><a href="#">下一页</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<!--页脚-->
<div class="footer">
    <div class="top">
        <div class="for-center-top">
            <ul class="splendid-weibo">
                <h2>微博精彩</h2>
                <li><a href="">热门微博</a>
                </li>
                <li><a href="">热门话题</a>
                </li>
                <li><a href="">名人堂</a>
                </li>
                <li><a href="">微博会员</a>
                </li>
                <li><a href="">微相册</a>
                </li>
                <li><a href="">微游戏</a>
                </li>
            </ul>
            <ul class="phone-weibo">
                <h2>手机玩转微博</h2>
                <li><a href="">WAP版</a>
                </li>
                <li><a href="">短彩信发微博</a>
                </li>
                <li><a href="">iPhone客户端</a>
                </li>
                <li><a href="">ipad客户端</a>
                </li>
                <li><a href="">Android客户端</a>
                </li>
            </ul>
            <ul class="cooperation">
                <h2>认证&合作</h2>
                <li><a href="">申请合作</a>
                </li>
                <li><a href="">开放平台</a>
                </li>
                <li><a href="">企业微博</a>
                </li>
                <li><a href="">链接网站</a>
                </li>
                <li><a href="">微博标识</a>
                </li>
                <li><a href="">广告服务</a>
                </li>
                <li><a href="">微博商学院</a>
                </li>
            </ul>
            <ul class="help">
                <h2>微博帮助</h2>
                <li><a href="">常见问题</a>
                </li>
                <li><a href="">自助服务</a>
                </li>
            </ul>
        </div>
    </div>
    <!--top-->
    <div class="bottom">
        <div class="for-center-bottom">
            <ul>
                <li><a href="">微博客服</a>
                </li>
                <li><a href="">意见反馈</a>
                </li>
                <li><a href="">开放平台</a>
                </li>
                <li><a href="">微博招聘</a>
                </li>
                <li><a href="">新浪网导航</a>
                </li>
                <li><a href="">举报处理大厅</a>
                </li>
                <li><a href="">京ICP证100780号</a>
                </li>
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
        </div>
        <!--for-center-bottom-->
    </div>
    <!--bottom-->
</div>
<!--footer-->
<a href="#" id="backTop"></a>
<div id="privateMassage">
    <a href="#">
        <span></span> 私信聊天
    </a>
</div>

<fieldset style="width: 120px; height: auto; float: left;">
    <legend>个人中心</legend>
    <p>
        <a href="${basePath}">
            <button type="button">我的收藏</button>
        </a>
    </p>

    <p>
        <a href="${basePath}">
            <button type="button">我的相册</button>
        </a>
    </p>
</fieldset>

<%-- TODO 搜索 --%>
<%--
<form method="post" action="${basePath}/u/lookup" style="float: right;">
    <p>
        <label>
            <input type="text" name="nickname"/>
        </label>
    </p>

    <p>
        <button type="submit">检索</button>
    </p>
</form>
--%>

</body>
</html>
