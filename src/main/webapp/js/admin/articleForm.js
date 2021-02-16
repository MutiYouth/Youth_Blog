layui.use(['form', 'layer'], function () {
    var form = layui.form
        , layer = layui.layer;

    var editor = CKEDITOR.replace('content');

    form.on('submit(publishArticle)', function (data) {
        var content = editor.getData();
        if (!content) {
            layer.alert("文章内容不能为空", {icon: 2});
            return false;
        }
        var isStick = 0, allowComment = 0;
        if (data.field.isStick == "on") {
            isStick = 1;
        }
        if (data.field.allowComment == "on") {
            allowComment = 1;
        }
        $.ajax({
            url: "admin/publishArticle",
            data: {
                id: data.field.articleId,
                categoryId: data.field.categoryId,
                label: data.field.label,
                title: data.field.title,
                allowComment: allowComment,
                isStick: isStick,
                content: editor.getData()
            },
            type: "POST",
            success: function (response) {
                if (response.errNo == 0) {
                    //layer.alert(response.errMsg, {icon: 1});
                    layer.alert(response.errMsg, {
                        icon: 1, yes: function () {
                            parent.layui.table.reload('articleList');
                            parent.layer.closeAll('iframe'); //再执行关闭
                        }
                    })
                } else {
                    layer.alert(response.errMsg == undefined ? response : "ERROR", {icon: 2});
                }
            },
            error: function () {
                parent.layer.alert("请求异常！", {icon: 2});
            }
        });
        return false;
    })
})