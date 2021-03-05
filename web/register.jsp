<%--
  Created by IntelliJ IDEA.
  User: loveliness
  Date: 2020/11/22
  Time: 5:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register</title>
</head>
<body>
<form action="/RegisterServlet" method="post">
    登录名:<input type="text" name="u_login_name"><br>
    昵称:<input type="text" name="u_name"><br>
    邮箱:<input type="text" name="email"><br>
    手机号:<input type="text" name="mobile"><br>
    密码:<input type="password" name="u_pwd"><br>
    请重新输入一遍密码:<input type="password" name="u_pwd_again"><br>
    <input type="submit" value="注册"><br>
    <span style="font-size: 24px;color: darkred ">${requestScope.reg_msg}</span>

</form>

</body>
</html>
