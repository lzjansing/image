<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域名称（ID）"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="隐藏域值（ID）"%>
<%@ attribute name="labelName" type="java.lang.String" required="true" description="输入框名称（Name）"%>
<%@ attribute name="labelValue" type="java.lang.String" required="true" description="输入框值（Name）"%>
<%@ attribute name="title" type="java.lang.String" required="true" description="选择框标题"%>
<%@ attribute name="url" type="java.lang.String" required="true" description="树结构数据地址"%>
<div class="input-icon right">
    <i class="fa fa-search"></i>
    <input id="${id}Id" name="${name}" type="hidden" value="${value}"/>
    <input id="${id}Name" name="${labelName}" type="text" placeholder="" class="form-control" value="${labelValue}" readonly="readonly" data-toggle="modal" href="#static${id}" />
</div>
<!-- BEGIN MODAL DIALOG -->
<div id="static${id}" class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title">选择${title}</h4>
            </div>
            <div class="modal-body">
                <div id="${id}Tree" class="tree tree-no-line tree-unselectable">
                    <div class = "tree-folder" style="display:none;">
                        <div class="tree-folder-header">
                            <i class="fa fa-folder"></i>
                            <div class="tree-folder-name"></div>
                        </div>
                        <div class="tree-folder-content"></div>
                        <div class="tree-loader" style="display:none"></div>
                    </div>
                    <div class="tree-item" style="display:none;">
                        <i class="tree-dot"></i>
                        <div class="tree-item-name"></div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn default">关闭</button>
            </div>
        </div>
    </div>
</div>
<!-- END MODAL DIALOG -->
<script type="text/javascript">
    var ${id}DataSourceTree = function (options) {
        this._data  = options.data;
        this._delay = options.delay;
    };

    ${id}DataSourceTree.prototype = {
        data: function (options, callback) {
            var self = this;
            setTimeout(function () {
                if (!('text' in options || 'type' in options)) {
                    var data = $.extend(true, [], self._data);
                    callback({ data: data });
                } else {
                    $.ajax( {
                        url:'${ctx}${url}',
                        data:{
                            "parentId": options.additionalParameters.id
                        },
                        type:'post',
                        cache:false,
                        dataType:'json',
                        success:function(data) {
                            var dataArray = new Array();
                            for (var i = 0 ; i < data.length ; i++) {
                                if (data[i].isItem) {
                                    dataArray[i] = {
                                        name: data[i].name, type: 'item', additionalParameters: { id: data[i].id }
                                    }
                                } else {
                                    dataArray[i] = {
                                        name: data[i].name, type: 'folder', additionalParameters: { id: data[i].id }
                                    }
                                }
                            }
                            callback({ data: dataArray });
                        },
                        error : function() {
                            alert("服务器出现异常，请联系管理员");
                        }
                    });
                }
            }, this._delay);
        }
    };

    $.ajax( {
        url:'${ctx}${url}',
        type:'post',
        cache:false,
        dataType:'json',
        success:function(data) {
            if (data != null && data.length > 0) {
                var myData = new Array();
                for (var i = 0 ; i < data.length ; i++) {
                    myData[i] = {
                        name: data[i].name, type: 'folder', additionalParameters: { id: data[i].id }
                    };
                }
                var treeDataSource = new ${id}DataSourceTree({
                    data : myData,
                    delay: 100
                });

                $('#${id}Tree').tree({
                    selectable: false,
                    folderSelect: false,
                    dataSource: treeDataSource,
                    loadingHTML: '<img src="${ctxFront}/img/input-spinner.gif"/>'
                });

                // 菜单项选择
                $('#${id}Tree').on('selected', function (event, data) {
                    $("#${id}Id").val(data.info[0].additionalParameters.id);
                    $("#${id}Name").val(data.info[0].name);
                });

                $('#${id}Tree').on('opened', function (event, data) {
                    $("#${id}Id").val(data.additionalParameters.id);
                    $("#${id}Name").val(data.name);
                });

                $('#${id}Tree').on('closed', function (event, data) {
                    $("#${id}Id").val(data.additionalParameters.id);
                    $("#${id}Name").val(data.name);
                });
            }
        },
        error : function() {
            alert("服务器出现异常，请联系管理员");
        }
    });
</script>