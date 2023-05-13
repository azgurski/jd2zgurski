package com.zgurski.controller.converters;

import com.zgurski.controller.requests.HibernateRestaurantCreateRequest;
import com.zgurski.domain.Capacity;
import com.zgurski.domain.HibernateRestaurant;
import org.springframework.core.convert.converter.Converter;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class RestaurantBaseConverter<S, T> implements Converter<S, T> {

    public HibernateRestaurant doConvert(HibernateRestaurant restaurantForUpdate,
                                         HibernateRestaurantCreateRequest request) {

        restaurantForUpdate.setName(request.getName());
        restaurantForUpdate.setEmail(request.getEmail());
        restaurantForUpdate.setPhone(request.getPhone());
        restaurantForUpdate.setStreetHouse(request.getStreet_house());
        restaurantForUpdate.setCity(request.getCity());
        restaurantForUpdate.setPostcode(request.getPostcode());
        restaurantForUpdate.setCountry(request.getCountry());
        restaurantForUpdate.setLongitude(request.getLongitude());
        restaurantForUpdate.setLatitude(request.getLatitude());
        restaurantForUpdate.setImageURL(request.getImage_URL());
        restaurantForUpdate.setRoleId(Long.parseLong(request.getRole_id()));
        restaurantForUpdate.setCapacity(request.getCapacity());
//        restaurantForUpdate.setCapacity(Capacity.valueOf(request.getCapacity().toUpperCase()));
        restaurantForUpdate.setWebsite(request.getWebsite());

        /* System fields filling */

//        restaurantForUpdate.setCreated(Timestamp.valueOf(LocalDateTime.now()));
//        restaurantForUpdate.setChanged(Timestamp.valueOf(LocalDateTime.now()));
        restaurantForUpdate.setIsDeleted(false);

        return restaurantForUpdate;
    }

}
