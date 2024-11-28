package by.clevertec.services.impl;

import by.clevertec.models.CarShowroom;
import by.clevertec.services.CarShowroomsServices;
import by.clevertec.factory.CarShowroomFactory;
import by.clevertec.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CarShowroomServiceImpl implements CarShowroomsServices {
    @Override
    public void addCarShowroom(CarShowroom carShowroom) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            carShowroom = CarShowroomFactory.getCarShowroom();
            session.save(carShowroom);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeCarShowroom(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            session.delete(session.get(CarShowroom.class, id));
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateCarShowroom(CarShowroom carShowroomUpdate, Long id) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            CarShowroom carShowroom = session.get(CarShowroom.class, id);
            carShowroom.setName(carShowroomUpdate.getName());
            carShowroom.setAddress(carShowroomUpdate.getAddress());
            session.update(carShowroom);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
