package com.shop.servlet;

import com.shop.bean.AccountBean;
import com.shop.service.AccountService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    HashMap<String, String> map = new HashMap<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置中文编码
        request.setCharacterEncoding("utf-8");
        //每一次登录都清除之前保存的msg
        request.removeAttribute("log_msg");
        HttpSession session = request.getSession();
        session.removeAttribute("modify_msg");
        //获取用户名
        String username = request.getParameter("u_login_name");
        //获取密码
        String pwd = request.getParameter("u_pwd");
        //封装bean对象
        AccountBean bean = new AccountBean();
        bean.setU_login_name(username);
        bean.setU_pwd(pwd);
        //登录
        AccountService accountService = new AccountService();
        AccountBean loginbean = accountService.login(bean);
        if (loginbean != null) {
            //登录成功
            //将用户信息放入session域中
            session.setAttribute("accountBean", loginbean);
            //重定向到操作中心页面
            //ajax后不管用
//            response.sendRedirect("/choose.jsp");
            map.put("url", request.getContextPath()+"/choose.jsp");
            map.put("status", "succ");
            JSONObject json = JSONObject.fromObject(map);
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().print(json);
        } else {
            //登录失败
            //显示错误信息
            map.put("msg", "用户名或密码错误");
            map.put("status", "err");
            JSONObject json = JSONObject.fromObject(map);
            //一起用下方的这个才能在json中传中文
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().print(json);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
