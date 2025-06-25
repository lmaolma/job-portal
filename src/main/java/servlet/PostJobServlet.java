package servlet;

import model.Job;
import model.User;
import service.JobService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


public class PostJobServlet extends HttpServlet {

    private JobService jobService;

    @Override
    public void init() throws ServletException {
        ServletContext ctx = getServletContext();
        WebApplicationContext springCtx =
                WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);
        jobService = springCtx.getBean(JobService.class); // ✅ lấy JobService từ Spring
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // ✅ chỉ cho phép admin hoặc recruiter post job
        if (user == null || !( "admin".equalsIgnoreCase(user.getRole())
                || "recruiter".equalsIgnoreCase(user.getRole()))) {
            response.sendRedirect("login.jsp");
            return;
        }

        String title       = request.getParameter("title");
        String company     = request.getParameter("company");
        String location    = request.getParameter("location");
        String description = request.getParameter("description");

        Job job = new Job(title, company, location, description, user.getId());

        boolean success = jobService.postJob(job);  // ✅ gọi service thay vì DAO

        if (success) {
            response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("error", "Failed to post job.");
            request.getRequestDispatcher("postJob.jsp").forward(request, response);
        }
    }
}
