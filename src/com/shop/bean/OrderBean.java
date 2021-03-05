package com.shop.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderBean {
    private int order_id,user_id;
    private String order_status,order_time_str;
    private Double order_money;
    private Date order_time;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getOrder_time_str() {
        return order_time_str;
    }

    public void setOrder_time_str(String order_time_str) {
        this.order_time_str = order_time_str;
    }

    public Double getOrder_money() {
        return order_money;
    }

    public void setOrder_money(Double order_money) {
        this.order_money = order_money;
    }

    public Date getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Date order_time) {
        this.order_time = order_time;
        if(order_time!=null){
            SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            order_time_str=simp.format(this.order_time);
        }

    }
}
