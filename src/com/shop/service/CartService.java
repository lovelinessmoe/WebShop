package com.shop.service;

import com.shop.bean.AccountBean;
import com.shop.bean.CartBean;
import com.shop.bean.PageBean;
import com.shop.dao.CartDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CartService {
    CartDao cartDao = new CartDao();
    Scanner sc = new Scanner(System.in);

    public ArrayList<CartBean> getCart(AccountBean loginbean) {
        return cartDao.getCart(loginbean);
    }


    public int addProduct(AccountBean loginbean) {
        System.out.println("请输入你想添加货物的PID");
        int id = sc.nextInt();
        System.out.println("请输入你想添加货物的数量");
        int num = sc.nextInt();
        return cartDao.addProduct(loginbean, id, num);

    }

    public int deleteProduct(AccountBean loginbean) {
        System.out.println("请输入你想删除的CID");
        int cid = sc.nextInt();
        return cartDao.deleteProduct(loginbean, cid);
    }

    public int changeNum(AccountBean loginbean) {
        System.out.println("请输入你想更改产品的CID");
        int cid = sc.nextInt();
        System.out.println("请输入你想更改为的数量");
        int num = sc.nextInt();
        return cartDao.changeNum(loginbean, cid, num);
    }

    public void buy(AccountBean loginbean, double sum, ArrayList<CartBean> cartBeans) {
        System.out.println("当前购物车总价是" + sum);
        System.out.println("是否确定购买？Y/N");
        String input = sc.nextLine();
        if ("y".equals(input.toLowerCase())) {
            /*
             * 创建订单
             * ordertable
             * 返回id给data
             *
             * 将购物车信息上传到orderdata
             * 删除购物车原有物品
             * */
            OrderService orderService = new OrderService();
            int order_id = orderService.create(loginbean, sum);

            OrderDataService orderDataService = new OrderDataService();
            //将购物车信息上传到orderdata
            orderDataService.add(loginbean, cartBeans, order_id);

            cartDao.deleteAll(loginbean);

            cartDao.buy(loginbean, sum, cartBeans);

            System.out.println("请支付你的订单");
            //进入支付系统。。。
            //orderService.pay();
            if (true) {//上边的返回值
                int paid = orderService.changeStatus(order_id, "paid");
                if (paid > 0) {
                    System.out.println("支付成功");
                } else {
                    System.err.println("出现异常");
                }
            } else {
                System.err.println("支付失败");
            }


        } else if ("n".equals(input.toLowerCase())) {
            System.out.println("再见");
        } else {
            System.err.println("输入有误");
        }
    }

    /*
    分页查询
     */
    public PageBean<CartBean> getCartByPage(String _currentPage, String _rows,int u_id) {

        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        //创建一个空的pageBean
        PageBean<CartBean> cartBeanPageBean = new PageBean<>();
        cartBeanPageBean.setCurrentPage(currentPage);
        cartBeanPageBean.setRows(rows);

        //调用Dao查询总记录数
        CartDao cartDao = new CartDao();
        int totalCount = cartDao.findTotalCount(u_id);
        cartBeanPageBean.setTotalCount(totalCount);
        //调用Dao查询List集合
        //计算开始时的索引
        int start = (currentPage - 1) * rows;
        List<CartBean> list = cartDao.findByPage(start, rows,u_id);
        cartBeanPageBean.setList(list);

        //计算总页码
        int totalPage = (totalCount % rows) == 0 ? (totalCount / rows) : (totalCount / rows) + 1;
        cartBeanPageBean.setTotalPage(totalPage);


        return cartBeanPageBean;


    }
}
