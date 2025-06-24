package service;

import dao.JobDAO;
import model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.JobIOUtil;

import java.util.List;

@Service
@Transactional   // Spring sẽ mở / commit / rollback transaction
public class JobService {

    @Autowired
    private JobDAO jobDAO;

    /* -------------------- IMPORT / EXPORT -------------------- */

    /**
     * Đọc danh sách Job từ file CSV và lưu vào DB
     */
    public void importJobsFromCSV(String filePath) {
        // ✅ Đổi sang hàm mới parseJobsFromFile (trả về List<Job>)
        List<Job> jobs = JobIOUtil.parseJobsFromFile(filePath);

        // Lưu từng Job bằng DAO
        for (Job job : jobs) {
            jobDAO.postJob(job);   // dùng phương thức đã có trong JobDAO
        }
    }

    /* -------------------- CRUD / SEARCH -------------------- */

    /** Thêm job mới (dùng cho PostJobServlet) */
    public boolean postJob(Job job) {
        return jobDAO.postJob(job);
    }

    /** Lấy toàn bộ job */
    @Transactional(readOnly = true)
    public List<Job> getAllJobs() {
        return jobDAO.getAllJobs();
    }

    /** Tìm kiếm job theo từ khóa */
    @Transactional(readOnly = true)
    public List<Job> searchJobs(String keyword) {
        return jobDAO.searchJobs(keyword);
    }
}
