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
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script type="text/javascript" src="${ctxFront}/plugins/respond.min.js"></script>
<script type="text/javascript" src="${ctxFront}/plugins/excanvas.min.js"></script>
<![endif]-->
<script type="text/javascript" src="${ctxFront}/plugins/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${ctxFront}/plugins/jquery-migrate-1.2.1.min.js"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script type="text/javascript" src="${ctxFront}/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript" src="${ctxFront}/plugins/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctxFront}/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js" ></script>
<script type="text/javascript" src="${ctxFront}/plugins/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<script type="text/javascript" src="${ctxFront}/plugins/jquery.blockui.min.js"></script>
<script type="text/javascript" src="${ctxFront}/plugins/jquery.cookie.min.js"></script>
<script type="text/javascript" src="${ctxFront}/plugins/uniform/jquery.uniform.min.js" ></script>
<!-- END CORE PLUGINS -->

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script type="text/javascript" src="${ctxFront}/plugins/fuelux/js/tree.min.js"></script>
<script type="text/javascript" src="${ctxFront}/plugins/jquery-jbox/2.3/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="${ctxFront}/plugins/jquery-validation/dist/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctxFront}/plugins/jquery-validation/localization/messages_zh.js"></script>
<script type="text/javascript" src="${ctxFront}/plugins/bootstrap-toastr/toastr.min.js"></script>
<script type="text/javascript" src="${ctxFront}/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${ctxFront}/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js"></script>
<script type="text/javascript" src="${ctxFront}/plugins/jquery-ztree/3.5.12/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="${ctxFront}/plugins/treeTable/jquery.treeTable.min.js"></script>
<script type="text/javascript" src="${ctxFront}/plugins/baidu-ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${ctxFront}/plugins/baidu-ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="${ctxFront}/plugins/baidu-ueditor/lang/zh-cn/zh-cn.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->

<script type="text/javascript" src="${ctxFront}/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>

<script type="text/javascript" src="${ctxFront}/scripts/app.js"></script>
<script type="text/javascript" src="${ctxFront}/scripts/base.js"></script>

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