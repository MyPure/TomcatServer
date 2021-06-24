package servlet;

import JDBC.JdbcCRUDByPreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import JSON.JSONMaker;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * Created by dhc on 17-5-5.
 * User: 网络黑寡妇
 */
@WebServlet(displayName="LoginServlet", urlPatterns={"/servlet/LoginServlet"})
public class LoginServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        request.setCharacterEncoding("UTF-8");
        String result;
        //获取用户名
        String username = request.getParameter("username");
        String psw = request.getParameter("password");

        JSONMaker jsonMaker = new JSONMaker();
        JSONObject jsonObject = new JSONObject();
        //判断用户名和密码
        if ((username == "") || (username == null) || (username.length() > 20)) {
            try {
                result = "请输入的用户名（不能超过20个字符）！";

                //response.sendRedirect("login.jsp");//注意路径，建立的文件路径不同，所写的路径是有差异的
                PrintWriter pw = response.getWriter();
                jsonMaker.setSuccess(0);
                jsonMaker.setError(result);
                jsonMaker.setJsonObject(jsonObject);
                pw.println(jsonMaker);
                pw.close();
                pw.flush();
                // response.sendRedirect(request.getContextPath() + "login.jsp");
                //服务器内部跳转，这里表示的是服务器的根目录
                //request.getRequestDispatcher("login.jsp").forward(request,response);//错误
                // request.getRequestDispatcher("/login.jsp").forward(request,response);//正确也可定义为“../login.jsp”
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if ((psw == "") || (psw == null) || (psw.length() > 20)) {
            try {
                result = "请输入密码(不能超过20个字符)！";
                PrintWriter pw = response.getWriter();
                jsonMaker.setSuccess(0);
                jsonMaker.setError(result);
                jsonMaker.setJsonObject(jsonObject);
                pw.println(jsonMaker);
                pw.close();
                pw.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //利用JDBC驱动程序查询数据库


        List<Map<String, Object>> list = JdbcCRUDByPreparedStatement.shared().login(username,psw);//返回结果集

        //如果记录集非空，表明有匹配的用户名和密码，登陆成功
        // 登录成功后将username设置为session变量的username
        // 这样在后面就可以通过 session.getAttribute("username") 来获取用户名，
        // 同时这样还可以作为用户登录与否的判断依据
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        String k1, m1;
        if (!list.isEmpty()) {
            for (Map<String, Object> m : list) {
                for (String k : m.keySet()) {
                    k1 = k;
                    m1 = String.valueOf(m.get(k));
                    session.setAttribute(k1, m1);
                }
            }
            try {
                PrintWriter pw = response.getWriter();
                jsonObject.put("token", "success");
                jsonMaker.setSuccess(1);
                jsonMaker.setError("");
                jsonMaker.setJsonObject(jsonObject);
                pw.println(jsonMaker);
                pw.close();
                pw.flush();
            }catch (Exception e){

            }

        } else {
            try {
                PrintWriter pw = response.getWriter();

                jsonMaker.setSuccess(0);
                jsonMaker.setError("not found");
                jsonMaker.setJsonObject(jsonObject);
                pw.println(jsonMaker);
                pw.close();
                pw.flush();
            }catch (Exception e){

            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
