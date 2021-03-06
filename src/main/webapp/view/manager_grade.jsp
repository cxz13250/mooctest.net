<%@ page import="java.util.List" %>
<%@ page import="com.mooctest.weixin.data.Task" %>
<%@ page import="com.mooctest.weixin.manager.Managers" %>
<%--
  Created by IntelliJ IDEA.
  User: ROGK
  Date: 2017/5/10
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <%
        List<Task> list=(List<Task>)request.getAttribute("task");
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
<div id="container" align="center">
    <div id="div1" >
        <h1 class="weui-header-title" align="center">任务列表</h1>
    </div> <br/>
    <div id="div2">
        <table class="weui-table">
            <% if(list.isEmpty()||list == null){ %>
            <p align="center">暂无已结束的任务</p>
            <% }else{%>
            <thead>
            <tr><th width="50%">任务名称</th>
                <th width="50%">查看详情</th>
            </tr>
            </thead>
            <tbody>
                <%for(Task task:list){%>
                <tr>
                    <td width="50%"><%=task.getTaskName()%></td>
                    <td width="50%"><a href="worker_grade?id=<%=task.getId()%>&name=<%=task.getTaskName()%>">详情</a></td>
                </tr>
                <%}}%>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
