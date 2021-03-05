package com.shop.util;

import java.sql.*;

public class JDBCUtil {
    private static String url = "jdbc:mysql://localhost:3306/shopping";
    private static String user = "root";
    private static String password = "CAOshunmiao";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*
     * 获取数据库连接对象
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }

    public static void getPreparedStatement() {

    }

    /*
     * 关闭数据库连接
     * Connection 数据库连接对象
     * PrepareStatement sql 语句预处理对象
     * ResultSet 结果集
     */
    public static void closeJDBC(Connection conn, PreparedStatement pstm, ResultSet rs) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (pstm != null) {
            try {
                pstm.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
