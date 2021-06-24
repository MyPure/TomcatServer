package servlet;

import com.sun.net.httpserver.HttpsServer;
import returnmsg.ReturnMsg;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class ProServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charser=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");


        String methodName = request.getParameter("method");
        if(methodName.equals("") || methodName.trim().isEmpty()) {
            responseError(response,"");
            return;
        }

        Method method = null;
        try {
            method = getClass().getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
        } catch (Exception e) {
            responseError(response, "Method " + methodName + " isn't exist.");
            return;
        }
        try {
            method.invoke(this,request,response);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    protected void responseError(HttpServletResponse response,String err) throws IOException {
        response.getWriter().write(ReturnMsg.getErrorMsg(err).toJson());
    }
}
