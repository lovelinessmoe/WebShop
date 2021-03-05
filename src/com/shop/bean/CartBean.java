package com.shop.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CartBean {
    private int c_id,u_id,p_id,num;
    private double total_money;
    private String create_time_str;
    private Date create_time;

    private String p_name;

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
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

    public double getTotal_money() {
        return total_money;
    }

    public void setTotal_money(double total_money) {
        this.total_money = total_money;
    }

    public String getCreate_time_str() {
        return create_time_str;
    }

    public void setCreate_time_str(String create_time_str) throws ParseException {
        this.create_time_str = create_time_str;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
        if(create_time!=null){
            SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            create_time_str = simp.format(this.create_time);

        }
    }
}
