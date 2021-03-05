package com.shop.bean;

public class OrderDataBean {
    private int od_id,order_id,p_id,num;
    private Double od_money;
    public int getOd_id() {
        return od_id;
    }

    public void setOd_id(int od_id) {
        this.od_id = od_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Double getOd_money() {
        return od_money;
    }

    public void setOd_money(Double od_money) {
        this.od_money = od_money;
    }
}
