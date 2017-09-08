<%@ page import="java.util.List" %>
<%@ page import="com.mooctest.weixin.entity.Grade" %><%--
  Created by IntelliJ IDEA.
  User: ROGK
  Date: 2017/5/10
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <%
        String name=(String)request.getAttribute("name");
        List<Grade> list=(List<Grade>)request.getAttribute("grade");
    %>
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path + "/";
    %>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>任务成绩</title>
    <link rel="stylesheet" href="<%=basePath%>css/rokg.css">
    <link rel="stylesheet" href="http://cdn.bootcss.com/weui/1.1.1/style/weui.min.css">
    <link rel="stylesheet" href="http://cdn.bootcss.com/jquery-weui/1.0.1/css/jquery-weui.min.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
</head>
    <style type="text/css">
        td{
            text-align: center;
        }
    </style>
<body>
<div id="container">
    <div id="div1">
        <%--<p><label>任务名称:</label><%=name%></p>--%>
    </div>
    <div id="div2">
        <h1 class="weui-header-title" align="center">学生列表</h1>
        <table id="example" cellspacing="0" width="100%" class="weui-table weui-border-tb">
            <thead>
            <tr>
                <th width="50%">学生姓名</th>
                <th width="50%">学生成绩</th>
            </tr>
            </thead>
            <tbody>
            <%for(Grade grade:list){%>
            <tr>
                <td width="50%"><%=grade.getWorkerName()%></td>
                <td width="50%"><%=grade.getGrade()%></td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
    <div>
        <p class="weui-btn-area">
            <a onclick="javascript:history.back()" class="weui-btn weui-btn_primary">返回</a>
        </p>
    </div>
</div>
</body>
</html>
