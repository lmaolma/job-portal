<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.User" %>

<%
    // ✅ Ngăn quay lại sau khi logout
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    // ✅ Cho phép cả admin và recruiter
    User sessionUser = (User) session.getAttribute("user");
    if (sessionUser == null ||
            (!"admin".equalsIgnoreCase(sessionUser.getRole()) &&
                    !"recruiter".equalsIgnoreCase(sessionUser.getRole()))) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<jsp:include page="navbar.jsp" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Post a Job</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
    <h2 class="text-primary mb-4">Post a New Job</h2>

    <form action="postjob" method="post">
        <div class="form-group">
            <label>Job Title:</label>
            <input type="text" name="title" class="form-control" required />
        </div>

        <div class="form-group">
            <label>Company:</label>
            <input type="text" name="company" class="form-control" required />
        </div>

        <div class="form-group">
            <label>Location:</label>
            <input type="text" name="location" class="form-control" required />
        </div>

        <div class="form-group">
            <label>Description:</label>
            <textarea name="description" rows="5" class="form-control" required></textarea>
        </div>

        <button type="submit" class="btn btn-primary">Post Job</button>
        <a href="admin-dashboard.jsp" class="btn btn-secondary ml-2">Back</a>
    </form>
</div>

</body>
</html>
