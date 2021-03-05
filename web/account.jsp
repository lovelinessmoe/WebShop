<%--
  Created by IntelliJ IDEA.
  User: loveliness
  Date: 2020/11/22
  Time: 7:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account</title>
</head>
<body>
<div style="text-align:center;margin: 0 auto;">
    <table border="0">
        <thead>
        <tr>
            <th>ID</th>
            <th>昵称</th>
            <th>登录名</th>
            <th>手机</th>
            <th>邮箱</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th>${sessionScope.accountBean.u_id}</th>
            <th>${sessionScope.accountBean.u_name}</th>
            <th>${sessionScope.accountBean.u_login_name}</th>
            <th>${sessionScope.accountBean.mobile}</th>
            <th>${sessionScope.accountBean.email}</th>
        </tr>
        </tbody>
    </table>
</div>
<div>
    <h2 style="text-align: center">修改信息</h2>
    <h3 style="text-align: center">填写你要修改的信息，不修改的可以不填</h3>
    //servlet分流
    <form action="/AccountServlet?type=login" method="post">
        昵称:<input type="text" name="u_name" placeholder="${sessionScope.accountBean.u_name} "><br>
        邮箱:<input type="text" name="email" placeholder="${sessionScope.accountBean.email}"><br>
        手机号:<input type="text" name="mobile" placeholder="${sessionScope.accountBean.mobile}"><br>
        密码:<input type="text" name="u_pwd" placeholder="就不告诉你"><br>
        请输入原来的密码:<input type="text" name="u_pwd_old" placeholder="整点老密码"><br>
        <input type="submit" value="确定">
        <input type="reset" value="清空"><br>
        <span style="font-size: 24px;color: darkred ">${sessionScope.pwd_error_msg}</span>
    </form>
</div>
</body>
</html>
