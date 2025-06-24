package servlet;

import dao.JobDAO;
import model.Job;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchJobServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("keyword");

        List<Job> jobs = JobDAO.searchJobs(keyword);

        request.setAttribute("jobs", jobs);
        request.getRequestDispatcher("viewJobs.jsp").forward(request, response);
    }
}
