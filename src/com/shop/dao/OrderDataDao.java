package com.shop.dao;

import com.shop.bean.AccountBean;
import com.shop.bean.CartBean;
import com.shop.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDataDao {

    public int add(AccountBean loginbean, ArrayList<CartBean> cartBeans, int order_id) {
        //将购物车信息上传到orderdata
        Connection conn = JDBCUtil.getConnection();
        String sql = "insert into orderdata (p_id, num, od_money, order⁯_id) values (?,?,?,?);";
        int rs = 0;
        PreparedStatement pstm = null;
        try {
            for (CartBean cartBean : cartBeans
                    ) {
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, cartBean.getP_id());
                pstm.setInt(2, cartBean.getNum());
                pstm.setDouble(3, cartBean.getTotal_money());
                pstm.setInt(4, order_id);
                rs += pstm.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeJDBC(conn, pstm, null);
        }
        return rs;
    }

    public int deleteOrder(int index) {

        Connection conn = JDBCUtil.getConnection();
        String sql = "delete from orderdata where order⁯_id = ?";
        int rs = 0;
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, index);
            rs = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeJDBC(conn, pstm, null);
        }
        return rs;
    }
}
