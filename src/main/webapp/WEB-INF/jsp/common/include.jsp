<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="${rc.contextPath}/plugins/layui/css/layui.css" />
<link rel="stylesheet" type="text/css" href="${rc.contextPath}/css/global.css" />
<script type="text/javascript" src="${rc.contextPath}/plugins/jquery.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/plugins/layui/layui.js"></script>
<script type="text/javascript" src="${rc.contextPath}/js/global.js"></script>


