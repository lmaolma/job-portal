<%@ page import="model.User" %>
<%
  // Chặn lưu cache (ngăn quay lại khi logout)
  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
  response.setHeader("Pragma", "no-cache");
  response.setDateHeader("Expires", 0);

  // Kiểm tra quyền admin
  User user = (User) session.getAttribute("user");
  if (user == null || !"admin".equalsIgnoreCase(user.getRole())) {
    response.sendRedirect("login.jsp");
    return;
  }
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Admin Dashboard</title>
  <link rel="stylesheet"
        href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body>

<jsp:include page="navbar.jsp" />

<div class="container mt-5">
  <h2 class="text-center text-primary">Welcome to Admin Dashboard</h2>
  <p class="text-center">Hello, <strong><%= user.getUsername() %></strong>. You have admin privileges.</p>

  <!-- ✅ Thông báo Export -->
  <% if ("success".equals(request.getParameter("export"))) { %>
  <div class="alert alert-success text-center">
    ✅ Job list exported successfully to <strong>jobs_export.csv</strong>
  </div>
  <% } %>

  <!-- ✅ Thông báo Import -->
  <% if ("success".equals(request.getParameter("import"))) { %>
  <div class="alert alert-info text-center">
    📥 Job list imported successfully from <strong>jobs_to_import.csv</strong>
  </div>
  <% } %>

  <!-- ✅ Thông báo Upload -->
  <% if ("success".equals(request.getParameter("upload"))) { %>
  <div class="alert alert-success text-center">
    ✅ Jobs imported successfully from uploaded CSV file!
  </div>
  <% } %>

  <!-- ✅ Thông báo Export Users -->
  <% if ("success".equals(request.getParameter("userExport"))) { %>
  <div class="alert alert-success text-center">
    👤 User list exported successfully to <strong>users_export.csv</strong>
  </div>
  <% } %>

  <div class="row justify-content-center mt-4">
    <div class="col-md-6">
      <ul class="list-group">
        <li class="list-group-item"><a href="viewJobs.jsp">View All Jobs</a></li>
        <li class="list-group-item"><a href="postJob.jsp">Post New Job</a></li>

        <!-- ✅ Export Jobs -->
        <li class="list-group-item">
          <form action="export-jobs" method="get" style="display:inline;">
            <button class="btn btn-link p-0 m-0 align-baseline" type="submit">
              📁 Export Jobs to File
            </button>
          </form>
        </li>

        <!-- ✅ Import Jobs -->
        <li class="list-group-item">
          <form action="import-jobs" method="post" style="display:inline;">
            <button class="btn btn-link p-0 m-0 align-baseline" type="submit">
              📥 Import Jobs from File
            </button>
          </form>
        </li>

        <!-- ✅ Upload CSV -->
        <li class="list-group-item">
          <form action="upload-jobs" method="post" enctype="multipart/form-data" style="display:inline;">
            <input type="file" name="csvFile" accept=".csv" required />
            <button class="btn btn-sm btn-outline-primary ml-2">📤 Upload & Import CSV</button>
          </form>
        </li>

        <!-- ✅ Export Users -->
        <li class="list-group-item">
          <form action="export-users" method="get" style="display:inline;">
            <button class="btn btn-link p-0 m-0 align-baseline" type="submit">
              👤 Export Users to File
            </button>
          </form>
        </li>

        <li class="list-group-item"><a href="#">Manage Users (future feature)</a></li>
        <li class="list-group-item"><a href="#">View Applications (future feature)</a></li>
      </ul>
    </div>
  </div>
</div>

</body>
</html>
