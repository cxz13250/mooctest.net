<%@ page import="java.util.List" %>
<%@ page import="com.mooctest.weixin.entity.Password" %><%--
  Created by IntelliJ IDEA.
  User: ROGK
  Date: 2017/5/10
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <%
        String name=(String)request.getAttribute("name");
        List<Password> list=(List<Password>)request.getAttribute("pwd");
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
    <title>Title</title>
    <link rel="stylesheet" href="http://weixin.yoby123.cn/weui/style/weui.css"/>
    <link rel="stylesheet" href="http://weixin.yoby123.cn/weui/style/weui2.css"/>
    <link rel="stylesheet" href="http://weixin.yoby123.cn/weui/style/weui3.css"/>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
</head>
    <style type="text/css">
        td{
            text-align: center;
        }
    </style>
<body>
<div id="div1">
    <p><label>任务名称:</label><%=name%></p><br/>
</div>
<div id="div2">
    <h1 class="weui-header-title" align="center">学生列表</h1>
    <table id="example" class="display" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th width="30%">学生姓名</th>
            <th width="70%">任务密码</th>
        </tr>
        </thead>
        <tfoot>
        <tr>
            <th width="30%">学生姓名</th>
            <th width="70%">任务密码</th>
        </tr>
        </tfoot>
        <tbody>
        <%for(Password password:list){%>
        <tr>
            <td width="30%"><%=password.getWorkerName()%></td>
            <td width="70%"><%=password.getPassword()%></td>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>
<div>
    <p class="weui_btn_area">
        <a onclick="javascript:history.back()" class="weui_btn weui_btn_primary">返回</a>
    </p>
</div>
</div>
<script type="text/javascript">
    $(document).ready(function() {
        $('#example').DataTable();
    } );
</script>
</body>
</html>
