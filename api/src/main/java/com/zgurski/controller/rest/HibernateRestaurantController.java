package com.zgurski.controller.rest;

import com.zgurski.controller.exceptions.IllegalRequestException;
import com.zgurski.controller.requests.HibernateRestaurantCreateRequest;
import com.zgurski.controller.requests.HibernateRestaurantUpdateRequest;
import com.zgurski.controller.requests.RestaurantSearchCriteria;
import com.zgurski.domain.AuthenticationInfo;
import com.zgurski.domain.Capacity;
import com.zgurski.domain.HibernateRestaurant;
import com.zgurski.service.HibernateRestaurantService;
import com.zgurski.util.RestaurantFieldsGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/rest/hibernate/restaurants")
@RequiredArgsConstructor
public class HibernateRestaurantController {

    private final HibernateRestaurantService restaurantService;

    private final RestaurantFieldsGenerator generator;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneRestaurantById(@PathVariable("id") String id) {
        HibernateRestaurant restaurant = restaurantService.findOne(Long.parseLong(id));
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<Object> getAllRestaurants() {
        List<HibernateRestaurant> restaurants = restaurantService.findAll();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchUserByNameCountry(
            @Valid @ModelAttribute RestaurantSearchCriteria criteria,
            BindingResult bindingResult) {
        System.out.println(bindingResult); // TODO throw new

        List<HibernateRestaurant> searchList = restaurantService.searchRestaurant(criteria.getQuery(), criteria.getCountry());

        return new ResponseEntity<>(Collections.singletonMap("restaurants", searchList), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveRestaurant(
            @Valid @RequestBody HibernateRestaurantCreateRequest request,
            BindingResult bindingResult) {

        //TODO Spring Converter : request -> entity

        if (bindingResult.hasErrors()) {
            throw new IllegalRequestException(bindingResult);
        }

        HibernateRestaurant restaurant = HibernateRestaurant.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .streetHouse(request.getStreet_house())
                .city(request.getCity())
                .postcode(request.getPostcode())
                .country(request.getCountry())
                .longitude(request.getLongitude())
                .latitude(request.getLatitude())
                .imageURL(request.getImage_URL())
                .roleId(Long.parseLong(request.getRole_id()))
                .website(request.getWebsite())
//                .emailUserAuth(generator.generateEmail(request.getPostcode()))
//                .passwordUserAuth(generator.generatePassword())
                .capacity(Capacity.NOT_SELECTED)
                .build();

        String generatedEmail = generator.generateEmail(request.getPostcode());
        String generatedPassword = generator.generatePassword();

        AuthenticationInfo info = new AuthenticationInfo(generatedEmail, generatedPassword);

        restaurant.setAuthenticationInfo(info);

        restaurant = restaurantService.create(restaurant);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    //можно через @PathVariable + id, либо HRUR по id
    @PutMapping
    public ResponseEntity<Object> updateRestaurant(
            @RequestBody HibernateRestaurantUpdateRequest request) {


        //TODO Spring Converter : request -> entity
        //HibernateRestaurant restaurant = converterService.convert(request, HibernateRestaurant.class);

        HibernateRestaurant one = restaurantService.findOne(request.getRestaurant_id());

        one.setRestaurantId(request.getRestaurant_id());
        one.setName(request.getName());
        one.setEmail(request.getEmail());
        one.setPhone(request.getPhone());
        one.setStreetHouse(request.getStreet_house());
        one.setCity(request.getCity());
        one.setPostcode(request.getPostcode());
        one.setCountry(request.getCountry());
        one.setLongitude(request.getLongitude());
        one.setLatitude(request.getLatitude());
        one.setImageURL(request.getImage_URL());
        one.setRoleId(Long.parseLong(request.getRole_id()));
        one.setWebsite(request.getWebsite());
//      one.set.emailUserAuth(generator.generateEmail(request.getPostcode()))
//      one.set.passwordUserAuth(generator.generatePassword())
        one.setCapacity(Capacity.valueOf(request.getCapacity()));

        one = restaurantService.update(one);

        return new ResponseEntity<>(one, HttpStatus.OK);
    }

    //для @PatchMapping будет не (patchRequest), а Map<String,Object>
    // мапа будет по ключу, а ключ будет определяться из возможных полей внутри сущности


}