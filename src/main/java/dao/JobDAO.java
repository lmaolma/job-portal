package dao;

import model.Job;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JobDAO {

    @Autowired
    private SessionFactory sessionFactory;

    /* Thêm Job */
    public boolean postJob(Job job) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(job);
            tx.commit();
            return true;
        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
        }
        return false;
    }

    /* Lấy tất cả Job */
    public List<Job> getAllJobs() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Job", Job.class).list();
        }
    }

    /* Tìm kiếm */
    public List<Job> searchJobs(String kw) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "FROM Job WHERE title LIKE :kw OR company LIKE :kw OR description LIKE :kw",
                            Job.class)
                    .setParameter("kw", "%" + kw + "%")
                    .list();
        }
    }

    /* Lưu từ CSV */
    public void save(Job job) {
        postJob(job);   // hoặc tách logic nếu cần
    }
}
