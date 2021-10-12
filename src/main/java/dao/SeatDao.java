package dao;

import entities.Seat;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class SeatDao {
    public Seat findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Seat.class, id);
    }

    public void save(Seat seat) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(seat);
        tx1.commit();
        session.close();
    }

    public void update(Seat seat) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(seat);
        tx1.commit();
        session.close();
    }

    public void delete(Seat seat) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(seat);
        tx1.commit();
        session.close();
    }

    public List<Seat> findAll() {
        List<Seat> seats =
                (List<Seat>) HibernateSessionFactoryUtil
                        .getSessionFactory().openSession().createQuery("From Seat ").list();
        return seats;
    }

    public List<Seat> findByTicketId(Integer ticketId) {
        List<Seat> seats =
                (List<Seat>) HibernateSessionFactoryUtil
                        .getSessionFactory().openSession().createQuery("From Seat where ticket = :ticket")
                        .setParameter("ticket", new TicketDao().findById(ticketId)).list();
        return seats;
    }
}
