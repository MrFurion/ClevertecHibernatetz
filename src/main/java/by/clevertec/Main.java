package by.clevertec;

import by.clevertec.models.Car;
import by.clevertec.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class Main {
    public static void main(String[] args) {

        testSecondLevelCache();
    }
    public static void testSecondLevelCache() {

        try (Session session1 = HibernateUtil.getSession()) {
            session1.beginTransaction();
            Car car1 = session1.get(Car.class, 2L);
            System.out.println(car1.getModel());
            session1.getTransaction().commit();
        }

        try (Session session2 = HibernateUtil.getSession()) {
            session2.beginTransaction();
            Car car2 = session2.get(Car.class, 2L);
            System.out.println(car2.getModel());
            session2.getTransaction().commit();
        }
    }

    public static void dropAllTables() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            String sql = "DROP SCHEMA public CASCADE; CREATE SCHEMA public;";
            Query query = session.createNativeQuery(sql);
            query.executeUpdate();
            transaction.commit();
            System.out.println("All tables dropped successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        HibernateUtil.shutdown();
    }
}