package com.azgurski.util;

import com.azgurski.controller.requests.BillingDataCreateRequest;
import com.azgurski.repository.impl.BillingDataRepositoryImpl;
import com.azgurski.repository.impl.RestaurantRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class BillingDataCreateRequestValidator implements Validator {

    private final BillingDataRepositoryImpl billingDataRepository;
    private final RestaurantRepositoryImpl restaurantRepository;

    @Autowired
    public BillingDataCreateRequestValidator(BillingDataRepositoryImpl billingDataRepository, RestaurantRepositoryImpl restaurantRepository) {
        this.billingDataRepository = billingDataRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return BillingDataCreateRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BillingDataCreateRequest request = (BillingDataCreateRequest) target;

//        Pattern pattern = Pattern.compile("^[0-9]*$");
//        Matcher matcher = pattern.matcher(request.getRestaurant_id());

//        if (!matcher.matches()) {
//            errors.rejectValue("restaurant_id", "", "Error! Restaurant id must be an Integer.");
//        } else
            if (request.getRestaurant_id() == null) {
            errors.rejectValue("restaurant_id", "", "Error! Restaurant id should not be empty.");
        } else if (restaurantRepository.findOne(request.getRestaurant_id()) == null) {
            errors.rejectValue("restaurant_id", "", "Error! Restaurant id should be present in database.");
        }
    }
}
