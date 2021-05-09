<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Jobs Login</title>
        <jsp:include page="../../jspf/scriptLib.jspf" />
    </head>
    <body>
        <jsp:include page="../../jspf/navBar.jspf" />
        <div class="loginBody">
        <h1>Login</h1>
        <%
            if((Boolean)request.getAttribute("loginFailed")) {
                %>
                <p style="font-weight: bold;">The username or password you entered are not correct. Please try again.</p>
                <%
            } else {
                %>
                <p>You must log in to view applications.</p>
                <%}
        %>
        
        <form method="POST" action="/Java3_Final_Proj/login">
            <label for="username">Username</label>
            <input type="text" name="username" id="username" /><br><br>
            <label for="password">Password</label>
            <input type="password" name="password" id="password" /><br><br>
            <input type="submit" value="Log In" />
        </form>
        </div>
    </body>
</html>
