package servlet;

import model.User;
import service.UserService;
import util.UserIOUtil;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;


public class ExportUsersServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        WebApplicationContext springCtx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        userService = springCtx.getBean(UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        // Lấy danh sách user từ Spring Service
        List<User> users = userService.getAllUsers();

        // Ghi ra file CSV
        String filePath = getServletContext().getRealPath("/") + "users_export.csv";
        UserIOUtil.writeUsersToFile(users, filePath);

        // Chuyển hướng về admin-dashboard
        response.sendRedirect("admin-dashboard.jsp?userExport=success");
    }
}
