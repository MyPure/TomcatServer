package servlet;

import JDBC.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.junit.Test;
import returnmsg.ReturnMsg;
import room.Room;
import room.RoomManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@WebServlet(name = "TestServlet", urlPatterns = "/test")
public class TestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charser=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.getWriter().write("中文");

//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        System.out.println(df.format(new Date()) + ":" + request.getHeader("User-Agent"));// new Date()为获取当前系统时间
    }

    @Test
    public void test() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());
        List<Object[]> objects = queryRunner.query("select * from roominfo;", new ArrayListHandler());
        for (Object[] obj : objects) {
            System.out.println(Arrays.toString(obj));
        }

        List<Room> rooms = RoomManager.getAllRoom();
        for (Room room : rooms) {
            System.out.println(room.toJson());
        }
    }
    @Test
    public void addRoom() throws SQLException {
        Room room = new Room("寂静",99);
        ReturnMsg ret = RoomManager.addRoom(room);
        System.out.println(ret.toJson());
    }

    @Test
    public void deleteRoom(){
        ReturnMsg ret = RoomManager.deleteRoom("寂静岭");
        System.out.println(ret.toJson());
    }
}