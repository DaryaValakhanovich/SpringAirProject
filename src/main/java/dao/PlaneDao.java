package dao;

import entities.Plane;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class PlaneDao {
    public Plane findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Plane.class, id);
    }

    public void save(Plane plane) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(plane);
        tx1.commit();
        session.close();
    }

    public void update(Plane plane) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(plane);
        tx1.commit();
        session.close();
    }

    public void delete(Plane plane) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(plane);
        tx1.commit();
        session.close();
    }

    public List<Plane> findAll() {
        List<Plane> planes =
                (List<Plane>) HibernateSessionFactoryUtil
                        .getSessionFactory().openSession().createQuery("From Plane ").list();
        return planes;
    }
}
