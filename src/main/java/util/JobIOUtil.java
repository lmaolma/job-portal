package util;

import dao.JobDAO;
import model.Job;

import java.io.*;
import java.util.List;

public class JobIOUtil {

    // ✅ Ghi danh sách công việc ra file
    public static void writeJobsToFile(List<Job> jobs, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Job job : jobs) {
                writer.write(job.getId() + "," + job.getTitle() + "," +
                        job.getCompany() + "," + job.getLocation() + "," +
                        job.getDescription().replace(",", " "));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ✅ Đọc file và hiển thị dòng
    public static void readJobsFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            System.out.println("Danh sách công việc từ file:");
            while ((line = reader.readLine()) != null) {
                System.out.println("• " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ✅ Đọc file và lưu từng job vào DB
    public static void importJobsFromFile(String filePath) {
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

                JobDAO.postJob(job);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
