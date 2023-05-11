package com.azgurski.service;

import com.azgurski.domain.HibernateRestaurant;
import com.azgurski.repository.HibernateRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HibernateRestaurantServiceImpl implements HibernateRestaurantService {

    private final HibernateRestaurantRepository repository;

    @Override
    public HibernateRestaurant findOne(Long id) {
        return repository.findOne(id);
    };

    @Override
    public List<HibernateRestaurant> searchRestaurant(String searchQuery, String searchCountry) {
        return repository.searchRestaurant(searchQuery, searchCountry);
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