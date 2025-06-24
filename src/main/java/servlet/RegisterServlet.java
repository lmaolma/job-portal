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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserService userService;   // Bean Spring

    @Override
    public void init() {
        /* Lấy Spring container đã khởi tạo bởi ContextLoaderListener */
        WebApplicationContext ctx =
                WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        /* Lấy bean UserService đã @Service */
        userService = ctx.getBean(UserService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String rawPassword = request.getParameter("password");
        String email = request.getParameter("email");

        // Nếu không có field "role" thì mặc định là user
        String role = request.getParameter("role");
        if (role == null || role.isEmpty()) role = "user";

        /* Mã hoá mật khẩu */
        String hashed = BCrypt.hashpw(rawPassword, BCrypt.gensalt());

        /* Tạo đối tượng User */
        User user = new User();
        user.setUsername(username);
        user.setPassword(hashed);
        user.setEmail(email);
        user.setRole(role);

        /* Gọi tầng Service thay vì DAO trực tiếp */
        boolean ok = userService.registerUser(user);

        if (ok) {
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("error", "Đăng ký thất bại. Vui lòng thử lại!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
