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

@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置中文编码
        request.setCharacterEncoding("utf-8");
        //获取session域
        HttpSession session = request.getSession();
        String u_name = request.getParameter("u_name");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String u_pwd = request.getParameter("u_pwd");
        String u_pwd_old = request.getParameter("u_pwd_old");
        AccountBean accountBean = (AccountBean) session.getAttribute("accountBean");


        AccountService accountService = new AccountService();
        if (!u_name.equals("")) {
            accountService.modifyName(accountBean, u_name);
        }
        if (!email.equals("")) {
            accountService.modifyEmail(accountBean, email);
        }
        if (!mobile.equals("")) {
            accountService.modifyMobile(accountBean, mobile);
        }
        if (!u_pwd.equals("")) {
            int flag = accountService.modifyPwd(accountBean, u_pwd, u_pwd_old);
            if (flag == 1) {
                System.out.printf("pwdy");
                //两次密码一致
                //密码修改成功
            } else {
                System.out.printf("pwdn");
                //flag==-1
                session.setAttribute("pwd_error_msg", "原密码不正确");
            }
        }

        session.setAttribute("modify_msg", "修改完成，请重新登录");
        session.removeAttribute("accountBean");
        response.sendRedirect("/login.jsp");

        /*
        AccountService accountService = new AccountService();
        //从request中获取loginbean
        AccountBean loginbean = (AccountBean) request.getAttribute("loginbean");
        AccountBean accountBean = accountService.getAccountBean(loginbean);
        //将accountBean添加到session域中
        HttpSession session = request.getSession();
        session.setAttribute("accountBean", accountBean);
        //重定向到account.jsp
        response.sendRedirect("account.jsp");
        */
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
