package com.zgurski.controller.rest;

import com.zgurski.domain.BillingData;
import com.zgurski.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/hibernate/all_restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/billing_data/{id}")
    public ResponseEntity<Object> getBillingDataByRestaurantId(@PathVariable("id") String id) {
        List<BillingData> billingDataList = restaurantService.findAllBillingData(Long.parseLong(id));
        return new ResponseEntity<>(billingDataList, HttpStatus.OK);
    }


}
