package servlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import service.JobService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


public class ImportJobsServlet extends HttpServlet {

    private JobService jobService;

    @Override
    public void init() throws ServletException {
        ServletContext ctx = getServletContext();
        WebApplicationContext springCtx =
                WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);
        jobService = springCtx.getBean(JobService.class); // ✅ lấy Spring bean
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        // ✅ Đường dẫn file CSV
        String filePath = getServletContext().getRealPath("/") + "jobs_to_import.csv";

        // ✅ Gọi Service để import vào DB
        jobService.importJobsFromCSV(filePath);

        // ✅ Redirect lại admin-dashboard.jsp
        response.sendRedirect("admin-dashboard.jsp?import=success");
    }
}
