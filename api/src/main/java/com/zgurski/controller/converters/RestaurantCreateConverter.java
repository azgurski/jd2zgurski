package com.zgurski.controller.converters;

import com.zgurski.controller.requests.HibernateRestaurantCreateRequest;
import com.zgurski.domain.AuthenticationInfo;
import com.zgurski.domain.HibernateRestaurant;
import com.zgurski.util.RestaurantFieldsGenerator;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class RestaurantCreateConverter extends
        RestaurantBaseConverter<HibernateRestaurantCreateRequest, HibernateRestaurant> {

//    private final PasswordEncoder encoder;

    private final RestaurantFieldsGenerator generator;

    @Override
    public HibernateRestaurant convert(HibernateRestaurantCreateRequest request) {
        HibernateRestaurant restaurant = new HibernateRestaurant();

        String generatedEmailUserAuth = generator.generateEmail();
        String generatedPasswordUserAuth = generator.generatePassword();

        AuthenticationInfo info = new AuthenticationInfo(generatedEmailUserAuth, generatedPasswordUserAuth);

        restaurant.setAuthenticationInfo(info);

        /* System fields filling */
        restaurant.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        restaurant.setChanged(Timestamp.valueOf(LocalDateTime.now()));

        return doConvert(restaurant, request);
    }
}
