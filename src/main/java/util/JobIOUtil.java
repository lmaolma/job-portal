package util;

import model.Job;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Chỉ còn nhiệm vụ IO – KHÔNG chạm vào DAO / Hibernate
 */
public class JobIOUtil {

    /* Ghi ra CSV ----------------------------------------------------------- */
    public static void writeJobsToFile(List<Job> jobs, String path) {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(path))) {
            for (Job j : jobs) {
                w.write(String.join(",",
                        j.getTitle(),
                        j.getCompany(),
                        j.getLocation(),
                        j.getDescription().replace(",", " ")));
                w.newLine();
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    /* Đọc CSV & trả về List<Job> ------------------------------------------ */
    public static List<Job> parseJobsFromFile(String path) {
        List<Job> list = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = r.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length < 4) continue;
                Job j = new Job();
                j.setTitle(p[0].trim());
                j.setCompany(p[1].trim());
                j.setLocation(p[2].trim());
                j.setDescription(p[3].trim());
                list.add(j);
            }
        } catch (IOException e) { e.printStackTrace(); }
        return list;
    }
}
