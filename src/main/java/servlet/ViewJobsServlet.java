package servlet;

import model.Job;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import service.JobService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewjobs")
public class ViewJobsServlet extends HttpServlet {

    private JobService jobService;

    @Override
    public void init() {
        ServletContext ctx = getServletContext();
        WebApplicationContext springCtx =
                WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);
        jobService = springCtx.getBean(JobService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Job> jobs = jobService.getAllJobs(); // ✅ dùng service thay vì DAO trực tiếp
        request.setAttribute("jobs", jobs);
        RequestDispatcher rd = request.getRequestDispatcher("viewJobs.jsp");
        rd.forward(request, response);
    }
}
