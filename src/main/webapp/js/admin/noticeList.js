layui.use(['table','layer','form'], function(){
    var table = layui.table;
    var layer = layui.layer;
    var form = layui.form;
    var $ = layui.jquery;

    //第一个实例
    var tableIns = table.render({
        elem: '#noticeList'
        ,id:'noticeList'
        ,height: 'pull'//高度自适应
        ,url: 'admin/getNoticeListData' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {type:'checkbox'}
            ,{field: 'id', title: 'ID',  sort: true}
            ,{field: 'title', title: '标题'}
            ,{field: 'author', title: '作者'}
            ,{field: 'content', title: '备注'}
            ,{field: 'gmtCreate', title: '创建时间', sort: true}
            ,{field: 'gmtModified', title: '修改时间', sort: true}
            ,{field: 'isActive', title: '是否显示',templet: '#isActiveTpl'}
        ]]
    });

    var $ = layui.$, active = {
        addNotice: function(){ //添加
            layer.open({
                type: 2,
                title: '添加通知',
                shadeClose: false,
                border: [0],
                shade: [0.5, '#000'],
                offset: '50px',
                area: ['600', '400'],
                content: ['admin/toNoticeForm','no'],
                scrollbar: false
            });
        }
        ,changeNotice: function(){ //修改
            var checkStatus = table.checkStatus('noticeList')
                ,data = checkStatus.data;
            if(data.length != 1){
                layer.alert('请选择一条记录', {icon: 0});
                return;
            }
            layer.open({
                type: 2,
                title: '修改通知',
                offset: '50px',
                shade: 0.8,
                shadeClose: false,
                area: ['600', '400'],
                content: ['admin/toNoticeForm?noticeId=' + data[0].id,'no'],
                scrollbar: false
            });
        }
        ,delNotice: function(){ //删除
            var checkStatus = table.checkStatus('noticeList')
                ,data = checkStatus.data;
            if(data.length <= 0){
                layer.alert('未选择任何记录', {icon: 0});
                return;
            }
            var ids = data.map(function(v){return v.id;});
            layer.confirm("删除后不可恢复，是否确认删除？", function () {
                $.ajax({
                    url: 'admin/delNotice',
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
    form.on('checkbox(activeDemo)', function(obj){
        //layer.tips(this.value + ' ' + this.name + '：'+ obj.elem.checked, obj.othis);

        var isActive;
        if(obj.elem.checked){
            isActive = 1;//置顶
        }else{
            isActive = 0;//取消置顶
        }

        $.ajax({
            url: 'admin/updateNotcieIsActive',
            type: 'POST',
            traditional:true,
            data:{
                "id":this.value,
                "isActive":isActive
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



