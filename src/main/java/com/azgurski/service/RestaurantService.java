package com.azgurski.service;

import com.azgurski.domain.Restaurant;

import java.util.List;

public interface RestaurantService {

    List<Restaurant> findAll();

    List<String> getRestaurantEmails();
}