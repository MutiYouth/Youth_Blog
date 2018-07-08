layui.use(['table','layer','form'], function(){
    var table = layui.table;
    var layer = layui.layer;
    var form = layui.form;
    var $ = layui.jquery;
    //第一个实例
    var tableIns = table.render({
        elem: '#letterList'
        ,id:'letterList'
        ,height: 'pull'//高度自适应
        ,url: 'admin/getLetterListData' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {type:'checkbox'}
            ,{field: 'id', title: 'ID',  sort: true}
            ,{field: 'userName', title: '发件人'}
            ,{field: 'viewStatus', title: '查阅状态', templet: '#stateTpl'}
            ,{field: 'gmtCreate', title: '创建时间', sort: true}
            ,{field: 'gmtModified', title: '修改时间', sort: true}
        ]]
    });

    var $ = layui.$, active = {
        checkLetter: function(){
            var checkStatus = table.checkStatus('letterList')
                ,data = checkStatus.data;
            if(data.length != 1){
                layer.alert('请选择一条记录', {icon: 0});
                return;
            }
            layer.open({
                type: 2,
                title: '查看信件',
                border: [0],
                shade: [0.3, '#000'],
                offset: '50px',
                shadeClose: false,
                area: ['800px', '500px'],
                closeBtn: 2,
                content: ['admin/toLetterForm?letterId=' + data[0].id,'no'],
                scrollbar: false,
                cancel: function(index, layero){//点击关闭按钮刷新
                    layui.table.reload('letterList');
                }
            });
        }
        ,delLetter: function(){ //删除
            var checkStatus = table.checkStatus('letterList')
                ,data = checkStatus.data;
            if(data.length <= 0){
                layer.alert('未选择任何记录', {icon: 0});
                return;
            }
            var ids = data.map(function(v){return v.id;});
            layer.confirm("删除后不可恢复，是否确认删除？", function () {
                $.ajax({
                    url: 'admin/delLetter',
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

});
