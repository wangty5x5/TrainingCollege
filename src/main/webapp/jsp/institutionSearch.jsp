<%@ page import="entity.Student" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>InstitutionSearch</title>

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
                    <li class="active"><a href="instSearch.action">预订课程</a> </li>
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
        <li><a href="stuOrder_getOrders.action"><h5 style="padding-left: 15px">订单状态</h5></a> </li>
    </ul>
</div>

<fieldset style="position:absolute;top:100px;left:200px;width: 900px;height: 620px">
    <legend>培训机构搜索</legend>
    <div class="col-md-offset-10">
        <button type="button" class="btn btn-default btn-success" onclick="openSearch()">
            <span class="glyphicon glyphicon-search" aria-hidden="true"></span>展开搜索
        </button>
    </div>
    <form action="siftInst" method="post" id="instSearch" style="display: none">
        <label>机构相关搜索:</label>
        <select id="select_one" name="province" onchange="get_city()">
            <option value="">请选择省份</option>
        </select>
        <select id="select_two" name="city">
            <option value="">请选择城市</option>
        </select>
        <span class="glyphicon glyphicon-search" onclick="document.getElementById('instSearch').submit()"></span>
    </form>
    <br>

    <form action="courseSearch" method="post" id="instForm">
        <input type="hidden" name="institutionId" id="instId">
        <table class="table dataTable" id="table-local">
            <thead style="background-color: rgba(190, 188, 198, 0.67)">
            <tr>
                <th>机构编号</th>
                <th>机构名称</th>
                <th>城市</th>
                <th>师资</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sessionScope.institutions}" var="institution">
                <tr>
                    <td>${institution.id}</td>
                    <td>${institution.name}</td>
                    <td>${institution.province}${institution.city}</td>
                    <td>${institution.teachers}</td>
                    <td>
                        <div class="row">
                            <button class="btn btn-default btn-primary btn-xs" onclick="test(${institution.id})" type="submit">查看</button>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>

</fieldset>

<script>
    function openSearch() {
        document.getElementById('instSearch').style.display = "block";
        set_city();
    }

    function test(id) {
        document.getElementById('instId').value = id;
    }

</script>

<script src="../js/city.js"></script>
</body>
</html>
