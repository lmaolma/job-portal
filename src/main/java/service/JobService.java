// JobService.java
package service;

import dao.JobDAO;
import model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.JobIOUtil;

import java.util.List;

@Service
@Transactional
public class JobService {

    @Autowired
    private JobDAO jobDAO;

    public void importJobsFromCSV(String path) {
        List<Job> jobs = JobIOUtil.parseJobsFromFile(path);
        for (Job j : jobs) {
            jobDAO.save(j);
        }
    }

    public void save(Job job) {
        jobDAO.save(job);
    }

    public boolean postJob(Job job) {
        return jobDAO.postJob(job);
    }

    @Transactional(readOnly = true)
    public List<Job> getAll() {
        return jobDAO.getAll();
    }

    @Transactional(readOnly = true)
    public List<Job> search(String keyword) {
        return jobDAO.search(keyword);
    }

    public List<Job> getAllJobs() {
        return getAll();
    }

    public List<Job> searchJobs(String keyword) {
        return search(keyword);
    }

    // ✅ ĐÃ BỔ SUNG HÀM NÀY
    public List<Job> searchByOwner(int ownerId) {
        return jobDAO.searchByOwner(ownerId);
    }
}
