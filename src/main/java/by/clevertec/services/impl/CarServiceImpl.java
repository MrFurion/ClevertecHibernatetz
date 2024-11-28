package by.clevertec.services.impl;

import by.clevertec.models.Car;
import by.clevertec.services.CarServices;
import by.clevertec.factory.CarFactory;
import by.clevertec.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CarServiceImpl implements CarServices {
    @Override
    public void foundCarById(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            Car car = session.get(Car.class, id);
            System.out.println(car);
        }
    }

    @Override
    public void foundAllCars() {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            List<Car> cars = session.createQuery("from Car").list();
            for (Car car : cars) {
                System.out.println(car);
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCar(Car car) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            car = CarFactory.getCar();
            session.save(car);
            tx.commit();
            System.out.println("Individual Client saved with ID: " + car.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCar(Car carUpdate, long id) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();

            Car car = session.get(Car.class, id);

            car.setModel(carUpdate.getModel());
            car.setBrandCar(carUpdate.getBrandCar());
            car.setYearOfProduction(carUpdate.getYearOfProduction());
            car.setPrice(carUpdate.getPrice());

            session.update(car);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCarById(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            Car car = session.find(Car.class, id);
            session.delete(car);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
