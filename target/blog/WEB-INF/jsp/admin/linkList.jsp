<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>友链列表</title>
    <%@include file="../common/include.jsp" %>
    <script type="text/javascript" src="${rc.contextPath}/js/admin/linkList.js"></script>
    <style>
        html body{margin-top: 0px;}
    </style>
</head>
<body>
<div class="layui-card">
    <div class="layui-card-header" style="background-color: #c2c2c2">友链列表</div>
    <div class="layui-card-body">
        <div class="layui-btn-group demoTable">
            <button class="layui-btn layui-btn-primary  layui-btn-sm" data-type="addLink">
                <i class="layui-icon" title="添加">&#xe654;</i>
            </button>
            <button class="layui-btn layui-btn-primary  layui-btn-sm" data-type="changeLink">
                <i class="layui-icon" title="修改">&#xe642;</i>
            </button>
            <shiro:hasRole name="admin">
            <button class="layui-btn layui-btn-primary  layui-btn-sm" data-type="delLink">
                <i class="layui-icon" title="删除">&#xe640;</i>
            </button>
            </shiro:hasRole>
            <button class="layui-btn layui-btn-primary  layui-btn-sm" data-type="reload">
                <i class="layui-icon" title="刷新">&#xe666;</i>
            </button>
        </div>
        <table id="linkList" lay-filter="*"></table>
    </div>
</div>

</body>
</html>
