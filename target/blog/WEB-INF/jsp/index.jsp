<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>Youth Blog</title>
    <%@include file="./common/include.jsp" %>
    <link rel="stylesheet" type="text/css" href="${rc.contextPath}/css/global.css" />
    <link rel="stylesheet" type="text/css" href="${rc.contextPath}/css/index.css" />
    <script type="text/javascript" src="${rc.contextPath}/js/index.js"></script>
</head>
<body>
<div class="fly-header layui-bg-black">
    <div class="layui-container">
        <a class="fly-logo" href="javascript:jump('toArticleList',0);">
            <img src="resources/images/logo.png" alt="layui">
        </a>
        <ul class="layui-nav fly-nav layui-hide-xs" lay-filter="">
            <li class="layui-nav-item layui-this">
                <a href="javascript:jump('contactMe',0);">
                    <i class="iconfont icon-jiaoliu"></i>联系我
                </a>
            </li>
        </ul>
        <ul class="layui-nav fly-nav-user">
            <li class="layui-nav-item">
                <div class="layui-container">
                    <div class="fly-column-right layui-hide-xs">
                        <div class="layui-form-item">
                            <input type="text" id="searchContent" name="searchContent" placeholder="请输入标题或作者..." autocomplete="off">
                            <span class="fly-search"><i class="layui-icon"></i></span>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>
<div class="fly-panel fly-column">
</div>

<div class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md8" style="height: 100%">
                <IFRAME border=0 marginWidth=0 frameSpacing=0 marginHeight=0 src="toArticleList" frameBorder=0
                        noResize scrolling="no" width=100% height=100% vspale="0" id="mainFrame" name="mainFrame"></IFRAME>
        </div>
        <div class="layui-col-md4">
            <%--通知--%>
            <div class="layui-card">
                <div class="layui-card-header">${notice.title}</div>
                <div class="layui-card-body">
                    <div class="layui-anim layui-anim-rotate">
                        ${notice.content}<br>
                        —— 作者：
                            <c:if test="${notice.author == ''|| notice.author == null}">无</c:if>
                            <c:if test="${notice.author != null && notice.author != ''}">${notice.author}</c:if>
                    </div>
                </div>
            </div>

            <div class="layui-card">
                <div class="layui-card-header">分类</div>
                <div class="layui-card-body">
                    <div class="layui-anim layui-anim-scale">
                        <ul class="fly-list-static">
                            <c:forEach items="${categorys}" var="item">
                                <li><a href="javascript:jump('toArticleList?categoryId=${item.id}&categoryTitle=${item.title}',0);" >${item.title}（${item.articleCount}） </a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="layui-card">
                <div class="layui-card-header">归档</div>
                <div class="layui-card-body">
                    <div class="layui-anim layui-anim-scale">
                        <ul class="fly-list-static">
                            <c:forEach items="${archives}" var="item">
                                <li><a href="javascript:jump('toArticleList?date=${item.date}',0);" >${item.date}（${item.count}）</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="layui-card fly-link">
                <div class="layui-card-header">友链</div>
                <dl class="layui-card-body">
                    <c:forEach items="${links}" var="item">
                        <dd><a href="${item.url}" target="_blank">${item.title}</a><dd>
                    </c:forEach>
                    <dd><a href="javascript:applyFriendChain();" class="fly-link">申请友链</a><dd>
                </dl>
            </div>
        </div>
    </div>
</div>

<div class="fly-footer">
    <p>
        &copy; 2018 Powered by &middot;
        <a href="https://github.com/754594774/blog" target="_blank">李难难</a>
        <a href="https://github.com/csunking" target="_blank">WENG</a>
        &middot;<a href="https://www.cnblogs.com/arxive/." target="_blank">Youth Blog</a>
    </p>
</div>
<input type="hidden" id="webApp" value="${rc.contextPath}"/>
</body>
</html>
