package util;

import dao.JobDAO;
import model.Job;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JobIOUtil {

    // ✅ Ghi danh sách công việc ra file CSV
    public static void writeJobsToFile(List<Job> jobs, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Job job : jobs) {
                writer.write(job.getTitle() + "," + job.getCompany() + "," +
                        job.getLocation() + "," + job.getDescription().replace(",", " "));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ❌ Hàm chỉ in ra file – không còn dùng nữa
    // public static void readJobsFromFile(String filePath) { ... }

    // ✅ Đọc file và TRẢ VỀ danh sách Job (dùng cho service)
    public static List<Job> parseJobsFromFile(String filePath) {
        List<Job> jobs = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 4) continue;

                Job job = new Job();
                job.setTitle(parts[0].trim());
                job.setCompany(parts[1].trim());
                job.setLocation(parts[2].trim());
                job.setDescription(parts[3].trim());

                jobs.add(job);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return jobs;
    }

    // ✅ Dùng trực tiếp để import vào DB (nếu không dùng Service)
    public static void importJobsFromFile(String filePath) {
        List<Job> jobs = parseJobsFromFile(filePath);
        for (Job job : jobs) {
            JobDAO.postJob(job); // sử dụng trực tiếp DAO
        }
    }
}
