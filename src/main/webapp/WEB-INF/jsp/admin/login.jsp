<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页</title>
    <%@include file="../common/include.jsp" %>
    <script type="text/javascript" src="${rc.contextPath}/js/admin/login.js"></script>
    <style type="text/css">
        html body {
            margin-top: 180px;
            background: url('${rc.contextPath}/resources/images/login_bg.png') no-repeat;
            background-size: cover;
            background-color: #f8f7ee;
        }
    </style>
</head>
<body>
<div class="layui-container">
    <div class="layui-row">

        <div class="layui-col-md4" >
            <div class="layui-card"  style="background-color: rgba(200,200,200,0.5)">
                <div class="layui-card-header">管理员登录</div>
                <div class="layui-card-body">
                    <form class="layui-form layui-form-pane" action="subLogin" method="post">
                        <div class="layui-form-item">
                            <label class="layui-form-label">用户名</label>
                            <div class="layui-input-block">
                                <input type="test" name="username" lay-verify="required" placeholder="请输入用户名"
                                       autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">密码</label>
                            <div class="layui-input-block">
                                <input type="password" name="password" lay-verify="required" placeholder="请输入密码"
                                       autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block" style="height: 50px">
                                <%--lay-ignore--%>
                                    <input type="checkbox" name="rememberMe" title="记住我" lay-skin="primary">
                                    <button class="layui-btn" lay-submit="" lay-filter="">登陆</button>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <span style="color: #FF5722">${errMsg}</span>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>