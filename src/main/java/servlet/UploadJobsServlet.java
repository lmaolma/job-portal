package servlet;

import util.JobIOUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/upload-jobs")
@MultipartConfig
public class UploadJobsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Part filePart = request.getPart("csvFile"); // tên input
        String fileName = "uploaded_jobs.csv";
        String savePath = getServletContext().getRealPath("/") + fileName;

        // Ghi file vào thư mục webapp
        try (InputStream input = filePart.getInputStream();
             FileOutputStream output = new FileOutputStream(savePath)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        }

        // Import vào DB
        JobIOUtil.importJobsFromFile(savePath);

        response.sendRedirect("admin-dashboard.jsp?upload=success");
    }
}
