<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <%@include file="./common/include.jsp" %>
    <link rel="stylesheet" type="text/css" href="${rc.contextPath}/css/global.css" />
    <link rel="stylesheet" type="text/css" href="${rc.contextPath}/css/articleList.css" />
    <script type="text/javascript" src="${rc.contextPath}/js/articleList.js"></script>
</head>
<body>
    <input type="hidden" id="categoryId" value="${categoryId}"/>
    <input type="hidden" id="date" value="${date}"/>
    <input type="hidden" id="searchContent" value="${searchContent}"/>
    <div class="layui-card">
        <div class="layui-card-header">
            最新发布
            <c:if test="${date != ''&& date != null}"> - ${date}</c:if>
            <c:if test="${title != ''&& title != null}"> - ${title}</c:if>
            <c:if test="${searchContent != ''&& searchContent != null}"> - 【${searchContent}】</c:if>
        </div>
        <div class="layui-card-body">
            <ul class="flow-default fly-list" id="LAY_demo1">
            </ul>
        </div>
    </div>
</body>
</html>
