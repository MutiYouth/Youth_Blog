<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>站内信form</title>
    <%@include file="../common/include.jsp" %>
    <script type="text/javascript" src="${rc.contextPath}/js/admin/letterForm.js"></script>
    <style>
        html body {
            margin-top: 0px;
        }
    </style>
</head>
<body>

<div class="layui-container">
    <div class="layui-card">
        <div class="layui-card-header"></div>
        <div class="layui-card-body">
            <form class="layui-form" action="" method="post">
                <input type="hidden" id="letterId" name="letterId" value="${letter.id}">
                <div class="layui-form-item">
                    <label class="layui-form-label">发件人</label>
                    <div class="layui-input-block">
                        <input type="text" id="userName" name="userName" lay-verify="required" class="layui-input" value="${letter.userName}" readonly>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">联系方式</label>
                    <div class="layui-input-block">
                        <input type="text" id="contactWay" name="contactWay" lay-verify="required" class="layui-input" value="${letter.contactWay}" readonly>
                    </div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">内容</label>
                    <div class="layui-input-block">
                        <textarea id="content" name="content"  class="layui-textarea" rows="10" readonly>${letter.content}</textarea>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
