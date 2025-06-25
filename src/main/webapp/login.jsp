<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.User" %>

<%
    // Kiểm tra nếu đã đăng nhập thì chuyển hướng
    User user = (User) session.getAttribute("user");
    if (user != null) {
        if ("admin".equalsIgnoreCase(user.getRole())) {
            response.sendRedirect("admin-dashboard.jsp");
        } else if ("recruiter".equalsIgnoreCase(user.getRole())) {
            response.sendRedirect("postJob.jsp");
        } else {
            response.sendRedirect("dashboard.jsp");
        }
        return;
    }
%>

<%@ include file="navbar.jsp" %>

<html>
<head>
    <title>Login</title>
</head>
<body>
<div class="main-content" align="center">
    <h2>Login</h2>
    <form action="login" method="post">
        Username: <input type="text" name="username" required /><br/>
        Password: <input type="password" name="password" required /><br/>
        <button type="submit">Login</button>
    </form>
</div>
</body>
</html>
