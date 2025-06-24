package servlet;

import dao.ApplicationDAO;
import model.Application;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/applyjob")
public class ApplyJobServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ✅ Kiểm tra quyền
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !"user".equalsIgnoreCase(user.getRole())) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy ID công việc từ form
        int jobId = Integer.parseInt(request.getParameter("jobId"));

        // ✅ Tạo đối tượng Application (Hibernate Entity)
        Application app = new Application();
        app.setUserId(user.getId());
        app.setJobId(jobId);

        // ✅ Gọi DAO để lưu với Hibernate
        boolean applied = ApplicationDAO.apply(app);

        if (applied) {
            response.sendRedirect("applySuccess.jsp");
        } else {
            request.setAttribute("error", "Failed to apply for job.");
            request.getRequestDispatcher("viewJobs.jsp").forward(request, response);
        }
    }
}
