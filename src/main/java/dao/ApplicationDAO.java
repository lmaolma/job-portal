package dao;

import model.Application;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ApplicationDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public boolean apply(Application app) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(app);
            tx.commit();
            return true;
        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
        }
        return false;
    }
}
