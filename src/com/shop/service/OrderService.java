package com.shop.service;

import com.shop.bean.AccountBean;
import com.shop.bean.OrderBean;
import com.shop.bean.OrderDataBean;
import com.shop.dao.OrderDao;

import java.util.ArrayList;
import java.util.Scanner;

public class OrderService {
    OrderDao orderDao = new OrderDao();
    Scanner sc = new Scanner(System.in);

    public ArrayList<OrderBean> getOrder(AccountBean loginbean) {
        return orderDao.getOrder(loginbean);
    }

    public int create(AccountBean loginbean, double sum) {
        //订单创建,返回订单id
        return orderDao.creat(loginbean, sum);
    }

    public int changeStatus(int order_id, String status) {

        return orderDao.changeStatus(order_id, status);
    }

    public void deleteOrder(AccountBean loginbean) {
        System.out.println("请输入你要删除订单的id");
        int index = sc.nextInt();
        if (orderDao.deleteOrder(loginbean, index) > 0) {
            OrderDataService orderDataService = new OrderDataService();
            if (orderDataService.deleteOrder(index) > 0) {
                System.out.println("删除成功");
            } else {
                System.err.println("删除失败");
            }
        } else {
            System.err.println("删除失败");
        }
    }

    public void showDetail() {
        System.out.println("请输入你要查看订单的id");
        int index = sc.nextInt();

        ArrayList<OrderDataBean> orderDataBeans = orderDao.showDetail(index);
        for (OrderDataBean orderDataBean:orderDataBeans
             ) {
            System.out.println("----------------------------");
            System.out.println("p_id"+orderDataBean.getP_id());
            System.out.println("od_money"+orderDataBean.getOd_money());
            System.out.println("num"+orderDataBean.getNum());
            System.out.println("od_id"+orderDataBean.getOd_id());
            System.out.println("----------------------------");
        }
    }
}
