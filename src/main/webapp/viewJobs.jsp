<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Job" %>
<%@ page import="model.User" %>

<%
    // Chặn quay lại sau khi logout
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    // Chặn người chưa login
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Job> jobs = (List<Job>) request.getAttribute("jobs");
%>

<%@ include file="navbar.jsp" %>

<html>
<head>
    <title>Available Jobs</title>
</head>
<body>
<h2>Job Listings</h2>
<table border="1">
    <tr><th>Title</th><th>Company</th><th>Location</th><th>Description</th><th>Action</th></tr>
    <%
        for (Job job : jobs) {
    %>
    <tr>
        <td><%= job.getTitle() %></td>
        <td><%= job.getCompany() %></td>
        <td><%= job.getLocation() %></td>
        <td><%= job.getDescription() %></td>
        <td>
            <%-- Chỉ user mới được thấy nút Apply --%>
            <% if ("user".equalsIgnoreCase(user.getRole())) { %>
            <form action="applyjob" method="post">
                <input type="hidden" name="jobId" value="<%= job.getId() %>" />
                <button type="submit">Apply</button>
            </form>
            <% } else { %>
            <span style="color: gray;">Only users can apply</span>
            <% } %>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
