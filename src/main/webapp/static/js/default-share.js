
//具体的分享消息
//公开私有按钮
$(document).on("click", ".lockedShare", function(){
    var t = $(this);
    $.get(ctx+'/account/unsetSharePrivate/'+t.attr("share-id"));
    t.removeClass("lockedShare");
    t.addClass("unlockedShare");
});
$(document).on("click", ".unlockedShare", function(){
    var t = $(this);
    $.get(ctx+'/account/setSharePrivate/'+t.attr("share-id"));
    t.removeClass("unlockedShare");
    t.addClass("lockedShare");
});
//收藏按钮
$(document).on("click", ".collected", function(){
    var t = $(this);
    $.get(ctx+'/account/uncollected/'+t.attr("share-id"),function(msg){
        if(msg.code=="success"){
            t.find("span").text(msg.extra.count);
        }
    });
    t.removeClass("collected");
    t.addClass("uncollected");
    t.css("background", "url("+ctxStatic+"/icon/uncollected.bmp') no-repeat 2px 0");
});
$(document).on("click", ".uncollected", function(){
    if(!assertLogin()){
        alert("请先登录！");
        return false;
    }
    var t = $(this);
    $.get(ctx+'/account/collected/'+t.attr("share-id"),function(msg){
        if(msg.code=="success"){
            t.find("span").text(msg.extra.count);
        }
    });
    t.removeClass("uncollected");
    t.addClass("collected");
    t.css("background", "url("+ctxStatic+"/icon/collected.bmp') no-repeat 2px -2px");
});
//点赞按钮
$(document).on("click", ".praised", function(){
    var t = $(this);
    $.get(ctx+'/account/unpraised/'+t.attr("share-id"),function(msg){
        if(msg.code=="success"){
            t.find("span").text(msg.extra.count);
        }
    });
    t.removeClass("praised");
    t.addClass("unpraised");
    t.css("background", "url("+ctxStatic+"/icon/weizan.bmp') no-repeat");
});
$(document).on("click", ".unpraised", function(){
    if(!assertLogin()){
        alert("请先登录！");
        return false;
    }
    var t = $(this);
    $.get(ctx+'/account/praised/'+t.attr("share-id"),function(msg){
        if(msg.code=="success"){
            t.find("span").text(msg.extra.count);
        }
    });
    t.removeClass("unpraised");
    t.addClass("praised");
    t.css("background", "url("+ctxStatic+"/icon/yizan.bmp') no-repeat");
});

//评论
//点击评论展示评论列表
$(document).on("click", ".collection2.unopen" ,function(){
    var t = $(this);
    showComments(t.attr("share-id"), 1, t.parent().find(".commentsLists"));
    $(this).removeClass("unopen");
});

//发评论
$(".commentsPublish").click(function(){
    if(!assertLogin()){
        alert("请先登录！");
        return false;
    }
    var t = $(this);
    var form = t.parent().parent().parent();
    submitCommentForm(form)
});
function submitCommentForm(form){
    $.post(form.attr("action"), form.serialize(), function(msg){
        if(msg.code=="success"){
            form.parent().parent().parent().find(".commentsList:first").before(
                createCommentHtml(msg.extra.item));
            form.parent().parent().parent().parent().find(".collection2 > span").text(msg.extra.count);
            form.find("textarea").val("");
            form.find("textarea").text("");
        }
    });
}
function showComments(shareId, pageNo, commentsListsDiv){
    $.get(ctx+'/account/getComments/'+shareId+'?pageNo='+pageNo+'&pageSize=30',function(page){
        var list = page.list;console.log(list);
        for(var i=0; i<list.length; i++){
            var item = list[i];
            commentsListsDiv.find(".commentsList:last").after(createCommentHtml(item));
        }

        if(pageNo==1 && page.firstResult<page.count-page.pageSize){
            //第一页同时还有更多
            var moreHtml = '<div class="commentsRemain"><a href="javascript:void(0);" class="showMoreCommentsClick" share-id="'+shareId+'" next-page-no="'+(pageNo+1)+'">还有更多，点击查看</a></div>';
            commentsListsDiv.find("div:first").after(moreHtml);
        }else if(page.firstResult<page.count-page.pageSize){
            var a = commentsListsDiv.find(".showMoreCommentsClick");
            a.attr("next-page-no", pageNo+1);
        }else{
            commentsListsDiv.find("div:last").remove();
        }
    });
}
//点击查看更多评论
$(document).on("click", ".showMoreCommentsClick", function(){
    var t = $(this);
    showComments(t.attr("share-id"), t.attr("next-page-no"), t.parent().parent());
});
function createCommentHtml(item){
    var h = '\
<div class="commentsList">\
	<div class="first">\
		<a href="'+ctx+'/access/otherPersonal/'+item.fromUser.id+'"><img src="'+ctxStatic+'/image/portrait.jpg"/></a>\
	</div>\
	<div class="second">\
		<a href="'+ctx+'/access/otherPersonal/'+item.fromUser.id+'" class="commentsUserName" >'+item.fromUser.nickname+'</a>';
    if(item.parent!=null && item.parent.id!=null){
        h += '@<a href="'+ctx+'/access/otherPersonal/'+item.toUser.id+'" class="adverseUserName">'+item.toUser.nickname+'</a>';
    }
    h += ':<br/>\
		'+item.content+'\
	</div>\
	<div class="third clearfix">\
		<span class="commentsTime">'+item.createDate+'</span>\
		<div class="commentsKinds">';
    if(currentUserId==item.fromUser.id){
        h += '<a href="javascript:void(0)" class="commentDeleteClick" comment-id="'+item.id+'">删除</a><span>|</span>';
    }
    h+='<a href="javascript:void(0)" class="response commentResponseClick" to-user-nickname="'+item.fromUser.nickname+'" comment-id="'+item.id+'">回复</a>\
		</div>\
		<div class="clearfix"></div>\
	</div>\
</div>\
';console.log(item.createDate);
    return h;
}
//删除评论
$(document).on("click", ".commentDeleteClick", function(){
    var t = $(this);
    $.get(ctx+'/account/uncomment/'+t.attr("comment-id"),function(msg){
        if(msg.code=="success"){
            t.parent().parent().parent().parent().parent().parent().parent().parent().find(".collection2 > span").text(msg.extra.count);
            t.parent().parent().parent().remove();
        }
    });
});
//回复评论
$(document).on("click", ".commentResponseClick", function(){
    var t = $(this);
    var commentId = t.attr("comment-id");
    var toUserNickname = t.attr("to-user-nickname");
    var html = '<div class="responseDiv" style="display: block;">\
			<div class="responseInput">\
				<div class="responseTip">可输入字数<span>100</span>个</div>\
				<input type="hidden" name="parent.id" value="'+commentId+'"/>\
				<textarea maxlength="100" placeholder="回复@'+toUserNickname+'："></textarea>\
			</div>\
			<div class="clearfix"></div>\
			<div class="responseFun">\
				<a class="responseExpression"></a>\
				<a class="responsePublish" href="javascript:void(0)">回复</a>\
				<div class="clearfix"></div>\
			</div>\
		</div>';
    t.parent().parent().find("div:last").after(html);
});
//点击回复按钮
$(document).on("click", ".responsePublish", function(){
    if(!assertLogin()){
        alert("请先登录！");
        return false;
    }
    var t = $(this);
    var base = t.parent().parent();
    var parentIdInput = base.find("input")[0];
    var content = base.find("textarea").val();
    var form = base.parent().parent().parent().parent().parent().parent().find("form");
    form.find("input:last").after(parentIdInput);
    form.find("textarea").val(content);
    submitCommentForm(form);
    form.find("input:last").remove();
    base.remove();
});


function htmlEncode(value){
    return $('<div />').text(value).html();
}
function htmlDecode(value){
    return $('<div />').html(value).text();
}

function assertLogin(){
    if(currentUserId){
        return true;
    }else{
        return false;
    }
}