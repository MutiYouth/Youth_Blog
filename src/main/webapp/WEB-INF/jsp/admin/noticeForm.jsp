<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>通知form</title>
    <%@include file="../common/include.jsp" %>
    <script type="text/javascript" src="${rc.contextPath}/js/admin/noticeForm.js"></script>
    <style>
        html body {
            margin-top: 0px;
        }
    </style>
</head>
<body>
<div class="layui-container">
    <div class="layui-card">
        <div class="layui-card-body">
            <form class="layui-form" action="" method="post">
                <input type="hidden" id="noticeId" name="noticeId" value="${notice.id}">
                <div class="layui-form-item">
                    <label class="layui-form-label">标题</label>
                    <div class="layui-input-block">
                        <input type="text" id="title" name="title" lay-verify="required" class="layui-input" value="${notice.title}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">作者</label>
                    <div class="layui-input-block">
                        <input type="text" id="author" name="author" lay-verify="required" class="layui-input" value="${notice.author}">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">显示</label>
                    <div class="layui-input-block">
                        <input type="checkbox" id="isActive" name="isActive" lay-skin="switch" <c:if test="${notice.isActive eq '1'}">checked</c:if>>
                    </div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">内容</label>
                    <div class="layui-input-block">
                        <textarea id="content" name="content"  class="layui-textarea" >${notice.content}</textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-filter="editNotice" lay-submit>提交</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
