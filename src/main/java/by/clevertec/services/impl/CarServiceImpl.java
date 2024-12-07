package by.clevertec.services.impl;

import by.clevertec.enums.car.CarBrand;
import by.clevertec.enums.category.CarCategory;
import by.clevertec.factoryes.CarFactory;
import by.clevertec.models.Car;
import by.clevertec.models.CarShowroom;
import by.clevertec.repositories.CarRepository;
import by.clevertec.services.CarServices;
import by.clevertec.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CarServiceImpl implements CarServices {

    private final CarRepository carRepository;


    @Override
    public void foundCarById(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            session.beginTransaction();
            Car car = session.get(Car.class, id);
            System.out.println(car);
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Car> foundCarAll() {
        return carRepository.findAll();
    }

    @Override
    public void foundCarByBrandYearOfProductionCategoryPrice(CarBrand brandCar, LocalDate yearOfProduction, CarCategory category, String price) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            String[] priceBounds = (price != null && price.contains("-")) ? price.split("-") : null;
            String minPrice = (priceBounds != null && priceBounds.length > 0) ? priceBounds[0] : null;
            String maxPrice = (priceBounds != null && priceBounds.length > 1) ? priceBounds[1] : null;


            String hql = "FROM Car c WHERE c.brandCar = :brandCar " +
                    "AND c.yearOfProduction = :yearOfProduction " +
                    "AND c.categoryes.carCategory = :category " +
                    "AND CAST(c.price AS DOUBLE) BETWEEN :minPrice AND :maxPrice";

            Query<Car> carQuery = session.createQuery(hql, Car.class);
            carQuery.setParameter("brandCar", brandCar);
            carQuery.setParameter("yearOfProduction", yearOfProduction);
            carQuery.setParameter("category", category);
            carQuery.setParameter("minPrice", Double.parseDouble(minPrice));
            carQuery.setParameter("maxPrice", Double.parseDouble(maxPrice));

            List<Car> cars = carQuery.getResultList();

            cars.forEach(car -> System.out.println(car.getBrandCar() +
                    " " + car.getYearOfProduction() +
                    " " + car.getCategoryes().getCarCategory() +
                    " " + car.getPrice()));
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public void findCarsSortedByPriceAsc() {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
            Root<Car> root = criteriaQuery.from(Car.class);

            List<Car> cars = session.createQuery(criteriaQuery).getResultList();

            List<Car> sortedCars = cars.stream()
                    .filter(car -> car.getPrice() != null && car.getPrice().matches("[0-9.]+"))
                    .sorted(Comparator.comparingDouble(car -> Double.parseDouble(car.getPrice())))
                    .collect(Collectors.toList());

            sortedCars.forEach(car -> System.out.println("Car: " + car.getBrandCar() + " " + car.getModel() + ", Price: " + car.getPrice()));
            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public void findCarsSortedByPriceDesc() {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
            Root<Car> root = criteriaQuery.from(Car.class);

            List<Car> cars = session.createQuery(criteriaQuery).getResultList();

            List<Car> sortedCars = cars.stream()
                    .filter(car -> car.getPrice() != null && car.getPrice().matches("[0-9.]+"))
                    .sorted((car1, car2) -> Double.compare(Double.parseDouble(car2.getPrice()), Double.parseDouble(car1.getPrice())))
                    .collect(Collectors.toList());

            sortedCars.forEach(car -> System.out.println("Car: " + car.getBrandCar() + " " + car.getModel() + ", Price: " + car.getPrice()));

            tx.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void foundAllCars(int pageNumber, int pageSize) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();

            String hql = "SELECT DISTINCT c FROM Car c " +
                    "JOIN FETCH c.categoryes " +
                    "JOIN FETCH c.showroom";
            Query<Car> query = session.createQuery(hql, Car.class);
            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);

            List<Car> cars = query.list();

            cars.forEach(car ->
                    System.out.println(car.getBrandCar() + " - " + car.getCategoryes().getCarCategory())
            );

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCar() {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            Car car = CarFactory.getCar();
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

    @Override
    public void assignCarToShowroom(Car car, CarShowroom showroom) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            Car carAssign = session.get(Car.class, car.getId());
            carAssign.setShowroom(showroom);
            session.update(carAssign);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
