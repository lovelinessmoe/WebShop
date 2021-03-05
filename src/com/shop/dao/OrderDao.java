package com.shop.dao;

import com.shop.bean.AccountBean;
import com.shop.bean.OrderBean;
import com.shop.bean.OrderDataBean;
import com.shop.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class OrderDao {
    public ArrayList<OrderBean> getOrder(AccountBean loginbean) {
        Connection conn = JDBCUtil.getConnection();
        String sql = "select * from ordertable where user_id = ?";
        ResultSet rs = null;
        PreparedStatement pstm = null;
        ArrayList<OrderBean> orderBeans = new ArrayList<>();
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, loginbean.getU_id());
            rs = pstm.executeQuery();
            while (rs.next()) {
                OrderBean orderBean = new OrderBean();
                orderBean.setOrder_id(rs.getInt("order_id"));
                orderBean.setOrder_money(rs.getDouble("order_money"));
                orderBean.setOrder_status(rs.getString("order_status"));
                orderBean.setOrder_time(rs.getTime("order_time"));
                orderBeans.add(orderBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeJDBC(conn, pstm, rs);
        }
        return orderBeans;
    }

    public int creat(AccountBean loginbean, double sum) {
        Connection conn = JDBCUtil.getConnection();
        String sql = "insert into ordertable (order_time, user_id, order_money, order_status) values (?,?,?,?);";
        PreparedStatement pstm = null;
        ArrayList<OrderBean> orderBeans = new ArrayList<>();
        java.sql.Date time = new java.sql.Date(new Date().getTime());
        int order_id = 0;
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setDate(1, time);
            pstm.setInt(2, loginbean.getU_id());
            pstm.setDouble(3, sum);
            pstm.setString(4, "unpaid");
            pstm.executeUpdate();


            sql = "select * from ordertable where order_time = ?";
            //曲线救国，通过创建时间返回订单id
            pstm = conn.prepareStatement(sql);
            pstm.setDate(1, time);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                order_id = rs.getInt("order_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeJDBC(conn, pstm, null);
        }
        return order_id;
    }

    public int changeStatus(int order_id, String status) {
        Connection conn = JDBCUtil.getConnection();
        String sql = "update ordertable set order_status = ? where order_id = ?;";
        int rs = 0;
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, status);
            pstm.setInt(2, order_id);
            rs = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeJDBC(conn, pstm, null);
        }
        return rs;

    }

    public int deleteOrder(AccountBean loginbean, int index) {
        Connection conn = JDBCUtil.getConnection();
        String sql = "delete from ordertable where order_id = ? && user_id = ?";
        int rs = 0;
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, index);
            pstm.setInt(2, loginbean.getU_id());
            rs = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeJDBC(conn, pstm, null);
        }
        return rs;
    }

    public ArrayList<OrderDataBean> showDetail(int index) {
        Connection conn = JDBCUtil.getConnection();
        String sql = "select * from orderdata where order⁯_id = ?";
        ResultSet rs = null;
        PreparedStatement pstm = null;
        ArrayList<OrderDataBean> orderDataBeans = new ArrayList<>();
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, index);
            rs = pstm.executeQuery();
            while (rs.next()){
                OrderDataBean orderDataBean = new OrderDataBean();
                orderDataBean.setNum(rs.getInt("num"));
                orderDataBean.setOd_money(rs.getDouble("od_money"));
                orderDataBean.setP_id(rs.getInt("p_id"));
                orderDataBean.setOd_id(rs.getInt("od_id"));
                orderDataBeans.add(orderDataBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeJDBC(conn, pstm, rs);
        }
        return orderDataBeans;

    }
}
