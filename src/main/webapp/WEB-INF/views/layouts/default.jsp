<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh-CN" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="zh-CN" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="zh-CN" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <title><sitemesh:title/></title>
    <%@include file="/WEB-INF/views/include/head.jsp" %>
    <sitemesh:head/>
</head>
<body class="page-header-fixed">
<%@include file="/WEB-INF/views/include/js.jsp" %>

<sitemesh:body/>

<!-- BEGIN MODAL DIALOG -->
<div id="confirm" class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 id="confirm-title" class="modal-title">温馨提示</h4>
            </div>
            <div id="confirm-body" class="modal-body">
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn default">取消</button>
                <button id="confirm-ok" type="button" class="btn blue">确定</button>
            </div>
        </div>
    </div>
</div>
<!-- END MODAL DIALOG -->

<!-- BEGIN AJAX DIALOG -->
<div class="modal fade" id="ajax" tabindex="-1" data-backdrop="static" data-keyboard="false">
    <img src="${ctxFront}/img/ajax-modal-loading.gif" alt="" class="loading">
</div>
<!-- END AJAX DIALOG -->

<script type="text/javascript">
    // 选择对应父菜单选中效果
    $('.active').parent().parent().attr('class', 'active').find('a').append('<span class="selected"></span>').find('.arrow').attr('class', 'arrow open');

    // 表单有效性验证
    $("#inputForm").validate({
        errorClass: 'help-block',

        submitHandler: function (form) {
            loading('正在提交，请稍等...');
            form.submit();
        },

        errorContainer: "#messageBox",

        highlight: function (element) { // hightlight error inputs
            $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
        },

        success: function (label) {
            label.closest('.form-group').removeClass('has-error');
            label.remove();
        },

        errorPlacement: function (error, element) {
            $("#messageBox").text("输入有误，请先更正。");
            if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                error.appendTo(element.parent().parent().parent().parent());
            } else {
                error.insertAfter(element);
            }
        }
    });

    // 初始化日期组件
    if (jQuery().datepicker) {
        $('.date-picker').datepicker({
            rtl: App.isRTL(),
            autoclose: true
        });
        $('body').removeClass("modal-open"); // fix bug when inline picker is used in modal
    }
</script>
</body>
</html>