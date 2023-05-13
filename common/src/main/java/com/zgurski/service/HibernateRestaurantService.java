package com.zgurski.service;

import com.zgurski.domain.Capacity;
import com.zgurski.domain.HibernateRestaurant;

import java.util.List;

public interface HibernateRestaurantService {
    HibernateRestaurant findOne(Long id);

    List<HibernateRestaurant> searchRestaurantByCountry(String searchQuery, String searchCountry);

    List<HibernateRestaurant> findAll();

    HibernateRestaurant create(HibernateRestaurant object);

    HibernateRestaurant update(HibernateRestaurant object);

    List<HibernateRestaurant> findByCapacity(Capacity capacity);
}
