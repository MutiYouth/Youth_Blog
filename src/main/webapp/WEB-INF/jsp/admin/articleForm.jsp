<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>文章form</title>
    <%@include file="../common/include.jsp" %>
    <script src="${rc.contextPath}/plugins/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="${rc.contextPath}/js/admin/articleForm.js"></script>
    <style>
        html body {
            margin-top: 0px;
        }
    </style>
</head>
<body>
<div class="layui-container fly-marginTop">
    <div class="fly-panel" pad20 style="padding-top: 5px;">
        <div class="layui-form layui-form-pane">
            <div class="layui-tab layui-tab-brief" lay-filter="*">
                <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
                    <div class="layui-tab-item layui-show">

                        <form action="" method="post">
                            <input type="hidden" id="articleId" name="articleId" value="${article.id}">
                            <div class="layui-row layui-col-space15 layui-form-item">
                                <div class="layui-col-md3">
                                    <label class="layui-form-label">所在分类</label>
                                    <div class="layui-input-block">
                                        <select lay-verify="required" name="categoryId" lay-filter="column">
                                            <option value="">请选择一个分类</option>
                                            <c:forEach items="${categorys}" var="item">
                                                <option value="${item.id}"
                                                        <c:if test="${article.categoryId == item.id}">selected</c:if> >${item.title }</option>
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="layui-col-md3">
                                    <label for="title" class="layui-form-label">标签</label>
                                    <div class="layui-input-block">
                                        <input type="text" id="label" name="label" required lay-verify="required"
                                               class="layui-input" value="${article.label}">
                                    </div>
                                </div>
                                <div class="layui-col-md6">
                                    <label for="title" class="layui-form-label">标题</label>
                                    <div class="layui-input-block">
                                        <input type="text" id="title" name="title" required lay-verify="required"
                                               autocomplete="off" class="layui-input" value="${article.title}">
                                    </div>
                                </div>
                            </div>
                            <div class="layui-form-item layui-form-text">
                                <div class="layui-input-block">
                                    <textarea id="content" name="content"
                                              placeholder="详细描述">${article.content}</textarea>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <input type="checkbox" name="isStick" title="置顶"
                                       <c:if test="${article.isStick == '1'}">checked</c:if>>
                                <input type="checkbox" name="allowComment" title="禁言"
                                       <c:if test="${article.allowComment == '1'}">checked</c:if>>
                            </div>

                            <div class="layui-form-item">
                                <%--<shiro:hasRole name="admin">--%>
                                    <%--<button class="layui-btn" lay-filter="publishArticle" lay-submit>立即发布</button>--%>
                                <%--</shiro:hasRole>--%>
                                    <button class="layui-btn" lay-filter="publishArticle" lay-submit>立即发布</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
