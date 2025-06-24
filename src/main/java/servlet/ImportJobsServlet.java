package servlet;

import util.JobIOUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/import-jobs")
public class ImportJobsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String filePath = getServletContext().getRealPath("/") + "jobs_to_import.csv";
        JobIOUtil.importJobsFromFile(filePath);

        response.sendRedirect("admin-dashboard.jsp?import=success");
    }
}
