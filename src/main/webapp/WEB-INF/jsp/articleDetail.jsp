<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>文章详情页面</title>
    <%@include file="./common/include.jsp" %>
    <link rel="stylesheet" type="text/css" href="${rc.contextPath}/css/evalute.css" />
    <link rel="stylesheet" type="text/css" href="${rc.contextPath}/plugins/highlight/styles/github.css"/>
    <script type="text/javascript" src="${rc.contextPath}/js/articleDetail.js"></script>
    <script type="text/javascript" src="${rc.contextPath}/plugins/highlight/highlight.pack.js"></script>
</head>
<body>
    <div class="layui-container">
        <div class="fly-panel detail-box">
            <h1>${article.title}</h1>
            <div class="fly-detail-info">
                <c:if test="${article.isStick == 1}">
                    <span class="layui-badge layui-bg-black">置顶</span>
                </c:if>
                <c:if test="${article.commentCount > 1}">
                    <span class="layui-badge layui-bg-red">精帖</span>
                </c:if>
                &nbsp&nbsp&nbsp&nbsp
                <span><i class="layui-icon layui-icon-username"></i>${article.author}</span>&nbsp&nbsp&nbsp&nbsp
                <span><i class="layui-icon layui-icon-log"></i><fmt:formatDate value="${article.gmtCreate}" pattern="yyyy-MM-dd"/></span>

                <span class="fly-list-nums">
                    <a href="#flyReply"><i class="iconfont" title="回答">&#xe60c;</i> ${article.commentCount}</a>
                    <i class="iconfont" title="人气">&#xe60b;</i> 99999
                </span>
            </div>
            <div class="detail-body photos">
                ${article.content}
            </div>
        </div>
        <div class="fly-panel detail-box">
            <fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
                <legend>评论</legend>
            </fieldset>
            <form class="layui-form layui-form-pane" action="">
                <input type="hidden" id="articleId" name="articleId" value="${article.id}" />
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">你的名字</label>
                        <div class="layui-input-inline">
                            <input type="tel" name="name" id="name" lay-verify="required|maxLength" placeholder="输入名字" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">联系方式</label>
                        <div class="layui-input-inline">
                            <input type="text" name="contactInfo" id="contactInfo" lay-verify="required|maxLength" placeholder="输入联系方式" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">内容</label>
                    <div class="layui-input-block">
                        <textarea id="msg" placeholder=" 请输入留言..." name="msg" lay-verify="required|msg" class="layui-textarea"></textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
                </div>
            </form>
        </div>
        <div class="fly-panel detail-box" id="flyReply" name="flyReply">
            <fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
                <legend>留言</legend>
            </fieldset>
            <ul class="flow-default" id="pn"></ul>
        </div>
    </div>
<script>
    //代码高亮
    $(function(){
        hljs.initHighlightingOnLoad();

    });
</script>
</body>
</html>
