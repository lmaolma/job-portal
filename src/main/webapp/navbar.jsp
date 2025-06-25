<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        ...
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">

                <c:choose>
                    <c:when test="${sessionScope.user == null}">
                        <li class="nav-item active"><a class="nav-link" href="index.jsp">Home</a></li>
                        <li class="nav-item active"><a class="nav-link" href="login.jsp">Login</a></li>
                        <li class="nav-item active"><a class="nav-link" href="register.jsp">Register</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item nav-welcome">
                            Welcome, <strong>${sessionScope.user.username}</strong>
                        </li>

                        <c:choose>
                            <c:when test="${sessionScope.user.role == 'admin'}">
                                <li class="nav-item active"><a class="nav-link" href="admin-dashboard.jsp">Admin Panel</a></li>
                                <li class="nav-item active"><a class="nav-link" href="viewJobs.jsp">All Jobs</a></li>
                            </c:when>
                            <c:when test="${sessionScope.user.role == 'recruiter'}">
                                <li class="nav-item active"><a class="nav-link" href="postJob.jsp">Post Job</a></li>
                                <li class="nav-item active"><a class="nav-link" href="viewJobs.jsp">My Jobs</a></li>
                            </c:when>
                            <c:when test="${sessionScope.user.role == 'user'}">
                                <li class="nav-item active"><a class="nav-link" href="dashboard.jsp">Dashboard</a></li>
                                <li class="nav-item active"><a class="nav-link" href="viewJobs.jsp">Browse Jobs</a></li>
                            </c:when>
                        </c:choose>

                        <li class="nav-item active"><a class="nav-link" href="logout">Logout</a></li>
                    </c:otherwise>
                </c:choose>

            </ul>
        </div>
    </nav>
</div>
</body>
</html>
