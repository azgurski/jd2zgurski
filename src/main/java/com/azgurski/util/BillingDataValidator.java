package com.azgurski.util;

import com.azgurski.domain.BillingData;
import com.azgurski.repository.impl.BillingDataRepositoryImpl;
import com.azgurski.repository.impl.RestaurantRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BillingDataValidator implements Validator {

    private final BillingDataRepositoryImpl billingDataRepository;

    private final RestaurantRepositoryImpl restaurantRepository;

    @Autowired
    public BillingDataValidator(BillingDataRepositoryImpl billingDataRepository, RestaurantRepositoryImpl restaurantRepository) {
        this.billingDataRepository = billingDataRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return BillingData.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BillingData billingData = (BillingData) target;

//        ValidationUtils.rejectIfEmpty(errors,"restaurant_id", "", "Restaurant id should not be empty.");

//        if (String.valueOf(billingData.getRestaurant_id()).matches("^\\d")) {
//            errors.rejectValue("restaurant_id", "", "Restaurant id should be a number type.");
//        }
        if (billingData.getRestaurant_id() == null) {
            errors.rejectValue("restaurant_id", "", "Restaurant id should not be empty.");
        } else if (restaurantRepository.findOne(billingData.getRestaurant_id()) == null) {
            errors.rejectValue("restaurant_id", "", "Restaurant id should be present in database.");
        }

        //TODO
        //валидация поля на BackEnd e на нетекстовый формат (exception convert String to Long)
        //текущее решение: new.html линия 21 : input type="number"

    }
}
