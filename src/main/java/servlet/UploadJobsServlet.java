package servlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import service.JobService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/upload-jobs")
@MultipartConfig
public class UploadJobsServlet extends HttpServlet {

    private JobService jobService;

    @Override
    public void init() {
        ServletContext ctx = getServletContext();
        WebApplicationContext springCtx =
                WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);
        jobService = springCtx.getBean(JobService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Part filePart = request.getPart("csvFile");      // <input type="file" name="csvFile">
        String fileName = "uploaded_jobs.csv";
        String savePath = getServletContext().getRealPath("/") + fileName;

        /* Lưu file CSV vào thư mục webapp */
        try (InputStream in  = filePart.getInputStream();
             FileOutputStream out = new FileOutputStream(savePath)) {

            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
        }

        /* Gọi service để import vào DB */
        jobService.importJobsFromCSV(savePath);

        /* Chuyển về dashboard */
        response.sendRedirect("admin-dashboard.jsp?upload=success");
    }
}
