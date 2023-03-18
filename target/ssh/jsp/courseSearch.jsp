<%@ page import="entity.Student" %>
<%@ page import="entity.Institution" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>CourseSearch</title>

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
    <legend>课程搜索</legend>
    <div class="col-md-offset-10">
        <button type="button" class="btn btn-default btn-success" onclick="openSearch()">
            <span class="glyphicon glyphicon-search" aria-hidden="true"></span>展开搜索
        </button>
    </div>

    <form action="siftCourse" method="post" id="typeSearch" style="display: none">
        <label>课程相关搜索</label>
        <select name="category">
            <option value="all">请选择类别</option>
            <option value="奥数">奥数</option>
            <option value="物理">物理</option>
            <option value="英语">英语</option>
            <option value="考研">考研</option>
        </select>
        <select name="time">
            <option value="all">请选择上课时间</option>
            <option value="全天">全天</option>
            <option value="周末">周末</option>
            <option value="工作日">工作日</option>
        </select>
        <span class="glyphicon glyphicon-search" onclick="document.getElementById('typeSearch').submit()"></span>
    </form>
    <br>

    <legend>培训机构信息</legend>
    <div class="row">
        <div class="col-md-4">
            <label style="margin: 10px">机构编号:</label>
            <label><%=institution.getId()%></label>
        </div>
        <div class="col-md-4">
            <label style="margin: 10px">机构名称:</label>
            <label><%=institution.getName()%></label>
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col-md-4">
            <label style="margin: 10px">城市:</label>
            <label><%=institution.getProvince()%><%=institution.getCity()%></label>
        </div>
        <div class="col-md-4">
            <label style="margin: 10px">师资:</label>
            <label><%=institution.getTeachers()%></label>
        </div>
    </div>
    <br>
    <label style="margin: 10px">详细地址:</label>
    <label><%=institution.getAddress()%></label>
    <br>

    <legend>课程列表</legend>

    <form action="getCourse" method="post">
        <input type="hidden" name="cid" id="cid">
        <table class="table dataTable">
            <thead style="background-color: rgba(190, 188, 198, 0.67)">
            <tr>
                <th>课程编号</th>
                <th>类别</th>
                <th>班级数</th>
                <th>开始时间</th>
                <th>课时数</th>
                <th>上课时间</th>
                <th>总人数</th>
                <th>空余人数</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sessionScope.courseList}" var="course">
                <tr>
                    <td>${course.courseId}</td>
                    <td>${course.category}</td>
                    <td>${course.classNum}</td>
                    <td>${course.start}</td>
                    <td>${course.period}</td>
                    <td>${course.time}</td>
                    <td>${course.number}</td>
                    <td>${course.emptyNum}</td>
                    <td>
                        <div class="row">
                            <button class="btn btn-default btn-primary btn-xs" onclick="setId(${course.courseId})" type="submit">预订</button>
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
        document.getElementById('typeSearch').style.display = "block";
    }

    function setId(id) {
        document.getElementById('cid').value = id;
    }
</script>

</body>
</html>
