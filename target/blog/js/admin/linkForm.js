layui.use(['form', 'layer'], function(){
    var form = layui.form
        ,layer = layui.layer;

    form.on('submit(editLink)', function(data){
        $.ajax({
            url: "admin/addOrUpdateLink",
            data: {
                id: data.field.linkId,
                title:data.field.title,
                url:data.field.url,
                remark:data.field.remark
            },
            type: "POST",
            success: function(response){
                if(response.errNo == 0){
                    layer.alert(response.errMsg, {
                        icon: 1, yes: function () {
                            parent.layui.table.reload('linkList');
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