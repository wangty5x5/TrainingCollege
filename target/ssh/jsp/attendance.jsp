<%@ page import="entity.Institution" %>
<%@ page import="entity.Course" %><%--
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

    <title>Attendance</title>

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
    Course course = (Course) request.getSession().getAttribute("course");
    int classNum = (int) request.getSession().getAttribute("classNum");
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
        <li class="active"><a href="instCourse.action"><h5 style="padding-left: 15px">课程管理</h5></a> </li>
        <li><a href="instOrder.action"><h5 style="padding-left: 15px">查看订单</h5></a> </li>
    </ul>
</div>

<fieldset style="position:absolute;top:100px;left:200px;width: 900px;height: 620px">
    <legend>课程信息</legend>
    <div class="row">
        <div class="col-md-4">
            <label style="margin: 10px">课程编号:</label>
            <label><%=course.getCourseId()%></label>
        </div>
        <div class="col-md-4">
            <label style="margin: 10px">类别:</label>
            <label><%=course.getCategory()%></label>
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col-md-4">
            <label style="margin: 10px">开始时间:</label>
            <label><%=course.getStartDate()%></label>
        </div>
        <div class="col-md-4">
            <label style="margin: 10px">班级数:</label>
            <label><%=classNum%></label>
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col-md-4">
            <label style="margin: 10px">上课时间:</label>
            <label><%=course.getClassTime()%></label>
        </div>
        <div class="col-md-4">
            <label style="margin: 10px">课时数:</label>
            <label><%=course.getPeriod()%></label>
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col-md-4">
            <label style="margin: 10px">总人数:</label>
            <label><%=course.getNumber()%></label>
        </div>
        <div class="col-md-4">
            <label style="margin: 10px">空余人数:</label>
            <label><%=course.getEmptyNum()%></label>
        </div>
    </div>
    <br>

    <form action="absence" method="post">
        <input type="hidden" name="sid" id="sid">
        <table class="table dataTable" style="width: 800px">
            <thead style="background-color: rgba(190, 188, 198, 0.67)">
            <tr>
                <th>学员号</th>
                <th>学员名</th>
                <th>班号</th>
                <th>缺席次数</th>
                <th>成绩</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sessionScope.infoList}" var="info">
                <tr>
                    <td>${info.studentId}</td>
                    <td>${info.studentName}</td>
                    <td>${info.classId%10}</td>
                    <td>${info.absence}</td>
                    <td>${info.grade}</td>
                    <td>
                        <div class="row">
                            <button class="btn btn-default btn-primary btn-xs" type="submit" onclick="setId(${info.studentId})">登记缺席</button>
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
        document.getElementById('sid').value = id;
    }
</script>
</body>
</html>
