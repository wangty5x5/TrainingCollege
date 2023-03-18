<%@ page import="entity.Student" %><%--
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

    <title>StudentOrder</title>

    <script src="../js/jquery-3.1.1.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/dataTables.bootstrap.js"></script>
    <script src="../js/jquery.dataTables.js"></script>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/dataTables.bootstrap.css" rel="stylesheet">
</head>
<body background="../img/background.jpg" style="background-size: cover">
<%
    Student student = (Student) request.getSession().getAttribute("student");
    String str = (String) request.getSession().getAttribute("type");
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
                    <li><a href="instSearch.action">预订课程</a> </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="stuInfo_studentInfo.action">会员名:<%=student.getName()%></a> </li>
                    <li><a href="main.action">退出登陆</a> </li>
                </ul>
            </div>
        </div>
    </nav>
</div>

<div class="navbar navbar-default navbar-fixed" style="width: 150px;top: 50px;background: transparent;border: transparent;position: fixed">
    <ul class="nav nav-pills nav-stacked" style="margin-top: 50px;">
        <li><a href="stuInfo_studentInfo.action"><h5 style="padding-left: 15px">个人信息</h5></a> </li>
        <li class="active"><a href="stuOrder_getOrders.action"><h5 style="padding-left: 15px">订单状态</h5></a> </li>
    </ul>
</div>

<fieldset style="position:absolute;top:100px;left:200px;width: 900px;height: 620px">
    <legend>我的订单</legend>
    <form action="stuOrder_searchOrder" method="post" id="searchForm">
        <div class="form-inline" style="margin-top: 20px;margin-bottom: 20px">
            <label>订单分类:</label>
            <select id="type" name="type">
                <option value="all">全部</option>
                <option value="done">已执行订单</option>
                <option value="wait">未执行订单</option>
                <option value="notPay">未支付订单</option>
                <option value="cancel">退订订单</option>
            </select>
            <input type="hidden" id="test" value="<%=str%>">
            <span class="glyphicon glyphicon-search" onclick="search()"></span>
        </div>
    </form>


    <form action="stuOrder_changeOrder" method="post" name="orderForm" id="orderForm">
        <input name="orderId" type="hidden" id="orderId">
        <table class="table dataTable" id="table-local">
            <thead style="background-color: rgba(190, 188, 198, 0.67)">
            <tr>
                <th>订单号</th>
                <th>机构编号</th>
                <th>课程号</th>
                <th>人数</th>
                <th>价格</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sessionScope.orders}" var="order">
                <tr>
                    <td>${order.orderId}</td>
                    <td>${order.institutionId}</td>
                    <td>${order.courseId}</td>
                    <td>${order.number}</td>
                    <td>${order.price}</td>
                    <td>
                        <c:choose>
                            <c:when test="${order.state=='SE'||order.state=='NE'}">已执行</c:when>
                            <c:when test="${order.state=='SW'||order.state=='NW'}">未执行</c:when>
                            <c:when test="${order.state=='UP'}">未支付</c:when>
                            <c:otherwise>退订</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="#" onclick="change(${order.orderId});return false;">
                            <c:choose>
                                <c:when test="${order.state=='UP'}">支付</c:when>
                                <c:otherwise>退订</c:otherwise>
                            </c:choose>
                        </a>
                    </td>
                </tr>
            </c:forEach>

            </tbody>

        </table>
    </form>

</fieldset>

<script>
    function change(id) {
        document.getElementById('orderId').value = id;
        document.getElementById('orderForm').submit();
    }

    function search() {
        document.getElementById('searchForm').submit();
    }

    var v = document.getElementById('test').value;
    $("#type").val(v);

</script>

</body>
</html>
