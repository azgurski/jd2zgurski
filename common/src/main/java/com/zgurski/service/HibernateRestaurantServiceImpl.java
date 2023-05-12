package com.zgurski.service;

import com.zgurski.domain.Capacity;
import com.zgurski.domain.HibernateRestaurant;
import com.zgurski.repository.HibernateRestaurantRepository;
import com.zgurski.repository.springdata.RestaurantDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HibernateRestaurantServiceImpl implements HibernateRestaurantService {

    private final HibernateRestaurantRepository repository;

    private final RestaurantDataRepository restaurantDataRepository;

    @Override
    public HibernateRestaurant findOne(Long id) {
        return repository.findOne(id);
    };

    @Override
    public List<HibernateRestaurant> searchRestaurant(String searchQuery, String searchCountry) {
        return repository.searchRestaurant(searchQuery, searchCountry);
    }

    @Override
    public List<HibernateRestaurant> findByCapacity(Capacity capacity) {
        return restaurantDataRepository.findByCapacity(capacity);
    }


    @Override
    public List<HibernateRestaurant> findAll() {
        return repository.findAll();
    }

    @Override
    public HibernateRestaurant create(HibernateRestaurant object) {
        return repository.create(object);
    }

    @Override
    public HibernateRestaurant update(HibernateRestaurant object) {
        return repository.update(object);
    }
}