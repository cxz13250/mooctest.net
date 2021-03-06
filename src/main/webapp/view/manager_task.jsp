<%@ page import="java.util.List" %>
<%@ page import="com.mooctest.weixin.data.Task"%>
<%@ page import="com.mooctest.weixin.manager.Managers" %>
<%--
  Created by IntelliJ IDEA.
  User: ROGK
  Date: 2017/5/10
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <%
        List<Task> current=(List<Task>)request.getAttribute("current");
        List<Task> unstart=(List<Task>)request.getAttribute("unstart");
    %>
<head>
    <%
        String basePath = Managers.config.getBaseUrl();
    %>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>任务列表</title>

</head>
    <jsp:include page="header.jsp" flush="true"></jsp:include>
<body>
<div id="container">
    <div class="weui-tab">
        <div class="weui-navbar">
            <a class="weui-navbar__item weui-bar__item--on" href="#tab1">
                进行中
            </a>
            <a class="weui-navbar__item" href="#tab2">
                未开始
            </a>
        </div>
        <div class="weui-tab__bd">
            <div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active">
                <h1 class="weui-header-title" align="center">任务列表</h1><br/>
                <table class="weui-table">
                    <% if(current.isEmpty()||current == null){ %>
                    <p align="center">暂无进行中的任务</p>
                    <% }else{%>
                    <thead>
                    <tr><th width="50%">任务名称</th>
                        <th width="50%">查看详情</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%for(Task task:current){%>
                    <tr>
                        <td width="50%"><%=task.getTaskName()%></td>
                        <td width="50%"><a href="<%=basePath%>q/task/worker_task?id=<%=task.getId()%>&name=<%=task.getTaskName()%>">详情</a></td>
                    </tr>
                    <%}}%>
                    </tbody>
                </table>
            </div>
            <div id="tab2" class="weui-tab__bd-item">
                <h1 class="weui-header-title" align="center">任务列表</h1><br/>
                <table class="weui-table">
                    <% if(unstart.isEmpty()||unstart == null){ %>
                    <p align="center">暂无未开始的任务</p>
                    <% }else{%>
                    <thead>
                    <tr><th width="50%">任务名称</th>
                        <th width="50%">查看详情</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%for(Task task:unstart){%>
                    <tr>
                        <td width="50%"><%=task.getTaskName()%></td>
                        <td width="50%"><a href="<%=basePath%>q/task/worker_task?id=<%=task.getId()%>&name=<%=task.getTaskName()%>">详情</a></td>
                    </tr>
                    <%}}%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>

</body>
</html>
