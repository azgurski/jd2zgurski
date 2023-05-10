package com.azgurski.repository.impl;

import com.azgurski.domain.Subscription;
import com.azgurski.repository.SubscriptionRepository;
import com.azgurski.rowmapper.SubscriptionRowMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SubscriptionRepositoryImpl implements SubscriptionRepository {

    private final SessionFactory sessionFactory;

    private final EntityManagerFactory entityManagerFactory;

    @Override
    public Subscription create(Subscription subscription) {
        return null;
    }

    @Override
    public Subscription findOne(Long subscription_id) {
        final String findByHQL = "select s from Subscription s where s.id = " + subscription_id;

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery(findByHQL, Subscription.class).getSingleResult();
    }

    @Override
    public Subscription update(Subscription subscription) {
    return null;
    }

    @Override
    public List<Subscription> searchUnpaidSubscriptions() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();

            List<Subscription> subscription = session.createQuery(
                    "select s from Subscription s where is_paid = false ").getResultList();
            session.flush();

            transaction.commit();

            return subscription;
        }
    }

    @Override
    public Long delete(Long subscription_id) {
        return null;
    }

    @Override
    public List<Subscription> findAll() {
        return null;
    }
}