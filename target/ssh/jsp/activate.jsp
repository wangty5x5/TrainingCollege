<%--
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Activate</title>

    <link href="../css/bootstrap.min.css" rel="stylesheet">
</head>
<body background="../img/background.jpg" style="background-size: cover">

<%
    String mail = (String) request.getSession().getAttribute("mail");
    String url = (String) request.getSession().getAttribute("url");
%>

<div style="position:absolute;top: 25%;left: 25%;background-color: rgba(151,160,179,0.34);height: 360px;width: 600px" align="center">
    <h1 style="color: #25ff2f"><span class="glyphicon glyphicon-ok-circle"></span> 注册成功!</h1>
    <br>
    <h3>请点击以下邮箱链接前去激活！</h3>
    <br>
    <h4>
        <a href="<%=url%>"><%=mail%></a>
    </h4>
</div>


</body>
</html>
