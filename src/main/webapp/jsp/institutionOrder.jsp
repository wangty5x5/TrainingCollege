<%@ page import="entity.Institution" %><%--
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

    <title>InstitutionOrder</title>

    <script src="../js/jquery-3.1.1.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/dataTables.bootstrap.js"></script>
    <script src="../js/jquery.dataTables.js"></script>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/dataTables.bootstrap.css" rel="stylesheet">
</head>
<body background="../img/background.jpg" style="background-size: cover">
<%
    Institution institution = (Institution) request.getSession().getAttribute("institution");
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
                    <li><a href="instInfo.action">机构名:<%=institution.getName()%></a> </li>
                    <li><a href="main.action">退出登陆</a> </li>
                </ul>
            </div>
        </div>
    </nav>
</div>

<div class="navbar navbar-default navbar-fixed" style="width: 150px;top: 50px;background: transparent;border: transparent;position: fixed">
    <ul class="nav nav-pills nav-stacked" style="margin-top: 50px;">
        <li><a href="instInfo.action"><h5 style="padding-left: 15px">机构信息</h5></a> </li>
        <li><a href="instCourse.action"><h5 style="padding-left: 15px">课程管理</h5></a> </li>
        <li class="active"><a href="instOrder.action"><h5 style="padding-left: 15px">查看订单</h5></a> </li>
    </ul>
</div>

<fieldset style="position:absolute;top:100px;left:200px;width: 900px;height: 620px">
    <legend>订单</legend>
    <div class="form-inline" style="margin-top: 20px;margin-bottom: 20px">
        <label>订单分类:</label>
        <select>
            <option>全部</option>
            <option>已执行订单</option>
            <option>未执行订单</option>
            <option>退订订单</option>
        </select>
    </div>
    <table class="table dataTable" id="table-local">
        <thead style="background-color: rgba(190, 188, 198, 0.67)">
        <tr>
            <th>订单号</th>
            <th>会员名</th>
            <th>课程号</th>
            <th>人数</th>
            <th>价格</th>
            <th>状态</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${sessionScope.instOrders}" var="order">
            <tr>
                <td>${order.orderId}</td>
                <td>${order.studentName}</td>
                <td>${order.courseId}</td>
                <td>${order.number}</td>
                <td>${order.price}</td>
                <td>
                    <c:choose>
                        <c:when test="${order.state=='SE'}">已结算已执行</c:when>
                        <c:when test="${order.state=='SW'}">已结算未执行</c:when>
                        <c:when test="${order.state=='NE'}">未结算已执行</c:when>
                        <c:when test="${order.state=='C'}">退订</c:when>
                        <c:otherwise>未结算未执行</c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</fieldset>

</body>
</html>
