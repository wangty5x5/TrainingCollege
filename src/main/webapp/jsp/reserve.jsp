<%@ page import="entity.Student" %>
<%@ page import="entity.Course" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Reserve</title>

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
    Course course = (Course) request.getSession().getAttribute("course");
    String error = (String) request.getSession().getAttribute("errorMessage");
    if(error!=""){
%>
<script>
    alert("<%=error%>")
</script>
<%
    }
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
    <legend>课程编号:<%=course.getCourseId()%></legend>
    <div class="col-md-offset-10">
        <button type="button" class="btn btn-default btn-success" onclick="createOrder()">
            <span class="glyphicon glyphicon-file" aria-hidden="true"></span>生成订单
        </button>
    </div>

    <form method="post" id="orderForm" style="display: none">
        <div class="row">
            <div class="col-md-4">
                <input type="radio" name="max" value="3" checked="checked" onclick="setMax(this.value)">选班
                <input type="radio" name="max" value="9" onclick="setMax(this.value)">不选班
            </div>
            <div class="col-md-1">
                <button type="button" class="btn btn-success btn-xs" onclick="addStu()">
                    <span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>添加学员
                </button>
            </div>
            <div class="col-md-1">
                <button type="button" class="btn btn-success btn-xs" onclick="deleteStu()">
                    <span class="glyphicon glyphicon-minus-sign" aria-hidden="true"></span>删除学员
                </button>
            </div>
        </div>
        <br>
        <div id="inputWrap">
            <div>
                <label>学员1:</label>
                <input type="text" id="stu_1" name="stu_1">
                <label>选择班级:</label>
                <input type="number" min="1" max="3" id="cl_1" name="cl_1">
            </div>
        </div>
        <br>
        <input type="hidden" name="count" id="count">
        <button type="submit" class="btn btn-success btn-xs" onclick="submitOrder()">
            <span class="glyphicon glyphicon-upload"></span>提交订单
        </button>
        <button type="submit" class="btn btn-success btn-xs" onclick="saveOrder()">
            <span class="glyphicon glyphicon-save"></span>保存订单
        </button>
    </form>

    <br>
    <div class="row" style="padding-top: 10px">
        <div class="col-md-4">
            <label style="margin: 10px">机构编号: <%=course.getInstitutionId()%></label>
        </div>
        <div class="col-md-4">
            <label style="margin: 10px">机构名称: <%=course.getInstitutionName()%></label>
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col-md-4">
            <label style="margin: 10px">开始时间: <%=course.getStartDate()%></label>
        </div>
        <div class="col-md-4">
            <label style="margin: 10px">类别: <%=course.getCategory()%></label>
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col-md-4">
            <label style="margin: 10px">上课时间: <%=course.getClassTime()%></label>
        </div>
        <div class="col-md-4">
            <label style="margin: 10px">课时数: <%=course.getPeriod()%></label>
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col-md-4">
            <label style="margin: 10px">总人数: <%=course.getNumber()%></label>
        </div>
        <div class="col-md-4">
            <label style="margin: 10px">空余人数: <%=course.getEmptyNum()%></label>
        </div>
    </div>
    <br>

    <legend>班级信息</legend>
    <table class="table dataTable">
        <thead style="background-color: rgba(190, 188, 198, 0.67)">
        <tr>
            <th>班号</th>
            <th>教师名</th>
            <th>班级人数</th>
            <th>空余人数</th>
            <th>价格</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${sessionScope.classes}" var="cl">
            <tr>
                <td>${cl.classId%10}</td>
                <td>${cl.teacher}</td>
                <td>${cl.number}</td>
                <td>${cl.emptyNum}</td>
                <td>${cl.price}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</fieldset>


<script>
    function createOrder() {
        document.getElementById('orderForm').style.display = "block";
    }

    var maxNum = 3;
    var count = 1;

    function setMax(m) {
        maxNum = m;
        if(maxNum<count){
            for(count;count>m;count--){
                $("#inputWrap").find("div").eq(count-1).remove();
            }
        }
        if(maxNum==9){
            for(var i=1;i<=count;i++){
                var id = "cl_" + i;
                $("#"+id).attr("disabled", true);
            }
        }
        else {
            for(var i=1;i<=count;i++){
                var id = "cl_" + i;
                $("#"+id).attr("disabled", false);
            }
        }
    }

    function addStu() {
        if(count<maxNum){
            count++;
            if(maxNum==3){
                $("#inputWrap").append('<div><label>学员'+count+ ':</label> <input type="text" id="stu_'+count+ '" name="stu_'
                    +count+'"> <label>选择班级:</label> <input type="number" min="1" max="3" id="cl_'+count+'" name="cl_'
                    +count+'">');
            }
            else {
                $("#inputWrap").append('<div><label>学员'+count+ ':</label> <input type="text" id="stu_'+count+ '" name="stu_'
                    +count+'"> <label>选择班级:</label> <input type="number" min="1" max="3" id="cl_'+count+'" name="cl_'
                    +count+'" disabled="disabled">');
            }
        }

    }

    function deleteStu() {
        if(count>1){
            count--;
            $("#inputWrap").find("div").eq(count).remove();
        }
    }

    function submitOrder() {
        if($("#inputWrap").length > <%=course.getEmptyNum()%>){
            alert("订单人数超过空余人数!");
            return false;
        }
        document.getElementById('count').value = count;
        document.getElementById('orderForm').action = "reserve";
        document.getElementById('orderForm').submit();
    }

    function saveOrder() {
        if($("#inputWrap").length > <%=course.getEmptyNum()%>){
            alert("订单人数超过空余人数!");
            return false;
        }
        document.getElementById('count').value = count;
        document.getElementById('orderForm').action = "saveOrder";
        document.getElementById('orderForm').submit();
    }
</script>

</body>
</html>
