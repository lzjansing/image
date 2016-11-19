<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8" />
    <title>${fns:getConfig('productName')} - 403</title>
    <!-- BEGIN THEME STYLES -->
    <link rel="stylesheet" type="text/css" href="${ctxFront}/css/pages/error.css"/>
    <!-- END THEME STYLES -->
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-404-full-page">
<%@include file="/WEB-INF/views/include/js.jsp" %>
<div class="row">
    <div class="col-md-12 page-404">
        <div class="number">
            403
        </div>
        <div class="details">
            <h3>xxx!竟然没有权限</h3>
            <p>
                哎，算了，我们还是<a href="javascript:history.go(-1);">干点别的</a>
            </p>
        </div>
    </div>
</div>
<script>
    jQuery(document).ready(function() {
        App.init();
    });
</script>
</body>
<!-- END BODY -->
</html>