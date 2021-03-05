package com.shop.dao;

import com.shop.bean.AccountBean;
import com.shop.bean.CartBean;
import com.shop.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CartDao {

    public ArrayList<CartBean> getCart(AccountBean loginbean) {
        Connection conn = JDBCUtil.getConnection();
        String sql = "select * from cart where u_id = ?";
        ResultSet rs = null;
        PreparedStatement pstm = null;
        ArrayList<CartBean> cartBeans = new ArrayList<>();
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, loginbean.getU_id());
            rs = pstm.executeQuery();
            while (rs.next()) {
                CartBean cartBean = new CartBean();
                cartBean.setC_id(rs.getInt("c_id"));
                cartBean.setU_id(rs.getInt("u_id"));
                cartBean.setP_id(rs.getInt("p_id"));
                cartBean.setCreate_time(rs.getDate("create_time"));
                cartBean.setTotal_money(rs.getDouble("total_money"));
                cartBean.setNum(rs.getInt("num"));
                cartBeans.add(cartBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeJDBC(conn, pstm, rs);
        }
        return cartBeans;
    }

    public int addProduct(AccountBean loginbean, int id, int num) {
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstm = null;

        java.sql.Date time = new java.sql.Date(new Date().getTime());
        //数据库Date日期


        ResultSet rs = null;
        int rsupdate = 0;
        try {
            String sql = "select product.p_price from product where p_id = ?;";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            double p_price = 0;
            while (rs.next()) {
                p_price = rs.getDouble("p_price");
            }
            double total_money = num * p_price;
            sql = "insert into cart (u_id, p_id, create_time, num, total_money) values ( ?, ?, ?, ?, ?)";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, loginbean.getU_id());
            pstm.setInt(2, id);
            pstm.setDate(3, time);
            pstm.setInt(4, num);
            pstm.setDouble(5, total_money);
            rsupdate = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeJDBC(conn, pstm, rs);
        }
        return rsupdate;
    }

    public int deleteProduct(AccountBean loginbean, int cid) {
        Connection conn = JDBCUtil.getConnection();
        String sql = "delete from cart where c_id = ? && u_id = ?";
        int rs = 0;
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, cid);
            pstm.setInt(2, loginbean.getU_id());
            rs = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeJDBC(conn, pstm, null);
        }
        return rs;
    }

    public int changeNum(AccountBean loginbean, int cid, int num) {
        Connection conn = JDBCUtil.getConnection();
        ResultSet rs = null;
        int rs_num = 0;
        PreparedStatement pstm = null;
        try {
            String sql = "select product.p_price from product where p_id = (SELECT p_id FROM cart WHERE c_id = ?)";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, cid);
            rs = pstm.executeQuery();
            double p_price = 0;
            while (rs.next()) {

                p_price = rs.getDouble("p_price");
            }
            double total_money = num * p_price;


            sql = "update cart set num = ?,total_money = ? where c_id = ? && u_id = ?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, num);
            pstm.setDouble(2, total_money);
            pstm.setInt(3, cid);
            pstm.setInt(4, loginbean.getU_id());
            rs_num = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeJDBC(conn, pstm, null);
        }
        return rs_num;


    }

    public void buy(AccountBean loginbean, double sum, ArrayList<CartBean> cartBeans) {
        /*
         * 创建订单
         * ordertable
         * 返回id给data
         * 将购物车信息上传到orderdata
         *
         * 删除购物车原有物品
         * */


    }

    public int deleteAll(AccountBean loginbean) {
        Connection conn = JDBCUtil.getConnection();
        String sql = "delete from cart where u_id = ?";
        int rs = 0;
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, loginbean.getU_id());
            rs = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeJDBC(conn, pstm, null);
        }
        return rs;
    }

    /*
    查询总记录数
     */
    public int findTotalCount(int u_id) {
        Connection conn = JDBCUtil.getConnection();
        String sql = "SELECT count(*) from cart where u_id = ?";
        ResultSet rs = null;
        PreparedStatement pstm = null;
        int totalCount = 0;
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, u_id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                totalCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeJDBC(conn, pstm, rs);
        }
        return totalCount;
    }

    /*
    分页查询每页记录
     */
    public List<CartBean> findByPage(int start, int rows, int u_id) {
        Connection conn = JDBCUtil.getConnection();
        String sql = "SELECT p.p_name,c.* FROM cart AS c LEFT JOIN product AS p ON c.p_id = p.p_id WHERE c.u_id = ? LIMIT ?, ?";
        ResultSet rs = null;
        PreparedStatement pstm = null;
        ArrayList<CartBean> list = new ArrayList<CartBean>();
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, u_id);
            pstm.setInt(2, start);
            pstm.setInt(3, rows);
            rs = pstm.executeQuery();
            while (rs.next()) {
                CartBean cartBean = new CartBean();
                cartBean.setP_name(rs.getString("p_name"));
                cartBean.setC_id(rs.getInt("c_id"));
                cartBean.setU_id(rs.getInt("u_id"));
                cartBean.setP_id(rs.getInt("p_id"));
                cartBean.setCreate_time(rs.getDate("create_time"));
                cartBean.setNum(rs.getInt("num"));
                cartBean.setTotal_money(rs.getDouble("total_money"));
                list.add(cartBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeJDBC(conn, pstm, rs);
        }
        return list;
    }
}
