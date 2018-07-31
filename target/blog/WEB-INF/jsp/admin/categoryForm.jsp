<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>分类form</title>
    <%@include file="../common/include.jsp" %>
    <script type="text/javascript" src="${rc.contextPath}/js/admin/categoryForm.js"></script>
    <style>
        html body {
            margin-top: 0px;
            padding-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="layui-container">
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-card-header"></div>

            <form class="layui-form" action="" method="post">
                <input type="hidden" id="categoryId" name="categoryId" value="${category.id}">
                <div class="layui-form-item">
                    <label class="layui-form-label">分类名称</label>
                    <div class="layui-input-block">
                        <input type="text" id="title" name="title"
                               lay-verify="required" class="layui-input" value="${category.title}">
                    </div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">备注</label>
                    <div class="layui-input-block">
                        <textarea id="intro" name="intro" class="layui-textarea">${category.intro}</textarea>
                    </div>
                </div>

                <%--提交--%>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <%--<shiro:hasRole name="admin">--%>
                            <button class="layui-btn" lay-filter="editCategory" lay-submit>提交</button>
                        <%--</shiro:hasRole>--%>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
