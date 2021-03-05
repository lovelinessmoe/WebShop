package com.shop.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AllFilter implements Filter {


    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        //设置response字符编码
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response= (HttpServletResponse) resp;
        chain.doFilter(req, resp);
        /*
        HttpSession session = request.getSession();
        if (session.getAttribute("accountBean") != null || request.getRequestURI().endsWith("login.jsp")) {
            chain.doFilter(req, resp);
        }else {
            response.sendRedirect("login.jsp");
            System.out.println("拒绝访问");
        }
        */


    }

    public void destroy() {

    }

    public void init(FilterConfig config) throws ServletException {
    }

}
