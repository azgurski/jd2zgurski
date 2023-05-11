package com.zgurski.repository;

import com.zgurski.domain.HibernateRestaurant;

import java.util.List;
import java.util.Optional;

public interface HibernateRestaurantRepository extends CRUDRepository<Long, HibernateRestaurant> {
    List<HibernateRestaurant> searchRestaurant(String query, int postcode);

    List<HibernateRestaurant> searchRestaurant(String searchQuery, String searchCountry);

    List<HibernateRestaurant> findAll();

    Optional<HibernateRestaurant> findByEmail(String email);

    HibernateRestaurant create(HibernateRestaurant object);

    HibernateRestaurant update(HibernateRestaurant object);

    HibernateRestaurant findOne(Long id);
}
