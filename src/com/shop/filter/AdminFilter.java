package com.shop.filter;

import com.shop.bean.AccountBean;
import com.sun.net.httpserver.HttpServer;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/ProductServlet")
public class AdminFilter implements Filter {


    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //强制装换
        HttpServletRequest request = (HttpServletRequest) req;
        //查看用户是否是管理员
        HttpSession session = request.getSession();
        AccountBean accountBean = (AccountBean) session.getAttribute("accountBean");
        //是管理员
        int admin = accountBean.getAdmin();
        if (admin == 1) {
            chain.doFilter(req, resp);
        }
    }


    public void destroy() {
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
