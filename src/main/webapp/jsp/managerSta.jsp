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

    <title>ManagerSta</title>

    <script src="../js/jquery-3.1.1.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/echarts.simple.min.js"></script>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/training.css" rel="stylesheet">
</head>
<body background="../img/background.jpg" style="background-size: cover">
<%
    Manager manager = (Manager) request.getSession().getAttribute("manager");
    int users = (int) request.getSession().getAttribute("users");
    double profit = (double) request.getSession().getAttribute("profit");
    int[] data = (int[]) request.getSession().getAttribute("data");
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
        <li class="active"><a href="managerSta.action"><h5 style="padding-left: 15px">统计信息</h5></a> </li>
        <li><a href="approval_approval.action"><h5 style="padding-left: 15px">审批管理</h5></a> </li>
        <li><a href="managerOrder.action"><h5 style="padding-left: 15px">订单管理</h5></a> </li>
    </ul>
</div>

<div style="position:absolute;top:50px;width: 800px;font-size: 16px">
    <div class="col-md-8" style="position: absolute;margin-left:180px;width:800px;">
        <fieldset style="margin-top: 30px;margin-left: 50px;">
            <legend>统计信息</legend>
            <div class="row">
                <div class="col-md-4 card">
                    <div class="card-block">
                        <div class="card-left">
                            <h3><%=users%></h3>
                            <h4>Users</h4>
                            <p>Hope</p>
                        </div>
                        <div class="card-right" style="margin-left: 35%">
                            <span class="glyphicon glyphicon-user" aria-hidden="true" style="top:5px;right: 1px"></span>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 col-md-offset-2 card">
                    <div class="card-block">
                        <div class="card-left">
                            <h3><%=profit%></h3>
                            <h4>Profit</h4>
                            <p>Hope</p>
                        </div>
                        <div class="card-right" style="margin-left: 35%">
                            <span class="glyphicon glyphicon-euro" aria-hidden="true" style="top:5px;right: 1px"></span>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>

        <fieldset style="margin-top: 30px;margin-left: 50px;">
            <legend>机构统计</legend>
            <table class="table dataTable" id="table-local">
                <thead style="background-color: rgba(190, 188, 198, 0.67)">
                <tr>
                    <th>机构编号</th>
                    <th>机构名称</th>
                    <th>订单数量</th>
                    <th>获得收益</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${sessionScope.instSta}" var="sta">
                    <tr>
                        <td>${sta.institutionId}</td>
                        <td>${sta.institutionName}</td>
                        <td>${sta.orders}</td>
                        <td>${sta.income}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </fieldset>

        <div id="main" style="width: 400px;height:400px;"></div>
    </div>
</div>
<script>
    var echarts = echarts.init(document.getElementById('main'));
    var option = {
        color: ['#3398DB'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {
                type : 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : ['<2000', '2000~5000', '5000~10000', '>10000'],
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'直接访问',
                type:'bar',
                barWidth: '60%',
                data:[<%=data[0]%>, <%=data[1]%>, <%=data[2]%>, <%=data[3]%>]
            }
        ]
    };
    echarts.setOption(option);
</script>
</body>

</html>
