package by.clevertec.services.impl;

import by.clevertec.models.Category;
import by.clevertec.services.CategoryServices;
import by.clevertec.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CategoryServiceImpl implements CategoryServices {
    @Override
    public void addCategory(Category category) {
        try (Session session = HibernateUtil.getSession()){
            Transaction tx = session.beginTransaction();
            session.save(category);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCategory(Long id) {
        try(Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            session.delete(session.get(Category.class, id));
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCategory(Category categoryUpdate, Long id) {
        try (Session session = HibernateUtil.getSession()){
            Transaction tx = session.beginTransaction();
            Category category = session.get(Category.class, id);
            category.setCarCategory(categoryUpdate.getCarCategory());
            session.update(category);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
