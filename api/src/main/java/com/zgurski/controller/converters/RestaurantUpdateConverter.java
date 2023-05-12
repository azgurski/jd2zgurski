package com.zgurski.controller.converters;

import com.zgurski.controller.requests.HibernateRestaurantUpdateRequest;
import com.zgurski.domain.HibernateRestaurant;
import com.zgurski.exception.EntityNotFoundException;
import com.zgurski.repository.springdata.RestaurantDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RestaurantUpdateConverter extends RestaurantBaseConverter<HibernateRestaurantUpdateRequest, HibernateRestaurant> {

    private final RestaurantDataRepository repository;

    @Override
    public HibernateRestaurant convert(HibernateRestaurantUpdateRequest request) {
        HibernateRestaurant restaurant = repository.findById(request.getRestaurant_id())
                .orElseThrow(EntityNotFoundException::new);

        /* System fields filling */
        restaurant.setChanged(Timestamp.valueOf(LocalDateTime.now()));

        return doConvert(restaurant, request);
    }
}
