<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="输入框名称"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="输入框值"%>
<i id="${id}Icon" class="fa ${not empty value?value:' hide'}"></i>&nbsp;<label id="${id}IconLabel">${not empty value?value:'无'}</label>&nbsp;
<input id="${id}" name="${name}" type="hidden" value="${value}"/><a href="${ctx}/tag/iconselect/${id}" class="btn default" data-target="#ajax" data-toggle="modal">选择</a>&nbsp;&nbsp;
<div class="modal fade" id="ajax" tabindex="-1" role="basic" aria-hidden="true">
    <img src="${ctxFront}/img/ajax-modal-loading.gif" alt="" class="loading">
</div>