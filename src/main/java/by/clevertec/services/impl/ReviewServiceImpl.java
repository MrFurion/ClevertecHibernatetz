package by.clevertec.services.impl;

import by.clevertec.factoryes.ReviewFactory;
import by.clevertec.models.Car;
import by.clevertec.models.Client;
import by.clevertec.models.Review;
import by.clevertec.services.ReviewServices;
import by.clevertec.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;

import java.util.List;

import static by.clevertec.util.HibernateUtil.initializeIndex;

public class ReviewServiceImpl implements ReviewServices {
    @Override
    public void addReview(Long clientId, Long carId) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            Review review = ReviewFactory.createReview();
            Car car = session.get(Car.class, carId);
            Client client = session.get(Client.class, clientId);
            review.setCars(car);
            review.setClient(client);
            session.save(review);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteReview(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            Review review = session.get(Review.class, id);
            session.delete(review);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateReview(Long id) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            Review reviewUpdate = ReviewFactory.createReview();
            Review review = session.get(Review.class, id);
            review.setReviewText(reviewUpdate.getReviewText());
            review.setRank(reviewUpdate.getRank());
            session.update(review);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchReviewsByKeywords(String keywords) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();


            String hql = "FROM Review r WHERE r.reviewText LIKE :keywords";
            Query<Review> query = session.createQuery(hql, Review.class);
            query.setParameter("keywords", "%" + keywords + "%");

            List<Review> reviews = query.getResultList();

            for (Review review : reviews) {
                System.out.println(review.getReviewText());
            }

            session.getTransaction().commit();

        } catch (HibernateException e) {
            e.printStackTrace();

        }
    }

    public void searchReviews(String keywords, Integer minRank, Integer maxRank) {
        try (Session session = HibernateUtil.getSession()) {
            SearchSession searchSession = Search.session(session);
            initializeIndex(session);

            var querys = searchSession.search(Review.class)
                    .where(f -> f.bool(b -> {
                        b.must(f.match()
                                .fields("reviewText")
                                .matching(keywords));
                        if (minRank != null) {
                            b.must(f.range()
                                    .field("rank")
                                    .atLeast(minRank));
                        }
                        if (maxRank != null) {
                            b.must(f.range()
                                    .field("rank")
                                    .atMost(maxRank));
                        }
                    }))
                    .fetchAllHits();
            for (var query : querys) {
                System.out.println(query.getReviewText());
            }
        }
    }
}
