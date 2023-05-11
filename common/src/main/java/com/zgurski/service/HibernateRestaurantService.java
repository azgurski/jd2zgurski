package com.zgurski.service;

import com.zgurski.domain.HibernateRestaurant;

import java.util.List;

public interface HibernateRestaurantService {
    HibernateRestaurant findOne(Long id);

    List<HibernateRestaurant> searchRestaurant(String searchQuery, String searchCountry);

    List<HibernateRestaurant> findAll();

    HibernateRestaurant create(HibernateRestaurant object);

    HibernateRestaurant update(HibernateRestaurant object);
}
