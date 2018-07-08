layui.use('flow', function(){
    var $ = layui.jquery; //不用额外加载jQuery，flow模块本身是有依赖jQuery的，直接用即可。
    var flow = layui.flow;
    flow.load({
        elem: '#LAY_demo1' //流加载容器
        ,scrollElem: '#LAY_demo1' //滚动条所在元素，一般不用填，此处只是演示需要。
        ,isAuto: false
        ,isLazyimg: true
        ,done: function(page, next){ //加载下一页
            var lis = [];
            var categoryId = document.getElementById("categoryId");
            var date = document.getElementById("date");
            var searchContent = document.getElementById("searchContent");
            var url = 'getArticleListData?pageNum=' + page +
                '&categoryId=' + categoryId.value +
                '&date=' + date.value +
                '&searchContent=' + searchContent.value;
            //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）
            $.get(url, function(res){
                //假设你的列表返回在data集合中
                layui.each(res.list, function(index, item){
                    if(item.user != null){
                        var url = "javascript:jump(\"toArticleDetail?articleId=" + item.id + "\",1);";
                        lis.push(
                            "<li>" +
                            "<a href=" + url + " class=\"fly-avatar\">" +
                            "<img src=\"" + item.user.avatar + "\"" +
                            "alt=\"贤心\">" +
                            "</a>" +
                            "<h2>" +
                            "<a class=\"layui-badge\">" + item.label + "</a>" +
                            "<a href=" + url + ">" + item.title + "</a>" +
                            "</h2>" +
                            "<div class=\"fly-list-info\">" +
                            "<cite>" + item.user.nickname + "</cite>" +
                            "<span>" + item.gmtCreate + "</span>" +
                            "<span class=\"fly-list-kiss\" title=\"热度\"><i " +
                            "class=\"iconfont icon-kiss\"></i> 60</span>" +
                            "<span class=\"fly-list-nums\">" +
                            "<i class=\"iconfont icon-pinglun1\" title=\"留言\"></i> " +
                            item.commentCount +
                            "</span>" +
                            "</div>" +
                            "<div class=\"fly-list-badge\">" +
                            stickFormatter(item.isStick) +
                            splendidFormatter(item.commentCount) +
                            "</div>" +
                            "</li>"
                        );
                    }
                });

                //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
                //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
                next(lis.join(''), page < res.pages);
                setParentIframeHeight();
            });

        }
    });

});

//显示文章置顶状态
function stickFormatter(isStick) {
    if(isStick == 1){
        return "<span class=\"layui-badge layui-bg-black\">置顶</span>";
    }else {
        return "";
    }
}


//显示文章是否精帖 评论数大于一定值即为精帖
function splendidFormatter(commentCount) {
    if(commentCount > 1){
        return "<span class=\"layui-badge layui-bg-red\">精帖</span>";
    }else {
        return "";
    }
}

