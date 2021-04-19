package ru.geekbrains.lesson5.dao;


import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.geekbrains.lesson5.models.Product;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@AllArgsConstructor
public class ProductDAO {
    private SessionFactory factory;


    public Product findById(Long id){
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
                Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }
    }

    public List<Product> findAll(){

        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
                // В Hibernate 5 .createCriteria объявлен Deprecated.
                // Нашел такой выход чтобы не использовать sql
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
                criteria.from(Product.class);
                List<Product> products = session.createQuery(criteria).getResultList();
            session.getTransaction().commit();
            return products;
        }
    }

    public void deleteById(Long id){
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.delete(product);
            session.getTransaction().commit();
        }
    }

    public void saveOrUpdate(Product product){
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        }
    }
}
