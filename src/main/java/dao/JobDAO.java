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

    // Thêm Job mới
    public static boolean postJob(Job job) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(job);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    // Lưu job (được dùng trong import)
    public void save(Job job) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(job);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Lấy tất cả Jobs
    public List<Job> getAllJobs() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Job", Job.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Tìm kiếm Job theo từ khóa
    public List<Job> searchJobs(String keyword) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "FROM Job WHERE title LIKE :kw OR description LIKE :kw OR company LIKE :kw",
                            Job.class)
                    .setParameter("kw", "%" + keyword + "%")
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
