package by.clevertec.services.impl;

import by.clevertec.factoryes.ClientFactory;
import by.clevertec.models.Car;
import by.clevertec.models.Client;
import by.clevertec.services.ClientServices;
import by.clevertec.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClientServiceImpl implements ClientServices {


    @Override
    public void createClient() {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            Client client = ClientFactory.createClient();
            session.save(client);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateClient(Client clientUpdate, Long id) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            Client client = session.get(Client.class, id);
            client.setName(clientUpdate.getName());
            client.setContact(clientUpdate.getContact());
            session.update(client);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteClient(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            Client client = session.get(Client.class, id);
            session.delete(client);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buyCar(Long clientId, Long carId) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            Client client = session.get(Client.class, clientId);
            Car car = session.get(Car.class, carId);
            car.setShowroom(null);
            List<Car> cars = List.of(car);
            List<Client> clients = List.of(client);
            client.setCars(cars);
            car.setClientas(clients);
            session.update(client);
            session.update(car);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
