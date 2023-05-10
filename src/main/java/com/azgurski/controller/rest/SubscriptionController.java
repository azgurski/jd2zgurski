package com.azgurski.controller.rest;

import com.azgurski.controller.exceptions.BillingDataNotFoundException;
import com.azgurski.controller.requests.SubscriptionUpdateRequest;
import com.azgurski.domain.Subscription;
import com.azgurski.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/rest/hibernate/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;


    @GetMapping("/{id}")
    public ResponseEntity<Object> findOneSubscriptionById(@PathVariable("id") String id) {
        return new ResponseEntity<>(subscriptionService.findOne(Long.parseLong(id)), HttpStatus.OK);

//        try {
//            return new ResponseEntity<>(billingDataService.findOne(Long.parseLong(id)), HttpStatus.OK);
//        } catch (IllegalArgumentException ex) {
//            throw new BillingDataNotFoundException("Error! The id=[" + id + "] you are looking for doesn't match an integer.");
//        } catch (EmptyResultDataAccessException ex) {
//            throw new BillingDataNotFoundException("Error! Billing_data with id=[" + id + "] doesn't exist.");
//        }
    }

    @GetMapping("/search/unpaid")
    public ResponseEntity<Object> findUnpaidSubscriptions() {
        List<Subscription> unpaidSubscriptions = subscriptionService.searchUnpaidSubscriptions();
        return new ResponseEntity<>(unpaidSubscriptions, HttpStatus.OK);

//        try {
//            return new ResponseEntity<>(billingDataService.findOne(Long.parseLong(id)), HttpStatus.OK);
//        } catch (IllegalArgumentException ex) {
//            throw new BillingDataNotFoundException("Error! The id=[" + id + "] you are looking for doesn't match an integer.");
//        } catch (EmptyResultDataAccessException ex) {
//            throw new BillingDataNotFoundException("Error! Billing_data with id=[" + id + "] doesn't exist.");
//        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSubscription(@PathVariable("id") String id,
                                                     Principal principal,
                                                     @RequestBody SubscriptionUpdateRequest request) {

        //path by subscription_id -> restaurant_id don't change,
        //path by restaurant_id -> реализовать hql по restaurant_id

        //        //Spring Converter: request -> entity
        //        //Subscription subscription = converterService.convert(request, Subscription.class);

        Subscription one = subscriptionService.findOne(Long.parseLong(id));

        if (request.getRestaurant_id() != null) {
            one.setRestaurant_id(request.getRestaurant_id());
        }

        if (request.getIs_paid() != null) {
            one.setIs_paid(request.getIs_paid());
        }

        if (request.getDays_quantity() != null) {
            one.setDays_quantity(request.getDays_quantity());
        }

        if (request.getNext_payment_date() != null) {
            one.setNext_payment_date(request.getNext_payment_date());
        }

        if (request.getRole_id() != null) {
            one.setRole_id(request.getRole_id());
        }

        one = subscriptionService.update(one);

        return new ResponseEntity<>(one, HttpStatus.CREATED);
    }
}
