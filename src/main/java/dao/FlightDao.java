package dao;

import entities.Flight;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class FlightDao {
    public Flight findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Flight.class, id);
    }

    public void save(Flight flight) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(flight);
        tx1.commit();
        session.close();
    }

    public void update(Flight flight) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(flight);
        tx1.commit();
        session.close();
    }

    public void delete(Flight flight) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(flight);
        tx1.commit();
        session.close();
    }

    public List<Flight> findAll() {
        List<Flight> flights =
                (List<Flight>) HibernateSessionFactoryUtil
                        .getSessionFactory().openSession().createQuery("From Flight ").list();
        return flights;
    }


    public List<Flight> findFlightsForDifficultWay(LocalDate departure, int numberOfSeats) {
        List<Flight> flights =
                (List<Flight>) HibernateSessionFactoryUtil
                        .getSessionFactory().openSession()
                        .createQuery("FROM Flight WHERE numberOfFreeSeats >= ?1 and (date(departure) = ?2 OR date(departure) = ?3)")
                        .setParameter(1, numberOfSeats)
                        .setParameter(2, Date.valueOf(departure))
                        .setParameter(3, Date.valueOf(departure.plusDays(1)))
                        .list();
        return flights;
    }

    public List<Flight> findRightFlights(LocalDate departure, int numberOfSeats, String startAirport, String finalAirport) {
        List<Flight> flights =
                (List<Flight>) HibernateSessionFactoryUtil
                        .getSessionFactory().openSession()
                        .createQuery("FROM Flight WHERE date(departure) = ?1 AND numberOfFreeSeats >= ?2 AND startAirport = ?3 AND finalAirport = ?4")
                        .setParameter(1, Date.valueOf(departure))
                        .setParameter(2, numberOfSeats)
                        .setParameter(3, startAirport)
                        .setParameter(4, finalAirport)
                        .list();
        return flights;
    }

    public void buyTicket(Integer id, int numberOfSeats) {
        Flight flight = findById(id);
        flight.setNumberOfFreeSeats(flight.getNumberOfFreeSeats() - numberOfSeats);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(flight);
        tx1.commit();
        session.close();
    }

    public void returnTicket(Integer id, int numberOfSeats) {
        Flight flight = findById(id);
        flight.setNumberOfFreeSeats(flight.getNumberOfFreeSeats() + numberOfSeats);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(flight);
        tx1.commit();
        session.close();
    }
}
