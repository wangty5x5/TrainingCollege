<%@ page import="entity.Manager" %><%--
  Created by IntelliJ IDEA.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Approval</title>

    <script src="../js/jquery-3.1.1.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/dataTables.bootstrap.js"></script>
    <script src="../js/jquery.dataTables.js"></script>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/dataTables.bootstrap.css" rel="stylesheet">
</head>
<body background="../img/background.jpg" style="background-size: cover">
<%
    Manager manager = (Manager) request.getSession().getAttribute("manager");
%>
<div>
    <nav class="navbar navbar-default navbar-fixed-top" style="color: #55acee">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" aria-controls="navbar" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand">Training College</a>
            </div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="#">首页</a> </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="managerSta.action">经理名:<%=manager.getName()%></a> </li>
                    <li><a href="main.action">退出登陆</a> </li>
                </ul>
            </div>
        </div>
    </nav>
</div>

<div class="navbar navbar-default navbar-fixed" style="width: 150px;top: 50px;background: transparent;border: transparent;position: fixed">
    <ul class="nav nav-pills nav-stacked" style="margin-top: 50px;">
        <li><a href="managerSta.action"><h5 style="padding-left: 15px">统计信息</h5></a> </li>
        <li class="active"><a href="approval_approval.action"><h5 style="padding-left: 15px">审批管理</h5></a> </li>
        <li><a href="managerOrder.action"><h5 style="padding-left: 15px">订单管理</h5></a> </li>
    </ul>
</div>

<fieldset style="position:absolute;top:100px;left:200px;width: 900px;height: 620px">
    <legend>申请列表</legend>
    <form action="approval_approve" method="post" id="form">
        <input name="approvalId" type="hidden" id="appId">
        <table class="table dataTable">
            <thead style="background-color: rgba(190, 188, 198, 0.67)">
            <tr>
                <th>申请编号</th>
                <th>机构编号</th>
                <th>申请种类</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sessionScope.approvals}" var="approval">
                <tr>
                    <td>${approval.approvalId}</td>
                    <td>${approval.institutionId}</td>
                    <td>${approval.type}</td>
                    <td>未审批</td>
                    <td>
                        <div class="row">
                            <button class="btn btn-default btn-primary btn-xs" onclick="setId(${approval.approvalId})" type="submit">通过</button>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>

</fieldset>

<script>
    function setId(id) {
        document.getElementById('appId').value = id;
    }
</script>
</body>
</html>
