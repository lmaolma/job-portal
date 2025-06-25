package dao;

import model.Application;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ApplicationDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public boolean apply(Application app) {
        try {
            sessionFactory.getCurrentSession().save(app);
            return true;
        } catch (Exception e) {
            e.printStackTrace(); // có thể dùng logger
            return false;
        }
    }
}
