<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.User" %>
<jsp:include page="navbar.jsp" />

<%
    // Ngăn quay lại sau khi logout
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    // Kiểm tra đăng nhập và role = user
    User user = (User) session.getAttribute("user");
    if (user == null || !"user".equalsIgnoreCase(user.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<%@ include file="navbar.jsp" %>

<html>
<head>
    <title>Dashboard</title>
</head>
<body>
<div class="main-content" align="center">
    <h3>Welcome, <%= user.getUsername() %>!</h3>

    <%-- Chỉ hiển thị phần này nếu bạn mở quyền recruiter vào dashboard --%>
    <% if ("recruiter".equalsIgnoreCase(user.getRole())) { %>
    <form action="postjob" method="post">
        <h3>Post a Job</h3>
        Title: <input type="text" name="title" required /><br>
        Company: <input type="text" name="company" required /><br>
        Location: <input type="text" name="location" required /><br>
        Description:<br/>
        <textarea name="description" rows="5" cols="30"></textarea><br>
        <button type="submit">Post Job</button>
    </form>
    <% } %>

    <p><a href="viewjobs">View Jobs</a></p>
</div>
</body>
</html>
