package com.shop.dao;

import com.shop.bean.ProductBean;
import com.shop.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDao {


//    获取商品信息列表

    public ArrayList<ProductBean> getProductList() {
        ArrayList<ProductBean> list = new ArrayList<ProductBean>();
        ResultSet rs = null;
        Connection conn = JDBCUtil.getConnection();
        String sql = "select * from product";
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                ProductBean bean = new ProductBean();
                bean.setP_id(rs.getInt("p_id"));
                bean.setP_name(rs.getString("p_name"));
                bean.setP_num(rs.getInt("p_num"));
                bean.setSell_num(rs.getInt("sell_num"));
                bean.setP_price(rs.getDouble("p_price"));
                list.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeJDBC(conn, pstm, rs);
        }
        return list;

    }

    public int addProduct(ProductBean bean) {
        int rs = 0;
        Connection conn = JDBCUtil.getConnection();
        String sql = "insert into product(p_name,p_price,p_num,sell_num)values (?,?,?,?)";
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, bean.getP_name());
            pstm.setDouble(2, bean.getP_price());
            pstm.setInt(3, bean.getP_num());
            pstm.setInt(4, bean.getSell_num());
            rs = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeJDBC(conn, pstm, null);
        }
        return rs;
    }

    public int deleteProduct(int i) {
        Connection conn = JDBCUtil.getConnection();
        String sql = "Delete from product where p_id=?";
        PreparedStatement pstm = null;
        int rs = 0;
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, i);
            rs = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeJDBC(conn, pstm, null);
        }
        return rs;
    }

    public int modifyProduct(int id, String index, String value) {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstm = null;
        int rs = 0;
        try {
            if (index.toLowerCase().equals("a")) {
                String sql = "update product set p_name = ? where p_id = ? ";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(2, id);
                pstm.setString(1, value);
            } else if (index.toLowerCase().equals("b")) {
                String sql = "update product set p_price = ? where p_id = ? ";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(2, id);
                pstm.setInt(1, Integer.parseInt(value));
            } else if (index.toLowerCase().equals("c")) {
                String sql = "update product set p_num = ? where p_id = ? ";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(2, id);
                pstm.setDouble(1, Integer.parseInt(value));
            } else if (index.toLowerCase().equals("d")) {
                String sql = "update product set sell_num = ? where p_id = ? ";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(2, id);
                pstm.setInt(1, Integer.parseInt(value));
            }

            rs = pstm.executeUpdate();
            JDBCUtil.closeJDBC(conn, pstm, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
