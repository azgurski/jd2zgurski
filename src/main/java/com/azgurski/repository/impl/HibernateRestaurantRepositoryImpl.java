package com.azgurski.repository.impl;

import com.azgurski.domain.HibernateRestaurant;
import com.azgurski.domain.HibernateRestaurant_;
import com.azgurski.domain.HibernateSlot_;
import com.azgurski.domain.Subscription;
import com.azgurski.repository.HibernateRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Parameter;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.Date;
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
        try (Session session = sessionFactory.openSession()) {
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

    //Criteria API

    @Override
    public List<HibernateRestaurant> searchRestaurant(String searchQuery, String searchCountry) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //Get Builder for Criteria object
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<HibernateRestaurant> query = cb.createQuery(HibernateRestaurant.class); // объект для постройки запроса here = select, where, orderBy, having
        Root<HibernateRestaurant> root = query.from(HibernateRestaurant.class); // что будет являться корневым условием запроса в базу, что будет селектом, откуда идет основной запрос, here params select * from h_restaurant -> mapping

        // Пример JOIN
//        root.join(HibernateRestaurant_.slots, JoinType.LEFT);

        //Type of future params in prepared statement
        //ориентируется, какое поле мы ищем и какой тип параметра будет подставляться
        //если ParameterExpression, то обращение как к полю, как ParameterStatement
        ParameterExpression<String> nameParam = cb.parameter(HibernateRestaurant_.name.getJavaType());
//        ParameterExpression<Long> userSearchParam = cb.parameter(HibernateRestaurant_.restaurantId.getJavaType()); // для поиска по id, типа long
        ParameterExpression<String> countryParam = cb.parameter(HibernateRestaurant_.country.getJavaType());

        //Provide access to fields in class that mapped to columns
        //если Expression, то в query надо перечислить явно значение value
//        Expression<Long> id = root.get(HibernateRestaurant_.restaurantId);
        Expression<String> nameExp = root.get(HibernateRestaurant_.name);
        Expression<String> countryExp = root.get(HibernateRestaurant_.country);

        //SQL Query customizing
        //нельзя вызвать оконные функции Postgres
        query
                .select(root)
                .distinct(true) // если без дубликатов результат
                .where(
                        cb.or(
                                cb.like(nameExp, nameParam) //userName like %param%
                        ),
                        cb.or(
                                cb.like(countryExp, countryParam)// >0
                        )
//                        cb.and(
//                                cb.gt(id, userSearchParam), // gt = >0
//                                cb.not(id.in(1L, 12L)) //id not 1, id not 12
//                        )

//                        ,
//                        cb.between(
//                                root.get(HibernateSlot_.dayCalendar), // между датами
//                                new Timestamp(new Date().getTime()),
//                                new Timestamp(new Date().getTime())
//                        )
                )
                .orderBy(cb.asc(root.get(HibernateRestaurant_.restaurantId)));

        TypedQuery<HibernateRestaurant> resultQuery = entityManager.createQuery(query); // prepared statement on HQL
        resultQuery.setParameter(nameParam, StringUtils.join("%", searchQuery, "%"));
        resultQuery.setParameter(countryParam, searchCountry);

        return resultQuery.getResultList();
    }
}
