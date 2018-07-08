layui.use(['form','element','upload'], function(){
    var element = layui.element
        ,form = layui.form
        ,upload = layui.upload;;

    //编辑工人信息
    form.on('submit(editUserInfo)', function(data){
        $.ajax({
            url: "admin/updateUserInfo",
            data: {
                userName:data.field.userName,
                email: data.field.email,
                nickname:data.field.nickname,
                sex:data.field.sex,
                city:data.field.city,
                signMsg:data.field.sign
            },
            type: "POST",
            success: function(response){
                if(response.errNo == 0){
                    layer.alert(response.errMsg, {icon: 1});
                }else {
                    layer.alert(response.errMsg, {icon: 2});
                }
            },
            error: function () {
                parent.layer.alert("请求异常！", {icon: 2});
            }
        });
        return false;
    });

    //上传头像
    var uploadInst = upload.render({
        elem: '#uplaodBtn' //绑定元素
        ,accept:'images'
        ,size:1024
        ,url: 'admin/uploadAvatar' //上传接口
        ,done: function(res, index, upload){
            if(res.errNo == 0){
                $('#userAvatar').attr('src', res.obj); //图片链接（base64）
            }
        }
        ,error: function(){
            //请求异常回调
            parent.layer.alert("请求异常！", {icon: 2});
        }
    });

    //修改密码
    form.on('submit(updatePass)', function(data){
        if(data.field.pass != data.field.repass){
            layer.alert("两次密码并不一致", {icon: 2});
            return false;
        }
        if(data.field.pass.length < 5){
            layer.alert("密码必须5到16个字符", {icon: 2});
            return false;
        }
        $.ajax({
            url: "admin/updateUserPass",
            data: {
                nowpass: data.field.nowpass,
                pass:data.field.pass,
                repass:data.field.repass
            },
            type: "POST",
            success: function(response){
                if(response.errNo == 0){
                    layer.alert(response.errMsg, {icon: 1});
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
});