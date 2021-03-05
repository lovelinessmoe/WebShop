package com.shop.dao;

import com.shop.bean.AccountBean;
import com.shop.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao {
    public AccountBean getAccountBean(AccountBean loginbean) {
        Connection conn = JDBCUtil.getConnection();
        String sql = "select * from account where u_login_name = ?";
        AccountBean accountbean = new AccountBean();
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, loginbean.getU_login_name());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                accountbean.setU_id(rs.getInt("u_id"));
                accountbean.setU_name(rs.getString("u_name"));
                accountbean.setU_login_name(rs.getString("u_login_name"));
                accountbean.setMobile(rs.getString("mobile"));
                accountbean.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeJDBC(conn, pstm, null);
        }
        return accountbean;
    }

    public int modifyName(String name, AccountBean loginbean) {
        Connection conn = JDBCUtil.getConnection();
        String sql = "update account set u_name = ? where u_login_name = ?";
        int rs = 0;
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, name);
            pstm.setString(2, loginbean.getU_login_name());
            rs = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeJDBC(conn, pstm, null);
        }
        return rs;
    }

    public int modifyPwd(String pwd_new, String pwd_old, AccountBean loginbean) {

        Connection conn = JDBCUtil.getConnection();
        String sql = "select * from account where u_login_name = ?";
        ResultSet rs_query = null;
        PreparedStatement pstm = null;
        int flag = -1;
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, loginbean.getU_login_name());
            rs_query = pstm.executeQuery();
            while (rs_query.next()) {
                if (rs_query.getString("u_pwd").equals(pwd_old))
                    flag = 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeJDBC(null, pstm, rs_query);
        }

        if (flag == 1) {
            sql = "update account set u_pwd = ? where u_login_name = ?";
            try {
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, pwd_new);
                pstm.setString(2, loginbean.getU_login_name());
                pstm.executeUpdate();
                //不需要rs来接受返回的值
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                JDBCUtil.closeJDBC(conn, pstm, null);
            }
        }
        return flag;

    }

    public int modifyMobile(String mobile, AccountBean loginbean) {
        Connection conn = JDBCUtil.getConnection();
        String sql = "update account set mobile = ? where u_login_name = ?";
        int rs = 0;
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, mobile);
            pstm.setString(2, loginbean.getU_login_name());
            rs = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeJDBC(conn, pstm, null);
        }
        return rs;
    }

    public int modifyEmail(String email, AccountBean loginbean) {
        Connection conn = JDBCUtil.getConnection();
        String sql = "update account set email = ? where u_login_name = ?";
        int rs = 0;
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, email);
            pstm.setString(2, loginbean.getU_login_name());
            rs = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeJDBC(conn, pstm, null);
        }
        return rs;
    }

    public AccountBean login(AccountBean bean) {
        Connection conn = JDBCUtil.getConnection();
        String sql = "select * from account where u_login_name = ?";
        ResultSet rs = null;
        PreparedStatement pstm = null;
        AccountBean loginbean = new AccountBean();
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, bean.getU_login_name());
            rs = pstm.executeQuery();
            while (rs.next()) {
                loginbean.setU_pwd(rs.getString("u_pwd"));
                loginbean.setEmail(rs.getString("email"));
                loginbean.setMobile(rs.getString("mobile"));
                loginbean.setU_login_name(rs.getString("u_login_name"));
                loginbean.setU_name(rs.getString("u_name"));
                loginbean.setU_id(rs.getInt("u_id"));
                loginbean.setAdmin(rs.getInt("admin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeJDBC(conn, pstm, rs);
        }
        return loginbean;
    }

    public AccountBean register(String u_name, String u_login_name, String u_pwd, String mobile, String email) {
        Connection conn = JDBCUtil.getConnection();
        String sql = "insert into account (u_name, u_login_name, u_pwd,mobile,email) values ( ?, ?, ? ,?,?)";
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, u_name);
            pstm.setString(2, u_login_name);
            pstm.setString(3, u_pwd);
            pstm.setString(4, mobile);
            pstm.setString(5, email);
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeJDBC(conn, pstm, null);
        }

        conn = JDBCUtil.getConnection();
        AccountBean bean = new AccountBean();
        pstm = null;

        sql = "select * from account where u_login_name = ?";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, u_login_name);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                bean.setU_pwd(rs.getString("u_pwd"));
                bean.setEmail(rs.getString("email"));
                bean.setMobile(rs.getString("mobile"));
                bean.setU_login_name(rs.getString("u_login_name"));
                bean.setU_name(rs.getString("u_name"));
                bean.setU_id(rs.getInt("u_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeJDBC(conn, pstm, null);
        }
        return bean;
    }
}
