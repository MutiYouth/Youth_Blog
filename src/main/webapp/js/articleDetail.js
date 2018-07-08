layui.use(['form', 'flow'], function(){
    var form = layui.form
        ,layer = layui.layer
        ,$ = layui.jquery //不用额外加载jQuery，flow模块本身是有依赖jQuery的，直接用即可。
        ,flow = layui.flow;

    //自定义验证规则
    form.verify({
        maxLength: function(value){
            if(value.length > 20){
                return '最多20个字符';
            }
        }

    });
    //自定义验证规则
    form.verify({
        msg: function(value){
            if(value.length > 50){
                return '最多50个字符';
            }
        }
    });

    flow.load({
        elem: '#pn' //流加载容器
        //,isAuto: false
        //,isLazyimg: true
        ,done: function(page, next){ //加载下一页
            var articleId = document.getElementById("articleId");
            var url = 'toCommentList?pageNum=' + page +
                '&articleId=' + articleId.value;
            var lis = [];
            //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）
            $.get(url, function(res){
                //假设你的列表返回在data集合中
                layui.each(res.list, function(index, item){
                    str = '';
                    //如果不是叶子节点，说明该评论下有回复，加载回复信息
                    if(item.isleaf == 0){
                        tree(item.childCommentList,item.memberName);
                    }
                    lis.push(
                        "<li class=\"list0\"> <a class=\"close\" href=\"javascript:;\">X</a>\n" +
                        "  <div class=\"head\"><img src=\"resources/images/foot.png\" alt=\"\"></div>\n" +
                        "  <div class=\"content\">\n" +
                        "    <p class=\"text\"><span class=\"name\" rootId=" + item.id + ">" + item.memberName + "：</span>" + item.content + "</p>\n" +
                        "    <div class=\"good\"><span class=\"date\">" + formatDateTime(item.pdate) + "</span></div>\n" +
                        "    <div class=\"people\" total=\"0\" style=\"display: none;\"></div>\n" +
                        "    <div class=\"comment-list\">\n" +
                        str +
                        "    </div>" +
                        "    <div class=\"hf\">\n" +

                        "<div class=\"layui-inline\" style=\"display:none\">\n" +
                        "    <div class=\"layui-input-inline\">\n" +
                        "        <input type=\"text\" class=\"layui-input\" name=\"name\" placeholder=\"请输入姓名...\" >\n" +
                        "    </div>\n" +
                        "    <div class=\"layui-input-inline\">\n" +
                        "        <input type=\"text\" class=\"layui-input\" name=\"contactInfo\" \n" +
                        "               placeholder=\"联系方式...\" >\n" +
                        "    </div>\n" +
                        "</div>" +

                        "      <textarea type=\"text\" class=\"hf-text\" autocomplete=\"off\" maxlength=\"100\">评论…</textarea>\n" +
                        "      <button class=\"hf-btn\">回复</button>\n" +
                        "      <span class=\"hf-nub\">0/100</span> </div>\n" +
                        "  </div>\n" +
                        "</li>"
                    );
                });

                //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
                //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
                next(lis.join(''), page < res.pages);
                initCommentEvent();
                setParentIframeHeight();
            });
        }
    });

    //监听提交
    form.on('submit(demo1)', function(data){
        var name = data.field.name;
        var msg = data.field.msg;
        var contactInfo = data.field.contactInfo;
        var articleId = data.field.articleId;

        var innerHtml =
            "<li class=\"list0\"> <a class=\"close\" href=\"javascript:;\">X</a>\n" +
            "  <div class=\"head\"><img src=\"resources/images/foot.png\" alt=\"\"></div>\n" +
            "  <div class=\"content\">\n" +
            "    <p class=\"text\"><span class=\"name\">我：</span>" + msg + "</p>\n" +
            "    <div class=\"good\"><span class=\"date\">" + getTime() + "</span></div>\n" +
            "    <div class=\"people\" total=\"0\" style=\"display: none;\"></div>\n" +
            "    <div class=\"comment-list\">\n" +
            "    </div>" +
            "  </div>\n" +
            "</li>";
        $("#pn").prepend(innerHtml);
        setParentIframeHeight();
        $("#msg").val("");
        //发送ajax请求
        $.ajax({
            url: 'addComment',
            type: 'POST',
            data: {
                pid: 0,
                content:msg,
                memberName:name,
                contactInfo:contactInfo,
                articleId:articleId
            },
            success: function (data) {
                if(data.errNo == 0){
                   console.log("comment success");
                }else {
                    alert("失败!");
                }
            },
            error: function () {
                alert("评论失败!");
            }
        });

        return false;
    });
});


//加载回复信息 到str
var str = '';
function tree(comments,toUser){

    for(var i=0;i<comments.length;i++){
        var comment = comments[i];
        var replayUser = '';
        if(toUser != null || toUser != undefined){
            replayUser +=  " 回复 " + toUser + "：";
        }
        str +=
            "      <div class=\"comment\" user=\"self\">\n" +
            "        <div class=\"comment-left\"><img src=\"resources/images/foot.png\" alt=\"\"></div>\n" +
            "        <div class=\"comment-right\">\n" +
            "          <div class=\"comment-text\"><span class=\"user\" pid=" + comment.id + ">" +comment.memberName  + "：</span>" + replayUser + comment.content + "</div>\n" +
            "          <div class=\"comment-date\">" + formatDateTime(comment.pdate) + " <a class=\"comment-dele\" href=\"javascript:;\">回复</a> </div>\n" +
            "        </div>\n" +
            "      </div>\n";
        if(comment.isleaf == 0) {
            tree(comment.childCommentList,comment.memberName)
        }
    }
};


function initCommentEvent() {
    var pn = document.getElementById("pn");
    var lists = pn.children;
    var pid =0;
    var rootId =0;
    //删除当前节点
    function remove(node) {
        node.parentNode.removeChild(node);
    }
    //上面的点赞
    function praisebox(box, el) {
        //获取赞数量容器
        var praise = box.getElementsByClassName("people")[0];
        //获取容器当前total值
        var total = parseInt(praise.getAttribute("total"));
        //获取点击的innerHTML
        var txt = el.innerHTML;
        //创建一个新的total存储用
        var newtotal;
        //判断点击的文字内容
        if (txt == "赞") {
            //total值+1 因为我还没点击赞，所以要点击的时候就多了一个人 total+1
            newtotal = total + 1;
            //判断赞数量 把相应文字放到赞容器里
            praise.innerHTML = newtotal == 1 ? "我觉得很赞" : "我和" + total + "个人觉得很赞";
            el.innerHTML = "取消赞";
        } else {
            //反之total值-1
            newtotal = total - 1;
            praise.innerHTML = newtotal == 0 ? "" : newtotal + "个人觉得很赞";
            el.innerHTML = "赞";
        }
        //更新total值
        praise.setAttribute("total", newtotal);
        //如果没有人点赞容器隐藏
        praise.style.display = (newtotal == 0) ? "none" : "block";
    }
    //回复评论
    function reply(box) {
        var replyId = 0;
        //获取用户姓名和联系方式
        var name = box.getElementsByClassName("layui-input")[0].value;
        var contactInfo = box.getElementsByClassName("layui-input")[1].value;
        if(!name || !contactInfo ){
            layer.alert("姓名和联系方式不能为空", {icon: 0,closeBtn: 2,offset: 'b'});
            return;
        }
        //获取评论框
        var textarea = box.getElementsByTagName("textarea")[0];
        //获取包含所有评论的容器
        var comment = box.getElementsByClassName("comment-list")[0];
        var content = textarea.value.split("：");
        var commentStr = "";
        if(content.length == 2){
            commentStr = content[1];
            replyId = pid;
        }else {
            commentStr = content[0];
            replyId = rootId;
        }

        //发送ajax请求
        $.ajax({
            url: 'addComment',
            type: 'POST',
            data: {
                pid: replyId,
                content:commentStr,
                memberName:name,
                contactInfo:contactInfo,
                articleId:$("#articleId").val()
            },
            success: function (data) {
                if(data.errNo == 0){
                    //swal("成功!", "添加评论成功:)!", "success")
                }else {
                    //swal("失败!", data.errMsg, "error")
                    layer.alert("评论失败", {icon: 2,closeBtn: 2});
                }
            },
            error: function () {
                layer.alert("评论请求异常", {icon: 2,closeBtn: 2});
            }
        });
        //初始化pid
        pid = 0;
        rootId = 0;

        //创建新的评论div
        var div = document.createElement("div");
        //赋类名
        div.className = "comment";
        //设置属性
        div.setAttribute("user", "self");
        //获取每条评论的innerHTML结构，每次只替换textarea的输入内容和 当前发送时间
        var html = '<div class="comment-left">' + '<img src="resources/images/foot.png" alt=""/>' + '</div>' +
            '<div class="comment-right">' +
            '<div class="comment-text"><span>我：</span>' + textarea.value + '</div>' +
            '<div class="comment-date">' +
            getTime() +
            '</div>' +
            '</div>';
        //插入到新建的评论div
        div.innerHTML = html;
        //把新评论插入到评论列表
        comment.appendChild(div);
        //评论后初始化textarea输入框
        textarea.value = "评论…";
        textarea.parentNode.className = "hf";
        $(box).find(".layui-inline").hide();
    }

    //回复里点赞
    function praiseReply(el) {
        //获取当前total值 也就是所有点赞数量
        var total = parseInt(el.getAttribute("total"));
        //获取当前my值 我的点赞
        var my = parseInt(el.getAttribute("my"));
        //创建新的total
        var newtotal;
        //判断my=0就是我准备要点赞
        if (my == 0) {
            //我点了赞总数量就要+1
            newtotal = total + 1;
            //更新total值
            el.setAttribute("total", newtotal);
            //更新my值
            el.setAttribute("my", 1);
            //更新文字 就是我点了后 文字就是取消赞了
            el.innerHTML = newtotal + " 取消赞";
        } else {
            //反之-1
            newtotal = total - 1;
            el.setAttribute("total", newtotal);
            el.setAttribute("my", 0);
            el.innerHTML = (newtotal == 0) ? " 赞" : newtotal + " 赞";
        }
    }
    //操作回复
    function operateReply(el) {
        //每条评论
        var comment = el.parentNode.parentNode.parentNode;
        //整个状态
        var box = comment.parentNode.parentNode.parentNode;
        //评论框
        var textarea = box.getElementsByTagName("textarea")[0];
        //名字
        var user = comment.getElementsByClassName("user")[0];
        //点击的innerHTML
        var txt = el.innerHTML;
        //判断当前点击的是否为回复
        if (txt == "回复") {
            //评论框触发焦点事件 也就是变高
            textarea.onfocus();
            //内容变为回复+当前人的名字
            textarea.value = "回复 " + user.innerHTML;
            //调用键盘事件
            textarea.onkeyup();
        } else {
            //否则就是删除节点
            remove(comment);
        }
        setParentIframeHeight();
    }
    //遍历所有状态消息
    for (var i = 0; i < lists.length-1; i++) {
        //全部事件代理
        lists[i].onclick = function(e) {
            //获取当前点击事件
            var e = e || window.event;
            var el = e.srcElement;
            if (!el) {
                el = e.target; //兼容火狐
            }
            //判断点击的类名
            switch (el.className) {
                //关闭整个状态
                case "close":
                    remove(el.parentNode);
                    break;
                //上面的点赞
                case "dzan":
                    praisebox(el.parentNode.parentNode.parentNode, el);
                    break;
                //回复评论
                case "hf-btn hf-btn-on":
                    rootId = $(el).parent().siblings("p.text").children(".name").attr("rootId");
                    reply(el.parentNode.parentNode.parentNode);
                    break;
                //每条评论中点赞
                case "comment-zan":
                    praiseReply(el);
                    break;
                case "comment-dele":
                    pid =  $(el).parent().prev().children(".user").attr("pid");
                    operateReply(el);
                    break;
            }
        }
        var textarea = lists[i].getElementsByClassName("hf-text")[0];
        //焦点事件
        textarea.onfocus = function() {

            this.parentNode.className = 'hf hf-on';
            this.value = this.value == '评论…' ? '' : this.value;
            setParentIframeHeight();
        }
        //失焦事件
        textarea.onblur = function() {
            if (this.value == '') {
                $(this).siblings(".layui-inline").addClass("hidden");
                this.parentNode.className = 'hf';
                this.value = '评论…';
            }
        }
        //键盘事件
        textarea.onkeyup = function() {
            var len = this.value.length;
            var textParentNode = this.parentNode;
            var textBtn = textParentNode.children[2];
            var textNub = textParentNode.children[3];

            if (len == 0 /*|| len>100*/ ) {
                $(this).siblings(".layui-inline").hide();
                textBtn.className = "hf-btn";
            } else {
                $(this).siblings(".layui-inline").show();
                textBtn.className = "hf-btn hf-btn-on";
                /*this.style.color="#333"; */
            }
            textNub.innerHTML = len + "/100";
        }
    }
    //遍历结束
}

//获取当前时间回复评论时调用
function getTime() {
    var t = new Date();
    var y = t.getFullYear();
    var m = t.getMonth() + 1;
    var d = t.getDate();
    var h = t.getHours();
    var mi = t.getMinutes();
    m = m < 10 ? "0" + m : m;
    d = d < 10 ? "0" + d : d;
    h = h < 10 ? "0" + h : h;
    mi = mi < 10 ? "0" + mi : mi;
    return y + "-" + m + "-" + d + "" + h + ":" + mi;
}




