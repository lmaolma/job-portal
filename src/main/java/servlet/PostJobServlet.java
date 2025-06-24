package servlet;

import dao.JobDAO;
import model.Job;
import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/postjob")
public class PostJobServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String title = request.getParameter("title");
        String company = request.getParameter("company");
        String location = request.getParameter("location");
        String description = request.getParameter("description");

        Job job = new Job(title, company, location, description, user.getId());

        boolean success = JobDAO.postJob(job);
        if (success) {
            response.sendRedirect("dashboard.jsp");
        } else {
            response.getWriter().println("Failed to post job.");
        }
    }
}
