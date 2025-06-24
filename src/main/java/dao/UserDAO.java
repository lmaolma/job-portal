package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public boolean registerUser(User user) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            return true;
        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
        }
        return false;
    }

    public User getUserByUsername(String username) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "FROM User WHERE username = :u", User.class)
                    .setParameter("u", username)
                    .uniqueResult();
        }
    }

    public User login(String username, String rawPwd) {
        User u = getUserByUsername(username);
        return (u != null && BCrypt.checkpw(rawPwd, u.getPassword())) ? u : null;
    }

    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User", User.class).list();
        }
    }
}
