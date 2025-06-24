package dao;

import model.Job;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class JobDAO {

    // Thêm Job mới
    public static boolean postJob(Job job) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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

    // Lấy tất cả Jobs
    public static List<Job> getAllJobs() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Job", Job.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Tìm kiếm Job theo từ khóa
    public static List<Job> searchJobs(String keyword) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
