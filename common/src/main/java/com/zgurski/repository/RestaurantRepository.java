package com.zgurski.repository;

import com.zgurski.domain.BillingData;
import com.zgurski.domain.Restaurant;

import java.util.List;

public interface RestaurantRepository extends CRUDRepository <Long, Restaurant> {

    List<Restaurant> findAll();
    List<String> getRestaurantEmails();

    List<BillingData> findAllBillingData(Long restaurant_id);

    boolean support(String param);
}
