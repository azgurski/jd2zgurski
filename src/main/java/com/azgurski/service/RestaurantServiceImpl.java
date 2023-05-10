package com.azgurski.service;

import com.azgurski.domain.BillingData;
import com.azgurski.domain.Restaurant;
import com.azgurski.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<BillingData> findAllBillingData(Long restaurant_id) {
        return restaurantRepository.findAllBillingData(restaurant_id);
    }

    @Override
    public List<String> getRestaurantEmails() {
        return restaurantRepository.getRestaurantEmails();
    }
}