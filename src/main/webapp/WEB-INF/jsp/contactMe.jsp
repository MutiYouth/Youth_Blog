<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>联系我</title>
    <%@include file="./common/include.jsp" %>
    <script type="text/javascript" src="${rc.contextPath}/js/contactMe.js"></script>
    <style>
        html body{margin-top: 0px;}
    </style>
</head>
<body>
<div class="layui-card">
    <div class="layui-card-header">
        站内信
    </div>
    <div class="layui-card-body">
        <form class="layui-form layui-form-pane" action="">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">你的名字</label>
                    <div class="layui-input-inline">
                        <input type="tel" name="name" id="name" lay-verify="required" placeholder="输入名字" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">联系方式</label>
                    <div class="layui-input-inline">
                        <input type="text" name="contactInfo" id="contactInfo" lay-verify="required" placeholder="输入联系方式" autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">内容</label>
                <div class="layui-input-block">
                    <textarea id="msg" placeholder=" 请输入内容..." name="msg" lay-verify="required" class="layui-textarea" rows="12"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <button class="layui-btn layui-btn-fluid" lay-submit="" lay-filter="contactMe">提交您的内容</button>
            </div>
        </form>
    </div>
</div>

</body>
</html>
