<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entity.Student" %>
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

    <title>StudentInfo</title>

    <script src="../js/jquery-3.1.1.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/echarts.simple.min.js"></script>
    <script src="../js/echarts.js"></script>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/training.css" rel="stylesheet">
</head>
<body background="../img/background.jpg" style="background-size: cover">
<%
    Student student = (Student) request.getSession().getAttribute("student");
    double money = (double) request.getSession().getAttribute("money");
    int ordersNum = (int) request.getSession().getAttribute("ordersNum");
    double consume = (double) request.getSession().getAttribute("consume");
    int[] datas = (int[]) request.getSession().getAttribute("datas");
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
        <li class="active"><a href="stuInfo_studentInfo.action"><h5 style="padding-left: 15px">个人信息</h5></a> </li>
        <li><a href="stuOrder_getOrders.action"><h5 style="padding-left: 15px">订单状态</h5></a> </li>
    </ul>
</div>

<div style="position:absolute;top:50px;width: 800px;font-size: 16px">
    <div class="col-md-8" style="position: absolute;margin-left:180px;width:800px;">
        <fieldset style="margin-top: 20px;margin-left: 50px">
            <legend>基本信息</legend>
            <a href="#" onclick="EditInfo()" style="color: #1ab7ea; float: right; margin-right: 60px;"><b>修改资料</b></a>
            <div id="pastInfo" >
                <label style="margin: 10px">邮箱号:</label>
                <label><%=student.getMail()%></label>
                <br>
                <label style="margin: 10px">会员名:</label>
                <label><%=student.getName()%></label>
                <br>
                <label style="margin: 10px">支付号:</label>
                <label><%=student.getAccountId()%></label>
                <br>
            </div>
            <form action="stuInfo_modifyInfo" method="post" id="changeInfo" onsubmit="return modifyInfo()" style="display: none;">
                <label style="margin: 10px">邮箱号:</label>
                <label><%=student.getMail()%></label>
                <br>
                <label style="margin: 10px">会员名:</label>
                <input type="text" id="name" name="name" value="<%=student.getName()%>"
                       style="position: absolute; left: 155px;margin-top: 6px; width: 200px;">
                <br>
                <label style="margin: 10px">支付号:</label>
                <label><%=student.getAccountId()%></label>
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
                        <button type="submit" id="submit_btn" class="btn btn-default btn-primary btn-sm">保存修改</button>
                    </div>
                    <div class="col-md-2">
                        <button type="button" onclick="cancelEdit()" class="btn btn-default btn-primary btn-sm">取消修改</button>
                    </div>
                </div>
            </form>
        </fieldset>

        <fieldset style="margin-top: 30px;margin-left: 50px;">
            <legend>会员信息</legend>
            <div class="row">
                <div class="col-xs-8">
                    <label style="margin-left: 10px">会员等级:</label>
                    <label>
                        <c:choose>
                            <c:when test="${sessionScope.student.viPrank=='N'}">停用</c:when>
                            <c:otherwise><%=student.getViPrank()%></c:otherwise>
                        </c:choose>
                    </label>
                </div>
                <div class="col-xs-3">
                    <a href="stuInfo_cancelVip.action" style="color: #1ab7ea">取消会员</a>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-xs-8">
                    <label style="margin-left: 10px">会员积分:</label>
                    <label><%=student.getCredit()%></label>
                </div>
                <div class="col-xs-3">
                    <a data-toggle="modal" data-target="#convert" data-dismiss="modal" class="text-center"
                       style="color: #1ab7ea">兑换积分</a>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-xs-8">
                    <label style="margin-left: 10px">账号余额:</label>
                    <label><%=money%></label>
                </div>
                <div class="col-xs-3">
                    <a data-toggle="modal" data-target="#charge" data-dismiss="modal" class="text-center"
                       style="color: #1ab7ea">账号充值</a>
                </div>
            </div>
        </fieldset>

        <fieldset style="margin-top: 30px;margin-left: 50px;">
            <legend>统计信息</legend>
            <div class="row">
                <div class="col-md-4 card">
                    <div class="card-block">
                        <div class="card-left">
                            <h3><%=ordersNum%></h3>
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
                            <h3><%=consume%></h3>
                            <h4>Consume</h4>
                            <p>Hope</p>
                        </div>
                        <div class="card-right" style="margin-left: 35%">
                            <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true" style="top:5px;right: 1px"></span>
                        </div>
                    </div>
                </div>
            </div>

            <div id="main" style="width: 400px;height:400px;"></div>
        </fieldset>
    </div>

</div>

<div class="modal fade" role="dialog" id="convert" style="margin-top: 10%">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" aria-label="Close" data-dismiss="modal" style="color: lime">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" style="align-items: center;padding-left: 3%;color: #55acee">兑换积分</h4>
            </div>
            <div class="modal-body">
                <form action="stuInfo_convertCredit" method="post">
                    <label>当前积分:<%=student.getCredit()%>></label>
                    <br>
                    <label class="input-group">
                        兑换积分:
                        <input type="number" name="point" id="point" min="0" class="form-control" style="margin-top: 5px">
                    </label>
                    <div class="row">
                        <div class="col-xs-8"></div>
                        <div class="col-xs-4">
                            <button type="submit" onsubmit="return convert()" class="btn btn-primary btn-block btn-flat">兑换</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" role="dialog" id="charge" style="margin-top: 10%">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" aria-label="Close" data-dismiss="modal" style="color: lime">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" style="align-items: center;padding-left: 3%;color: #55acee">账号充值</h4>
            </div>
            <div class="modal-body">
                <form action="stuInfo_charge" method="post">
                    <label>账号余额:<%=money%>></label>
                    <br>
                    <label class="input-group">
                        充值金额:
                        <input name="money" id="money" type="number" step="0.01" min="0" class="form-control" style="margin-top: 5px">
                    </label>
                    <div class="row">
                        <div class="col-xs-8"></div>
                        <div class="col-xs-4">
                            <button type="submit" class="btn btn-primary btn-block btn-flat">充值</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

</script>

<script>
    function EditInfo() {
        document.getElementById("pastInfo").style.display = "none";
        document.getElementById("changeInfo").style.display = "block";
    }

    function cancelEdit() {
        document.getElementById("pastInfo").style.display = "block";
        document.getElementById("changeInfo").style.display = "none";
    }

    function modifyInfo() {
        var pastPsw = document.getElementById("pastPsw").value;
        var neoPsw = document.getElementById("neoPsw").value;
        var confirm = document.getElementById("confirm").value;
        if(pastPsw==""){
            return true;
        }
        if(pastPsw!=<%=student.getPassword()%>){
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
        return true;
    }

    function convert() {
        var point = document.getElementById("point").value;
        var credit = <%=student.getCredit()%>;
        if(point==""){
            alert("不能为空!");
            return false;
        }
        if(Number(point)>Number(credit)){
            alert("兑换的积分超出余额!");
            return false;
        }
        return true;
    }

    var echarts = echarts.init(document.getElementById('main'));
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        series:[
            {
                name:'订单状态',
                type:'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '30',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data:[
                    {value:<%=datas[1]%>, name:'已执行'},
                    {value:<%=datas[2]%>, name:'未执行'},
                    {value:<%=datas[0]%>, name:'退订'}
                ]
            }
        ]
    };
    echarts.setOption(option);

</script>
</body>
</html>
