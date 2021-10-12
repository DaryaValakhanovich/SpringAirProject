package dao;

import entities.Ticket;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class TicketDao {

    public Ticket findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Ticket.class, id);
    }

    public void save(Ticket ticket) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(ticket);
        tx1.commit();
        session.close();
    }

    public void update(Ticket ticket) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(ticket);
        tx1.commit();
        session.close();
    }

    public void delete(Ticket ticket) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(ticket);
        tx1.commit();
        session.close();
    }

    public List<Ticket> findAll() {
        List<Ticket> tickets =
                (List<Ticket>) HibernateSessionFactoryUtil
                        .getSessionFactory().openSession().createQuery("From Ticket ").list();
        return tickets;
    }

    public void deactivate(Ticket ticket) {
        ticket.setActive(false);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(ticket);
        tx1.commit();
        session.close();
    }

    public List<Ticket> findByAccountId(Integer id) {
        AccountDao accountDao = new AccountDao();
        List<Ticket> list =
                (List<Ticket>) HibernateSessionFactoryUtil
                        .getSessionFactory().openSession().createQuery("From Ticket where account = :account")
                        .setParameter("account", accountDao.findById(id)).list();
        return list;
    }
}
