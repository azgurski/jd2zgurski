package com.azgurski.service;

import com.azgurski.domain.BillingData;
import com.azgurski.domain.HibernateRestaurant;
import com.azgurski.domain.Restaurant;

import java.util.List;

public interface HibernateRestaurantService {
    HibernateRestaurant findOne(Long id);

    List<HibernateRestaurant> findAll();

    HibernateRestaurant create(HibernateRestaurant object);

    HibernateRestaurant update(HibernateRestaurant object);
}
