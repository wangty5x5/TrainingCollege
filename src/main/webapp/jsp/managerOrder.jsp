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

    <title>ManagerOrder</title>

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
        <li><a href="approval_approval.action"><h5 style="padding-left: 15px">审批管理</h5></a> </li>
        <li class="active"><a href="managerOrder.action"><h5 style="padding-left: 15px">订单管理</h5></a> </li>
    </ul>
</div>

<fieldset style="position:absolute;top:100px;left:200px;width: 900px;height: 620px">
    <legend>未结算订单</legend>
    <form action="settle.action" method="post" id="undoForm">
        <input type="hidden" name="orderId" id="oid">
        <table class="table dataTable">
            <thead style="background-color: rgba(190, 188, 198, 0.67)">
            <tr>
                <th>订单编号</th>
                <th>机构编号</th>
                <th>机构名称</th>
                <th>学员邮箱</th>
                <th>价格</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sessionScope.undoUtils}" var="undoOrder">
                <tr>
                    <td>${undoOrder.orderId}</td>
                    <td>${undoOrder.institutionId}</td>
                    <td>${undoOrder.institutionName}</td>
                    <td>${undoOrder.studentMail}</td>
                    <td>${undoOrder.price}</td>
                    <td>
                        <a href="#" onclick="settleOrder(${undoOrder.orderId});return false;">结算</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>

    <br>

    <legend style="margin-top: 40px">已结算订单</legend>
    <table class="table dataTable">
        <thead style="background-color: rgba(190, 188, 198, 0.67)">
        <tr>
            <th>订单编号</th>
            <th>机构编号</th>
            <th>机构名称</th>
            <th>学员邮箱</th>
            <th>价格</th>
        </tr>
        </thead>
        <c:forEach items="${sessionScope.doneUtils}" var="doneOrder">
            <tr>
                <td>${doneOrder.orderId}</td>
                <td>${doneOrder.institutionId}</td>
                <td>${doneOrder.institutionName}</td>
                <td>${doneOrder.studentMail}</td>
                <td>${doneOrder.price}</td>
            </tr>
        </c:forEach>
    </table>
</fieldset>

<script>
    function settleOrder(id) {
        document.getElementById('oid').value = id;
        document.getElementById('undoForm').submit();
    }
</script>

</body>
</html>
