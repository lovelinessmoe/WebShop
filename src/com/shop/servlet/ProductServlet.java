package com.shop.servlet;

import com.shop.bean.ProductBean;
import com.shop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
    ProductService productService = new ProductService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        if ("find".equals(type)) {
            //获取list货物列表
            ArrayList<ProductBean> productList = productService.getProductList();
            //将list存入session域中
            HttpSession session = request.getSession();
            session.setAttribute("productList", productList);
            //重定向到product.jsp
            response.sendRedirect("product.jsp");
        } else if ("delete".equals(type)) {
            System.out.println("delete");
            //获取参数
            int p_id = Integer.parseInt(request.getParameter("p_id"));
            System.out.println(p_id);
            int i = productService.deleteProduct(p_id);
//            response.sendRedirect("/ProductServlet?type=find");
        } else if ("modifyName".equals(type)) {
            System.out.println("modifyName");
            //获取参数
            int p_id = Integer.parseInt(request.getParameter("p_id"));
            String name = request.getParameter("name");
            System.out.println(p_id);
            int i = productService.modifyProduct_name(p_id, name);

        } else if ("modifyPrice".equals(type)) {
            System.out.println("modifyPrice");
            //获取参数
            int p_id = Integer.parseInt(request.getParameter("p_id"));
            String price = request.getParameter("price");
            System.out.println(p_id);
            int i = productService.modifyProduct_price(p_id, price);

        } else if ("modifyNum".equals(type)) {
            System.out.println("modifyNum");
            //获取参数
            int p_id = Integer.parseInt(request.getParameter("p_id"));
            String num = request.getParameter("num");
            System.out.println(p_id);
            int i = productService.modifyProduct_num(p_id, num);


        } else if ("modifySellnum".equals(type)) {
            System.out.println("modifySellnum");
            //获取参数
            int p_id = Integer.parseInt(request.getParameter("p_id"));
            String sellnum = request.getParameter("sellnum");
            System.out.println(p_id);
            int i = productService.modifyProduct_sellnum(p_id, sellnum);
        }else if ("add".equals(type)) {
            System.out.println("add");
            //获取参数
            ProductBean productBean = new ProductBean();
            String p_name = request.getParameter("p_name");
            String p_num = request.getParameter("p_num");
            String price = request.getParameter("p_price");
            String sell_num = request.getParameter("sell_num");
            productBean.setP_price(Double.parseDouble(price));
            productBean.setP_num(Integer.parseInt(p_num));
            productBean.setP_name(p_name);
            productBean.setSell_num(Integer.parseInt(sell_num));
            productService.addProduct(productBean);
            response.sendRedirect("/ProductServlet?type=find");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
