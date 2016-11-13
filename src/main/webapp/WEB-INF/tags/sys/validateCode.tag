<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="验证码输入框名称"%>
<%@ attribute name="inputCssStyle" type="java.lang.String" required="false" description="验证框样式"%>
<%@ attribute name="inputClass" type="java.lang.String" required="false" description="验证框类样式"%>

<%@ attribute name="imageCssStyle" type="java.lang.String" required="false" description="验证码图片样式"%>
<%@ attribute name="imageClass" type="java.lang.String" required="false" description="验证码图片类样式"%>

<%@ attribute name="buttonCssStyle" type="java.lang.String" required="false" description="看不清按钮样式"%>
<%@ attribute name="buttonClass" type="java.lang.String" required="false" description="看不清按钮类样式"%>
<div class="row" >
    <div class="col-md-6" style="padding-right: 0px;" >
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">验证码</label>
            <div class="input-icon">
                <i class="fa fa-keyboard-o"></i>
                <input class="required ${inputClass}" data-msg-required="请输入验证码" style="${inputCssStyle}" type="text" autocomplete="off" placeholder="验证码" maxlength="5" id="${name}" name="${name}"/>
            </div>
        </div>
    </div>
    <div class="col-md-6 no-pd-l" style="padding-left: 0px;" >
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">验证码</label>
            <div class="input-icon">
                <img src="${pageContext.request.contextPath}/captcha" onclick="$('.${name}Refresh').click();" class="mid ${name}" style="${imageCssStyle}"/>
                <a href="javascript:" onclick="$('.${name}').attr('src','${pageContext.request.contextPath}/captcha?'+new Date().getTime());" class="mid ${name}Refresh" style="${buttonCssStyle}">看不清</a>
            </div>
        </div>
    </div>
</div>