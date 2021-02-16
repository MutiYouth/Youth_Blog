//注意：导航 依赖 element 模块，否则无法进行功能性操作
layui.use(['element', 'layer', 'util'], function () {
    var $ = layui.jquery; //不用额外加载jQuery，flow模块本身是有依赖jQuery的，直接用即可。
    var element = layui.element
        , layer = layui.layer
        , util = layui.util;

    //执行
    util.fixbar({
        bar1: true
        , click: function (type) {
            console.log(type);
            if (type === 'bar1') {
                alert('点击了bar1')
            }
        }
    });

    $('.fly-search').on('click', function () {
        var searchContent = $('#searchContent').val();
        jump("toArticleList?searchContent=" + searchContent, 0)

    });
});

//申请友链
function applyFriendChain() {
    layer.open({
        type: 1,
        shade: false,
        title: false, //不显示标题
        content: '<div style="padding: 50px; line-height: 22px; background-color: #5FB878; color: #fff; font-weight: 300;">申请友链么？亲！<br><br>首先请确认你的网站能正常访问<br><br>然后点击标题栏“联系我”，留下你网站的名字和url地址。<br><br>已经OK了，我看到信件确认后就会加上了 ^_^</div>', //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
        cancel: function () {
            layer.msg('点击标题栏“联系我”给站长留言即可申请友链', {time: 5000, icon: 6});
        }
    });
}