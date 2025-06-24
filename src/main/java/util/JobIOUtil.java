package util;

import model.Job;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Tiện ích IO cho Job: chỉ xử lý CSV <‐> List&lt;Job&gt;
 * - KHÔNG thao tác DB tại đây (mọi thao tác DB sẽ do JobService xử lý).
 */
public class JobIOUtil {

    /* ─────────────── EXPORT ─────────────── */

    /** Ghi danh sách Job ra file CSV */
    public static void writeJobsToFile(List<Job> jobs, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Job job : jobs) {
                writer.write(job.getTitle()       + "," +
                        job.getCompany()     + "," +
                        job.getLocation()    + "," +
                        job.getDescription().replace(",", " "));
                writer.newLine();
            }
        } catch (IOException e) {
            // TODO: thay bằng logger phù hợp (SLF4J, Log4j…)
            e.printStackTrace();
        }
    }

    /* ─────────────── IMPORT ─────────────── */

    /** Đọc file CSV và trả về danh sách Job (không lưu DB ở đây) */
    public static List<Job> parseJobsFromFile(String filePath) {
        List<Job> jobs = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 4) continue;      // Bỏ dòng lỗi định dạng

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

    /**
     * Hàm cũ import thẳng vào DB đã bị loại bỏ để tuân thủ kiến trúc.
     * Hãy dùng: JobService.importJobsFromCSV().
     */
    @Deprecated
    public static void importJobsFromFile(String filePath) {
        throw new UnsupportedOperationException(
                "Đã chuyển logic import sang JobService.importJobsFromCSV(filePath)");
    }
}
