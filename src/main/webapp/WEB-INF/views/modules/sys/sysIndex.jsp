<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<header>
    <title>${fns:getConfig('productName')}</title>
    <meta name="decorator" content="default"/>
</header>
<body>
<sys:navbar />
<div class="clearfix"></div>

<!-- BEGIN CONTAINER -->
<div class="page-container">
    <sys:sidebar tag="null" />

    <!-- BEGIN PAGE -->
    <div class="page-content">

    </div>
    <!-- END PAGE -->
</div>
<!-- END CONTAINER -->

<sys:footer />

<script>
    jQuery(document).ready(function() {
        App.init();
    });
</script>
</body>
</html>