package servlet;

import model.User;
import service.UserService; // ✅ Import Spring Service
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService; // ✅ Sử dụng Spring service

    @Override
    public void init() throws ServletException {
        // Lấy Spring context và inject bean
        ServletContext context = getServletContext();
        WebApplicationContext springCtx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        userService = springCtx.getBean(UserService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // ✅ Sử dụng service thay vì DAO trực tiếp
        User user = userService.getUserByUsername(username);

        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            if ("admin".equalsIgnoreCase(user.getRole())) {
                response.sendRedirect("admin-dashboard.jsp");
            } else {
                response.sendRedirect("dashboard.jsp");
            }
        } else {
            request.setAttribute("error", "Invalid username or password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
