package servlet;

import model.Job;
import service.JobService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchJobServlet extends HttpServlet {

    private JobService jobService;

    @Override
    public void init() throws ServletException {
        // Lấy Spring Context và Bean Service
        ServletContext context = getServletContext();
        WebApplicationContext springCtx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        jobService = springCtx.getBean(JobService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("keyword");

        // Sử dụng JobService (Spring)
        List<Job> jobs = jobService.searchJobs(keyword);

        request.setAttribute("jobs", jobs);
        request.getRequestDispatcher("viewJobs.jsp").forward(request, response);
    }
}
