layui.use(['form', 'layer'], function(){
    var form = layui.form
        ,layer = layui.layer;

    form.on('submit(editNotice)', function(data){
        var isActive = 0;
        if(data.field.isActive == "on"){
            isActive = 1;
        }

        $.ajax({
            url: "admin/addOrUpdateNotice",
            data: {
                id: data.field.noticeId,
                title:data.field.title,
                author: data.field.author,
                content:data.field.content,
                isActive:isActive
            },
            type: "POST",
            success: function(response){
                if(response.errNo == 0){
                    layer.alert(response.errMsg, {
                        icon: 1, yes: function () {
                            parent.layui.table.reload('noticeList');
                            parent.layer.closeAll('iframe'); //再执行关闭
                        }
                    })
                }else {
                    layer.alert(response.errMsg, {icon: 2});
                }
            },
            error: function () {
                parent.layer.alert("请求异常！", {icon: 2});
            }
        });
        return false;
    })
})