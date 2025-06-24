package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Load hibernate.cfg.xml and build session factory
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Get SessionFactory (dùng lại trong DAO hoặc nơi khác)
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // Shutdown Hibernate
    public static void shutdown() {
        getSessionFactory().close();
    }
}
