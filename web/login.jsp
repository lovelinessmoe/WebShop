<%--
  Created by IntelliJ IDEA.
  User: loveliness
  Date: 2020/11/19
  Time: 7:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <link rel="stylesheet" href="css/Login.css">
</head>
<body style="background-image: url('img/logo.png');background-repeat: no-repeat">

<div style="width: 50% ; height: 500px; margin: 0 auto;text-align: center ">
    <form action="/LoginServlet" method="post" id="login_form">
        <span class="login_style">用户名</span>
        <span id="u_error_msg"></span><br>

        <input class="login_input" type="text" name="u_login_name" id="u_login_name"><br>
        <span class="login_style">密码</span>
        <span id="p_error_msg"></span><br>

        <input class="login_input" type="password" name="u_pwd" id="u_pwd"><br>

        <div style="height: 100px;width: 300px;margin: 0 auto">
            <input class="login_button" type="button" value="登录"
                   onclick="login()">
            <input class="login_button" type="reset" value="重置">

        </div>
        <%--<input style="clear: both">--%>

        <span class="err_msg" id="log_msg"></span><br>
        <span class="err_msg">${sessionScope.modify_msg}</span><br>
    </form>
</div>
</body>
<script src="js/jquery-3.4.1.min.js"></script>
<script src="js/common.js"></script>
<script type="text/javascript">
    function login() {
        if ($("#u_login_name").val() == '') {
            // console.log($("#u_login_name").val())
            // $("#u_error_msg").html('<h1>"用户名不能为空！"</h1>');
            $("#u_error_msg").html("用户名不能为空！");
            $("#u_login_name").focus();
            return false;
        }
        else if ($("#u_pwd").val() == '') {
            $("#p_error_msg").html("密码不能为空！");
            $("#u_pwd").focus();
            return false;
        } else {
            fpost('login_form', postCallBack);
        }
    }

    function postCallBack(data) {
        if (data.status == "err") {
            $("#log_msg").html(data.msg);
        } else {
            //跳转到choose.jsp
            window.location.href = data.url;
        }
    }
</script>
</html>
