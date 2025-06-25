package servlet;

import model.User;
import org.mindrot.jbcrypt.BCrypt;
import service.UserService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


public class RegisterServlet extends HttpServlet {

    private UserService userService;   // Spring-managed Service

    @Override
    public void init() {
        // Lấy Spring context từ ServletContext đã load trước đó (ContextLoaderListener)
        WebApplicationContext ctx =
                WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        // Lấy Bean từ context
        userService = ctx.getBean(UserService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy dữ liệu từ form
        String username = request.getParameter("username");
        String rawPassword = request.getParameter("password");
        String email = request.getParameter("email");
        String role = request.getParameter("role");

        if (role == null || role.trim().isEmpty()) {
            role = "user";
        }

        // Băm mật khẩu bằng BCrypt
        String hashedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt());

        // Tạo entity User
        User user = new User();
        user.setUsername(username);
        user.setPassword(hashedPassword);
        user.setEmail(email);
        user.setRole(role);

        // Gọi tầng Service (Spring Bean)
        boolean success = userService.registerUser(user);

        if (success) {
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("error", "Đăng ký thất bại. Vui lòng thử lại.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
