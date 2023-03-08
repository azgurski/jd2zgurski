package com.azgurski.repository;

import com.azgurski.domain.Restaurant;

import java.util.List;

public interface RestaurantRepository extends CRUDRepository <Long, Restaurant> {

    List<Restaurant> findAll();
    List<String> getRestaurantEmails();
}
