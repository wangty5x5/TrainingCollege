<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Training College</title>

    <link href="../css/bootstrap.min.css" rel="stylesheet">
</head>
<body background="../img/background.jpg" style="background-size: cover">
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
                    <li class="active"><a href="#">首页</a> </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">登陆/注册</a> </li>
                    <li><a href="#">机构注册</a> </li>
                </ul>
            </div>
        </div>
    </nav>
</div>

<div class="col-md-4 row" style="margin-top: 15%;margin-left: 10%;background-color: rgba(0,0,0,0.6)">
    <ul id="tab" class="nav nav-tabs">
        <li class="active">
            <a href="#student" data-toggle="tab">会员登陆</a>
        </li>
        <li><a href="#institution" data-toggle="tab">机构登陆</a> </li>
        <li><a href="#manager" data-toggle="tab">经理登陆</a> </li>
    </ul>
    <div id="tabContent" class="tab-content">
        <div class="tab-pane fade in active" id="student">
            <h4 class="modal-title" style="align-items: center;padding-left: 3%;padding-top: 3%;color: #55acee">会员登陆</h4>
            <div class="modal-body">
                <form action="stuLogin" method="post">
                    <font color="red"><s:actionerror></s:actionerror></font>
                    <div class="form-group">
                        <input type="email" id="email" name="email" class="form-control" placeholder="请输入邮箱号">
                    </div>
                    <div class="form-group">
                        <input type="password" id="stuPwd" name="stuPwd" class="form-control" placeholder="请输入密码">
                    </div>
                    <div class="row">
                        <div class="col-xs-8">
                            <a data-toggle="modal" data-target="#userRegister" data-dismiss="modal" class="text-center">注册会员</a>
                        </div>
                        <div class="col-xs-4">
                            <button type="submit" class="btn btn-primary btn-block btn-flat">登陆</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="tab-pane fade" id="institution">
            <h4 class="modal-title" style="align-items: center;padding-left: 3%;padding-top: 3%;color: #55acee">机构登陆</h4>
            <div class="modal-body">
                <form action="instLogin" method="post">
                    <div class="form-group">
                        <input type="text" id="instId" name="instId" class="form-control" placeholder="请输入机构编号">
                    </div>
                    <div class="form-group">
                        <input type="password" id="instPwd" name="instPwd" class="form-control" placeholder="请输入密码">
                    </div>
                    <div class="row">
                        <div class="col-xs-8">
                            <a data-toggle="modal" data-target="#instRegister" data-dismiss="modal" class="text-center" onclick="set_city()">注册机构</a>
                        </div>
                        <div class="col-xs-4">
                            <button type="submit" class="btn btn-primary btn-block btn-flat">登陆</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="tab-pane fade" id="manager">
            <h4 class="modal-title" style="align-items: center;padding-left: 3%;padding-top: 3%;color: #55acee">经理登陆</h4>
            <div class="modal-body">
                <form action="managerLogin" method="post">
                    <div class="form-group">
                        <input type="text" id="managerId" name="managerId" class="form-control" placeholder="请输入经理编号">
                    </div>
                    <div class="form-group">
                        <input type="password" id="managerPwd" name="managerPwd" class="form-control" placeholder="请输入密码">
                    </div>
                    <div class="row">
                        <div class="col-xs-8">
                        </div>
                        <div class="col-xs-4">
                            <button type="submit" class="btn btn-primary btn-block btn-flat">登陆</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" role="dialog" id="userRegister" style="margin-top: 10%">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="background-color: rgba(0,0,0,1)">
            <div class="modal-header">
                <button type="button" class="close" aria-label="Close" data-dismiss="modal" style="color: lime">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" style="align-items: center;padding-left: 3%;color: #55acee">注册会员</h4>
            </div>
            <div class="modal-body">
                <form action="register" method="post">
                    <div class="form-group">
                        <input type="email" id="mail" name="mail" class="form-control" placeholder="请输入邮箱号">
                    </div>
                    <div class="form-group">
                        <input type="password" id="password" name="password" class="form-control" placeholder="请输入密码">
                    </div>
                    <div class="form-group">
                        <input type="password" id="confirm" name="confirm" class="form-control" placeholder="请重复输入密码">
                    </div>
                    <div class="form-group">
                        <input type="text" id="name" name="name" class="form-control" placeholder="请输入用户名">
                    </div>
                    <div class="form-group">
                        <input type="text" id="accountId" name="accountId" class="form-control" placeholder="请输入支付卡号">
                    </div>
                    <div class="row">
                        <div class="col-xs-8"></div>
                        <div class="col-xs-4">
                            <button type="submit" onclick="return userRegister()" class="btn btn-primary btn-block btn-flat">注册</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" role="dialog" id="instRegister" style="margin-top: 10%">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="background-color: rgba(0,0,0,1)">
            <div class="modal-header">
                <button type="button" class="close" aria-label="Close" data-dismiss="modal" style="color: lime">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" style="align-items: center;padding-left: 3%;color: #55acee">注册机构</h4>
            </div>
            <div class="modal-body">
                <form action="instRegister" method="post">
                    <div class="form-group">
                        <input type="text" id="instName" name="instName" class="form-control" placeholder="请输入机构名称">
                    </div>
                    <div class="form-group">
                        <input type="password" id="instPassword" name="instPassword" class="form-control" placeholder="请输入密码">
                    </div>
                    <div class="form-group">
                        <input type="password" id="instConfirm" name="instConfirm" class="form-control" placeholder="请重复输入密码">
                    </div>
                    <div class="form-group">
                        <select id="select_one" name="province" class="form-control" onchange="get_city()">
                            <option value="">请选择省份</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <select id="select_two" name="city" class="form-control">
                            <option value="">请选择城市</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <input type="text" id="address" name="address" class="form-control" placeholder="请输入地址">
                    </div>
                    <div class="form-group">
                        <input type="number" id="teachers" name="teachers" min="1" class="form-control" placeholder="请输入师资数">
                    </div>
                    <input type="hidden" name="local" value="<%=request.getContextPath()%>">
                    <div class="row">
                        <div class="col-xs-8"></div>
                        <div class="col-xs-4">
                            <button type="submit" onclick="return instRegister()" class="btn btn-primary btn-block btn-flat">注册</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    function userRegister() {
        var mail = document.getElementById('mail').value;
        var password = document.getElementById('password').value;
        var confirm = document.getElementById('confirm').value;
        var name = document.getElementById('name').value;
        var accountId = document.getElementById('accountId').value;
        if(mail==""){
            alert("邮箱不能为空!");
            return false;
        }
        if(password==""){
            alert("密码不能为空!");
            return false;
        }
        if(password!=confirm){
            alert("两次输入的密码不一致!");
            return false;
        }
        if(name==""){
            alert("名字不能为空!");
            return false;
        }
        if(accountId==""){
            alert("支付号不能为空!");
            return false;
        }
        return true;
    }

    function instRegister() {
        var instName = document.getElementById('instName').value;
        var instPassword = document.getElementById('instPassword').value;
        var instConfirm = document.getElementById('instConfirm').value;
        var province = document.getElementById('select_one').value;
        var city = document.getElementById('select_two').value;
        var address = document.getElementById('address').value;
        var teachers = document.getElementById('teachers').value;
        if(instName==""){
            alert("名字不能为空!");
            return false;
        }
        if(instPassword==""){
            alert("密码不能为空!");
            return false;
        }
        if(instPassword!=instConfirm){
            alert("两次输入的密码不一致!");
            return false;
        }
        if(province==""){
            alert("请选择省份!");
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
        return true;
    }
</script>

<script src="../js/jquery-3.1.1.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/city.js"></script>
</body>
</html>
