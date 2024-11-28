package by.clevertec;

import by.clevertec.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class Main {
    public static void main(String[] args) {

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