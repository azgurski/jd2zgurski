package com.azgurski.service;

import com.azgurski.domain.BillingData;
import com.azgurski.domain.Restaurant;

import java.util.List;
import java.util.Set;

public interface RestaurantService {

    List<Restaurant> findAll();

    List<String> getRestaurantEmails();

    List<BillingData> findAllBillingData(Long restaurant_id);
}