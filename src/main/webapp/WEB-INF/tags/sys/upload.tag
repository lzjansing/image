1
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="输入框名称"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="输入框值"%>
<div class="input-icon right">
    <a onclick="show${id}();"><i id="show${id}" class="fa fa-cloud-upload"></i></a>
    <input id="${id}" name="${name}" htmlEscape="false" maxlength="100" type="text" class="form-control" value="${value}" readonly="readonly" onclick="upImage${id}();" />
</div>
<script type="text/plain" id="j_ueditorupload${id}" style="height:5px;display:none;" ></script>
<script>
    //实例化编辑器
    var o_ueditorupload${id} = UE.getEditor('j_ueditorupload${id}', {
        autoHeightEnabled: false
    });
    o_ueditorupload${id}.ready(function () {
        o_ueditorupload${id}.hide();//隐藏编辑器
        //监听图片上传
        o_ueditorupload${id}.addListener('beforeInsertImage', function (t, arg) {
            $("#${id}").val(arg[0].src);
        });
    });

    //弹出图片上传的对话框
    function upImage${id}() {
        var myImage${id} = o_ueditorupload${id}.getDialog("insertimage");
        myImage${id}.open();
    }

    // 查看图片
    function show${id}() {
        window.showModalDialog($("#${id}").val(), null, 'dialogWidth:800px;dialogHeight:600px;center:yes;edge:raised;scroll:yes;status:no;minimize:yes;maximize:yes;');
    }
</script>