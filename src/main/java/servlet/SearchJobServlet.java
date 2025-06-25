package servlet;

import model.Job;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import service.JobService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;


public class SearchJobServlet extends HttpServlet {

    private JobService jobService;

    @Override
    public void init() throws ServletException {
        ServletContext ctx = getServletContext();
        WebApplicationContext springCtx =
                WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);
        jobService = springCtx.getBean(JobService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("keyword");
        List<Job> jobs = jobService.searchJobs(keyword); // ✅ Dùng Service thay DAO

        request.setAttribute("jobs", jobs);
        request.getRequestDispatcher("viewJobs.jsp").forward(request, response);
    }
}
