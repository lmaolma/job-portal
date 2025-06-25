package servlet;

import model.Job;
import service.JobService;
import util.JobIOUtil;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;


public class ExportJobsServlet extends HttpServlet {

    private JobService jobService;

    @Override
    public void init() throws ServletException {
        ServletContext ctx = getServletContext();
        WebApplicationContext springCtx =
                WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);
        jobService = springCtx.getBean(JobService.class); // ✅ Inject Spring service
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        // ✅ Lấy danh sách job từ Service
        List<Job> jobs = jobService.getAllJobs();

        // ✅ Ghi ra file
        String path = getServletContext().getRealPath("/") + "jobs_export.csv";
        JobIOUtil.writeJobsToFile(jobs, path);

        // ✅ Redirect lại admin-dashboard.jsp
        response.sendRedirect("admin-dashboard.jsp?export=success");
    }
}
