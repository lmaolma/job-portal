<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Navbar</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
            crossorigin="anonymous"></script>

    <style type="text/css">
        * {
            padding: 0px;
            margin: 0px;
            box-sizing: border-box;
        }
        .main-container {
            text-align: center;
        }
        .navbar {
            font-weight: bolder;
            font-size: 25px;
        }
        .navbar-brand {
            font-size: 40px;
            font-family: Arial Black;
        }
        .job {
            color: blue;
            font-size: 50px;
            -webkit-text-stroke: 0.5px orange;
            font-style: italic;
        }
        .tracker {
            color: orange;
            font-size: 30px;
            font-style: italic;
            text-decoration: overline;
        }
        .nav-link {
            text-decoration: underline;
        }
        .nav-welcome {
            margin-top: 8px;
            font-size: 16px;
            margin-right: 10px;
        }
    </style>
</head>
<body>
<div class="main-container">

    <nav class="navbar navbar-expand-lg navbar-light">
        <a class="navbar-brand" href="#"><span class="job">JOB</span><span class="tracker">Tracker</span></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">

                <%
                    User user = (User) session.getAttribute("user");
                    if (user == null) {
                %>
                <li class="nav-item active">
                    <a class="nav-link" href="index.jsp">Home</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="login.jsp">Login</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="register.jsp">Register</a>
                </li>
                <% } else { %>
                <li class="nav-item nav-welcome">
                    Welcome, <strong><%= user.getUsername() %></strong>
                </li>

                <%-- Phân quyền menu theo role --%>
                <% if ("admin".equalsIgnoreCase(user.getRole())) { %>
                <li class="nav-item active">
                    <a class="nav-link" href="admin-dashboard.jsp">Admin Panel</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="viewJobs.jsp">All Jobs</a>
                </li>
                <% } else if ("recruiter".equalsIgnoreCase(user.getRole())) { %>
                <li class="nav-item active">
                    <a class="nav-link" href="postJob.jsp">Post Job</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="viewJobs.jsp">My Jobs</a>
                </li>
                <% } else if ("user".equalsIgnoreCase(user.getRole())) { %>
                <li class="nav-item active">
                    <a class="nav-link" href="dashboard.jsp">Dashboard</a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="viewJobs.jsp">Browse Jobs</a>
                </li>
                <% } %>

                <li class="nav-item active">
                    <a class="nav-link" href="logout">Logout</a>
                </li>
                <% } %>

            </ul>
        </div>
    </nav>
</div>
</body>
</html>
