package com.shop.servlet;

import com.shop.bean.AccountBean;
import com.shop.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置中文编码
        request.setCharacterEncoding("utf-8");
        String u_name = request.getParameter("u_name");
        String u_login_name = request.getParameter("u_login_name");
        String u_pwd = request.getParameter("u_pwd");
        String mobile = request.getParameter("mobile");
        String email = request.getParameter("email");
        String u_pwd_again = request.getParameter("u_pwd_again");
        if(u_pwd.equals(u_pwd_again)){
            //两次密码相同
            AccountService accountService = new AccountService();
            //注册，调用reg方法
            //返回accountbean类型对象
            AccountBean accountBean = accountService.register(u_name, u_login_name, u_pwd, mobile, email);
            //将accountBean对象放入session域中
            HttpSession session = request.getSession();
            session.setAttribute("accountBean", accountBean);
            //重定向到choose界面
            response.sendRedirect("/choose.jsp");
        }else {
            //两次密码不同
            request.setAttribute("reg_msg", "两次密码输入不一致，请重新输入");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
