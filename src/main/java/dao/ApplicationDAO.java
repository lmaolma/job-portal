package dao;

import model.Application;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class ApplicationDAO {

    // ✅ Nộp đơn ứng tuyển
    public static boolean apply(Application application) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(application);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    // ✅ Kiểm tra xem user đã apply job chưa
    public static boolean hasApplied(int userId, int jobId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Application WHERE userId = :uid AND jobId = :jid";
            Application app = session.createQuery(hql, Application.class)
                    .setParameter("uid", userId)
                    .setParameter("jid", jobId)
                    .uniqueResult();
            return app != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ (Tuỳ chọn) Lấy tất cả applications của 1 user
    public static List<Application> getApplicationsByUser(int userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Application WHERE userId = :uid", Application.class)
                    .setParameter("uid", userId)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
