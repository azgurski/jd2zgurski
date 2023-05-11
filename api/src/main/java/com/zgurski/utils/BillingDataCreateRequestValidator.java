package com.zgurski.utils;

import com.zgurski.controller.requests.BillingDataCreateRequest;
import com.zgurski.repository.impl.BillingDataRepositoryImpl;
import com.zgurski.repository.impl.RestaurantRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BillingDataCreateRequestValidator implements Validator {


    private final BillingDataRepositoryImpl billingDataRepository;
    private final RestaurantRepositoryImpl restaurantRepository;

    @Autowired
    public BillingDataCreateRequestValidator(BillingDataRepositoryImpl billingDataRepository, RestaurantRepositoryImpl restaurantRepository) {
        this.billingDataRepository = billingDataRepository;
        this.restaurantRepository = restaurantRepository;
    }

//    @Override
//    public boolean supports(Class<?> clazz) {
//        return false;
//    }
//
//    @Override
//    public void validate(Object target, Errors errors) {
//
//    }

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
