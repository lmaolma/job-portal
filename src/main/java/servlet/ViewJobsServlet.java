package servlet;

import model.Job;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import service.JobService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // ➜ recruiter: chỉ lấy job do chính họ đăng
        HttpSession session = req.getSession(false);
        List<Job> jobs;
        if (session != null &&
                session.getAttribute("user") != null &&
                "recruiter".equalsIgnoreCase(((model.User) session.getAttribute("user")).getRole())) {

            int ownerId = ((model.User) session.getAttribute("user")).getId();
            jobs = jobService.searchByOwner(ownerId);   // bạn thêm hàm này trong JobService/JobDAO
        } else {
            // user hoặc admin → lấy tất cả
            jobs = jobService.getAll();
        }

        req.setAttribute("jobs", jobs);
        req.getRequestDispatcher("viewJobs.jsp").forward(req, resp);
    }
}
