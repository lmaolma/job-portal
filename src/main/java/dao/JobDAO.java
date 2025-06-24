package dao;

import model.Job;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class JobDAO {

    @Autowired
    private SessionFactory sessionFactory;

    /* ===============  C R U D  =============== */

    public void save(Job job) {
        sessionFactory.getCurrentSession().save(job);
    }

    public boolean postJob(Job job) {
        try {
            sessionFactory.getCurrentSession().save(job);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Job> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Job", Job.class)
                .list();
    }

    public List<Job> search(String kw) {
        String hql = "FROM Job " +
                "WHERE title LIKE :kw " +
                "OR company LIKE :kw " +
                "OR location LIKE :kw " +
                "OR description LIKE :kw";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, Job.class)
                .setParameter("kw", "%" + kw + "%")
                .list();
    }
}
