package com.azgurski.repository;

import com.azgurski.domain.BillingData;
import com.azgurski.domain.Restaurant;

import java.util.List;
import java.util.Set;

public interface RestaurantRepository extends CRUDRepository <Long, Restaurant> {

    List<Restaurant> findAll();
    List<String> getRestaurantEmails();

    List<BillingData> findAllBillingData(Long restaurant_id);

    boolean support(String param);
}
