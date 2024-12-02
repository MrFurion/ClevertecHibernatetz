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

    public static Session getSession() {
        return sessionFactory.openSession();
    }
    public static void shutdown() {getSessionFactory().close();}

}
