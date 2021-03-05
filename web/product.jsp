<%--
  Created by IntelliJ IDEA.
  User: loveliness
  Date: 2020/11/22
  Time: 7:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <script src="js/jquery-3.4.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/Product.css">
    <title>商品管理</title>
</head>

<body>
<div style="width: 100%;margin: 0 auto;text-align: center">

    <table border="1" class="table table-bordered table-hover">
        <tr>
            <%--<th><input type="checkbox"></th>--%>
            <th>商品ID</th>
            <th>商品名字</th>
            <th>数量</th>
            <th>单价</th>
            <th>销售数量</th>
            <th>操作</th>
        </tr>

        <c:forEach items="${sessionScope.productList}" var="product">
            <tr>
                    <%--<td></td>--%>
                <td>${product.p_id}</td>
                <td>${product.p_name}</td>
                <td>${product.p_num}</td>
                <td>${product.p_price}</td>
                <td>${product.sell_num}</td>
                <td>
                        <%--按钮组--%>
                    <div class="btn-group" role="group" aria-label="...">
                        <button type="button" class="btn btn-default" onclick="modifyName(${product.p_id})">修改名字
                        </button>
                        <button type="button" class="btn btn-default" onclick="modifyNum(${product.p_id})">修改数量
                        </button>
                        <button type="button" class="btn btn-default" onclick="modifyPrice(${product.p_id})">修改价钱
                        </button>
                        <button type="button" class="btn btn-default" onclick="modifySellnum(${product.p_id})">修改售出量
                        </button>
                        <button type="button" class="btn btn-default" onclick="deletePrice(${product.p_id})">删除
                        </button>
                    </div>
                </td>
            </tr>
        </c:forEach>

        <form action="/ProductServlet?type=add" method="post">
            <tr>
                <td><span style="font-size: 30px">+</span></td>
                <td><input type="text" name="p_name"></td>
                <td><input type="text" name="p_num"></td>
                <td><input type="text" name="p_price"></td>
                <td><input type="text" name="sell_num"></td>
                <td>
                    <div class="btn-group" role="group" aria-label="...">
                        <input value="添加" type="submit" class="btn btn-default">
                    </div>
                </td>
            </tr>
        </form>
    </table>

</div>
</body>

<script>
    function deletePrice(data) {
        if(confirm("确定要删除吗？"))
            $.ajax({
                url: "/ProductServlet?type=delete",
                async: true,
                type: "POST",
                data: {
                    p_id: data
                },
                success: function () {
                    alert("删除成功");
                    $.post("/ProductServlet?type=find");
                    // 删除成功后刷新页面
                    //不好使(数据库未更改完就刷新导致)
                    window.location.reload();
                },
                error: function () {
                    alert("删除失败");
                },
                dataType: "text"
            });
    }

    function modifyName(data) {
        var name = prompt("请输入修改为的名字：");
        $.ajax({
            url: "/ProductServlet?type=modifyName",
            async: true,
            type: "POST",
            data: {
                p_id: data,
                name: name
            },
            success: function () {
                alert("修改成功");
                $.post("/ProductServlet?type=find");
                // 删除成功后刷新页面
                //不好使
                window.location.reload();
            },
            error: function () {
                alert("修改失败");
            },
            dataType: "text"
        });
    }

    function modifyNum(data) {
        var num = prompt("请输入修改后的数量：");
        $.ajax({
            url: "/ProductServlet?type=modifyNum",
            async: true,
            type: "POST",
            data: {
                p_id: data,
                num: num
            },
            success: function () {
                alert("修改成功");
                $.post("/ProductServlet?type=find");
                // 删除成功后刷新页面
                //不好使
                window.location.reload();
            },
            error: function () {
                alert("修改失败");
            },
            dataType: "text"
        });
    }

    function modifyPrice(data) {
        var price = prompt("请输入修改后的价格：");
        $.ajax({
            url: "/ProductServlet?type=modifyPrice",
            async: true,
            type: "POST",
            data: {
                p_id: data,
                price: price
            },
            success: function () {
                alert("修改成功");
                $.post("/ProductServlet?type=find");
                // 删除成功后刷新页面
                //不好使
                window.location.reload();
            },
            error: function () {
                alert("修改失败");
            },
            dataType: "text"
        });
    }

    function modifySellnum(data) {
        var sellnum = prompt("请输入修改后的数字：");
        $.ajax({
            url: "/ProductServlet?type=modifySellnum",
            async: true,
            type: "POST",
            data: {
                p_id: data,
                sellnum: sellnum
            },
            success: function () {
                alert("修改成功");
                $.post("/ProductServlet?type=find");
                // 删除成功后刷新页面
                //不好使
                window.location.reload();
            },
            error: function () {
                alert("修改失败");
            },
            dataType: "text"
        });
    }

    /*
        function addPrice() {
            $.ajax({
                url: "/ProductServlet?type=add",
                async: true,
                type: "POST",
                data: {
                    p_id: $(""),
                    sellnum: sellnum
                },
                success: function () {
                    alert("修改成功");
                    $.post("/ProductServlet?type=find");
                    // 删除成功后刷新页面
                    //不好使
                    window.location.reload();
                },
                error: function () {
                    alert("修改失败");
                },
                dataType: "text"
            });
        }
    */
</script>
</html>
