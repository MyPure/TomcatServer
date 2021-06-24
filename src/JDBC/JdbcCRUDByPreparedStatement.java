package JDBC;

import java.sql.*;
import java.util.*;


public class JdbcCRUDByPreparedStatement{
    private static Connection conn = null;
    private PreparedStatement st = null;
    private ResultSet rs = null;

    ///单例
    private static JdbcCRUDByPreparedStatement instance;

    public static JdbcCRUDByPreparedStatement shared(){
        if(instance==null){
            instance=new JdbcCRUDByPreparedStatement();
        }
        if(conn == null){
            try {
                conn = JDBCUtil.getConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }


    ///use func
    public List<Map<String, Object>> login(String username,String userpsw){

        String sql = "select * from userinfo where username='" + username + "' and userpsw= '" + userpsw + "'";

        return find(sql);

    }
    public String register(String username,String userpsw){
        String idOne = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        String sql = "insert into userinfo (id,username,userpsw) VALUES (?,?,?)";
        try {
            st = conn.prepareStatement(sql);
            st.setString(1, idOne);
            st.setString(2,username);
            st.setString(3,userpsw);
            st.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return idOne;

    }



///main func
    public List<Map<String, Object>> find(String strSql) {
        List<Map<String, Object>> list = new ArrayList();
        try {
            st = conn.prepareStatement(strSql);
            rs = st.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                Map<String, Object> map = new HashMap();
                int columnCount = rsmd.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = rsmd.getColumnName(i + 1);
                    map.put(columnName, rs.getObject(i + 1));
                }
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.release( st, rs);
        }
        return list;
    }

    public void doSql (String sql){
        try {
            st = conn.prepareStatement(sql);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
