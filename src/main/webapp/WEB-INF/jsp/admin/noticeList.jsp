<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>通知列表</title>
    <%@include file="../common/include.jsp" %>
    <script type="text/javascript" src="${rc.contextPath}/js/admin/noticeList.js"></script>
    <style>
        html body{margin-top: 0px;}
    </style>
</head>
<body>
    <div class="layui-card">
        <div class="layui-card-header" style="background-color: #c2c2c2">通知列表</div>
        <div class="layui-card-body">
            <div class="layui-btn-group demoTable">
                <button class="layui-btn layui-btn-primary  layui-btn-sm" data-type="addNotice">
                    <i class="layui-icon" title="添加">&#xe654;</i>
                </button>
                <button class="layui-btn layui-btn-primary  layui-btn-sm" data-type="changeNotice">
                    <i class="layui-icon" title="修改">&#xe642;</i>
                </button>
                <button class="layui-btn layui-btn-primary  layui-btn-sm" data-type="delNotice">
                    <i class="layui-icon" title="删除">&#xe640;</i>
                </button>
                <button class="layui-btn layui-btn-primary  layui-btn-sm" data-type="reload">
                    <i class="layui-icon" title="刷新">&#xe666;</i>
                </button>
            </div>
            <table id="noticeList" lay-filter=""></table>
            <script type="text/html" id="isActiveTpl">
                <input type="checkbox" name="isActive" value="{{d.id}}" title="显示" lay-filter="activeDemo" {{ d.isActive == 1 ? 'checked' : '' }}>
            </script>
        </div>
    </div>
</body>
</html>
