package servlet;
import JDBC.JdbcCRUDByPreparedStatement;
import MainClass.Main;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@WebServlet(displayName="RegisterServlet", urlPatterns={"/servlet/RegisterServlet"})
public class RegisterServlet {
    private static final long serialVersionUID = 1L;

    public RegisterServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");

    }

}
