package dao;

import entities.Account;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class AccountDao {

    public Account findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Account.class, id);
    }

    public Account findByEmail(String email) {
        List<Account> accounts =
                (List<Account>) HibernateSessionFactoryUtil
                        .getSessionFactory().openSession().createQuery("From Account where email = :email")
                        .setParameter("email", email).list();
        return accounts.get(0);
    }

    public void save(Account account) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(account);
        tx1.commit();
        session.close();
    }

    public void update(Account account) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(account);
        tx1.commit();
        session.close();
    }

    public void delete(Account account) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(account);
        tx1.commit();
        session.close();
    }

    public List<Account> findAll() {
        List<Account> accounts =
                (List<Account>) HibernateSessionFactoryUtil
                        .getSessionFactory().openSession().createQuery("From Account ").list();
        return accounts;
    }
}
