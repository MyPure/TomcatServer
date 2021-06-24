<%--
  Created by IntelliJ IDEA.
  User: bytedance
  Date: 2020/6/11
  Time: 6:37 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
</head>
<body>
<form method="POST" name="frmLogin" action="servlet/LoginServlet">
    <h1 align="center">用户登录</h1><br/>
    <center>
        <table border=1>
            <tr>
                <td>用户名：</td>
                <td>
                    <input type="text" name="username" value="Your name" size="20" maxlength="20"
                           onfocus="if (this.value=='Your name')  this.value='';"/>
                </td>
            </tr>
            <tr>
                <td>密&nbsp;&nbsp;码：</td>
                <td>
                    <input type="password" name="password" value="Your password" size="20" maxlength="20"
                           onfocus="if (this.value=='Your password')  this.value='';"/>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" name="Submit" value="提交" onClick="return validateLogin()"/>
                </td>
                <td>
                    <input type="reset" name="Reset" value="重置"/>
                </td>
            </tr>
        </table>
    </center>
</form>
<script language="javascript">
    function validateLogin() {
        var sUserName = document.frmLogin.username.value;
        var sPassword = document.frmLogin.password.value;

    }
</script>
</body>
</html>