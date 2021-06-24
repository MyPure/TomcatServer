package servlet.roomservlet;

import com.alibaba.fastjson.JSON;
import returnmsg.ReturnMsg;
import room.Room;
import room.RoomManager;
import servlet.ProServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "RoomServlet",urlPatterns = "/room")
public class RoomServlet extends ProServlet {
    public void addRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String s = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Room room = JSON.parseObject(s,Room.class);

        ReturnMsg returnMsg = RoomManager.addRoom(room);
        System.out.println(returnMsg.toJson());

        response.getWriter().write(returnMsg.toJson());
    }

    public void deleteRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String themeName = request.getParameter("themeName");
        if(themeName.isEmpty() || themeName.trim().isEmpty()){
            responseError(response,"ThemeName is null.");
            return;
        }

        ReturnMsg returnMsg = RoomManager.deleteRoom(themeName);
        System.out.println(returnMsg.toJson());

        response.getWriter().write(returnMsg.toJson());
    }

    public void updateRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String oldThemeName = request.getParameter("oldThemeName");
        if(oldThemeName.isEmpty() || oldThemeName.trim().isEmpty()){
            responseError(response,"Update error. OldThemeName is null.");
        }else{
            String s = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            Room room = JSON.parseObject(s,Room.class);

            ReturnMsg returnMsg = RoomManager.updateRoom(oldThemeName,room);
            System.out.println(returnMsg.toJson());

            response.getWriter().write(returnMsg.toJson());
        }
    }

    public void queryRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String themeName = request.getParameter("themeName");
        if(themeName.isEmpty() || themeName.trim().isEmpty()){
            responseError(response,"Query error. ThemeName is null.");
        }else{
            try {
                Room room = RoomManager.getRoom(themeName);
                if(room == null){
                    responseError(response,"Query error. This room isn't exist.");
                }else{
                    ReturnMsg returnMsg = ReturnMsg.getSuccessMsg(room.toJson());
                    response.getWriter().write(returnMsg.toJson());
                }
            } catch (SQLException e) {
                e.printStackTrace();
                responseError(response,e.getMessage());
            }
        }
    }

    public void queryAllRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            List<Room> rooms = RoomManager.getAllRoom();
            if(rooms.isEmpty()){
                responseError(response,"Query error. Room list is empty.");
            }else{
                ReturnMsg returnMsg = ReturnMsg.getSuccessMsg(JSON.toJSONString(rooms));
                response.getWriter().write(returnMsg.toJson());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            responseError(response,e.getMessage());
        }
    }
}
