package com.zgurski.repository.impl;

import com.zgurski.domain.HibernateSlot;
import com.zgurski.repository.HibernateSlotRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class HibernateSlotRepositoryImpl implements HibernateSlotRepository {

    private final SessionFactory sessionFactory;

    private final EntityManagerFactory entityManagerFactory;

    @Override
    public List<HibernateSlot> findAll() {
        final String findAllHQL = "select s from HibernateSlot s";

        //Entity Manager
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery(findAllHQL, HibernateSlot.class).getResultList();
    }

    @Override
    public HibernateSlot findOne(Long id) {
        return null;
    }

    @Override
    public HibernateSlot create(HibernateSlot object) {
        return null;
    }

    @Override
    public HibernateSlot update(HibernateSlot object) {
        return null;
    }

    @Override
    public Long delete(Long id) {
        return null;
    }
}
