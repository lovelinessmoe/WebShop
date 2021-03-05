package com.shop.servlet;

import com.shop.bean.AccountBean;
import com.shop.bean.CartBean;
import com.shop.bean.PageBean;
import com.shop.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/GetCartByPageServlet")
public class GetCartByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        HttpSession session = request.getSession();
        AccountBean accountBean = (AccountBean) session.getAttribute("accountBean");

        String currentPage = request.getParameter("currentPage");//当前页码
        String rows = request.getParameter("rows");//每一页的条数

        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }

        if (rows == null || "".equals(rows)) {
            rows = "5";
        }

        //调用service查询
        CartService cartService = new CartService();
        System.out.println(accountBean.getU_id());
        PageBean<CartBean> pageBean = cartService.getCartByPage(currentPage, rows, accountBean.getU_id());

        System.out.println(pageBean);

        //将pageBean存入request
        request.setAttribute("pageBean", pageBean);
        //转发到cart.jsp
        request.getRequestDispatcher("/cart.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
