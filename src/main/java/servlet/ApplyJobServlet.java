package servlet;

import model.Application;
import model.User;
import service.ApplicationService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


public class ApplyJobServlet extends HttpServlet {

    private ApplicationService applicationService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        applicationService = springContext.getBean(ApplicationService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !"user".equalsIgnoreCase(user.getRole())) {
            response.sendRedirect("login.jsp");
            return;
        }

        int jobId = Integer.parseInt(request.getParameter("jobId"));

        Application app = new Application();
        app.setUserId(user.getId());
        app.setJobId(jobId);

        boolean applied = applicationService.apply(app);

        if (applied) {
            response.sendRedirect("applySuccess.jsp");
        } else {
            request.setAttribute("error", "Failed to apply for job.");
            request.getRequestDispatcher("viewJobs.jsp").forward(request, response);
        }
    }
}
