package servlet;



import dao.JobDAO;
import model.Job;
import util.JobIOUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/export-jobs")
public class ExportJobsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Job> jobs = JobDAO.getAllJobs();
        String path = getServletContext().getRealPath("/") + "jobs_export.csv";
        JobIOUtil.writeJobsToFile(jobs, path);

        response.sendRedirect("admin-dashboard.jsp?export=success");
    }
}
