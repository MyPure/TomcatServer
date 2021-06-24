package JDBC;

import com.alibaba.druid.pool.DruidDataSource;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


public class JDBCUtil{
    //获取数据库连接驱动
    private static String driver;
    //获取数据库连接URL地址
    private static String url;

    //获取数据库连接用户名
    private static String username;
    //获取数据库连接密码
    private static String password;

    private static final DruidDataSource dataSource = new DruidDataSource();
    static {
//        Properties properties = new Properties();
//        try {
//            properties.load(new FileInputStream("src/conf/db.properties"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        driver = properties.getProperty("driverClassName");
//        url = properties.getProperty("url");
//        username = properties.getProperty("username");
//        password = properties.getProperty("password");
        driver = "com.mysql.cj.jdbc.Driver";
        url = "jdbc:mysql://39.97.123.115:3306/BackRoomServer?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        //url = "jdbc:mysql://localhost:3306/BackRoomServer?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        username = "root";
        //password = "000309";
        password = "Password1";


        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        try {
            //加载数据库驱动
            Class.forName(driver);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * @return Connection数据库连接对象
     * @Description: 获取数据库连接对象
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * Druid获得连接池对象
     * @return Druid连接池对象
     */
    public static DataSource getDataSource(){
        return dataSource;
    }

    /**
     * @Description: 释放资源，
     * 要释放的资源包括Connection数据库连接对象，负责执行SQL命令的Statement对象，存储查询结果的ResultSet对象
     */
    public static void release(Statement st, ResultSet rs) {
        releaseSR(st, rs);
    }

    public static void release(Statement st, ResultSet rs, Connection conn) {
        releaseSR(st, rs);
        if(conn != null){
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void releaseSR(Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void showResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        StringBuilder sb = new StringBuilder();
        while (rs.next()){
            for(int i = 1;i<=rsmd.getColumnCount();i++){
                sb.append(rs.getObject(i).toString()).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}

