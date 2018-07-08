layui.use(['form', 'layer'], function(){
    setParentIframeHeight();
    var form = layui.form
        ,$ = layui.jquery;

    form.on('submit(contactMe)', function(data){
        $.ajax({
            url: "addLetter",
            data: {
                userName:data.field.name,
                contactWay:data.field.contactInfo,
                content:data.field.msg
            },
            type: "POST",
            success: function(response){
                if(response.errNo == 0){
                    layer.alert(response.errMsg, {icon: 1, closeBtn: 2});
                    $("#msg").val("");
                }else {
                    layer.alert(response.errMsg, {icon: 2,closeBtn: 2});
                }
            },
            error: function () {
                layer.alert("请求异常", {icon: 2,closeBtn: 2});
            }
        });
        return false;
    });

});


