package com.shop.service;

import com.shop.bean.ProductBean;
import com.shop.dao.ProductDao;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductService {
    Scanner sc = new Scanner(System.in);
    ProductDao productdao = new ProductDao();

    public ArrayList<ProductBean> getProductList() {
        //加工代码忽略
        return productdao.getProductList();
    }

    public int addProduct(ProductBean bean) {
        /*
        ProductBean bean = new ProductBean();
        System.out.println("请输入商品名称");
        bean.setP_name(sc.nextLine());
        System.out.println("请输入商品数量");
        bean.setP_num(sc.nextInt());
        System.out.println("请输入商品价格");
        bean.setP_price(sc.nextDouble());
        */
        return productdao.addProduct(bean);
    }

    public int deleteProduct(int p_id) {
        return productdao.deleteProduct(p_id);
    }

    //    [a]商品名字[b]商品价格c]商品数量[d]商品销售量

    public int modifyProduct_name(int id, String value) {
        return productdao.modifyProduct(id, "a", value);
    }

    public int modifyProduct_price(int id, String value) {
        return productdao.modifyProduct(id, "b", value);
    }

    public int modifyProduct_num(int id, String value) {
        return productdao.modifyProduct(id, "c", value);
    }

    public int modifyProduct_sellnum(int id, String value) {
        return productdao.modifyProduct(id, "d", value);
    }
}
