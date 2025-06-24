package servlet;

import model.Job;
import service.JobService;
import util.JobIOUtil;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/export-jobs")
public class ExportJobsServlet extends HttpServlet {

    private JobService jobService;

    @Override
    public void init() {
        ServletContext context = getServletContext();
        WebApplicationContext springCtx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        jobService = springCtx.getBean(JobService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Job> jobs = jobService.getAllJobs();
        String path = getServletContext().getRealPath("/") + "jobs_export.csv";
        JobIOUtil.writeJobsToFile(jobs, path);

        response.sendRedirect("admin-dashboard.jsp?export=success");
    }
}
