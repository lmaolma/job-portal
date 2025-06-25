// JobDAO.java
package dao;

import model.Job;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JobDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Job job) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(job);
    }

    public boolean postJob(Job job) {
        try {
            save(job);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Job> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Job", Job.class).getResultList();
    }

    public List<Job> search(String keyword) {
        Session session = sessionFactory.getCurrentSession();
        Query<Job> query = session.createQuery("FROM Job j WHERE j.title LIKE :kw OR j.description LIKE :kw", Job.class);
        query.setParameter("kw", "%" + keyword + "%");
        return query.getResultList();
    }

    // ✅ ĐÃ BỔ SUNG HÀM NÀY
    public List<Job> searchByOwner(int ownerId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Job> query = session.createQuery("FROM Job j WHERE j.owner.id = :ownerId", Job.class);
        query.setParameter("ownerId", ownerId);
        return query.getResultList();
    }
}
