package room;

import JDBC.JDBCUtil;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import returnmsg.ReturnMsg;
public class RoomManager {
    public static ReturnMsg addRoom(Room room){
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());
        try {
            if(isRoomExist(room.getThemeName())){
                return ReturnMsg.getErrorMsg("Add error. This room is already exist.");
            }

            int value = queryRunner.update("insert into roominfo values(null,?,?,?);",room.getThemeName(),room.getPrice(),room.getEvaluation());
            if(value > 0){
                return ReturnMsg.getSuccessMsg(value + " records has been changed.");
            }else{
                return ReturnMsg.getErrorMsg("Add error. Nothing has been changed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ReturnMsg.getErrorMsg("Add error. "+e.getMessage());
        }
    }

    public static ReturnMsg deleteRoom(String themeName){
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());
        try {
            if(!isRoomExist(themeName)){
                return ReturnMsg.getErrorMsg("Delete error. This room isn't exist.");
            }

            int value = queryRunner.update("delete from roominfo where themeName = ?;",themeName);
            if(value > 0){
                return ReturnMsg.getSuccessMsg(value + " records has been changed.");
            }else{
                return ReturnMsg.getErrorMsg("Delete error. Nothing has been changed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ReturnMsg.getErrorMsg("Delete error. "+e.getMessage());
        }
    }

    public static ReturnMsg updateRoom(String oldThemeName, Room room){
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());

        try {
            if (!isRoomExist(oldThemeName)) {
                return ReturnMsg.getErrorMsg( "Update error. This room isn't exist.");
            }

            int value = queryRunner.update("update roominfo set themeName = ? , price = ? where themeName = ?;",room.getThemeName(),room.getPrice(),oldThemeName);
            if(value > 0){
                return ReturnMsg.getSuccessMsg(value + " records has been changed.");
            }else{
                return ReturnMsg.getErrorMsg("Update error. Nothing has been changed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ReturnMsg.getErrorMsg("Update error. "+e.getMessage());
        }
    }

    private static Boolean isRoomExist(String themeName) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());
        Map<String, Object> map = queryRunner.query("select themeName from roominfo where themeName = ?;", new MapHandler(), themeName);
        return !(map == null);
    }

    public static Room getRoom(String themeName) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());
        if(!isRoomExist(themeName)){
            return null;
        }

        Room room = queryRunner.query("select * from roominfo where themeName = ?;",new BeanHandler<>(Room.class),themeName);
        return room;
    }

    public static List<Room> getAllRoom() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(JDBCUtil.getDataSource());
        List<Room> rooms = queryRunner.query("select * from roominfo;",new BeanListHandler<Room>(Room.class));

        return rooms;
    }
}
