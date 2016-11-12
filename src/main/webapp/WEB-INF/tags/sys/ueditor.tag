1
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="输入框名称"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="输入框值"%>
<!-- 加载编辑器的容器 -->
<script id="container${id}" name="content" type="text/plain"></script>
<!-- 实例化编辑器 -->
<script type="text/javascript">
    var ue = UE.getEditor('container${id}');
    ue.ready(function() {
        ue.setContent(decodeHtml('${value}'));
    });
</script>
<form:input type="hidden" path="${name}" htmlEscape="false" class="form-control required"/>