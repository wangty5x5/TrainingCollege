<%@ page import="entity.Institution" %>
<%--
  Created by IntelliJ IDEA.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>InstitutionInfo</title>

    <script src="../js/jquery-3.1.1.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/echarts.simple.min.js"></script>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/training.css" rel="stylesheet">
</head>
<body background="../img/background.jpg" style="background-size: cover">
<%
    Institution institution = (Institution) request.getSession().getAttribute("institution");
    int orderNum = (int) request.getSession().getAttribute("orderNum");
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
        <li class="active"><a href="instInfo.action"><h5 style="padding-left: 15px">机构信息</h5></a> </li>
        <li><a href="instCourse.action"><h5 style="padding-left: 15px">课程管理</h5></a> </li>
        <li><a href="instOrder.action"><h5 style="padding-left: 15px">查看订单</h5></a> </li>
    </ul>
</div>

<div style="position:absolute;top:50px;width: 800px;font-size: 16px">
    <div class="col-md-8" style="position: absolute;margin-left:180px;width:800px;">
        <fieldset style="margin-top: 20px;margin-left: 50px">
            <legend>基本信息</legend>
            <a href="#" onclick="EditInfo()" style="color: #1ab7ea; float: right; margin-right: 60px;"><b>修改资料</b></a>
            <div id="pastInfo">
                <label style="margin: 10px">机构编号:</label>
                <label><%=institution.getId()%></label>
                <br>
                <label style="margin: 10px">机构名称:</label>
                <label><%=institution.getName()%></label>
                <br>
                <label style="margin: 10px">所在城市:</label>
                <label><%=institution.getProvince()%><%=institution.getCity()%></label>
                <br>
                <label style="margin: 10px">详细地址:</label>
                <label><%=institution.getAddress()%></label>
                <br>
                <label style="margin: 10px">师资情况:</label>
                <label><%=institution.getTeachers()%></label>
                <br>
            </div>
            <form action="instModify" method="post" id="changeInfo" style="display: none;">
                <label style="margin: 10px">机构编号:</label>
                <label><%=institution.getId()%></label>
                <br>
                <label style="margin: 10px">机构名称:</label>
                <input type="text" id="name" name="name" value="<%=institution.getName()%>"
                       style="position: absolute; left: 155px;margin-top: 6px; width: 200px;">
                <br>
                <label style="margin: 10px">所在城市:</label>
                <select id="select_one" name="province" onchange="get_city()">
                    <option value="">请选择省份</option>
                </select>
                <select id="select_two" name="city">
                    <option value="">请选择城市</option>
                </select>
                <br>
                <label style="margin: 10px">详细地址:</label>
                <input type="text" id="address" name="address" value="<%=institution.getAddress()%>"
                       style="position: absolute; left: 155px;margin-top: 6px; width: 400px;">
                <br>
                <label style="margin: 10px">师资情况:</label>
                <input type="number" id="teachers" name="teachers" min="1" value="<%=institution.getTeachers()%>"
                       style="position: absolute; left: 155px;margin-top: 6px; width: 200px;">
                <br>
                <label style="margin: 10px">原密码:</label>
                <input type="password" id="pastPsw" name="pastPsw" placeholder="请输入原密码"
                       style="position: absolute; left: 75px;margin-top: 6px; width: 200px;">
                <br>
                <label style="margin: 10px">新密码:</label>
                <input type="password" id="neoPsw" name="neoPsw" placeholder="请输入新的密码"
                       style="position: absolute; left: 75px;margin-top: 6px; width: 200px;">
                <br>
                <label style="margin: 10px">确认:</label>
                <input type="password" id="confirm" name="confirm" placeholder="请重复输入密码"
                       style="position: absolute; left: 75px;margin-top: 6px; width: 200px;">
                <br>
                <div class="row">
                    <div class="col-md-2">
                        <button type="submit" onclick="return modifyInfo()" id="submit_btn" class="btn btn-default btn-primary btn-sm">保存修改</button>
                    </div>
                    <div class="col-md-2">
                        <button type="button" onclick="cancelEdit()" class="btn btn-default btn-primary btn-sm">取消修改</button>
                    </div>
                </div>
            </form>
        </fieldset>

        <fieldset style="margin-top: 30px;margin-left: 50px;">
            <legend>统计信息</legend>
            <div class="row">
                <div class="col-md-4 card">
                    <div class="card-block">
                        <div class="card-left">
                            <h3><%=orderNum%></h3>
                            <h4>Orders</h4>
                            <p>Hope</p>
                        </div>
                        <div class="card-right" style="margin-left: 35%">
                            <span class="glyphicon glyphicon-list-alt" aria-hidden="true" style="top:5px;right: 1px"></span>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 col-md-offset-2 card">
                    <div class="card-block">
                        <div class="card-left">
                            <h3><%=institution.getIncome()%></h3>
                            <h4>Income</h4>
                            <p>Hope</p>
                        </div>
                        <div class="card-right" style="margin-left: 35%">
                            <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true" style="top:5px;right: 1px"></span>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
    </div>
</div>

<script>
    function EditInfo() {
        document.getElementById("pastInfo").style.display = "none";
        document.getElementById("changeInfo").style.display = "block";
        set_city()
    }

    function cancelEdit() {
        document.getElementById("pastInfo").style.display = "block";
        document.getElementById("changeInfo").style.display = "none";
    }

    function modifyInfo() {
        var name = document.getElementById('name').value;
        var province = document.getElementById('select_one').value;
        var city = document.getElementById('select_two').value;
        var address = document.getElementById('address').value;
        var teachers = document.getElementById('teachers').value;
        var pastPsw = document.getElementById('pastPsw').value;
        var neoPsw = document.getElementById('neoPsw').value;
        var confirm = document.getElementById('confirm').value;
        if(name==""){
            alert("名字不能为空!");
            return false;
        }
        if(document.getElementById('select_two').options.length>1&&city==""){
            alert("请选择城市!");
            return false;
        }
        if(address==""){
            alert("地址不能为空!");
            return false;
        }
        if(teachers==""){
            alert("师资情况不能为空!");
            return false;
        }
        if(pastPsw!=""){
            if(pastPsw!=<%=institution.getPassword()%>){
                alert("输入密码不正确!");
                return false;
            }
            if(neoPsw==""){
                alert("新密码不能为空!");
                return false;
            }
            if(neoPsw!=confirm){
                alert("两次输入密码不一致!");
                return false;
            }
        }
        return true;
    }
</script>
<script src="../js/city.js"></script>
</body>
</html>
