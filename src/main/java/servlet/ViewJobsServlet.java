package servlet;

import dao.JobDAO;
import model.Job;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewjobs")
public class ViewJobsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Job> jobs = JobDAO.getAllJobs();
        request.setAttribute("jobs", jobs);
        RequestDispatcher rd = request.getRequestDispatcher("viewJobs.jsp");
        rd.forward(request, response);
    }
}
