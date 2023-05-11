package com.zgurski.service;

import com.zgurski.domain.BillingData;
import com.zgurski.domain.Restaurant;

import java.util.List;

public interface RestaurantService {

    List<Restaurant> findAll();

    List<String> getRestaurantEmails();

    List<BillingData> findAllBillingData(Long restaurant_id);
}