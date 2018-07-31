layui.use(['form', 'layer'], function(){
    var form = layui.form
        ,layer = layui.layer;

    form.on('submit(editCategory)', function(data){
        $.ajax({
            url: "admin/addOrUpdateCatg",
            data: {
                id: data.field.categoryId,
                title:data.field.title,
                intro:data.field.intro
            },
            type: "POST",
            success: function(response){
                if(response.errNo == 0){
                    layer.alert(response.errMsg, {
                        icon: 1, yes: function () {
                            parent.layui.table.reload('categoryList');
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