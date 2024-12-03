package by.clevertec.util;

import by.clevertec.models.Car;
import by.clevertec.models.CarShowroom;
import by.clevertec.models.Category;
import by.clevertec.models.Client;
import by.clevertec.models.Review;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;


@UtilityClass
public class HibernateUtil {
    @Getter
    private final static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Car.class)
                    .addAnnotatedClass(CarShowroom.class)
                    .addAnnotatedClass(Category.class)
                    .addAnnotatedClass(Client.class)
                    .addAnnotatedClass(Review.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static void initializeIndex(Session session) {
        try {
            SearchSession searchSession = Search.session(session);
            MassIndexer indexer = searchSession.massIndexer(Review.class);
            indexer.startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static Session getSession() {
        return sessionFactory.openSession();
    }
    public static void shutdown() {getSessionFactory().close();}

}
