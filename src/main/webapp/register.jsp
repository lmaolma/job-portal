<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.User" %>
<jsp:include page="navbar.jsp" />

<%
    // Kiểm tra quyền admin nếu cần giới hạn truy cập đăng ký
    User sessionUser = (User) session.getAttribute("user");
    if (sessionUser != null && !"admin".equalsIgnoreCase(sessionUser.getRole())) {
        response.sendRedirect("dashboard.jsp");
        return;
    }
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Register</title>
</head>
<body>
<h2>Register</h2>

<form action="register" method="post">
    <label>Username:</label><br/>
    <input type="text" name="username" required><br/><br/>

    <label>Password:</label><br/>
    <input type="password" name="password" required><br/><br/>

    <label>Email:</label><br/>
    <input type="email" name="email" required><br/><br/>

    <label>Role:</label><br/>
    <select name="role">
        <option value="user" selected>User</option>
        <option value="recruiter">Recruiter</option>
        <option value="admin">Admin</option>
    </select><br/><br/>

    <button type="submit">Register</button>
</form>

<c:if test="${not empty error}">
    <p style="color:red">${error}</p>
</c:if>

</body>
</html>
