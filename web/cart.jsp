<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>购物车</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <script src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

<div style="width: 80%;margin: 0 auto;text-align: center">
    <form id="cart" action="/GetCartByPageServlet" method="post">
        <table border="1" class="table table-bordered table-hover">
            <tr>
                <th><input type="checkbox"></th>
                <th>购物车ID</th>
                <th>货物ID</th>
                <th>货物名字</th>
                <th>数量</th>
                <th>总价钱</th>
                <th>创建时间</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${requestScope.pageBean.list}" var="cart" varStatus="s">
                <tr>
                    <td></td>
                    <td>${cart.c_id}</td>
                    <td>${cart.p_id}</td>
                    <td>${cart.p_name}</td>
                    <td>${cart.num}</td>
                    <td>${cart.total_money}</td>
                    <td>${cart.create_time_str}</td>
                    <td>    <%--按钮组--%>
                        <div class="btn-group" role="group" aria-label="...">
                            <button type="button" class="btn btn-default" onclick="modifyNum(${cart.c_id})">修改数量
                            </button>
                            <button type="button" class="btn btn-default" onclick="deleteCart(${cart.c_id})">删除
                            </button>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>
    <from>
        <div>
            <%--这是个分页控制条--%>
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <c:if test="${requestScope.pageBean.currentPage == 1}">
                        <li class="disabled"></li>
                    </c:if>
                    <c:if test="${requestScope.pageBean.currentPage != 1}">
                        <li></li>
                    </c:if>
                    <li>
                        <a href="${pageContext.request.contextPath}/GetCartByPageServlet?currentPage=${requestScope.pageBean.currentPage-1}&rows=5"
                           aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>

                    <%--循环加载页码数--%>
                    <c:forEach begin="1" end="${requestScope.pageBean.totalPage}" var="i">
                        <c:if test="${requestScope.pageBean.currentPage == i}">
                            <li class="active"><a
                                    href="${pageContext.request.contextPath}/GetCartByPageServlet?currentPage=${i}&rows=5">${i}</a>
                            </li>
                        </c:if>
                        <c:if test="${requestScope.pageBean.currentPage != i}">
                            <li>
                                <a href="${pageContext.request.contextPath}/GetCartByPageServlet?currentPage=${i}&rows=5">${i}</a>
                            </li>
                        </c:if>
                    </c:forEach>
                    <li>
                        <a href="${pageContext.request.contextPath}/GetCartByPageServlet?currentPage=${requestScope.pageBean.currentPage+1}&rows=5"
                           aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                    <span style="font-size: 24px">
                        总共${requestScope.pageBean.totalCount}条记录，共${requestScope.pageBean.totalPage}页
                    </span>
                </ul>
            </nav>
            <%------------------%>
        </div>
    </from>
</div>
</body>
<script type="text/javascript">
    function modifyNum(c_id) {
        var num = prompt("请输入修改后的数量：");
        $.ajax({
            url: "/CartServlet?type=modifyNum",
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
</script>
</html>
