layui.use(['form','element','layer'], function(){
    var form = layui.form;
    var element = layui.element;
    var layer = layui.layer;

    //监听提交
    form.on('submit(subLogin)', function(data){
        $.ajax({
            method: 'POST',
            url: "subLogin",
            data: {
                userName: data.field.username,
                passWord: data.field.password,
                rememberMe: data.field.rememberMe
            },
            type: "POST",
            success: function(response){
                if(response.errNo == 0){
                    alert("成功!", response.errMsg, "success")
                    $("#msg").val("");
                }else {
                    alert("失败!", response.errMsg, "error")
                }
            },
            error: function () {
                alert("发送失败", "系统内部错误！", "error");
            }
        });
        return false;
    });

});
