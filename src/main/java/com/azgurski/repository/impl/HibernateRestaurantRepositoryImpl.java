package com.azgurski.repository.impl;

import com.azgurski.domain.HibernateRestaurant;
import com.azgurski.domain.Subscription;
import com.azgurski.repository.HibernateRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HibernateRestaurantRepositoryImpl implements HibernateRestaurantRepository {
    private final SessionFactory sessionFactory;

    private final EntityManagerFactory entityManagerFactory;

    @Override
    public List<HibernateRestaurant> searchRestaurant(String query, int postcode) {
        return null;
    }

    @Override
    public Optional<HibernateRestaurant> findByEmail(String email) {
        return Optional.empty();
    }


    //CRUD

    @Override
    public List<HibernateRestaurant> findAll() {
    final String findAllHQL = "select r from HibernateRestaurant r";

    //Entity Manager
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    return entityManager.createQuery(findAllHQL, HibernateRestaurant.class).getResultList();

    //Session Factory
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.getTransaction();
//            transaction.begin();
//
//            List<HibernateRestaurant> restaurants = session.createQuery(
//                    "select r from HibernateRestaurant r").getResultList();
//            session.flush();
//
//            transaction.commit();
//
//            return restaurants;
//        }



    }


    @Override
    public HibernateRestaurant findOne(Long id) {
        final String findByIdHQL = "select r from HibernateRestaurant r where r.id = " + id;

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery(findByIdHQL, HibernateRestaurant.class).getSingleResult();
    }



    @Override
    public HibernateRestaurant create(HibernateRestaurant object) {
        return update(object);
    }

    @Override
    public HibernateRestaurant update(HibernateRestaurant object) {
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.getTransaction();
            transaction.begin();

            session.saveOrUpdate(object);
            session.flush();

            transaction.commit();

            return object;
        }
    }

    @Override
    public Long delete(Long id) {
        return null;
    }
}
