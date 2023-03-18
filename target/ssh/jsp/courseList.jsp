<%@ page import="entity.Institution" %>
<%--
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

    <title>CourseList</title>

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
        <li class="active"><a href="instCourse.action"><h5 style="padding-left: 15px">课程管理</h5></a> </li>
        <li><a href="instOrder.action"><h5 style="padding-left: 15px">查看订单</h5></a> </li>
    </ul>
</div>

<fieldset style="position:absolute;top:100px;left:200px;width: 900px;height: 620px">
    <legend>全部课程</legend>
    <div class="col-md-offset-10" style="margin-bottom: 20px">
        <button type="button" class="btn btn-default btn-success" data-toggle="modal" data-target="#addPlan">
            <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>发布新课程
        </button>
    </div>

    <form action="courseInfo" method="post" id="courseForm">
        <input type="hidden" name="cid" id="cid">
        <table class="table dataTable" id="table-local">
            <thead style="background-color: rgba(190, 188, 198, 0.67)">
            <tr>
                <th>课程号</th>
                <th>类别</th>
                <th>开始时间</th>
                <th>课时数</th>
                <th>上课时间</th>
                <th>总人数</th>
                <th>空余人数</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sessionScope.courses}" var="course">
                <tr>
                    <td>${course.courseId}</td>
                    <td>${course.category}</td>
                    <td>${course.startDate}</td>
                    <td>${course.period}</td>
                    <td>${course.classTime}</td>
                    <td>${course.number}</td>
                    <td>${course.emptyNum}</td>
                    <td>
                        <a href="#" onclick="check(${course.courseId})">查看</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>

</fieldset>

<div class="modal fade" role="dialog" id="addPlan" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" aria-label="Close" data-dismiss="modal" style="color: lime">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" style="align-items: center;padding-left: 3%;color: #55acee">发布课程</h4>
            </div>
            <div class="modal-body">
                <form action="addPlan" method="post">
                    <table class="table">
                        <tbody>
                        <tr>
                            <td><label style="float: right">类别:</label></td>
                            <td>
                                <select id="category" name="category" style="width: 200px">
                                    <option value="奥数">奥数</option>
                                    <option value="物理">物理</option>
                                    <option value="英语">英语</option>
                                    <option value="考研">考研</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><label style="float: right">班级数:</label></td>
                            <td>
                                <input type="number" id="classNum" name="classNum" min="1" max="3"
                                       class="input-sm form-control" style="width: 200px">
                            </td>
                        </tr>
                        <tr>
                            <td><label style="float: right">开始时间:</label></td>
                            <td>
                                <input type="date" id="start" name="start" class="input-sm form-control" style="width: 200px">
                            </td>
                        </tr>
                        <tr>
                            <td><label style="float: right">课时数:</label></td>
                            <td>
                                <input type="number" id="period" name="period" min="5" max="20"
                                       class="input-sm form-control" style="width: 200px">
                            </td>
                        </tr>
                        <tr>
                            <td><label style="float: right">上课时间:</label></td>
                            <td>
                                <select id="time" name="time" style="width: 200px">
                                    <option value="全天">全天</option>
                                    <option value="工作日">工作日</option>
                                    <option value="周末">周末</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><label style="float: right">人数:</label></td>
                            <td>
                                <input type="number" id="number" name="number" min="30" max="120"
                                       class="input-sm form-control" style="width: 200px">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <button type="submit" onclick="return submitPlan()" class="btn btn-primary col-sm-offset-9">发布课程</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    function check(id) {
        document.getElementById('cid').value = id;
        document.getElementById('courseForm').submit();
    }

    function submitPlan() {
        var classNum = document.getElementById('classNum').value;
        var start = document.getElementById('start').value;
        var period = document.getElementById('period').value;
        var number = document.getElementById('number').value;
        if(classNum==""){
            alert("班级数不能为空!");
            return false;
        }
        if(start==""){
            alert("开始时间不能为空!");
            return false;
        }
        if(period==""){
            alert("课时数不能为空!");
            return false;
        }
        if(number==""){
            alert("人数不能为空!");
            return false;
        }
        return true;
    }
</script>

</body>
</html>
