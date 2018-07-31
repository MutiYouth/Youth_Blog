layui.use(['table','layer'], function(){
    var table = layui.table;
    var layer = layui.layer;
    var $ = layui.jquery;

    //第一个实例
    var tableIns = table.render({
        elem: '#categoryList'
        ,id:'categoryList'
        ,height: 'pull'//高度自适应
        ,url: 'admin/getCategoryListDate' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
             {type:'checkbox',unresize: true}
            ,{field: 'id', title: 'ID',  sort: true}
            ,{field: 'title', title: '名称'}
            ,{field: 'intro', title: '备注'}
            ,{field: 'gmtCreate', title: '创建时间', sort: true}
            ,{field: 'gmtModified', title: '修改时间', sort: true}
        ]]
    });

    var $ = layui.$, active = {
        addCategory: function(){ //添加
            layer.open({
                type: 2,
                title: '添加分类',
                // shade: 0.8,
                // offset: '50px', // this format tyle will not generate the efface
                border: [0],
                shade: [0.3, '#000'],
                offset: '50px',
                shadeClose: false,
                area: ['550', '300'],
                content: ['admin/toCategoryForm','no'],
                scrollbar: false
            });
        }
        ,changeCategory: function(){ //修改
            var checkStatus = table.checkStatus('categoryList')
                ,data = checkStatus.data;
            if(data.length != 1){
                layer.alert('请选择一条记录', {icon: 0});
                return;
            }
            layer.open({
                type: 2,
                title: '修改分类',
                border: [0],
                shade: [0.3, '#000'],
                offset: '50px',
                shadeClose: false,
                area: ['550', '300'],
                content: ['admin/toCategoryForm?categoryId=' + data[0].id,'no'],
                scrollbar: false
            });
        }
        ,delCategory: function(){ //删除
            var checkStatus = table.checkStatus('categoryList')
                ,data = checkStatus.data;
            if(data.length <= 0){
                layer.alert('未选择任何记录', {icon: 0});
                return;
            }
            var ids = data.map(function(v){return v.id;});
            layer.confirm("删除后不可恢复，是否确认删除？", function () {
                $.ajax({
                    url: 'admin/delCategory',
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

