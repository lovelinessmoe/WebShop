package com.shop.service;

import com.shop.bean.AccountBean;
import com.shop.bean.CartBean;
import com.shop.dao.OrderDataDao;

import java.util.ArrayList;

public class OrderDataService {

    OrderDataDao orderDataDao = new OrderDataDao();

    public void add(AccountBean loginbean, ArrayList<CartBean> cartBeans, int order_id) {
        //将购物车信息上传到orderdata

        orderDataDao.add(loginbean,cartBeans,order_id);

    }

    public int deleteOrder(int index) {
        return orderDataDao.deleteOrder(index);

    }
}
