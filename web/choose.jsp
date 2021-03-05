<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: loveliness
  Date: 2020/11/22
  Time: 5:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>choose</title>
</head>
<style>
    a {
        text-decoration: none;
    }

    a:hover {
        color: cornflowerblue;
    }

    a:link {
        color: rgb(23, 253, 23);
    }

    a:active {
        color: darkred;
    }

    a:visited {
        color: blueviolet;
    }

    body {
        background-repeat: no-repeat;
        background-image: url(http://api.mtyqx.cn/api/random.php);
        background-size: 100% auto;
        background-position: center;

    }

    .choose {
        width: 50%;
        height: 300px;
        float: left;
    }

    .choose span {
        font-size: 30px;
        text-align: center;
        line-height: 300px;
        background-color: aquamarine;
        border-radius: 5px;
    }
</style>
<body>


<div style="margin: 0 auto;text-align: center">
    <h1 style="background-color: deeppink">你好${sessionScope.accountBean.u_name}你想要干什么呢？</h1>
    <%
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date_str = simpleDateFormat.format(date);
    %>
    <h2 style="background-color: darksalmon">
        <%
            out.print("现在的时间是" + date_str);
        %>
    </h2>
    <div class="choose">
        <span><a href="/account.jsp">账户管理</a></span>
    </div>
    <div class="choose">
        <span><a href="/GetCartByPageServlet">查看购物车</a></span>
    </div>
    <div style="clear: both"></div>
    <br>
    <div class="choose">
        <span><a href="/order.jsp">查看订单</a></span>
    </div>
    <c:if test="${sessionScope.accountBean.admin == 1}">
        <div class="choose">
            <span><a href="/ProductServlet?type=find">商品管理</a></span>
        </div>
    </c:if>
    <c:if test="${sessionScope.accountBean.admin != 1}">
        <div class="choose">
            <span><a href="#">正在建设中</a></span>
        </div>
    </c:if>
    <div style="clear: both"></div>

</div>


</body>
</html>
