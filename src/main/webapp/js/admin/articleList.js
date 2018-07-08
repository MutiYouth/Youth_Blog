layui.use(['table','layer','form'], function(){
    var table = layui.table;
    var layer = layui.layer;
    var form = layui.form;
    var $ = layui.jquery;

    //第一个实例
    var tableIns = table.render({
        elem: '#articleList'
        ,id:'articleList'
        ,height: 'pull'//高度自适应
        ,url: 'admin/getArticleListDate' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {type:'checkbox'}
            ,{field: 'id', title: 'ID',  sort: true}
            ,{field: 'title', title: '标题'}
            ,{field: 'author', title: '作者'}
            ,{field: 'isStick', title: '是否置顶',templet: '#isStickTpl',unresize: true}
            ,{field: 'allowComment', title: '能否评论',templet: '#allowCommentTpl',unresize: true}
            ,{field: 'gmtCreate', title: '创建时间', sort: true}
            ,{field: 'gmtModified', title: '修改时间', sort: true}
        ]]
    });

    var $ = layui.$, active = {
        addArticle: function(){ //添加
            var checkStatus = table.checkStatus('articleList')
                ,data = checkStatus.data;
            //layer.alert(JSON.stringify(data));
            layer.open({
                type: 2,
                title: '发表新帖',
                border: [0],
                shade: [0.3, '#000'],
                offset: '30px',
                shadeClose: false,
                area: ['95%', '90%'],
                content: ['admin/toArticleForm'],
                scrollbar: false
            });
        }
        ,changeArticle: function(){ //修改
            var checkStatus = table.checkStatus('articleList')
                ,data = checkStatus.data;
            if(data.length != 1){
                layer.alert('请选择一条记录', {icon: 0});
                return;
            }
            layer.open({
                type: 2,
                title: '发表新帖',
                offset: '50px',
                border: [0],
                shade: [0.3, '#000'],
                shadeClose: false,
                area: ['95%', '90%'],
                content: ['admin/toArticleForm?articleId=' + data[0].id],
                scrollbar: false
            });
        }
        ,delArticle: function(){ //删除
            var checkStatus = table.checkStatus('articleList')
                ,data = checkStatus.data;
            if(data.length <= 0){
                layer.alert('未选择任何记录', {icon: 0});
                return;
            }
            var ids = data.map(function(v){return v.id;});
            layer.confirm("删除后不可恢复，是否确认删除？", function () {
                $.ajax({
                    url: 'admin/delArticle',
                    type: 'POST',
                    traditional:true,
                    data:{ids:ids},
                    success: function (data) {
                        if(data.errNo == 0){
                            layer.alert(data.errMsg, {
                                icon: 1, yes: function (index) {
                                    tableIns.reload();
                                    layer.close(index);
                                }
                            });
                        }else {
                            layer.alert(data.errMsg, {icon: 2});
                        }
                    },
                    error: function () {
                        layer.alert("请求失败！", {icon: 2});
                    }
                });
            });
            //layer.msg(checkStatus.isAll ? '全选': '未全选')
        }
        ,reload: function(){
            //执行重载
            tableIns.reload();
        }
    };

    $('.demoTable .layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //监听置顶操作
    form.on('checkbox(stickDemo)', function(obj){
        //layer.tips(this.value + ' ' + this.name + '：'+ obj.elem.checked, obj.othis);

        var stick;
        if(obj.elem.checked){
            stick = 1;//置顶
        }else{
            stick = 0;//取消置顶
        }

        $.ajax({
            url: 'admin/updateArticleStick',
            type: 'POST',
            traditional:true,
            data:{
                "id":this.value,
                "isStick":stick
            },
            success: function (data) {
                layer.tips(data.errMsg,obj.othis);
            },
            error: function () {
                layer.tips("请求失败！",obj.othis);
            }
        });

    });

    //监听允许评论操作
    form.on('checkbox(commentDemo)', function(obj){
        //layer.tips(this.value + ' ' + this.name + '：'+ obj.elem.checked, obj.othis);

        var allowComment;
        if(obj.elem.checked){
            allowComment = 0;//允许
        }else{
            allowComment = 1;//禁止
        }

        $.ajax({
            url: 'admin/updateAllowComment',
            type: 'POST',
            traditional:true,
            data:{
                "id":this.value,
                "allowComment":allowComment
            },
            success: function (data) {
                layer.tips(data.errMsg,obj.othis);
            },
            error: function () {
                layer.tips("请求失败！",obj.othis);
            }
        });

    });
});

